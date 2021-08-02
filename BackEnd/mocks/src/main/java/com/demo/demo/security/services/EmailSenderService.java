package com.demo.demo.security.services;

import com.demo.demo.exception.HappyBirthDayException;
import com.demo.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    private static String dateFormat="dd-MM";

    public void sendMail(String userEmail, String confirmationToken){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userEmail);
        mailMessage.setSubject("Account Activation!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8081/api/auth/confirm-account?token="+ confirmationToken
                + "   Note: This link will expire after 10 minutes. " + " click here to login your account : " + " http://localhost:4200/login");
        javaMailSender.send(mailMessage);
    }

    public boolean sendSimpleMail(String to, String sub, String body){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(sub);
        mailMessage.setText(body);
        Boolean isSent = false;
        try
        {
            javaMailSender.send(mailMessage);
            isSent = true;
        }
        catch (Exception e) {}

        return isSent;
    }

    public boolean isItBirthday(Users users) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Date today = new Date();
        Date birthDay = users.getDob ();
        if(birthDay == null)
        {
            throw new HappyBirthDayException ("Birth date is null", "201");
        }
        return (formatter.format(today).equals(formatter.format(birthDay)));
    }

    public void sendMailHappyBirthDay(String userEmail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userEmail);
        mailMessage.setSubject("Account Activation!");
        mailMessage.setText("A wish for you on your birthday, whatever you ask you may receive, " +
                "whatever you seek you may find, whatever you wish may be fulfilled on your birthday. " +
                "Happy birthday to you! ");
        javaMailSender.send(mailMessage);
    }
}
