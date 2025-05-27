package com.project.Profile.and.Enrollment.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEnrollmentMail(String toEmail, String courseId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Course Enrollment Confirmation");
        message.setText("You have been successfully enrolled in course: " + courseId);
        message.setFrom("your-email@gmail.com");

        mailSender.send(message);
    }
}
