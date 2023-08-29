package med.voll.api.physician;

public record DataListPhysician(Long id, String name, String specialty, String document, String email) {

    public DataListPhysician(Physician physician){
        this(
                physician.getId(),
                physician.getName(),
                physician.getSpecialty().toString(),
                physician.getDocument(),
                physician.getEmail()

        );

    }


}
