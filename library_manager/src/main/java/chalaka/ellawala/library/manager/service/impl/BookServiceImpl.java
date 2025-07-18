package chalaka.ellawala.library.manager.service.impl;

import chalaka.ellawala.library.manager.entity.AppMember;
import chalaka.ellawala.library.manager.entity.Book;
import chalaka.ellawala.library.manager.enums.AvailabilityStatus;
import chalaka.ellawala.library.manager.enums.LibraryManagerExceptionMessages;
import chalaka.ellawala.library.manager.enums.Role;
import chalaka.ellawala.library.manager.exception.LibraryManagerException;
import chalaka.ellawala.library.manager.repository.AppMemberRepository;
import chalaka.ellawala.library.manager.repository.BookRepository;
import chalaka.ellawala.library.manager.request.BorrowBookRequest;
import chalaka.ellawala.library.manager.request.DeleteBookRequest;
import chalaka.ellawala.library.manager.request.GetBooksBorrowedByMemberRequest;
import chalaka.ellawala.library.manager.request.ReturnBookRequest;
import chalaka.ellawala.library.manager.response.BookDto;
import chalaka.ellawala.library.manager.response.BookResponse;
import chalaka.ellawala.library.manager.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AppMemberRepository appMemberRepository;

    @Override
    public void borrowBook(BorrowBookRequest request) throws LibraryManagerException, Exception {
        try {
            Book book = bookRepository.findById(Long.parseLong(request.getBookId()))
                    .orElseThrow(() -> new LibraryManagerException(LibraryManagerExceptionMessages.BOOK_NOT_FOUND));

            if (!book.getAvailabilityStatus().equals(AvailabilityStatus.AVAILABLE)) {
                throw new LibraryManagerException(LibraryManagerExceptionMessages.BOOK_NOT_AVAILABLE);
            }

            AppMember member = appMemberRepository.findById(Long.parseLong(request.getMemberId()))
                    .orElseThrow(() -> new LibraryManagerException(LibraryManagerExceptionMessages.MEMBER_NOT_FOUND));

            book.setBorrower(member);
            book.setAvailabilityStatus(AvailabilityStatus.BORROWED);
            bookRepository.save(book);
        } catch (LibraryManagerException ex) {
            log.error("error in BookService.borrowBook: {}", ex);
            throw ex;
        }catch (Exception ex) {
            log.error("error in BookService.borrowBook: {}", ex);
            throw ex;
        }
    }

    @Override
    public void returnBook(ReturnBookRequest request) throws LibraryManagerException, Exception {
        try {
            Book book = bookRepository.findById(Long.parseLong(request.getBookId()))
                    .orElseThrow(() -> new LibraryManagerException(LibraryManagerExceptionMessages.BOOK_NOT_FOUND));

            if (!book.getAvailabilityStatus().equals(AvailabilityStatus.BORROWED)) {
                throw new LibraryManagerException(LibraryManagerExceptionMessages.INVALID_BOOK_AVAILABILITY);
            }

            if (!book.getBorrower().getId().equals(Long.parseLong(request.getMemberId()))) {
                throw new LibraryManagerException(LibraryManagerExceptionMessages.INVALID_BORROWER_DETAILS);
            }

            book.setBorrower(null);
            book.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
            bookRepository.save(book);
        } catch (LibraryManagerException ex) {
            log.error("error in BookService.returnBook: {}", ex);
            throw ex;
        }catch (Exception ex) {
            log.error("error in BookService.returnBook: {}", ex);
            throw ex;
        }
    }

    public void deleteBook(DeleteBookRequest request) throws LibraryManagerException, Exception {
        try {
            Book book = bookRepository.findById(Long.parseLong(request.getBookId()))
                    .orElseThrow(() -> new LibraryManagerException(
                            LibraryManagerExceptionMessages.BOOK_NOT_FOUND));
            bookRepository.delete(book);
        } catch (LibraryManagerException ex) {
            log.error("error in BookService.deleteBook: {}", ex);
            throw ex;
        }catch (Exception ex) {
            log.error("error in BookService.deleteBook: {}", ex);
            throw ex;
        }
    }

    public BookResponse getBooksBorrowedByMember(GetBooksBorrowedByMemberRequest request) throws LibraryManagerException, Exception {
        try {
            Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
            Page<BookDto> books = bookRepository.findByBorrowerId(Long.parseLong(request.getMemberId()), pageable)
                    .map(book -> BookDto.builder()
                            .title(book.getTitle())
                            .author(book.getAuthor())
                            .build()
                    );
            return BookResponse.builder()
                    .books(books)
                    .build();
        } catch (LibraryManagerException ex) {
            log.error("error in BookService.getBooksBorrowedByMember: {}", ex);
            throw ex;
        }catch (Exception ex) {
            log.error("error in BookService.getBooksBorrowedByMember: {}", ex);
            throw ex;
        }
    }
}
