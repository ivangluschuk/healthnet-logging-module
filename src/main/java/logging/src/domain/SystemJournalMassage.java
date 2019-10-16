package logging.src.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "system_massage")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SystemJournalMassage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @OneToOne(
            targetEntity = User.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "usr_id", referencedColumnName = "id")
    User user;

    @NonNull
    @Column(name = "massage")
    private String massage;
}
