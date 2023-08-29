package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.physician.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<DataListPhysician> listPhysician(@PageableDefault(size = 2, sort = "name") Pageable pagination) {
        return physicianRepository.findAll(pagination).map(DataListPhysician::new);
    }

    @PutMapping
    @Transactional // method to update, is necessary start a transaction
    public void updatePhysician(@RequestBody @Valid DataUpdatePhysician dataUpdatePhysician){
        Physician physician = physicianRepository.getReferenceById(dataUpdatePhysician.id());
        physician.updateData(dataUpdatePhysician);
    }

}
