package med.voll.api.controller;

import med.voll.api.physician.DataRegisterPhysician;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
@RequestMapping("/medico")
public class PhysicianController {

    @PostMapping
    public void registerPhysician(@RequestBody DataRegisterPhysician dataRegisterPhysician){
        System.out.println(dataRegisterPhysician);
    }

}
