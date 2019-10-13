package logging.src.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SystemJournal {

    @Getter
    @Setter
    @NonNull
    List<SystemJournalMassage> systemJournalMassages;
}
