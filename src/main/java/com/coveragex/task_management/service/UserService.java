package com.coveragex.task_management.service;


import com.coveragex.task_management.dto.LoginDto;
import com.coveragex.task_management.dto.UserDto;

import java.io.Serializable;
import java.util.Optional;

public interface UserService extends Serializable {

    String login(LoginDto loginDto);
    UserDto registerUser(UserDto userDto);
}
