package be.switchfully.user.repository;

import be.switchfully.user.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public Optional<User> getByEmailAddress(String emailAddress) {
        return find("emailAddress", emailAddress).firstResultOptional();
    }

    public Optional<User> findByUUIDOptionnal(UUID id) {
        return find("id", id).stream().findFirst();
    }
}
