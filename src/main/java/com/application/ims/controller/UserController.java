package com.application.ims.controller;

import com.application.ims.domain.dto.request.update.UpdateUserStatusRequestDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.ims.domain.service.interfaces.UserServiceInterface;
import jakarta.validation.Valid;
import com.application.ims.domain.dto.response.UserResponseDto;
import com.application.ims.domain.dto.request.create.UserRequestDto;
import com.application.ims.domain.dto.request.update.UpdateUserRequestDto;

import java.util.List;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/supplier/")
public class UserController {

    private final UserServiceInterface userService;

    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;

    }

    // POST method
    @PostMapping("add") // http://localhost:8080/api/supplier/add/
    public UserResponseDto save(@Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.save(userRequestDto);

    }

    // GET method (user per id)
    @GetMapping("/{id}/") // http://localhost:8080/api/supplier/{id}/
    public UserResponseDto getUser(
            @PathVariable Long id
    ) {
        return userService.getUser(id);
    }

    // GET method (all users)
    @GetMapping // http://localhost:8080/api/supplier/
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }

    // PUT method
    @PutMapping("/{id}/update/") // http://localhost:8080/api/supplier/{id}/update/
    public UserResponseDto update(
            @PathVariable Long id,
            @RequestBody UpdateUserRequestDto userRequestDto
    ) {
        return userService.update(id, userRequestDto);
    }

    // PATCH method
    @PatchMapping("{id}/update-status/") // http://localhost:8080/api/supplier/{id}/update-status/
    public UserResponseDto updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateUserStatusRequestDto userRequestDto
    ) {
        return userService.updateStatus(id, userRequestDto);
    }

    // DEL method
    @DeleteMapping(" {id}/delete/")
    public UserResponseDto delete(@PathVariable Long id) {
        return userService.delete(id);
    }

}
