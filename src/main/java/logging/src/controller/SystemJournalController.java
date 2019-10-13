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

    @GetMapping("/journal/id={id}")
    public UserSystemJournalResponse getUserSystemJournal(@PathVariable("id") Long id) {
        var systemJournal = systemJournalService.getUserSystemJournal(id);
        return new UserSystemJournalResponse(systemJournal);
    }

    @GetMapping("/journal")
    public SystemJournalResponse getUserSystemJournal() {
        return new SystemJournalResponse(systemJournalService.getSystemJournal());
    }
}
