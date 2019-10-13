package logging.src.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "system_massage")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class SystemJournalMassage {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @NonNull
    @OneToOne(
            targetEntity = User.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "usr_id", referencedColumnName = "id")
    User user;

    @Getter
    @Setter
    @NonNull
    @Column(name = "massage")
    private String massage;
}
