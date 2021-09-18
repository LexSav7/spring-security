package com.petproject.springsecurity.service;

import com.google.common.collect.Lists;
import com.petproject.springsecurity.model.security.DbAppUser;
import com.petproject.springsecurity.repository.AppUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.petproject.springsecurity.config.UserRole.STUDENT;

@Repository("fake")
public class DbAppUserDaoService implements AppUserDao {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DbAppUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private List<DbAppUser> getAppUsers() {
        List<DbAppUser> applicationUsers = Lists.newArrayList(
                new DbAppUser(
                        1L,
                        "annasmith",
                        passwordEncoder.encode("password"),
                        STUDENT.getGrantedAuthorities()
                ),
                new DbAppUser(
                        "linda",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new DbAppUser(
                        "tom",
                        passwordEncoder.encode("password"),
                        ADMINTRAINEE.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }

    @Override
    public Optional<DbAppUser> loadUserByUsername(String username) {
        return getAppUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }
}
