package com.coveragex.task_management.transformer;

import com.coveragex.task_management.dto.UserDto;
import com.coveragex.task_management.entity.User;

public class UserTransformer {

    public static User toEntity(UserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getEmail(),
                null,
                user.getFirstName(),
                user.getLastName()
        );
    }
}
