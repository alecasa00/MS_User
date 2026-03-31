package ms_user.it.aleca.services;

import ms_user.it.aleca.dto.out.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    UserDto getUserById(int userId);

    List<UserDto> getUserListPaginated(int page, int size);
}
