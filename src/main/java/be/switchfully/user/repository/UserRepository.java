package be.switchfully.user.repository;

import be.switchfully.user.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public List<User> getAll() {
        return listAll();
    }

    public void addUser(User user) {
        persist(user);
    }
}
