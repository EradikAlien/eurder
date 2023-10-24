package be.switchfully;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;


/**
 * Example JPA entity defined as a Panache Entity.
 * An ID field of Long type is provided, if you want to define your own ID field extends <code>PanacheEntityBase</code> instead.
 * <p>
 * This uses the active record pattern, you can also use the repository pattern instead:
 * .
 * <p>
 * Usage (more example on the documentation)
 * <p>
 * {@code
 * public void doSomething() {
 * MyEntity entity1 = new MyEntity();
 * entity1.field = "field-1";
 * entity1.persist();
 * <p>
 * List<MyEntity> entities = MyEntity.listAll();
 * }
 * }
 */
@Entity
public class Customer {
    @Id
    private String id;
    public String firstname;
    public String lastname;
    public String emailAddress;
    public String address;
    public String phoneNumber;

    public Customer(String firstname, String lastname, String emailAddress, String address, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {

    }

    public String getId() {
        return id;
    }

}
