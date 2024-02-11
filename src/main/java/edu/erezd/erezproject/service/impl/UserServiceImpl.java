package edu.erezd.erezproject.service.impl;

import edu.erezd.erezproject.dto.UserCreateDTO;
import edu.erezd.erezproject.dto.UserResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import edu.erezd.erezproject.entity.User;
import edu.erezd.erezproject.exception.ResourceNotFoundException;
import edu.erezd.erezproject.repository.UserRepository;
import edu.erezd.erezproject.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder; // Add this line


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserResponseDTO createUser(UserCreateDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserResponseDTO.class);
    }


    @Override
    public UserResponseDTO getUserById(long id) {
        User user = getUserEntityOrThrow(id); // Use the helper method to get the user or throw an exception
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO updateUser(long id, UserCreateDTO dto) {
        User user = getUserEntityOrThrow(id); // Use the helper method to ensure the user exists
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        // Consider adding more fields to update as necessary
        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO deleteUserById(long id) {
        User user = getUserEntityOrThrow(id);
        userRepository.deleteById(id);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    private User getUserEntityOrThrow(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.newInstance("User", "id", id).get());
    }


}