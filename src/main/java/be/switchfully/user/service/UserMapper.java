package be.switchfully.user.service;

import be.switchfully.user.domain.User;
import be.switchfully.user.service.dto.CreateUserDTO;
import be.switchfully.user.service.dto.UserDTO;

public class UserMapper {
    public static UserDTO mapToDTO(User user) {

        return new UserDTO(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmailAddress(),
                user.getAddress(),
                user.getPhoneNumber());
    }

    public static User mapToEntity(CreateUserDTO createUserDTO) {
        return User.createMember(createUserDTO.firstname(),
                createUserDTO.lastname(),
                createUserDTO.emailAddress(),
                createUserDTO.password(),
                createUserDTO.address(),
                createUserDTO.phoneNumber());
    }
}
