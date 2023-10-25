package be.switchfully.item.service;

import be.switchfully.item.repository.ItemRepository;
import be.switchfully.item.service.dto.CreateItemDTO;
import be.switchfully.item.domain.Item;
import be.switchfully.item.service.dto.ItemDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ItemService {
    ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    public List<ItemDTO> getAllItems() {
        return itemRepository.getAll().stream().map(ItemMapper::mapToDTO).collect(Collectors.toList());
    }

    public String addItem(CreateItemDTO createItemDTO) {
        Item itemToCreate = ItemMapper.mapToEntity(createItemDTO);
        itemRepository.addItem(itemToCreate);
        return itemToCreate.getId();
    }
}
