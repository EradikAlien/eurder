package be.switchfully.item;

import be.switchfully.item.service.dto.CreateAndUpdateItemDTO;

public class ItemConstantTest {
    public static final CreateAndUpdateItemDTO CREATE_ITEM_DTO = new CreateAndUpdateItemDTO("test create item", "item for testing purpose only", 95.21, 5);
    public static final CreateAndUpdateItemDTO UPDATE_ITEM_DTO = new CreateAndUpdateItemDTO("test update item", "item for testing purpose only", 21.95, 5);
}
