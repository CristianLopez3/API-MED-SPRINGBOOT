package med.voll.api.domain.physician;

import med.voll.api.domain.address2.AddressData;

public record ShowDataPhysician(Long id, String name, String document, String email, Specialty specialty, AddressData address) {
}
