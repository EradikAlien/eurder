package be.switchfully.order.service;

import be.switchfully.customExceptions.NoUserFoundException;
import be.switchfully.item.repository.ItemRepository;
import be.switchfully.order.domain.ItemGroup;
import be.switchfully.order.domain.Order;
import be.switchfully.order.repository.OrderRepository;
import be.switchfully.order.service.dto.CreateOrderDTO;
import be.switchfully.order.service.dto.ItemGroupDTO;
import be.switchfully.order.service.dto.OrderDTO;
import be.switchfully.user.domain.User;
import be.switchfully.user.repository.UserRepository;
import io.quarkus.logging.Log;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class OrderService {

    @Inject
    SecurityIdentity securityIdentity;
    UserRepository userRepository;
    OrderRepository orderRepository;
    ItemRepository itemRepository;

    public OrderService(UserRepository userRepository, OrderRepository orderRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public String addOrder(CreateOrderDTO createOrderDTO) {
        String memberEmailAddress = securityIdentity.getPrincipal().getName();
        User connectedUser = userRepository.getByEmailAddress(memberEmailAddress).orElseThrow(() -> new NoUserFoundException("User with email address " + memberEmailAddress + " not found"));


        List<ItemGroup> itemGroups = createOrderDTO.itemGroups().stream().map((this::createItemGroup)).collect(Collectors.toList());
        Order orderToCreate = new Order(itemGroups, connectedUser);
        orderRepository.persist(orderToCreate);
        return "Thank you " + connectedUser.getFirstname() + " for your order! ID of the order is " + orderToCreate.getId();
    }

    private ItemGroup createItemGroup(ItemGroupDTO itemGroupDTO) {
        return new ItemGroup(itemRepository.findById(itemGroupDTO.itemId()), itemGroupDTO.amount());
    }

    // TODO implement getAllOrders
    public List<OrderDTO> getAllOrders() {
        String memberEmailAddress = securityIdentity.getPrincipal().getName();
        User connectedUser = userRepository.getByEmailAddress(memberEmailAddress).orElseThrow(() -> new NoUserFoundException("User with email address " + memberEmailAddress + " not found"));
        return orderRepository.findByUserId(connectedUser.getId()).stream().map(OrderMapper::mapToDTO).toList();
    }
}
