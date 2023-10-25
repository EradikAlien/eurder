package be.switchfully.user.service.dto;

public class CreateUserDTO {
    private String firstname;
    private String lastname;
    private String emailAddress;
    private String address;
    private String phoneNumber;

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
