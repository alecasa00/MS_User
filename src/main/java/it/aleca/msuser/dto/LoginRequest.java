package it.aleca.msuser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "LoginRequest", description = "Login request")
public class LoginRequest {

    @Schema(pattern = "[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+")
    private String email;

    @Schema(pattern = "[A-Za-z0-9]")
    private String password;


}
