package be.switchfully.order.service.dto;

import java.util.List;

public record OrderDTO(
        Long OrderId,
        List<ItemGroupDTO> itemGroups,
        double orderTotalPrice) {
}
