package logging.src.service;

import logging.src.domain.User;
import logging.src.repository.UserRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserService {

    private final SystemJournalService systemJournalService;
    private final UserRepository userRepository;

    public UserService(@NonNull SystemJournalService systemJournalService,
                       @NonNull UserRepository userRepository) {
        this.systemJournalService = systemJournalService;
        this.userRepository = userRepository;
    }

    public void mockWork(@NonNull User user, @NonNull final String massage) {
        log.info(massage);
        systemJournalService.writeMassage(user, massage);
    }

    public void save(@NonNull final User user) {
        userRepository.save(user);
    }

    public User findById(@NonNull final Long id) {
        var user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new IllegalArgumentException("No any user presented with id: " + id);
        }
    }
}
