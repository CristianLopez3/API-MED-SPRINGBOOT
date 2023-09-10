package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.address2.AddressData;
import med.voll.api.domain.physician.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
    private PhysicianRepository2 physicianRepository;


    /**
     * Retornamos el código 201 - created, el cual nos retorna la url en la cual podemos
     * encontrar el médico que acabamos de insertaar
     * @param dataRegisterPhysician
     * @param uriComponentsBuilder
     * @return
     */

    @PostMapping
    public ResponseEntity<ShowDataPhysician> registerPhysician(@RequestBody @Valid DataRegisterPhysician dataRegisterPhysician
    , UriComponentsBuilder uriComponentsBuilder){
        Physician physician = physicianRepository.save(new Physician((dataRegisterPhysician)));
        // GET http://localhost:8080/medico/{id}
        ShowDataPhysician showDataPhysician = new ShowDataPhysician(
                physician.getId(),
                physician.getName(),
                physician.getDocument(),
                physician.getEmail(),
                physician.getSpecialty(),

                new AddressData(
                        physician.getAddress().getStreet(),
                        physician.getAddress().getDistrict(),
                        physician.getAddress().getCity(),
                        physician.getAddress().getNumber(),
                        physician.getAddress().getComplement()
                )
        );
        // URI uri = "http://localhost:8080/medico/"+physician.getId();
        URI url = uriComponentsBuilder.path("/medico/{id}").buildAndExpand(physician.getId()).toUri();
        return ResponseEntity.created(url).body(showDataPhysician);
    }

    @GetMapping
    public Page<DataListPhysician> listPhysician(@PageableDefault/*(size = 2, sort = "name")*/ Pageable pagination) {
        //return physicianRepository.findAll(pagination).map(DataListPhysician::new);
        return physicianRepository.findByActiveTrue(pagination).map(DataListPhysician::new);
    }


    @PutMapping
    @Transactional // method to update, is necessary start a transaction
    public ResponseEntity<ShowDataPhysician> updatePhysician(@RequestBody @Valid DataUpdatePhysician dataUpdatePhysician){
        Physician physician = physicianRepository.getReferenceById(dataUpdatePhysician.id());
        physician.updateData(dataUpdatePhysician);
        return ResponseEntity.ok(new ShowDataPhysician(
                physician.getId(),
                physician.getName(),
                physician.getDocument(),
                physician.getEmail(),
                physician.getSpecialty(),

                new AddressData(
                            physician.getAddress().getStreet(),
                            physician.getAddress().getDistrict(),
                            physician.getAddress().getCity(),
                            physician.getAddress().getNumber(),
                            physician.getAddress().getComplement()
                        )

                ));

    }


    /**
     * Delete a nivel Logico
     * No retornara contenido, para eso usamos la clase de spring
     * ResponseEntity en donde le decimos que no retorne nada
     * @param id
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePhysician(@PathVariable Long id){
        Physician physician = physicianRepository.getReferenceById(id);
        physician.disablePhysician();
        return ResponseEntity.noContent().build();
    }


    /**
     * Retornamos los datos del nuevo médico ingresado por metodo post y devolvemos el
     * codigo 200 de operación correcta ok.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ShowDataPhysician> returnDataPhysician(@PathVariable  Long id){
        Physician physician = physicianRepository.getReferenceById(id);
        var dataPhysician = new ShowDataPhysician(
                physician.getId(),
                physician.getName(),
                physician.getDocument(),
                physician.getEmail(),
                physician.getSpecialty(),

                new AddressData(
                        physician.getAddress().getStreet(),
                        physician.getAddress().getDistrict(),
                        physician.getAddress().getCity(),
                        physician.getAddress().getNumber(),
                        physician.getAddress().getComplement()
                )
        );
        return  ResponseEntity.ok(dataPhysician);
    }


    /**
     * Delete a  nivel de base de datos
     @DeleteMapping("/{id}")
     @Transactional
     public void deletePhysician(@PathVariable Long id){
     Physician physician = physicianRepository.getReferenceById(id);
     physicianRepository.delete(physician);

     }
     */


    /*
    Otra forma de restringir el acceso a ciertas funciones, según el perfil del usuario, es usar
    una función de Spring Security conocida como Method Security, que funciona con el uso de anotaciones en los métodos:

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity detallar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalladoMedico(medico));
    }
     */


}
