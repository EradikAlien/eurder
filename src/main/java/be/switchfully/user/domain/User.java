package be.switchfully.user.domain;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "test_user")
@UserDefinition
public class User {
    @Id
    @Column(name = "USER_ID")
    private UUID id;
    @Roles
    @Column(name = "ROLE")
    private String role;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Username
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Password
    @Column(name = "PASSWORD")
    private String password;
    @Embedded
    private Address address;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    private User(String firstname, String lastname, String emailAddress, String password, Address address, String phoneNumber) {
        this.id = UUID.randomUUID();
        this.role = "member";
        this.firstname = firstname;
        this.lastname = lastname;
        if (!isValid(emailAddress)) {
            throw new IllegalArgumentException(emailAddress + " is not a valid e-mail");
        } else {
            this.emailAddress = emailAddress;
        }
        this.password = BcryptUtil.bcryptHash(password);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public static User createMember(String firstname, String lastname, String emailAddress, String password, Address address, String phoneNumber) {
        return new User(firstname, lastname, emailAddress, password, address, phoneNumber);
    }

    private User(String emailAddress, String password) {
        this.id = UUID.randomUUID();
        this.emailAddress = emailAddress;
        this.password = BcryptUtil.bcryptHash(password);
        this.role = "admin";
    }

    public static User createAdmin(String emailAddress, String password) {
        return new User(emailAddress, password);
    }

    protected User() {

    }

    public UUID getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
