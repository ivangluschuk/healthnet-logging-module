package logging.src.service;

import logging.src.domain.SystemJournal;
import logging.src.domain.SystemJournalMassage;
import logging.src.domain.User;
import logging.src.repository.OffsetBasedPageRequest;
import logging.src.repository.SystemJournalMassageRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class SystemJournalService {

    private final SystemJournalMassageRepository systemJournalMassageRepository;

    public SystemJournalService(@NonNull SystemJournalMassageRepository systemJournalMassageRepository) {
        this.systemJournalMassageRepository = systemJournalMassageRepository;
    }

    public SystemJournal getUserSystemJournal(@NonNull final Long id, int limit, int offset) {
        Pageable pageable = new OffsetBasedPageRequest(limit, offset);

        final var massages = systemJournalMassageRepository.findByUserId(id, pageable);
        log.info("The system journal found for the user id {} with limit {} and offset {}", id, limit, offset);

        return new SystemJournal(massages);
    }

    public SystemJournal getSystemJournal() {
        final var massages = systemJournalMassageRepository.findAll();

        return new SystemJournal(massages);
    }

    @Transactional
    public void writeMassage(@NonNull final User user, @NonNull final String massage) {
        systemJournalMassageRepository.save(new SystemJournalMassage(user, massage));
    }
}
