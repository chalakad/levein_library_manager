package chalaka.ellawala.library.manager.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Schema(description = "Request object for borrowing a book")
public class GetBooksBorrowedByMemberRequest implements Serializable {
    @NotBlank
    @Schema(description = "ID of the member who is borrowing the book", example = "2")
    String memberId;
    @NotNull
    @Schema(description = "Number of the page", example = "0")
    private Integer page = 0;
    @NotNull
    @Schema(description = "Size of the page", example = "0")
    private Integer size = 10;
}
