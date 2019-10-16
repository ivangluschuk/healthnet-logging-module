package logging.src.payload;

import logging.src.domain.SystemJournal;
import logging.src.domain.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class SystemJournalResponse {

    private List<SystemJournalMassageResponse> massages;

    public SystemJournalResponse(@NonNull final SystemJournal systemJournal) {
        final var systemMassages = systemJournal.getSystemJournalMassages();

        if (systemMassages.size() > 0) {
            final var massages = new ArrayList<SystemJournalMassageResponse>();

            for (int i = 0; i < systemMassages.size(); i++) {
                massages.add(new SystemJournalMassageResponse(
                        i + 1,
                        systemMassages.get(i).getUser(),
                        systemMassages.get(i).getMassage()));
            }
            this.massages = massages;
        }
    }

    @Data
    @AllArgsConstructor
    private static class SystemJournalMassageResponse {

        private int id;
        private User user;
        private String massage;
    }
}
