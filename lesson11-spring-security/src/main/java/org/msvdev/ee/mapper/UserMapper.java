package org.msvdev.ee.mapper;

import org.msvdev.ee.dto.UserDto;
import org.msvdev.ee.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;


@Component
public class UserMapper {

    public User dtoToEntity(UserDto userDto) {
        return new User(userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                new HashSet<>(userDto.getRoles()));
    }

    public UserDto entityToDto(User user) {
        return new UserDto(user.getId(),
                user.getUsername(),
                "",
                "",
                new HashSet<>(user.getRoles()));
    }

}