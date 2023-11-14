package be.switchfully.webapi;

import be.switchfully.customExceptions.NoItemFoundException;
import be.switchfully.customExceptions.NoUserFoundException;
import be.switchfully.item.service.ItemService;
import be.switchfully.item.service.dto.CreateAndUpdateItemDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import static jakarta.ws.rs.core.Response.Status.*;

@Path("/item")
@Consumes(MediaType.APPLICATION_JSON)
public class ItemController {
    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GET
    @RolesAllowed({"member", "admin"})
    public Response getAllItems() {
        return Response.status(OK).entity(itemService.getAllItems()).build();
    }

    @POST
    @Path("/addItem")
    @RolesAllowed("admin")
    public Response addItem(CreateAndUpdateItemDTO createItemDTO) {
        return Response.status(CREATED).entity(itemService.addItem(createItemDTO)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response updateItem(@PathParam("id")Long id, CreateAndUpdateItemDTO updateItemDTO) {
        return Response.status(CREATED).entity(itemService.updateItem(id, updateItemDTO)).build();
    }
    @ServerExceptionMapper(NoItemFoundException.class)
    protected Response noItemFoundException(NoItemFoundException exception) {
        return Response.status(NOT_FOUND).entity(exception.getMessage()).build();
    }
}
