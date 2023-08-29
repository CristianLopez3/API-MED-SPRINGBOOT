package med.voll.api.physician;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressData;

public record DataUpdatePhysician(@NotNull Long id, String name, String document, AddressData addressData) {

}
