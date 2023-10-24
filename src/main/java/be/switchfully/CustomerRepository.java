package be.switchfully;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    public List<Customer> getAll() {
        return listAll();
    }

    public void addCustomer(Customer customer) {
        persist(customer);
    }
}
