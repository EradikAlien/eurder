package be.switchfully.webapi;

import be.switchfully.item.service.ItemService;
import be.switchfully.item.service.dto.CreateItemDTO;
import be.switchfully.item.service.dto.ItemDTO;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.CREATED;

@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemController {
    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GET
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    @POST
    @Transactional
    public Response addItem(CreateItemDTO createItemDTO) {
        return Response.status(CREATED).entity(itemService.addItem(createItemDTO)).build();
    }
}
