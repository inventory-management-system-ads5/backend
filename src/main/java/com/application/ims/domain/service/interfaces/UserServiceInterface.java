package com.application.ims.domain.service.interfaces;

import com.application.ims.domain.dto.request.create.UserRequestDto;
import com.application.ims.domain.dto.request.update.UpdateUserRequestDto;
import com.application.ims.domain.dto.request.update.UpdateUserStatusRequestDto;
import com.application.ims.domain.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceInterface {
    UserResponseDto save(UserRequestDto userRequestDto);
    UserResponseDto getUser(Long id);
    List<UserResponseDto> getUsers();
    UserResponseDto update(Long id, UpdateUserRequestDto userRequestDto);
    UserResponseDto updateStatus(Long id, UpdateUserStatusRequestDto userRequestDto);
    UserResponseDto delete(Long id);

}
