package be.switchfully.webapi;

import be.switchfully.customExceptions.NoItemFoundException;
import be.switchfully.item.service.ItemService;
import be.switchfully.item.service.dto.CreateAndUpdateItemDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
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
    @Operation(
            operationId = "getAllItems",
            summary = "Display all items",
            description = "Allows a member or the admin to see all available items, with all the information."
    )
    public Response getAllItems() {
        return Response.status(OK).entity(itemService.getAllItems()).build();
    }

    @POST
    @Path("/addItem")
    @RolesAllowed("admin")
    @Operation(
            operationId = "addItem",
            summary = "Create an item",
            description = "Allows the admin to add a new item in the database."
    )
    public Response addItem(CreateAndUpdateItemDTO createItemDTO) {
        return Response.status(CREATED).entity(itemService.addItem(createItemDTO)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    @Operation(
            operationId = "updateItem",
            summary = "Update an item",
            description = "Allows the admin to update an item, based on its ID."
    )
    public Response updateItem(@PathParam("id") Long id, CreateAndUpdateItemDTO updateItemDTO) {
        return Response.status(CREATED).entity(itemService.updateItem(id, updateItemDTO)).build();
    }

    @ServerExceptionMapper(NoItemFoundException.class)
    protected Response noItemFoundException(NoItemFoundException exception) {
        return Response.status(NOT_FOUND).entity(exception.getMessage()).build();
    }
}
