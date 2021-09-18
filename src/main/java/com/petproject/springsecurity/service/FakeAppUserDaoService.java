package com.petproject.springsecurity.service;

import com.google.common.collect.Lists;
import com.petproject.springsecurity.model.security.FakeAppUser;
import com.petproject.springsecurity.model.security.User;
import com.petproject.springsecurity.repository.AppUserDao;
import com.petproject.springsecurity.repository.FakeAppUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationUserRole.*;
import static com.petproject.springsecurity.config.UserRole.ADMIN;
import static com.petproject.springsecurity.config.UserRole.STUDENT;

@Repository("fake")
public class FakeFakeAppUserDaoService implements AppUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeFakeAppUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public Optional<User> loadUserByUsername(String username) {
        return (User) getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<FakeAppUser> getApplicationUsers() {
        List<FakeAppUser> applicationUsers = Lists.newArrayList(
                new FakeAppUser(
                        1L,
                        "annasmith",
                        passwordEncoder.encode("password"),
                        STUDENT.getGrantedAuthorities()
                ),

                new FakeAppUser(
                        2L,
                        "linda",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthorities()
                )
        );

        return applicationUsers;
    }


}
