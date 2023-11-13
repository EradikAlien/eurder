package be.switchfully.webapi;

import be.switchfully.user.repository.UserRepository;
import be.switchfully.user.service.UserService;
import be.switchfully.user.service.dto.CreateUserDTO;
import be.switchfully.user.service.dto.UserDTO;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static be.switchfully.webapi.UserConstantTest.*;

//@QuarkusTest
//class UserServiceTest {
//    private UserService userService;
//    private UserRepository userRepositoryMock;
//
//    @BeforeEach
//    void setUp() {
//        userRepositoryMock = Mockito.mock(UserRepository.class);
//        userService = new UserService(userRepositoryMock);
//        Mockito.when(userRepositoryMock.getAll()).thenReturn(USER_COLLECTION);
//    }
//
//    @Test
//    void getAll() {
//        List<UserDTO> actualList = userService.getAll();
//
//        Assertions.assertThat(actualList).containsExactlyInAnyOrder(USER_DTO_1, USER_DTO_2, USER_DTO_3);
//    }
//
//    @Test
//    void addUser() {
//        CreateUserDTO createUserDTO = new CreateUserDTO();
//        userService.addUser(createUserDTO);
//
//        Assertions.assertThat(userRepositoryMock.getAll()).containsExactlyInAnyOrder(USER_1);
//
//    }
//}
