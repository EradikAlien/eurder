package be.switchfully.webapi;

import be.switchfully.user.service.UserService;
import be.switchfully.user.service.dto.CreateUserDTO;
import be.switchfully.user.service.dto.UserDTO;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.CREATED;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @POST
    @Path("/register")
    @Transactional
    public Response addUser(CreateUserDTO createUserDTO) {
        return Response.status(CREATED).entity(userService.addUser(createUserDTO)).build();
    }
}
