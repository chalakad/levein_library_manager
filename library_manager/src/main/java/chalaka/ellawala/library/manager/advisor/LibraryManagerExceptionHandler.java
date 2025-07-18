package chalaka.ellawala.library.manager.advisor;

import chalaka.ellawala.library.manager.enums.LibraryManagerExceptionMessages;
import chalaka.ellawala.library.manager.enums.ResponseStatuses;
import chalaka.ellawala.library.manager.exception.LibraryManagerException;
import chalaka.ellawala.library.manager.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@ControllerAdvice
public class LibraryManagerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(LibraryManagerExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleValidationErrors(MethodArgumentNotValidException ex) {

        log.error("MethodArgumentNotValidException occurred: {}", ex.getMessage());
        StringBuilder errors = new StringBuilder();
        int errorCount = ex.getBindingResult().getFieldErrors().size();
        int currentError = 1;
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errors.append(error.getField() + ": " + error.getDefaultMessage());
            if (currentError < errorCount) {
                errors.append(" ").append(System.lineSeparator());
            }
        }
        );


        return ResponseEntity.badRequest().body(new BaseResponse(ResponseStatuses.FAILED, LibraryManagerExceptionMessages.INVALID_REQUEST.getErrorCode(), LibraryManagerExceptionMessages.INVALID_REQUEST.getMessage(), errors.toString(), LocalDateTime.now().toString(), null));
    }

    @ExceptionHandler(LibraryManagerException.class)
    public ResponseEntity<BaseResponse> handleCustomAppException(LibraryManagerException ex) {
        log.error("LibraryManagerException occurred: errorCode: {} , errorMessage: {}", ex.getErrorCode(), ex.getMessage());
        return ResponseEntity.badRequest().body(new BaseResponse(ResponseStatuses.FAILED, ex.getErrorCode(), ex.getMessage(), ex.getMessage(), LocalDateTime.now().toString(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleGeneralError(Exception ex) {
        log.error("Unexpected error occurred: {}", ex.getMessage());
        return ResponseEntity.internalServerError().body(new BaseResponse(ResponseStatuses.FAILED, LibraryManagerExceptionMessages.UNEXPECTED_ERROR.getErrorCode(), LibraryManagerExceptionMessages.UNEXPECTED_ERROR.getMessage(), ex.getMessage(), LocalDateTime.now().toString(), null));
    }
}
