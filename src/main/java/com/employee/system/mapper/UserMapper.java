package com.employee.system.mapper;

import com.employee.system.dto.UserDto;
import com.employee.system.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    List<User> toEntity(List<UserDto> userDto);
    User toEntity(UserDto userDto);

    List<UserDto> toDto(List<User> user);
    UserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto userDto, @MappingTarget User user);
}