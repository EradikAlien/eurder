package be.switchfully;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/eurder")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EurderController {

    CustomerService customerService;

    public EurderController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GET
    public List<CustomerDTO> getAll() {
        return customerService.getAll();
    }

    @POST
    @Transactional
    public Response addCustomer(CreateCustomerDTO createCustomerDTO) {
        customerService.addCustomer(createCustomerDTO);
        return Response.status(Response.Status.CREATED).entity(createCustomerDTO).build();
    }
}
