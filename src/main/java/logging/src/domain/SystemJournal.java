package logging.src.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class SystemJournal {

    @NonNull
    List<SystemJournalMassage> systemJournalMassages;
}
