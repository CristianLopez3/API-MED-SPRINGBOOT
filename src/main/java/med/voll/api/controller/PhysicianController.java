package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.physician.DataListPhysician;
import med.voll.api.physician.DataRegisterPhysician;
import med.voll.api.physician.Physician;
import med.voll.api.physician.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class PhysicianController {

    /**
     * La interfaz physician repository nos permite hacer la gestión del crud en nuestra base
     * de datos, esto debido a que esta interfaz extiende de jpaRepository, la cuál  nos permite
     * guardar los datos en la base de datos. Aunque no es recomendable usar autowired debido a que
     * a la hora de hacer testing nos va a causar errores.
     */
    @Autowired
    private PhysicianRepository physicianRepository;
    @PostMapping
    public void registerPhysician(@RequestBody @Valid DataRegisterPhysician dataRegisterPhysician){
        physicianRepository.save(new Physician((dataRegisterPhysician)));
    }

    @GetMapping
    public Page<DataListPhysician> listPhysician(Pageable pagination) {
        return physicianRepository.findAll(pagination).map(DataListPhysician::new);
    }

}
