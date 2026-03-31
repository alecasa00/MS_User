package ms_user.it.aleca.controller;

import ms_user.it.aleca.dto.AddUserRequest;
import ms_user.it.aleca.dto.out.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/users")
public interface UserController {

    //TODO : only admin can access these methods


    @GetMapping("/{userId}")
    ResponseEntity<UserDto> getUserById(@PathVariable int userId);

    @GetMapping()
    ResponseEntity<List<UserDto>> getUserListPaginated(@RequestParam int page , @RequestParam int size);

    @PostMapping
    ResponseEntity<Object> addNewUser(@RequestBody AddUserRequest userDto);

}
