package com.example.demo.integration.anouncements;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class AnnouncementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin@adminski.email", roles = {"USER", "ADMIN"})
    public void testNewAnnouncementShouldSave() throws Exception {
        mockMvc.perform(post("/announcements/save")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "Hello, Spring !")
                .param("description", "Spring integration testing !"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/announcements"));

    }

    @Test
    @WithMockUser(username = "admin@adminski.email", roles = {"USER", "ADMIN"})
    public void testNewAnnouncementShouldFailValidation() throws Exception {
        mockMvc.perform(post("/announcements/save")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "Hello, Spring !")
                        .param("description", "Short"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/announcements/new"));

    }
}
