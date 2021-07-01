package com.codeup.blog.controllers;

import com.codeup.blog.models.Contact;
import com.codeup.blog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    private final EmailService emailService;

    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContactForm(@ModelAttribute Contact contactToBeSaved) {
        emailService.prepareAndSend(contactToBeSaved, "Thanks for visiting My Blog", "Thanks for sending me your comments. I'll be in touch with you soon!");
        return "contact-done";
    }
}
