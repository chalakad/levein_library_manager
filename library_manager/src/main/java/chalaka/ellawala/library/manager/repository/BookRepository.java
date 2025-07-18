package chalaka.ellawala.library.manager.repository;

import chalaka.ellawala.library.manager.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByBorrowerId(Long borrowerId, Pageable pageable);
}
