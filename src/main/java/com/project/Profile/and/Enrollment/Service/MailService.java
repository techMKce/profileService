package com.project.Profile.and.Enrollment.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEnrollmentMail(String toEmail, String courseId){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Course Enrollment Confirmation");
        message.setText("<h1>You have been successfully enrolled in course: </h1>" + courseId);
        message.setFrom("your-email@gmail.com");

        mailSender.send(message);


        try {
            String html  ="<!DOCTYPE html>" +
                    "<html>" +
                    "<body>" +
                    "" +
                    "<p>This is a paragraph.</p>" +
                    "<p>This is a paragraph.</p>" +
                    "<p>This is a paragraph.</p>" +
                    "" +
                    "</body>" +
                    "</html>" +
                    "";
            MimeMessage message1 = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message1);
            helper.setTo(toEmail);
            helper.setSubject("Course Enrollment Confirmation");
            helper.setText(html, true);

            mailSender.send(message);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}



