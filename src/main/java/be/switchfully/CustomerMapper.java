package be.switchfully;

public class CustomerMapper {
    public static CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstname(customer.firstname);
        customerDTO.setLastname(customer.lastname);
        customerDTO.setEmailAddress(customer.emailAddress);
        customerDTO.setAddress(customer.address);
        customerDTO.setPhoneNumber(customer.phoneNumber);

        return customerDTO;
    }

    public static Customer mapToEntity(CreateCustomerDTO createCustomerDTO) {
        return new Customer(createCustomerDTO.getFirstname(),
                createCustomerDTO.getLastname(),
                createCustomerDTO.getEmailAddress(),
                createCustomerDTO.getAddress(),
                createCustomerDTO.getPhoneNumber());
    }
}
