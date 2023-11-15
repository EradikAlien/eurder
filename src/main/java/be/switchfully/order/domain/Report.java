package be.switchfully.order.domain;

import be.switchfully.order.service.dto.OrderDTO;

import java.util.List;

public record Report(
        List<OrderDTO> orderList,
        double totalPriceOfAllOrders) {
}
