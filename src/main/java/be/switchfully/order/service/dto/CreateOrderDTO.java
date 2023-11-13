package be.switchfully.order.service.dto;

import java.util.List;

public record CreateOrderDTO(List<ItemGroupDTO> itemGroups) {

}
