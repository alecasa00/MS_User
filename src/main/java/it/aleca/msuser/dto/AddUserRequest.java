package it.aleca.msuser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NonNull;

import java.util.List;


@Data
@Schema(name = "AddUserRequest" ,description = "Request per la creazione di un nuovo utente")
public class AddUserRequest {

    @NonNull
    @Schema(pattern = "[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+")
    private String email;

    @NonNull
    @Schema(
            description = "Lista di identificativi dei ruoli da assegnare",
            example = "[42,3,1]"
    )
    private List<Long> roleIdList;

}
