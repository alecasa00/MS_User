package it.aleca.msuser.mappers;

import it.aleca.msuser.dto.out.RoleDto;
import it.aleca.msuser.dto.out.UserDto;
import it.aleca.msuser.entities.User;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper
public interface UserMapper {


    static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .status(user.getStatus().name())
                .roles(user.getRoles().stream().map(userRole -> RoleDto.builder()
                        .id(userRole.getRole().getId())
                        .name(userRole.getRole().getDescrizione())
                        .build()).collect(Collectors.toSet()))
                .build();
    }

    //User userDtoToUser(UserDto userDto);


}
