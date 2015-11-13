package com.minlia.cloud.infrastructure.framework.usercenter.services;


import com.minlia.cloud.infrastructure.framework.usercenter.entities.AuthorizableUserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    Page<AuthorizableUserProfile> listAllUsers(Pageable pagingCriteria);

    void deleteUserById(String id);

}
