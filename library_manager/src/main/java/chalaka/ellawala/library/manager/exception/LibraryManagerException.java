package chalaka.ellawala.library.manager.exception;

import chalaka.ellawala.library.manager.enums.LibraryManagerExceptionMessages;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibraryManagerException extends RuntimeException {

    private String errorCode;

    public LibraryManagerException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public LibraryManagerException(LibraryManagerExceptionMessages msg) {
        super(msg.getMessage());
        this.errorCode = msg.getErrorCode();
    }
}
