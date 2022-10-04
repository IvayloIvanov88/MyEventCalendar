package com.example.demo.announcement.web;

import com.example.demo.announcement.model.dto.AnnouncementDTO;
import com.example.demo.announcement.service.AnnouncementsService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/announcements")
public class AnnouncementController {

    private final AnnouncementsService announcementsService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public String announcement(Model model) {
        model.addAttribute("announcements", announcementsService.findAll());

        return "announcement/announcements";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public String newAnnouncement(Model model) {

        if (!model.containsAttribute("formData")){
            model.addAttribute("formData", new AnnouncementDTO());
        }

        return "announcement/new";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("formData") AnnouncementDTO announcementDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("formData", announcementDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.formData", bindingResult);
            return "redirect:/announcements/new";
        }

        announcementsService.createOrUpdateAnnouncement(announcementDTO);

        return "redirect:/announcements";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public String delete(@ModelAttribute(name = "deleteId") long deleteId) {
        announcementsService.deleteAnnouncement(deleteId);
        return "redirect:/announcements";
    }
}
