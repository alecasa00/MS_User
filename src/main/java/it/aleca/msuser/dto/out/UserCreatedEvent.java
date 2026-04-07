package it.aleca.msuser.dto.out;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent implements Serializable {

    private String email;
    private String password;

}
