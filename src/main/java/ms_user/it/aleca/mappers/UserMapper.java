package ms_user.it.aleca.mappers;

import ms_user.it.aleca.dto.out.RoleDto;
import ms_user.it.aleca.dto.out.UserDto;
import ms_user.it.aleca.entities.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {


    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .active(user.getActive())
                .roles(user.getRoles().stream().map(role -> RoleDto.builder()
                        .id(role.getId())
                        .name(role.getRuolo())
                        .build()).collect(Collectors.toSet()))
                .build();
    }


}
