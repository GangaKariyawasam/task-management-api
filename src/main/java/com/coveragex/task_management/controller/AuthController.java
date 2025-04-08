package com.coveragex.task_management.controller;

import com.coveragex.task_management.dto.AuthResponseDTO;
import com.coveragex.task_management.dto.LoginDto;
import com.coveragex.task_management.dto.UserDto;
import com.coveragex.task_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        UserDto registeredUser = userService.registerUser(userDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDTO) {
        String token = userService.login(loginDTO);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
