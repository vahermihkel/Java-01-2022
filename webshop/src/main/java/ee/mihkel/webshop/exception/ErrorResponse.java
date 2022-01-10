package ee.mihkel.webshop.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

// Getterid, Setterid ja ühe NoArgsConstructori
@Data
public class ErrorResponse {
    private String message;
    private Date date;
    private HttpStatus httpStatus;
}
