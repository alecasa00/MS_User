package it.aleca.msuser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;


@Data
@Schema(name = "EditRolesRequest" ,description = "Request to assign roles to user")
public class EditRolesRequest {

    @NotNull
    @Schema(
            description = "Identificativo univoco dell'utente",
            example = "42",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long userId;

    @Schema(
            description = "Lista degli identificativi dei ruoli da assegnare",
            example = "[1, 2, 3]",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<Long> rolesList;
}
