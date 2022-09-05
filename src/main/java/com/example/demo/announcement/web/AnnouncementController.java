package com.example.demo.announcement.web;

import com.example.demo.announcement.service.AnnouncementsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/announcements")
public class AnnouncementController {

    private final AnnouncementsService announcementsService;

    @GetMapping
    public String announcement(Model model) {
        model.addAttribute("announcements", announcementsService.findAll());

        return "announcement/announcements";
    }
}
