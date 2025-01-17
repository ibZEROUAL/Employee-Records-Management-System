package com.employee.system.service;

import com.employee.system.dto.UserDto;
import com.employee.system.exception.UserNotFoundException;
import com.employee.system.mapper.UserMapper;
import com.employee.system.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDto getById(Long id) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return userMapper.toDto(user);
    }

    public List<UserDto> getAll(){
        return userMapper.toDto(userRepository.findAll());
    }

    public UserDto addUser(UserDto dto){
        var user = userMapper.toEntity(dto);
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto updateUser(Long id, UserDto dto){
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        var updatedUser = userMapper.partialUpdate(dto,user);
        return userMapper.toDto(userRepository.save(updatedUser));
    }

    public void deleteUser(Long id){
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(id);
    }
}
