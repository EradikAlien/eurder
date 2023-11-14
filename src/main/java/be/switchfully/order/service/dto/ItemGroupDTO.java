package be.switchfully.order.service.dto;

import be.switchfully.item.service.dto.ItemForItemGroupDTO;

public record ItemGroupDTO(Long itemGroupId, ItemForItemGroupDTO item, int amount, double totalPrice) {

}
