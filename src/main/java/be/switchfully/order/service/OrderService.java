package be.switchfully.order.service;

import be.switchfully.customExceptions.NoOrderFoundException;
import be.switchfully.customExceptions.NoUserFoundException;
import be.switchfully.item.repository.ItemRepository;
import be.switchfully.order.domain.ItemGroup;
import be.switchfully.order.domain.Order;
import be.switchfully.order.domain.Report;
import be.switchfully.order.repository.OrderRepository;
import be.switchfully.order.service.dto.CreateItemGroupDTO;
import be.switchfully.order.service.dto.CreateOrderDTO;
import be.switchfully.order.service.dto.OrderDTO;
import be.switchfully.user.domain.User;
import be.switchfully.user.repository.UserRepository;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
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
        User connectedUser = fetchConnectedUser();

        List<ItemGroup> itemGroups = createOrderDTO.itemGroups().stream().map((this::createItemGroup)).collect(Collectors.toList());
        Order orderToCreate = new Order(itemGroups, connectedUser);
        orderRepository.persist(orderToCreate);

        StringBuilder shippingDate = getShippingDate(itemGroups);
        return "Thank you " + connectedUser.getFirstname() + " for your order! ID of the order is " + orderToCreate.getId() + " the total price is " + orderToCreate.calculateOrderTotalPrice() + "€. " + shippingDate;
    }

    public String addReOrder(Long id) {
        User connectedUser = fetchConnectedUser();

        Order previousOrder = orderRepository.findByOrderIdAndUserId(connectedUser.getId(), id).orElseThrow(() -> new NoOrderFoundException("No order found with ID " + id));
        List<ItemGroup> itemGroups = previousOrder.getItemGroups().stream().map(ItemGroup::new).toList();

        Order orderToReCreate = new Order(itemGroups, connectedUser);
        orderRepository.persist(orderToReCreate);

        StringBuilder shippingDate = getShippingDate(itemGroups);
        return "Thank you " + connectedUser.getFirstname() + " for your re-ordering your order with ID " + id + ". ID of the new order is " + orderToReCreate.getId() + " the total price is " + orderToReCreate.calculateOrderTotalPrice() + "€. " + shippingDate;
    }

    public Report getAllOrders() {
        User connectedUser = fetchConnectedUser();

        List<OrderDTO> orderDTOList = orderRepository.findByUserId(connectedUser.getId()).stream().map(OrderMapper::mapToDTO).toList();

        return new Report(orderDTOList, calculateTotalPriceOfAllOrders(orderDTOList));
    }

    private static StringBuilder getShippingDate(List<ItemGroup> itemGroups) {
        StringBuilder shippingDate = new StringBuilder();
        for (ItemGroup itemGroup : itemGroups) {
            shippingDate.append("Shipping for ").append(itemGroup.getItem().name).append(" will be on the ").append(itemGroup.getShippingDate().toString()).append(", ");
        }
        return shippingDate.replace((shippingDate.length() - 2), (shippingDate.length()), ".");
    }

    private ItemGroup createItemGroup(CreateItemGroupDTO itemGroupDTO) {
        return new ItemGroup(itemRepository.findById(itemGroupDTO.itemId()), itemGroupDTO.amount());
    }

    private static double calculateTotalPriceOfAllOrders(List<OrderDTO> orderDTOList) {
        double totalPriceOfAllOrder = 0.0;
        for (OrderDTO orderDTO : orderDTOList) {
            totalPriceOfAllOrder += orderDTO.orderTotalPrice();
        }
        return totalPriceOfAllOrder;
    }

    private User fetchConnectedUser() {
        String memberEmailAddress = securityIdentity.getPrincipal().getName();
        return userRepository.getByEmailAddress(memberEmailAddress).orElseThrow(() -> new NoUserFoundException("User with email address " + memberEmailAddress + " not found"));
    }

}
