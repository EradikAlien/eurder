package be.switchfully.order.service;

import be.switchfully.item.service.ItemMapper;
import be.switchfully.order.domain.ItemGroup;
import be.switchfully.order.service.dto.ItemGroupDTO;

import java.util.List;

public class ItemGroupMapper {
    public static ItemGroupDTO mapToDTO(ItemGroup itemGroup) {
        return new ItemGroupDTO(itemGroup.getId(), ItemMapper.mapToDTO(itemGroup.getItem()), itemGroup.getAmount());
    }
}
