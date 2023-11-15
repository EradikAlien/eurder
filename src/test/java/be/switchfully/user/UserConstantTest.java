package be.switchfully.user;

import be.switchfully.order.service.dto.CreateItemGroupDTO;
import be.switchfully.order.service.dto.CreateOrderDTO;
import be.switchfully.user.domain.Address;
import be.switchfully.user.domain.User;
import be.switchfully.user.service.UserMapper;
import be.switchfully.user.service.dto.CreateUserDTO;
import be.switchfully.user.service.dto.UserDTO;

import java.util.List;
import java.util.Set;

import static be.switchfully.user.domain.User.createAdmin;
import static be.switchfully.user.domain.User.createMember;

public class UserConstantTest {
    public static final Address ADDRESS_1 = new Address("Teststraat", "36", "7090", "Testberg");
    public static final User USER_1 = createMember("Laurent", "Herry", "laurent@email.be", "user", ADDRESS_1, "123456789");
    public static final User USER_2 = createMember("France", "Herry", "france@email.be", "user", ADDRESS_1, "987654321");
    public static final User USER_3 = createMember("Tina", "Mavric", "tina@email.be", "user", ADDRESS_1, "134679852");

    public static final List<User> USER_COLLECTION = List.of(USER_1,USER_2,USER_3);

    public static final UserDTO USER_DTO_1 = new UserDTO(USER_1.getId(), "Laurent", "Herry", "laurent@email.be",  ADDRESS_1, "123456789");
    public static final UserDTO USER_DTO_2 = new UserDTO(USER_2.getId(), "France", "Herry", "france@email.be",  ADDRESS_1, "987654321");
    public static final UserDTO USER_DTO_3 = new UserDTO(USER_3.getId(), "Tina", "Mavric", "tina@email.be",  ADDRESS_1, "134679852");

    public static final CreateUserDTO CREATE_USER_DTO = new CreateUserDTO("Roger", "Rabbit", "roger@rabbit.be", "roger", ADDRESS_1, "917346852");
    public static final User USER_ADD = createMember("Roger", "Rabbit", "roger@rabbit.be", "roger", ADDRESS_1, "917346852");

    public static final CreateItemGroupDTO CREATE_ITEM_GROUP_DTO = new CreateItemGroupDTO(1L,2);

    public static final CreateOrderDTO CREATE_ORDER_DTO = new CreateOrderDTO(List.of(CREATE_ITEM_GROUP_DTO));

}
