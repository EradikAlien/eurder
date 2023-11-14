package be.switchfully.order.service.dto;

import java.util.List;

public record OrderDTO(Long id, List<ItemGroupDTO> itemGroups, double orderTotalPrice) {
}
