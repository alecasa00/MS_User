package ms_user.it.aleca.controller.impl;

import ms_user.it.aleca.controller.UserController;
import ms_user.it.aleca.dto.AddUserRequest;
import ms_user.it.aleca.services.UserService;
import org.springframework.http.ResponseEntity;

public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public ResponseEntity<Object> getUser(int userId) {
        return null;
    }

    @Override
    public ResponseEntity<Object> getUserList(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> addNewUser(AddUserRequest userDto) {
        return null;
    }
}
