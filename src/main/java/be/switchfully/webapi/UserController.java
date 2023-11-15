package be.switchfully.webapi;

import be.switchfully.order.service.OrderService;
import be.switchfully.order.service.dto.CreateOrderDTO;
import be.switchfully.user.service.UserService;
import be.switchfully.user.service.dto.CreateUserDTO;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import java.util.UUID;

import static jakarta.ws.rs.core.Response.Status.*;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    UserService userService;
    OrderService orderService;

    public UserController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GET
    @RolesAllowed("admin")
    public Response getAllUsers() {
        return Response.status(OK).entity(userService.getAllUsers()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response getUserById(@PathParam("id") UUID id) {
        return Response.status(OK).entity(userService.getUserById(id)).build();
    }

    @GET
    @Path("/myOrder")
    @RolesAllowed("member")
    public Response getAllOrders() {
        return Response.status(OK).entity(orderService.getAllOrders()).build();
    }

    @POST
    @Path("/register")
    @PermitAll
    public Response addUser(CreateUserDTO createUserDTO) {
        return Response.status(CREATED).entity(userService.addUser(createUserDTO)).build();
    }

    @POST
    @Path("/order")
    @RolesAllowed("member")
    public Response addOrder(CreateOrderDTO createOrderDTO) {
        return Response.status(CREATED).entity(orderService.addOrder(createOrderDTO)).build();
    }

    @POST
    @Path("/reorder/{id}")
    @RolesAllowed("member")
    public Response addReOrder(@PathParam("id") Long id) {
        return Response.status(CREATED).entity(orderService.addReOrder(id)).build();
    }

    @ServerExceptionMapper(NotFoundException.class)
    protected Response notFoundException(NotFoundException exception) {
        return Response.status(NOT_FOUND).entity(exception.getMessage()).build();
    }

}
