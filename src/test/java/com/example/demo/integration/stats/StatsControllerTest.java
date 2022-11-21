package com.example.demo.integration.stats;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "Ivo@mail.bg", roles = {"USER", "ADMIN"})
    public void testStatsAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/stats")).
                andExpect(status().isOk()).
                andExpect(view().name("stats/stats")).
                andExpect(model().attributeExists("requestCount", "startedOn"));
    }

    @Test
    @WithMockUser(username = "user@mail.bg", roles = {"USER"})
    public void testStatsDeniedForNormalUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/stats")).
                andExpect(status().isForbidden());
    }


}
