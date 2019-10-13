package logging.src.payload;

import logging.src.domain.SystemJournal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class UserSystemJournalResponse {

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

    @Getter
    @Setter
    private UserResponse user;

    @Getter
    @Setter
    private List<SystemJournalMassageResponse> massages;


    @AllArgsConstructor
    private static class SystemJournalMassageResponse {

        @Getter
        @Setter
        private int id;

        @Getter
        @Setter
        private String massage;
    }
}
