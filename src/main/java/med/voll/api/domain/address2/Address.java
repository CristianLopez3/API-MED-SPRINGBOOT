package med.voll.api.domain.address2;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String street;
    private String district;
    private String city;
    private String number;
    private String complement;

    public Address(AddressData address) {
        this.street = address.street();
        this.district = address.district();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
    }

    public Address updateData(AddressData address) {
        this.street = address.street();
        this.district = address.district();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
        return this;
    }
}
