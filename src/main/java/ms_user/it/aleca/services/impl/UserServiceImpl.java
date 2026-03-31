package ms_user.it.aleca.services.impl;


import ms_user.it.aleca.dto.out.UserDto;
import ms_user.it.aleca.entities.User;
import ms_user.it.aleca.mappers.UserMapper;
import ms_user.it.aleca.repository.UserRepository;
import ms_user.it.aleca.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDto getUserById(int userId) {

        Optional<User> user =userRepository.findById(userId);

        if (user.isPresent()) {
            return UserMapper.toUserDto(user.get());
        }
        return null;

    }

    @Override
    public List<UserDto> getUserListPaginated(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<User> userPage = userRepository.findAll(pageable);

        if (userPage.isEmpty()){
            return null;
        }

         return userPage.getContent().stream().map(UserMapper::toUserDto).toList();

    }
}
