package logging.src.service;

import logging.src.domain.SystemJournal;
import logging.src.domain.SystemJournalMassage;
import logging.src.domain.User;
import logging.src.repository.SystemJournalMassageRepository;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SystemJournalService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final SystemJournalMassageRepository systemJournalMassageRepository;

    public SystemJournalService(@NonNull SystemJournalMassageRepository systemJournalMassageRepository) {
        this.systemJournalMassageRepository = systemJournalMassageRepository;
    }

    public SystemJournal getUserSystemJournal(@NonNull final Long id) {
        final var massages = systemJournalMassageRepository.findByUserId(id);
        logger.info("The system journal found for the user with id: " + id);

        return new SystemJournal(massages);
    }

    public SystemJournal getSystemJournal() {
        final var massages = systemJournalMassageRepository.findAll();

        return new SystemJournal(massages);
    }

    @Transactional
    public void writeMassage(@NonNull User user, @NonNull final String massage) {
        systemJournalMassageRepository.save(new SystemJournalMassage(user, massage));
    }
}
