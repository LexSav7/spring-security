package com.petproject.springsecurity.service;

import com.petproject.springsecurity.model.security.DbAppUser;
import com.petproject.springsecurity.model.security.Privilege;
import com.petproject.springsecurity.model.security.Role;
import com.petproject.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;

//    @Autowired
//    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DbAppUser user = userRepository
                .findByUsernameContainingIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));;

        return new User(user.getUsername(), user.getPassword(), getAuthoritiesForUser(user));
    }

    private Set<SimpleGrantedAuthority> getAuthoritiesForUser(DbAppUser user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            for (Privilege privilege : role.getPrivileges()) {
                authorities.add(new SimpleGrantedAuthority(privilege.getName()));
            }
        }
        return authorities;
    }
}
