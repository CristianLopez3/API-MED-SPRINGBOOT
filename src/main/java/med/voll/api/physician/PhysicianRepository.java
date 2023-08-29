package med.voll.api.physician;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * El primer dato del generic es la entidad con la que vamos a trabajar
 * El segundo es el tipo de dato que es el id del tipo de dato
 */
public interface PhysicianRepository extends JpaRepository<Physician, Long> {

    Page<Physician> findByActiveTrue(Pageable pagination);
}
