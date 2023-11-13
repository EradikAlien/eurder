package be.switchfully.order.service.dto;

import be.switchfully.order.domain.ItemGroup;

import java.util.List;

public record OrderDTO(Long id, List<ItemGroupDTO> itemGroups) {
}
