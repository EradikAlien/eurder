package be.switchfully;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CustomerService {
    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getAll() {
        return customerRepository.getAll().stream().map(CustomerMapper::mapToDTO).collect(Collectors.toList());
    }

    public String addCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customerToCreate = CustomerMapper.mapToEntity(createCustomerDTO);
        customerRepository.addCustomer(customerToCreate);
        return customerToCreate.getId();
    }
}
