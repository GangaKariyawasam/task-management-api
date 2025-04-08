package com.coveragex.task_management.service.impl;

import com.coveragex.task_management.dto.LoginDto;
import com.coveragex.task_management.dto.UserDto;
import com.coveragex.task_management.entity.User;
import com.coveragex.task_management.exception.EmailAlreadyExistsException;
import com.coveragex.task_management.exception.UnauthorizedException;
import com.coveragex.task_management.repository.UserRepository;
import com.coveragex.task_management.service.UserService;
import com.coveragex.task_management.transformer.UserTransformer;
import com.coveragex.task_management.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtUtil.generateToken(userDetails.getUsername());
        }catch (Exception e){
            throw  new UnauthorizedException("Invalid Username or Password");
        }
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email is already associated with another user.");
        }
        User user = UserTransformer.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(user);
        return UserTransformer.toDto(savedUser);
    }
}
