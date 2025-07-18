package chalaka.ellawala.library.manager.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Schema(description = "Request object for borrowing a book")
public class BorrowBookRequest implements Serializable {

    @NotBlank
    @Schema(description = "ID of the book to be borrowed", example = "1")
    String bookId;

    @NotBlank
    @Schema(description = "ID of the member who is borrowing the book", example = "2")
    String memberId;
}
