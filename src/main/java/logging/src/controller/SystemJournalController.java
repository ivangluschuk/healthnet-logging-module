package logging.src.controller;

import logging.src.payload.SystemJournalResponse;
import logging.src.payload.UserSystemJournalResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import logging.src.service.SystemJournalService;

@RestController
public class SystemJournalController {

    private final SystemJournalService systemJournalService;

    public SystemJournalController(SystemJournalService systemJournalService) {
        this.systemJournalService = systemJournalService;
    }

    @GetMapping("/journal/id={id}&limit={limit}&offset={offset}")
    public UserSystemJournalResponse getUserSystemJournal(@PathVariable("id") Long id,
                                                            @PathVariable("limit") int limit,
                                                            @PathVariable("offset") int offset) {
        var systemJournal = systemJournalService.getUserSystemJournal(id, limit, offset);
        return new UserSystemJournalResponse(systemJournal);
    }

    @GetMapping("/journal/all")
    public SystemJournalResponse getUserSystemJournal() {
        return new SystemJournalResponse(systemJournalService.getSystemJournal());
    }
}
