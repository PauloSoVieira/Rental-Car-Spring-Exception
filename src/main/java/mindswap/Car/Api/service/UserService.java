package mindswap.Car.Api.service;


import mindswap.Car.Api.converter.UserConverter;
import mindswap.Car.Api.dto.UserDto;
import mindswap.Car.Api.model.User;
import mindswap.Car.Api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userConverter::fromModelToDto)
                .collect(Collectors.toList());
    }

    public UserDto addNewUser(UserDto userDto) {
        User user = userConverter.fromDtoToModel(userDto);
        user = userRepository.save(user);
        return userConverter.fromModelToDto(user);
    }


    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return userConverter.fromModelToDto(user);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "userRepository=" + userRepository +
                '}';
    }


    public UserDto deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Error"));
        if (user != null) {
            userRepository.deleteById(id);
        }
        return userConverter.fromModelToDto(user);
    }


    public UserDto updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            User updatedUser = userConverter.fromDtoToModel(userDto);
            updatedUser.setId(id);
            updatedUser = userRepository.save(updatedUser);
            return userConverter.fromModelToDto(updatedUser);
        }
        return null;
    }

}
