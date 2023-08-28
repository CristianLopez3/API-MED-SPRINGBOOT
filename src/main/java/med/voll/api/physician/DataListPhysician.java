package med.voll.api.physician;

public record DataListPhysician(String name, String specialty, String document, String email) {

    public DataListPhysician(Physician physician){
        this(
                physician.getName(),
                physician.getSpecialty().toString(),
                physician.getDocument(),
                physician.getEmail()

        );

    }


}
