package com.emailsend.controller;

import com.emailsend.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/email")
public class EmailSendingController {

    @Autowired
    private SendEmailService sendEmailService;

    @PostMapping("/send")
    public String sendEmail(
            @RequestParam("to") String recipient,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body,
            @RequestParam(value = "attachment", required = false) MultipartFile attachment) {

        try {
            sendEmailService.sendEmail(recipient, body, subject, attachment);
            return String.format("Email sent successfully to '%s' with subject '%s'.", recipient, subject);        } catch (Exception e) {
            return "Error while sending email: " + e.getMessage();
        }
    }
}
