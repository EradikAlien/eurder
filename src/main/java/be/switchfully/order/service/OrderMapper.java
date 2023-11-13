package be.switchfully.order.service;

import be.switchfully.order.domain.Order;
import be.switchfully.order.service.dto.OrderDTO;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO mapToDTO(Order order) {
        return new OrderDTO(order.getId(), order.getItemGroups().stream().map(ItemGroupMapper::mapToDTO).collect(Collectors.toList()));
    }
}
