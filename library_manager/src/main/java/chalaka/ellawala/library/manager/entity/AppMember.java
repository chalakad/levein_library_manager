package chalaka.ellawala.library.manager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "app_member")
@Getter
@Setter
public class AppMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "updated_on")
    private LocalDateTime updatedOn = LocalDateTime.now();

//    @OneToMany(mappedBy = "borrower", fetch = FetchType.LAZY)
//    private List<Book> borrowedBooks;
}
