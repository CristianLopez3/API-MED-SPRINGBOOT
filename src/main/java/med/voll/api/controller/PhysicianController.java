package med.voll.api.controller;

import med.voll.api.physician.DataRegisterPhysician;
import med.voll.api.physician.Physician;
import med.voll.api.physician.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
@RequestMapping("/medico")
public class PhysicianController {

    @Autowired
    private PhysicianRepository physicianRepository;
    @PostMapping
    public void registerPhysician(@RequestBody DataRegisterPhysician dataRegisterPhysician){
        physicianRepository.save(new Physician((dataRegisterPhysician)));
    }

}
