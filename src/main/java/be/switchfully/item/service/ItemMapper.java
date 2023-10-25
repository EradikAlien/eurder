package be.switchfully.item.service;

import be.switchfully.item.service.dto.CreateItemDTO;
import be.switchfully.item.domain.Item;
import be.switchfully.item.service.dto.ItemDTO;

public class ItemMapper {
    public static ItemDTO mapToDTO(Item item) {

        return new ItemDTO(
        item.getId(),
        item.name,
        item.description,
        item.price,
        item.stock);
    }

    public static Item mapToEntity(CreateItemDTO createItemDTO) {
        return new Item(createItemDTO.getName(),
                createItemDTO.getDescription(),
                createItemDTO.getPrice(),
                createItemDTO.getStock());
    }
}
