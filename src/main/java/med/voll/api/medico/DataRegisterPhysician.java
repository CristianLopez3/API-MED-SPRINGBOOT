package med.voll.api.medico;


import med.voll.api.AddressData;

public record DataRegisterPhysician(String name, String email, String document, Especialty especialidad, AddressData addressData) {



}
