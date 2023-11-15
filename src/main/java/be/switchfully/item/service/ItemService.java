package be.switchfully.item.service;

import be.switchfully.customExceptions.NoItemFoundException;
import be.switchfully.item.domain.Item;
import be.switchfully.item.repository.ItemRepository;
import be.switchfully.item.service.dto.CreateAndUpdateItemDTO;
import be.switchfully.item.service.dto.ItemDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class ItemService {
    ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDTO> getAllItems() {
        return itemRepository.listAll().stream().map(ItemMapper::mapToDTO).collect(Collectors.toList());
    }

    public Long addItem(CreateAndUpdateItemDTO createItemDTO) {
        Item itemToCreate = ItemMapper.mapToEntity(createItemDTO);
        itemRepository.persist(itemToCreate);
        return itemToCreate.getId();
    }

    public String updateItem(Long id, CreateAndUpdateItemDTO updateItemDTO) {
        Item itemToUpdate = itemRepository.findByIdOptional(id).orElseThrow(() -> new NoItemFoundException("No item found with id " + id));
        itemToUpdate.name = updateItemDTO.name();
        itemToUpdate.description = updateItemDTO.description();
        itemToUpdate.price = updateItemDTO.price();
        itemToUpdate.stock = updateItemDTO.stock();
        return "Item with ID " + itemToUpdate.getId() + " has been updated.";
    }
}
