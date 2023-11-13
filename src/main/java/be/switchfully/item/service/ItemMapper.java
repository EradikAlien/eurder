package be.switchfully.item.service;

import be.switchfully.item.domain.Item;
import be.switchfully.item.service.dto.CreateAndUpdateItemDTO;
import be.switchfully.item.service.dto.ItemDTO;

import static be.switchfully.item.domain.Item.createItem;

public class ItemMapper {
    public static ItemDTO mapToDTO(Item item) {

        return new ItemDTO(
                item.getId(),
                item.name,
                item.description,
                item.price,
                item.stock);
    }

    public static Item mapToEntity(CreateAndUpdateItemDTO createItemDTO) {
        return createItem(createItemDTO.name(),
                createItemDTO.description(),
                createItemDTO.price(),
                createItemDTO.stock());
    }
}
