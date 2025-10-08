package com.alanpmz.pharmacy_CRUD_spring.controller.auth;

import com.alanpmz.pharmacy_CRUD_spring.dto.auth.RegisterUserDTO;
import com.alanpmz.pharmacy_CRUD_spring.dto.user.UserResponseDTO;
import com.alanpmz.pharmacy_CRUD_spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<Void> createUser (@RequestBody RegisterUserDTO registerUserDTO){
        userService.createUser(registerUserDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
