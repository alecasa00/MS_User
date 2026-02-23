package ms_user.it.aleca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;



@Data
@Schema(name = "AddUserRequest" ,description = "Request per la creazione di un nuovo utente")
public class AddUserRequest {

    @Schema(pattern = "[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+")
    private String email;

    @Schema(
            description = "Identificativo univoco del ruolo uente",
            example = "42"
    )
    private int roleId;

    @Schema(description = "User profile status, default active")
    private Boolean status;

}
