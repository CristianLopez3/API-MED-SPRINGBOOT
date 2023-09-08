package med.voll.api.infra.error;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tryError400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(DataErrorValidation::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    private record DataErrorValidation(String field, String error){
        public DataErrorValidation(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }


}
