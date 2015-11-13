package com.minlia.cloud.infrastructure.framework.usercenter.services.impl;

import com.minlia.cloud.infrastructure.framework.usercenter.exception.BootException;
import com.minlia.cloud.infrastructure.framework.usercenter.domain.CredentialsUserProfile;
import com.minlia.cloud.infrastructure.framework.usercenter.domain.UserProfile;
import com.minlia.cloud.infrastructure.framework.usercenter.entities.RoleEntity;
import com.minlia.cloud.infrastructure.framework.usercenter.entities.UserEntity;
import com.minlia.cloud.infrastructure.framework.usercenter.repositories.RoleRepository;
import com.minlia.cloud.infrastructure.framework.usercenter.repositories.UserRepository;
import com.minlia.cloud.infrastructure.framework.usercenter.services.LogicException;
import com.minlia.cloud.infrastructure.framework.usercenter.services.ProfileService;
import com.minlia.cloud.infrastructure.framework.usercenter.services.ServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;
import java.util.List;


@Service("profileService")
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientDetailsService clientDetailsService;



    @Override
    public UserProfile getOwnProfile() throws LogicException {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        if (currentUser == null) {
            throw new LogicException(HttpStatus.UNAUTHORIZED, "No authentication information found. Are you logged in?");
        }

        UserEntity userEntity = userRepository.findByUsername(currentUser.getName());
        if (userEntity == null) {
            // This would indicate bad coding somewhere
            throw new LogicException(HttpStatus.INTERNAL_SERVER_ERROR, "Authenticated user " + currentUser.getName() +
                    " not found in database");
        }

        return userEntity.toUserProfile();
    }


    @Override
    public void register(CredentialsUserProfile profile, HttpServletRequest request) throws LogicException {

        if (profile.getUsername() == null || profile.getUsername().isEmpty()) {
            throw new LogicException(HttpStatus.BAD_REQUEST, "Missing username");
        }

        if (profile.getEmail() == null || profile.getEmail().isEmpty()) {
            throw new LogicException(HttpStatus.BAD_REQUEST, "Missing email");
        }

        if (!profile.getEmail().matches(".+@.+\\..+")) {
            throw new LogicException(HttpStatus.BAD_REQUEST, "Invalid email format: " + profile.getEmail());
        }

        if (!isValidClient(request)) {
            throw new LogicException(HttpStatus.UNAUTHORIZED, "Invalid client credentials");
        }

        UserEntity userEntity = userRepository.findByUsername(profile.getUsername());
        if (userEntity != null) {
            throw new LogicException(HttpStatus.BAD_REQUEST, "User " + profile.getUsername() +
                    " already exists");
        }

        userEntity = userRepository.findByEmail(profile.getEmail());
        if (userEntity != null) {
            throw new LogicException(HttpStatus.BAD_REQUEST, "Email " + profile.getEmail() +
                    " already exists with another user");
        }

        UserEntity entity = UserEntity.fromUserProfile(profile);
        List<RoleEntity> roles = roleRepository.findInByRoleName(ServiceConstants.DEFAULT_USER_ROLE_NAMES);
        entity.setRoles(roles);

        if (profile.getPassword() != null) {
            entity.setPassword(passwordEncoder.encode(CharBuffer.wrap(profile.getPassword())));
        }

        userRepository.save(entity);
    }

    private boolean isValidClient(HttpServletRequest request) {
        Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(currentAuthentication instanceof AnonymousAuthenticationToken)) {
            if (currentAuthentication.isAuthenticated() || isValidAuthentication(currentAuthentication)) {
                return true;
            }
        }

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Basic ")) {
            return false;
        }

        String[] tokens;
        try {
            tokens = extractAndDecodeHeader(header);
        } catch (UnsupportedEncodingException | BadCredentialsException anEx) {
            return false;
        }

        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(tokens[0], tokens[1]);

        return isValidAuthentication(authRequest);
    }

    private boolean isValidAuthentication(Authentication currentAuthentication) {
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId((String) currentAuthentication.getPrincipal());
        return passwordEncoder.matches(
                CharBuffer.wrap(((String) currentAuthentication.getCredentials()).toCharArray()),
                clientDetails.getClientSecret());
    }

    /**
     * Decodes the header into a username and password.
     *
     * @throws BadCredentialsException if the Basic header is not present or is not valid Base64
     */
    private String[] extractAndDecodeHeader(String header) throws UnsupportedEncodingException {

        byte[] base64Token = header.substring("Basic ".length()).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");

        int delim = token.indexOf(":");

        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[] {token.substring(0, delim), token.substring(delim + 1)};
    }


    @Override
    public void changePassword(char[] newPassword) throws BootException {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        if (currentUser == null) {
            throw new LogicException(HttpStatus.UNAUTHORIZED, "Attempt to change password for a non authenticated user");
        }

        changePassword(currentUser, newPassword);
    }


    @Override
    public void changePassword(char[] oldPassword, char[] newPassword) throws BootException {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        if (currentUser == null) {
            throw new LogicException(HttpStatus.UNAUTHORIZED, "Attempt to change password for a non authenticated user");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(currentUser.getName(),
                    new String(oldPassword)));
        } catch(AuthenticationException exception) {
            throw new LogicException(HttpStatus.UNAUTHORIZED, "Wrong password", exception);
        }

        changePassword(currentUser, newPassword);
    }


    private void changePassword(Authentication currentUser, char[] newPassword) throws BootException {
        UserEntity userEntity = userRepository.findByUsername(currentUser.getName());
        if (userEntity == null) {
            // This would indicate bad coding somewhere
            throw new LogicException(HttpStatus.INTERNAL_SERVER_ERROR, "Authenticated user " + currentUser.getName() +
                    " not found in database");
        }

        LOG.info("Changing password of user " + currentUser.getName());

        userEntity.setPassword(passwordEncoder.encode(CharBuffer.wrap(newPassword)));
        userRepository.save(userEntity);
    }

}
