package med.voll.api.domain.physician;

import med.voll.api.domain.address.AddressData;

public record ShowDataPhysician(Long id, String name, String document, String email, Specialty specialty, AddressData address) {
}
