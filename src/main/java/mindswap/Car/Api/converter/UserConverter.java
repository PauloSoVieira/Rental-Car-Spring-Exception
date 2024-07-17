package mindswap.Car.Api.converter;

import mindswap.Car.Api.dto.UserDto;
import mindswap.Car.Api.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User fromDtoToModel(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        //   user.setLastName(messageDto.lastName());
        return user;
    }

    public UserDto fromModelToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        return userDto;

        //  message.getLastName()

    }
}

