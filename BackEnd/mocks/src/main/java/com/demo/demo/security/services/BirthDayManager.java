package com.demo.demo.security.services;

import com.demo.demo.message.request.UpdateUser;
import com.demo.demo.message.response.BirthDayException;
import com.demo.demo.model.BirthDayConfig;
import com.demo.demo.model.Users;
import com.demo.demo.repository.BirthDayRepository;
import com.demo.demo.repository.UserRepository;
import com.demo.demo.util.ValueResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class BirthDayManager {

    Logger logger = LoggerFactory.getLogger(BirthDayManager.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BirthDayRepository birthDayRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailContentBuilder mailContentBuilder;

    @Autowired
    private ValueResolver valueResolver;

    private int configPicker=0;

    private List<BirthDayConfig> config = new ArrayList<>();

    private static String dateFormat="dd-MM";

    @Scheduled(cron = "0 58 10 * * ?")
    private void sendBirthdayMail(){
        List<Users> usersList = userRepository.findAll();
        List<Users> birthDayToday = new ArrayList<> ();

        config = birthDayRepository.findAll();

        for(Users users : usersList){
            try{
                if (isItBirthday(users)){
                    birthDayToday.add(users);
                }
            }catch (BirthDayException ex){
                logger.error("Error getting  date - "+" EXCEPTION : "+ex.getCode()+" : "+ex.getMessage());
            }
        }

        try{
            String[] ccList = config.get(0).getCcRecipients().split(",");

            List<InternetAddress> ccAddresses = new ArrayList<>();

            for (String cc : ccList){
                InternetAddress address = new InternetAddress();
                address.setAddress(cc);
                ccAddresses.add(address);
            }if (birthDayToday.size() > 0){
                for (Users users : birthDayToday){
                    chooseConfig();
                    sendBirthDayMail(users.getEmail().trim(),
                            config.get(configPicker).getFromEmail().trim(),
                            ccAddresses.toArray(new InternetAddress[] {}),
                            users);
                }
            }
        }
        catch (Exception ex){
            logger.error("Error sending mails.");
        }
    }

    private boolean isItBirthday(Users users) {
        SimpleDateFormat formatter = new SimpleDateFormat (dateFormat);
        Date today = new Date ( );
        Date birthDay = users.getDob ( );
        if (birthDay != null) {
            return (formatter.format (today).equals (formatter.format (birthDay)));
        }
//            throw new BirthDayException ("Birth date is null", "201");
        return false;
    }

    private void sendBirthDayMail(String toEmail, String fromEmail, InternetAddress[] cc, Users users){
        try{
            MimeMessagePreparator messagePreparator = mimeMessage ->{
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setFrom(fromEmail, config.get(configPicker).getFromName());
                messageHelper.setTo(toEmail);
                messageHelper.setSubject(valueResolver.buildString(users,config.get(configPicker).getMailSubject()));
                messageHelper.setCc(cc);
                String content = mailContentBuilder.build(
                        users.getUserName (),
                        config.get(configPicker).getMailImageLink(),
                        valueResolver.buildString(users,config.get(configPicker).getMailHeadLine()),
                        valueResolver.buildString(users,config.get(configPicker).getMailIntroContent()),
                        valueResolver.buildString(users,config.get(configPicker).getMailMainContent()),
                        config.get(configPicker).getRegardsFrom(),
                        config.get(configPicker).getCompanyLogoLink());
                messageHelper.setText(content,true);

                logger.info("MAIL sent to :"+toEmail+" with content "+content);
            };
            javaMailSender.send(messagePreparator);
        }
        catch (Exception ex){
            logger.error("Birthday Mail could not be sent for "+toEmail);
        }
    }

    private void chooseConfig(){
        Random random = new Random();
        int randomInt = random.nextInt(10+config.size());
        configPicker = randomInt % config.size();
    }
}
