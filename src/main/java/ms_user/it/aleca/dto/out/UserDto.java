package ms_user.it.aleca.dto.out;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class UserDto {

    private Long id;

    private String email;

    private String status;

    private Set<RoleDto> roles;
}
