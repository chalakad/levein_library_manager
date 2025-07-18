package chalaka.ellawala.library.manager.controller;

import chalaka.ellawala.library.manager.entity.Book;
import chalaka.ellawala.library.manager.enums.ResponseStatuses;
import chalaka.ellawala.library.manager.exception.LibraryManagerException;
import chalaka.ellawala.library.manager.request.BorrowBookRequest;
import chalaka.ellawala.library.manager.request.DeleteBookRequest;
import chalaka.ellawala.library.manager.request.GetBooksBorrowedByMemberRequest;
import chalaka.ellawala.library.manager.request.ReturnBookRequest;
import chalaka.ellawala.library.manager.response.BaseResponse;
import chalaka.ellawala.library.manager.response.BookResponse;
import chalaka.ellawala.library.manager.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Controller", description = "Operations related to borrowing books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Operation(
            summary = "Borrow a book",
            description = "Assigns a book to a user based on the given JSON body."
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/borrow")
    public ResponseEntity<BaseResponse> borrowBook(@RequestBody @Valid BorrowBookRequest request) throws LibraryManagerException, Exception {
        bookService.borrowBook(request);
        return ResponseEntity.ok(new BaseResponse(ResponseStatuses.SUCCESS));
    }

    @Operation(
            summary = "Borrow a book",
            description = "Assigns a book to a user based on the given JSON body."
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/return")
    public ResponseEntity<BaseResponse> returnBook(@RequestBody @Valid ReturnBookRequest request) throws LibraryManagerException, Exception {
        bookService.returnBook(request);
        return ResponseEntity.ok(new BaseResponse(ResponseStatuses.SUCCESS));
    }

    @Operation(
            summary = "Delete a book",
            description = "Delete a book based on the book ID."
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete")
    public ResponseEntity<BaseResponse> borrowBook(@RequestBody @Valid DeleteBookRequest request) throws LibraryManagerException, Exception {
        bookService.deleteBook(request);
        return ResponseEntity.ok(new BaseResponse(ResponseStatuses.SUCCESS));
    }

    @Operation(
            summary = "Get books borrowed by a member",
            description = "Get a list of paginated books response by member id."
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/getBooksBorrowedByMember")
    public ResponseEntity<BaseResponse> getBooksBorrowedByMember(@RequestBody @Valid GetBooksBorrowedByMemberRequest request) throws LibraryManagerException, Exception {
        BookResponse bookResponse = bookService.getBooksBorrowedByMember(request);
        BaseResponse baseResponse = new BaseResponse(ResponseStatuses.SUCCESS);
        baseResponse.setResponseData(bookResponse);
        return ResponseEntity.ok(baseResponse);
    }

}
