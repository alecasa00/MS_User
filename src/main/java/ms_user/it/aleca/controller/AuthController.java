package ms_user.it.aleca.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public interface AuthController {

    public ResponseEntity<Object> authenticate();

}
