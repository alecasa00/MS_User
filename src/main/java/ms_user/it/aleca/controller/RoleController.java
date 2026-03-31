package ms_user.it.aleca.controller;

import ms_user.it.aleca.dto.AddUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/roles")
public interface RoleController {

    @GetMapping
    ResponseEntity<Object> getAllRoles(int page , int size);

    @PostMapping
    ResponseEntity<Object> addRolesToUser(AddUserRequest userDto);


}
