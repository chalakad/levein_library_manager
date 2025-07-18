package chalaka.ellawala.library.manager.service;

import chalaka.ellawala.library.manager.exception.LibraryManagerException;
import chalaka.ellawala.library.manager.request.BorrowBookRequest;
import chalaka.ellawala.library.manager.request.DeleteBookRequest;
import chalaka.ellawala.library.manager.request.GetBooksBorrowedByMemberRequest;
import chalaka.ellawala.library.manager.request.ReturnBookRequest;
import chalaka.ellawala.library.manager.response.BookResponse;

public interface BookService {
    public void borrowBook(BorrowBookRequest request) throws LibraryManagerException, Exception;
    public void returnBook(ReturnBookRequest request) throws LibraryManagerException, Exception;
    public void deleteBook(DeleteBookRequest request) throws LibraryManagerException, Exception;
    public BookResponse getBooksBorrowedByMember(GetBooksBorrowedByMemberRequest request) throws LibraryManagerException, Exception;
}
