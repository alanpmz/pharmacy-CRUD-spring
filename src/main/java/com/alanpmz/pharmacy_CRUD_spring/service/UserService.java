package com.alanpmz.pharmacy_CRUD_spring.service;

import com.alanpmz.pharmacy_CRUD_spring.dto.auth.RegisterUserDTO;
import com.alanpmz.pharmacy_CRUD_spring.dto.user.UserResponseDTO;
import com.alanpmz.pharmacy_CRUD_spring.model.Role;
import com.alanpmz.pharmacy_CRUD_spring.model.User;
import com.alanpmz.pharmacy_CRUD_spring.repository.RoleRepository;
import com.alanpmz.pharmacy_CRUD_spring.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public void createUser(RegisterUserDTO registerUserDTO){
        Role userRole = roleRepository.findByName(Role.Values.USER.name())
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(Role.Values.USER.name());
                    return roleRepository.save(newRole);
                });

        if (userRepository.existsByUsername(registerUserDTO.username())){
            throw new EntityExistsException("Username already in use.");
        }

        User user = User.builder()
                .username(registerUserDTO.username())
                .password(passwordEncoder.encode(registerUserDTO.password()))
                .roles(Set.of(userRole)).build();

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    private UserResponseDTO mapToResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getRoles());
    }
}
