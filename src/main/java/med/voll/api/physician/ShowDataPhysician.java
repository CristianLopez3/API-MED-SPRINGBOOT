package med.voll.api.physician;

import med.voll.api.address.AddressData;

public record ShowDataPhysician(Long id, String name, String document, String email, Specialty specialty, AddressData address) {
}
