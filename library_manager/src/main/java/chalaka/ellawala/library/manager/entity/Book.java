package chalaka.ellawala.library.manager.entity;

import chalaka.ellawala.library.manager.enums.AvailabilityStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(length = 50, nullable = false, unique = true)
    private String isbn;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "availability_status", length = 20)
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "updated_on")
    private LocalDateTime updatedOn = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "borrower_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_book_borrower"))
    private AppMember borrower;
}
