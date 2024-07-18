package mindswap.Car.Api.service;


import mindswap.Car.Api.converter.UserConverter;
import mindswap.Car.Api.dto.UserDto;
import mindswap.Car.Api.exception.UserException;
import mindswap.Car.Api.message.Message;
import mindswap.Car.Api.model.User;
import mindswap.Car.Api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;


    private final UserConverter userConverter;


    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userConverter::fromModelToDto)
                .collect(Collectors.toList());
    }

    public UserDto addNewUser(UserDto userDto) {
        ValidateNewUser(userDto);
        User user = userConverter.fromDtoToModel(userDto);
        user = userRepository.save(user);
        return userConverter.fromModelToDto(user);
    }

    /******************************  Validate User Null    ****************************************/
    private void ValidateNewUser(UserDto userDto) {
        if (userDto.getName() == null || userDto.getName().trim().isEmpty()) {
            throw new UserException(Message.INVALID_NAME);
        }
        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty() || !userDto.getEmail().matches("[^@ ]+@[^@ ]+\\.[^@ ]+")) {
            throw new UserException(Message.INVALID_EMAIL);
        }
        if (!userDto.getName().matches("[a-zA-Z]+")) {
            throw new UserException(Message.INVALID_NAME);
        }
    }


    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException(Message.USER_NOT_FOUND));
        return userConverter.fromModelToDto(user);
    }


    @Override
    public String toString() {
        return "UserService{" +
                "userRepository=" + userRepository +
                '}';
    }


    public UserDto deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException(Message.USER_NOT_FOUND_CAN_NOT_DELETE));
        if (user != null) {
            userRepository.deleteById(id);
        }
        return userConverter.fromModelToDto(user);
    }


    public UserDto updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserException(Message.USER_NOT_FOUND_CAN_NOT_EDIT));
        if (existingUser != null) {
            User updatedUser = userConverter.fromDtoToModel(userDto);
            updatedUser.setId(id);

            updatedUser.setEmail(userDto.getEmail());
            ValidateNewUser(userDto);
            updatedUser = userRepository.save(updatedUser);
            return userConverter.fromModelToDto(updatedUser);
        }
        return null;
    }

}
