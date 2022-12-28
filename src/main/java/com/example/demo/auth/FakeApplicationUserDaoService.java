package com.example.demo.auth;

import com.example.demo.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUser()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    public List<ApplicationUser> getApplicationUser(){
        List<ApplicationUser> applicationUsers = new ArrayList<>(
                Arrays.asList(
                        new ApplicationUser(
                                ApplicationUserRole.STUDENT.getGrantedAuthorities(),
                                passwordEncoder.encode("password"),
                                "annasmith",
                                true,
                                true,
                                true,
                                true
                        ),
                        new ApplicationUser(
                                ApplicationUserRole.ADMIN.getGrantedAuthorities(),
                                passwordEncoder.encode("password"),
                                "linda",
                                true,
                                true,
                                true,
                                true
                        ),
                        new ApplicationUser(
                                ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities(),
                                passwordEncoder.encode("password"),
                                "tom",
                                true,
                                true,
                                true,
                                true
                        )
                )
        );
        return applicationUsers;
    }
}
