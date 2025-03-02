package com.application.ims.domain.service.implementation;

import org.springframework.stereotype.Service;
import com.application.ims.domain.service.interfaces.UserServiceInterface;
import com.application.ims.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.ims.domain.dto.response.UserResponseDto;
import com.application.ims.domain.entity.User;
import com.application.ims.domain.dto.request.create.UserRequestDto;
import com.application.ims.domain.dto.request.update.UpdateUserRequestDto;
import com.application.ims.domain.dto.request.update.UpdateUserStatusRequestDto;

import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // POST method implementation
    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {

        // creating up a new user
        User user = new User();

        // setting up the new user attributes
        user.setFullName(userRequestDto.getFull_name());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setAdmin(userRequestDto.getIs_admin());
        user.setActive(true);

        User savedUser = userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setFull_name(savedUser.getFullName());
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setPassword(savedUser.getPassword());
        userResponseDto.setIs_admin(savedUser.isAdmin());
        userResponseDto.setIs_active(savedUser.isActive());

        return userResponseDto;

    }

    // GET method implementation
    @Override
    public UserResponseDto getUser(Long id) {

        // fetching the user with the given id
        User user = userRepository.findById(id).orElseThrow(()
            -> new RuntimeException("User with id " + id + " not found"));

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFull_name(user.getFullName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setIs_admin(user.isAdmin());
        userResponseDto.setIs_active(user.isActive());

        return userResponseDto;
    }

    // GET method implementation (list)
    @Override
    public List<UserResponseDto> getUsers() {

        // fetching all existing users
        List<User> users = userRepository.findAll();
        return users.stream().map(this::userResponseDtos).toList();

    }

    private UserResponseDto userResponseDtos(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFull_name(user.getFullName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setIs_admin(user.isAdmin());
        userResponseDto.setIs_active(user.isActive());

        return userResponseDto;
    }

    // GET method implementation
    @Override
    public UserResponseDto update(Long id, UpdateUserRequestDto userRequestDto) {

        // fetching the user with the given id
        User user = userRepository.findById(id).orElseThrow(()
                -> new RuntimeException("User with id " + id + " not found"));

        user.setFullName(userRequestDto.getFull_name());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setAdmin(userRequestDto.getIs_admin());

        // updating the user with new given data
        User updatedUser = userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(updatedUser.getId());
        userResponseDto.setFull_name(user.getFullName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setIs_admin(user.isAdmin());
        userResponseDto.setIs_active(user.isActive());

        return userResponseDto;
    }

    // PATCH method implementation
    @Override
    public UserResponseDto updateStatus(Long id, UpdateUserStatusRequestDto userRequestDto) {

        // fetching the user with the given id
        User user = userRepository.findById(id).orElseThrow(()
                -> new RuntimeException("User with id " + id + " not found"));

        user.setActive(userRequestDto.getIs_active());

        // updating the user status
        User updatedUser = userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(updatedUser.getId());
        userResponseDto.setFull_name(user.getFullName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setIs_admin(user.isAdmin());
        userResponseDto.setIs_active(user.isActive());

        return userResponseDto;
    }

    // DELETE method implementation
    @Override
    public UserResponseDto delete(Long id) {

        // fetching the user with the given id
        User user = userRepository.findById(id).orElseThrow(()
                -> new RuntimeException("User with id " + id + " not found"));

        userRepository.delete(user);

        return null;
    }

}
