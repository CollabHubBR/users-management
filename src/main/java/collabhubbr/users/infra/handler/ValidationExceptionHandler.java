package collabhubbr.users.infra.handler;

import collabhubbr.users.exceptions.CredentialsExceptions;
import collabhubbr.users.exceptions.EmailDuplicateException;
import collabhubbr.users.exceptions.TokenException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Log4j2
@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(CredentialsExceptions.class)
    protected ResponseEntity<MessageError> handleCredentialsException(
            CredentialsExceptions ex) {
        log.error("Credentials exception: {}", ex.getMessage());
        return ResponseEntity.status(401).body(
                new MessageError(ex.getMessage(), HttpStatus.FORBIDDEN)
        );
    }

    @ExceptionHandler(EmailDuplicateException.class)
    protected ResponseEntity<MessageError> handleEmailDuplicateException(
            EmailDuplicateException ex) {
        log.error("Email duplicate exception: {}", ex.getMessage());
        return ResponseEntity.status(409).body(
                new MessageError(ex.getMessage(), HttpStatus.CONFLICT)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<List<DataException>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors()
                .stream()
                .map(DataException::new)
                .toList();
        log.warn("Data request exception");

        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(TokenException.class)
    protected ResponseEntity<MessageError> handleTokenException(
            TokenException ex) {
        log.error("Token exception: {}", ex.getMessage());
        return ResponseEntity.status(401).body(
                new MessageError(ex.getMessage(), HttpStatus.UNAUTHORIZED)
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<MessageError> handleEntityNotFoundException(
            EntityNotFoundException ex) {
        log.error("Entity not found exception: {}", ex.getMessage());
        return ResponseEntity.status(404).body(
                new MessageError(ex.getMessage(), HttpStatus.NOT_FOUND)
        );
    }

    private record MessageError(String message, HttpStatus status) {}

    private record DataException(String label, String message) {
        DataException(FieldError error) {
            this(
                    error.getField(),
                    error.getDefaultMessage()
            );
        }
    }
}
