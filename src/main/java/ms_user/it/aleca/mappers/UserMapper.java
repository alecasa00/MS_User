package ms_user.it.aleca.mappers;

import ms_user.it.aleca.dto.out.RoleDto;
import ms_user.it.aleca.dto.out.UserDto;
import ms_user.it.aleca.entities.User;
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
