package med.voll.api.domain.physician;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address2.AddressData;
import org.hibernate.validator.constraints.Email;

public record DataRegisterPhysician(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,10}")
        String document,
        @NotBlank
        String cellphone,
        @NotNull
        @Valid
        Specialty specialty,
        @NotNull
        @Valid
        AddressData address
)

{



}
