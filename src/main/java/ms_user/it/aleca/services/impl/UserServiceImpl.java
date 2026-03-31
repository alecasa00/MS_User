package ms_user.it.aleca.services.impl;


import jakarta.transaction.Transactional;
import ms_user.it.aleca.dto.AddUserRequest;
import ms_user.it.aleca.dto.out.UserDto;
import ms_user.it.aleca.entities.Role;
import ms_user.it.aleca.entities.User;
import ms_user.it.aleca.enums.UserStatusEnum;
import ms_user.it.aleca.exceptions.ApplicationLogicException;
import ms_user.it.aleca.mappers.UserMapper;
import ms_user.it.aleca.repository.UserRepository;
import ms_user.it.aleca.services.UserService;
import ms_user.it.aleca.utils.PasswordUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleServiceImpl roleService;

    public UserServiceImpl(UserRepository userRepository, RoleServiceImpl roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
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

            return result.getId();


        }catch (Exception e){
            throw e;
        }
    }
}
