package be.switchfully.order.service.dto;

import be.switchfully.item.domain.Item;
import be.switchfully.item.service.dto.ItemDTO;

public record ItemGroupDTO(Long itemId, ItemDTO item, int amount) {

}
