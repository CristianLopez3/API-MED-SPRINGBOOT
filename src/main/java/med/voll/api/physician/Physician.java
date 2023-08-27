package med.voll.api.physician;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

@Entity(name="Physician")
@Table(name="medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Physician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String document;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;


    public Physician(DataRegisterPhysician dataRegisterPhysician) {
        this.name = dataRegisterPhysician.name();
        this.email = dataRegisterPhysician.email();
        this.document = dataRegisterPhysician.document();
        this.specialty = dataRegisterPhysician.specialty();
        this.address = new Address(dataRegisterPhysician.address());
    }
}
