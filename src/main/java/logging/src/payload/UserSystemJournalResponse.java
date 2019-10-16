package logging.src.payload;

import logging.src.domain.SystemJournal;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserSystemJournalResponse {

    private UserResponse user;
    private List<SystemJournalMassageResponse> massages;

    public UserSystemJournalResponse(@NonNull final SystemJournal systemJournal) {
        final var systemMassages = systemJournal.getSystemJournalMassages();

        if (systemMassages.size() > 0) {
            final var userResponse = new UserResponse();

            userResponse.setId(systemMassages.get(0).getUser().getId());
            userResponse.setName(systemMassages.get(0).getUser().getName());
            this.user = userResponse;

            final var massages = new ArrayList<SystemJournalMassageResponse>();

            for (int i = 0; i < systemMassages.size(); i++) {
               massages.add(new SystemJournalMassageResponse(i + 1, systemMassages.get(i).getMassage()));
            }
            this.massages = massages;
        }
    }

    @Data
    @AllArgsConstructor
    private static class SystemJournalMassageResponse {

        private int id;
        private String massage;
    }
}
