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
    private String cellphone;
    private Boolean active;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;


    public Physician(DataRegisterPhysician dataRegisterPhysician) {
        this.active = true;
        this.name = dataRegisterPhysician.name();
        this.email = dataRegisterPhysician.email();
        this.document = dataRegisterPhysician.document();
        this.cellphone = dataRegisterPhysician.cellphone();
        this.specialty = dataRegisterPhysician.specialty();
        this.address = new Address(dataRegisterPhysician.address());
    }

    public void updateData(DataUpdatePhysician dataUpdatePhysician) {
        if(dataUpdatePhysician.name() != null){
            this.name = dataUpdatePhysician.name();
        }
        if(dataUpdatePhysician.document() != null){
            this.document = dataUpdatePhysician.document();
        }
        if(dataUpdatePhysician.addressData() != null){
            this.address = address.updateData(dataUpdatePhysician.addressData());
        }
    }

    public void disablePhysician() {
        this.active = false;
    }

}
