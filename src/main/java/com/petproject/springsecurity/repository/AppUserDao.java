package com.petproject.springsecurity.repository;

import com.petproject.springsecurity.model.security.DbAppUser;
import com.petproject.springsecurity.model.security.User;

import java.util.Optional;

public interface AppUserDao {

    Optional<User> loadUserByUsername(String username);

}
