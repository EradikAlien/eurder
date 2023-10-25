package be.switchfully.user.service.dto;

public class UserDTO {
    private final String id;
    private final String firstname;
    private final String lastname;
    private final String emailAddress;
    private final String address;
    private final String phoneNumber;

    public UserDTO(String id, String firstname, String lastname, String emailAddress, String address, String phoneNumber) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
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

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
