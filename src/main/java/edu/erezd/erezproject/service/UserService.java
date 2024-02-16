package edu.erezd.erezproject.service;

import edu.erezd.erezproject.dto.UserCreateDTO;
import edu.erezd.erezproject.dto.UserResponseDTO;
import edu.erezd.erezproject.entity.User;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserCreateDTO dto);

    UserResponseDTO getUserById(long id);

    UserResponseDTO updateUser(long id, UserCreateDTO dto);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO deleteUserById(long id);

    User getUserEntityOrThrow(long id);

}
