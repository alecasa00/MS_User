package it.aleca.msuser.services;

import it.aleca.msuser.dto.AddUserRequest;
import it.aleca.msuser.dto.out.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    UserDto getUserById(int userId);

    List<UserDto> getUserListPaginated(int page, int size);

    Long addNewUser(AddUserRequest addUserRequest);
}
