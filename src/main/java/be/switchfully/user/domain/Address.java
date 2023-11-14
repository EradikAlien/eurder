package be.switchfully.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public final class Address {
    @Column(name = "STREET_NAME")
    private String streetName;
    @Column(name = "STREET_NUMBER")
    private String streetNumber;
    @Column(name = "CODE")
    private String code;
    @Column(name = "LABEL")
    private String label;

    public Address() {
    }

    public Address(String streetName, String streetNumber, String code, String label) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.code = code;
        this.label = label;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}
