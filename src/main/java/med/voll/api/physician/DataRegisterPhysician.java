package med.voll.api.physician;


import med.voll.api.address.AddressData;

public record DataRegisterPhysician(String name, String email, String document, Specialty especialidad, AddressData address) {



}
