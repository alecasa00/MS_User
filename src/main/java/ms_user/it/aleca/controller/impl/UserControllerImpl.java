package ms_user.it.aleca.controller.impl;

import ms_user.it.aleca.controller.UserController;
import ms_user.it.aleca.dto.AddUserRequest;
import ms_user.it.aleca.dto.out.UserDto;
import ms_user.it.aleca.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;



    //TODO: implementare i metodi del controller, iniettare il servizio e gestire le risposte HTTP
    //pre autoorizzare i metodi con @PreAuthorize("hasRole('ROLE_USER')"), gestire i tipi di ritorno adeguati

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public ResponseEntity<UserDto> getUserById(int userId) {

        UserDto userDto = userService.getUserById(userId);

        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(userDto);
    }

    @Override
    public ResponseEntity<List<UserDto>> getUserListPaginated(int page, int size) {

        List<UserDto> userDtoList = userService.getUserListPaginated(page,size);

        if (userDtoList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(userDtoList);
    }

    @Override
    public ResponseEntity<Object> addNewUser(AddUserRequest userDto) {
        return null;
    }
}
