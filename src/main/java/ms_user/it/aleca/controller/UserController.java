package ms_user.it.aleca.controller;

import ms_user.it.aleca.dto.AddUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public interface UserController {


    @GetMapping("/{userId}")
    ResponseEntity<Object> getUser(@PathVariable int userId);

    @GetMapping("/{page}/{size}")
    ResponseEntity<Object> getUserList(int page ,int size);

    //TODO : only admin can create new users
    @PostMapping
    ResponseEntity<Object> addNewUser(AddUserRequest userDto);

}
