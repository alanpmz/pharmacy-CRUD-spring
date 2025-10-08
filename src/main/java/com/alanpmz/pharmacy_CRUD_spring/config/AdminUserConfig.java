package com.alanpmz.pharmacy_CRUD_spring.config;

import com.alanpmz.pharmacy_CRUD_spring.model.Role;
import com.alanpmz.pharmacy_CRUD_spring.model.User;
import com.alanpmz.pharmacy_CRUD_spring.repository.RoleRepository;
import com.alanpmz.pharmacy_CRUD_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class AdminUserConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleRepository.findByName(Role.Values.ADMIN.name())
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(Role.Values.ADMIN.name());
                    return roleRepository.save(newRole);
                });

        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                (user) -> {
                    System.out.println("User "+ user.getUsername() + " already exists.");
                },
                () -> {
                    var user = new User();
                    user.setUsername("admin");
                    user.setPassword(passwordEncoder.encode("admin"));
                    user.setRoles(Set.of(adminRole));
                    userRepository.save(user);
                    System.out.println("Admin user created successfully.");
                }
        );
    }
}