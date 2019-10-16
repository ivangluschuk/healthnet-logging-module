package logging.src.controller;

import logging.src.domain.User;
import logging.src.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;
import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableConfigurationProperties
@TestPropertySource("/application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SystemJournalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void getUserSystemJournalWithZeroOffset() throws Exception {
        File file = ResourceUtils
                .getFile("classpath:json/controller/SystemJournalControllerTest#getUserSystemJournalWithZeroOffset.json");
        String preparedJsonResponse = new String(Files.readAllBytes(file.toPath()));

        final var user = new User();
        user.setName("Eve");

        for (int i = 0; i < 10; i++) {
            userService.mockWork(user,"getUserSystemJournal(" + i + ")");
        }

        var response = this.mockMvc.perform(get("/journal/id=1&limit=10&offset=0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        var responseBody = response.getResponse().getContentAsString();

        assertEquals(responseBody, preparedJsonResponse);
    }

    @Test
    @Transactional
    public void getUserSystemJournalWithNonZeroOffset() throws Exception {
        File file = ResourceUtils
                .getFile("classpath:json/controller/SystemJournalControllerTest#getUserSystemJournalWithNonZeroOffset.json");
        String preparedJsonResponse = new String(Files.readAllBytes(file.toPath()));

        final var user = new User();
        user.setName("Eve");

        for (int i = 0; i < 10; i++) {
            userService.mockWork(user,"getUserSystemJournal(" + i + ")");
        }

        var response = this.mockMvc.perform(get("/journal/id=1&limit=2&offset=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        var responseBody = response.getResponse().getContentAsString();

        assertEquals(responseBody, preparedJsonResponse);
    }

    @Test
    @Transactional
    public void getSystemJournal() throws Exception {
        File file = ResourceUtils
                .getFile("classpath:json/controller/SystemJournalControllerTest#getSystemJournal.json");
        String preparedJsonResponse = new String(Files.readAllBytes(file.toPath()));

        final var user_0 = new User();
        user_0.setName("Eve");
        userService.mockWork(user_0, "getSystemJournal(0)");
        userService.mockWork(user_0, "getSystemJournal(1)");

        final var user_1 = new User();
        user_1.setName("Adam");
        userService.mockWork(user_1, "getSystemJournal(0)");
        userService.mockWork(user_1, "getSystemJournal(1)");

        var response = this.mockMvc.perform(get("/journal/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        var responseBody = response.getResponse().getContentAsString();

        assertEquals(responseBody, preparedJsonResponse);
    }
}
