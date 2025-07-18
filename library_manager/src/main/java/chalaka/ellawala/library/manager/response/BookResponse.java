package chalaka.ellawala.library.manager.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@Builder
public class BookResponse {
    Page<BookDto> books;
}
