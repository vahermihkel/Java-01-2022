package ee.mihkel.webshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

// Nõuandja Controllerile
@ControllerAdvice
public class ErrorHandler {

    // Siin püüan kinni Exceptioneid
    @ExceptionHandler                               // Siin määratlen millist Exceptionit
    public ResponseEntity<ErrorResponse> handleException(ProductNotFoundException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage("Toodet ei leitud");
        response.setDate(new Date());
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler                               // Siin määratlen millist Exceptionit
    public ResponseEntity<ErrorResponse> handleException(ArrayIndexOutOfBoundsException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage("Listi piiridest väljas");
        response.setDate(new Date());
        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
