package it.aleca.msuser.services.impl;


import it.aleca.msuser.dto.out.UserCreatedEvent;
import it.aleca.msuser.events.publishers.UserEventPublisher;
import jakarta.transaction.Transactional;
import it.aleca.msuser.dto.AddUserRequest;
import it.aleca.msuser.dto.out.UserDto;
import it.aleca.msuser.entities.Role;
import it.aleca.msuser.entities.User;
import it.aleca.msuser.enums.UserStatusEnum;
import it.aleca.msuser.exceptions.ApplicationLogicException;
import it.aleca.msuser.mappers.UserMapper;
import it.aleca.msuser.repository.UserRepository;
import it.aleca.msuser.services.UserService;
import it.aleca.msuser.utils.PasswordUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleServiceImpl roleService;
    private final UserEventPublisher userEventPublisher;

    public UserServiceImpl(UserRepository userRepository, RoleServiceImpl roleService, UserEventPublisher userEventPublisher) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userEventPublisher = userEventPublisher;
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

    @Override
    @Transactional
    public Long addNewUser(AddUserRequest addUserRequest) {

        User user = new User();
        List<Role> roleList;

        try{

            if (userRepository.existsByEmail(addUserRequest.getEmail())) {
                throw new ApplicationLogicException("Email already exists");
            }

            roleList = roleService.getRolesByIdList(addUserRequest.getRoleIdList());

            if(roleList.isEmpty()){
                throw new ApplicationLogicException("no role found matching user input");
            }
            for( Role role : roleList){
                user.addRole(role);
            }

            //New users get a random password and are forced to set a personal one at first login
            user.setStatus(UserStatusEnum.PASSWORD_CHANGE_REQUIRED);
            user.setEmail(addUserRequest.getEmail());

            //TODO this random passw (or generate a reset token) must be sent via mail using event driven operation on MS_Notification
            String randomPassword = PasswordUtils.generateRandomPassword();

            user.setPassword(PasswordUtils.passwordEncoder(randomPassword));

            User result = userRepository.save(user);

            if(result.getId()!=null){

                UserCreatedEvent userEvent = new UserCreatedEvent();
                userEvent.setEmail(user.getEmail());
                userEvent.setPassword(randomPassword);
                userEventPublisher.publishUserCreated(userEvent);


                return result.getId();

            }else {return null;}


        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
