package cryst.blogapi.service;

import cryst.blogapi.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserId(Integer userId);
    List<UserDto> getAll();
    Void deleteUser(Integer userId);

}
