package com.demo.demo.controller;

import com.demo.demo.config.EmailCfg;
import com.demo.demo.config.MailConfig;
import com.demo.demo.message.request.LeavePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import javax.validation.ValidationException;
import javax.validation.executable.ValidateOnExecution;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/leave")
@CrossOrigin(origins = "http://localhost:4200")
public class LeaveController {

    @Autowired
    private EmailCfg emailCfg;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender sender;

    @PostMapping("/sendMail")
    public void sendLeave(@RequestBody LeavePost leavePost, BindingResult bindingResult){

        if (bindingResult.hasErrors ()){
            throw new ValidationException ("Fales");
        }

        // Create a mail sender
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl ();
        javaMailSender.setHost (this.emailCfg.getHost ());
        javaMailSender.setPort (this.emailCfg.getPort ());
        javaMailSender.setUsername (this.emailCfg.getUsername ());
        javaMailSender.setPassword (this.emailCfg.getPassword ());

        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(leavePost.getEmail());
        mailMessage.setTo("vantan1305@gmail.com");
        mailMessage.setSubject("New Subject from " + leavePost.getName());
        mailMessage.setText(leavePost.getReason ());
        mailMessage.setText (leavePost.getMessage ());
        mailMessage.setSentDate (leavePost.getFormDate ());
        mailMessage.setSentDate (leavePost.getToDate ());

        //send mail
        javaMailSender.send(mailMessage);
    }

    @RequestMapping("/post")
    public @ResponseBody LeavePost sendMail(@RequestBody LeavePost leavePost) throws Exception {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Map<String, Object> model = new HashMap<String, Object> ();
        model.put("name",leavePost.getName());
        model.put("reason",leavePost.getReason ());
        model.put ("yourMail", leavePost.getYourMail ());
//        model.put("formDate",leavePost.getFormDate ());
//        model.put ("toDate", leavePost.getToDate ());
        model.put ("message", leavePost.getMessage ());

        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process("email-template", context);

        try {
            helper.setTo(leavePost.getEmail ());
            helper.setText(html,true);
            helper.setSubject("Please Leave");
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);

        return leavePost;

    }
}
