package med.voll.api.physician;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * El primer dato del generic es la entidad con la que vamos a trabajar
 * El segundo es el tipo de dato que es el id del tipo de dato
 */
public interface PhysicianRepository extends JpaRepository<Physician, Long> {

}
