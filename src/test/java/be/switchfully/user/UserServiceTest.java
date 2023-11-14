package be.switchfully.user;

import be.switchfully.user.repository.UserRepository;
import be.switchfully.user.service.UserService;
import be.switchfully.user.service.dto.UserDTO;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static be.switchfully.user.UserConstantTest.*;
import static org.mockito.Mockito.mockStatic;


@ExtendWith(MockitoExtension.class)
@QuarkusTest
class UserServiceTest {
    private UserService userService;
    private UserRepository userRepositoryMock;

    @BeforeEach
    void setUp() {
        userRepositoryMock = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepositoryMock);
    }

    @Test
    void getAll() {
        Mockito.when(userRepositoryMock.listAll()).thenReturn(USER_COLLECTION);

        List<UserDTO> actualList = userService.getAllUsers();

        Assertions.assertThat(actualList).containsExactlyInAnyOrder(USER_DTO_1, USER_DTO_2, USER_DTO_3);
    }

    @Test
    void getByUserId() {
        UUID id = USER_DTO_1.id();
        Mockito.when(userRepositoryMock.findByUUIDOptional(id)).thenReturn(Optional.of(USER_1));

        UserDTO actualUser = userService.getUserById(id);

        Assertions.assertThat(actualUser).isEqualTo(USER_DTO_1);
    }
    @Test
    void addUser() {
        UUID userAddId = USER_ADD.getId();

        UUID actualId;
        try (MockedStatic<UUID> uuid = mockStatic(UUID.class)) {
            uuid.when(UUID::randomUUID).thenReturn(userAddId);
            actualId = userService.addUser(CREATE_USER_DTO);
        }

        Mockito.verify(userRepositoryMock, Mockito.times(1))
                .persist(USER_ADD);
        Assertions.assertThat(actualId).isEqualTo(USER_ADD.getId());
    }
}
