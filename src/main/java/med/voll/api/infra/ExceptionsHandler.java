package med.voll.api.infra;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * La clase lanzador de excepciones nos permite controlar mucho mas el comportamiento
 * de nuestro sistema ya que nos permite validar que tipo de error queremos enviar a
 * nuestro servidor.
 */
// actua como una especie de proxy entre nuestras conexiones -> programación orientada a aspectos
@RestControllerAdvice
public class ExceptionsHandler {

    /**
     * Cuando nuestro sistema registre la excepcion la tratara con el metodo
     * que nosotros le proporcionamos
     * @return
     */

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tryError404(){
        return ResponseEntity.notFound().build();
    }

}
