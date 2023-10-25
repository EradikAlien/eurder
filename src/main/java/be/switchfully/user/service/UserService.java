package be.switchfully.user.service;

import be.switchfully.user.repository.UserRepository;
import be.switchfully.user.service.dto.CreateUserDTO;
import be.switchfully.user.domain.User;
import be.switchfully.user.service.dto.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAll() {
        return userRepository.getAll().stream().map(UserMapper::mapToDTO).collect(Collectors.toList());
    }

    public String addUser(CreateUserDTO createUserDTO) {
        User userToCreate = UserMapper.mapToEntity(createUserDTO);
        userRepository.addUser(userToCreate);
        return userToCreate.getId();
    }
}
