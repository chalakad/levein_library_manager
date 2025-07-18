package chalaka.ellawala.library.manager.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookDto {
    private String title;
    private String author;
}
