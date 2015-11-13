package com.minlia.cloud.infrastructure.framework.usercenter.services.impl;

import com.minlia.cloud.infrastructure.framework.usercenter.domain.AuthorizableUserProfile;
import com.minlia.cloud.infrastructure.framework.usercenter.entities.UserEntity;
import com.minlia.cloud.infrastructure.framework.usercenter.repositories.UserRepository;
import com.minlia.cloud.infrastructure.framework.usercenter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void deleteUserById(String id) {
        userRepository.delete(id);
    }

    @Override
    public Page<AuthorizableUserProfile> listAllUsers(Pageable pagingCriteria) {

        List<AuthorizableUserProfile> authorizableUserProfiles = new ArrayList<>();

        Page<UserEntity> userEntities = userRepository.findAll(pagingCriteria);
        for (UserEntity userEntity : userEntities) {
            authorizableUserProfiles.add(userEntity.toAuthorizableUserProfile());
        }

        return new PageImpl<>(authorizableUserProfiles, pagingCriteria, userRepository.count());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User name " + username + " not found.");
        }

        return user;
    }
}
