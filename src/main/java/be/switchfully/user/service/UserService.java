package be.switchfully.user.service;

import be.switchfully.customExceptions.NoUserFoundException;
import be.switchfully.user.domain.User;
import be.switchfully.user.repository.UserRepository;
import be.switchfully.user.service.dto.CreateUserDTO;
import be.switchfully.user.service.dto.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.listAll().stream().map(UserMapper::mapToDTO).collect(Collectors.toList());
    }

    public UUID addUser(CreateUserDTO createUserDTO) {
        User userToCreate = UserMapper.mapToEntity(createUserDTO);
        userRepository.persist(userToCreate);
        return userToCreate.getId();
    }

    public UserDTO getUserById(UUID id) {
        User user = userRepository.findByUUIDOptional(id).orElseThrow(() -> new NoUserFoundException("No user found with ID " + id.toString()));
        return UserMapper.mapToDTO(user);
    }
}
