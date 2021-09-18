package com.petproject.springsecurity.config;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

import static com.petproject.springsecurity.config.UserPermission.*;

public enum UserRole {
    STUDENT(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE)),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserPermission permission : getPermissions()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermission()));
        }
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;
    }


}
