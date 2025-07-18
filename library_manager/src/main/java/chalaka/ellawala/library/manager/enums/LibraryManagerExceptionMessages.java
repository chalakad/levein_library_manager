package chalaka.ellawala.library.manager.enums;

import lombok.Getter;

@Getter
public enum LibraryManagerExceptionMessages {


    UNEXPECTED_ERROR("LBM000", "Unexpected Error Occured"),
    INVALID_REQUEST("LBM001", "Invalid Request"),
    MEMBER_NOT_FOUND("LBM002", "Member not found"),
    BOOK_NOT_FOUND("LBM003", "Book not found"),
    BOOK_NOT_AVAILABLE("LBM004", "Book is not available"),
    INVALID_BOOK_AVAILABILITY("LBM005", "Invalid book availability"),
    INVALID_BORROWER_DETAILS("LBM006", "Invalid borrower details"),;

    private String errorCode;
    private String message;

    LibraryManagerExceptionMessages(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
