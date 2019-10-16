package logging.src.repository;

import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import logging.src.domain.SystemJournalMassage;

import java.util.List;

@Repository
public interface SystemJournalMassageRepository extends JpaRepository<SystemJournalMassage, Long> {

    List<SystemJournalMassage> findByUserId(@NonNull final Long id, @NonNull Pageable pageable);
}
