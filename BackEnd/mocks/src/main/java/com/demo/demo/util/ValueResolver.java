package com.demo.demo.util;

import com.demo.demo.model.Users;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import freemarker.template.Configuration;
import javax.annotation.PostConstruct;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class ValueResolver {

    private static final Logger LOG = LoggerFactory.getLogger(ValueResolver.class);

    private Configuration configuration;

    private static String dateFormat = "dd-MM-yyyy";

    @PostConstruct
    private void init(){
        configuration = new Configuration();
        configuration.setObjectWrapper(new DefaultObjectWrapper ());
    }

    public String buildString(Users users, String text){
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        Map<String,Object> map=new HashMap<> ();
        map.put("name", users.getUserName ());
        map.put("email", users.getEmail());
        map.put("birthDate", formatter.format(users.getAnniversaryDate ()));
        map.put("anniversaryDate", formatter.format(users.getAnniversaryDate()));
        map.put("age", getDiff(users.getDob ())+"");
        map.put("workYears", getDiff(users.getAnniversaryDate())+"");

        String receiptText = text;
        try
        {
            StringReader reader = new StringReader(text);
            Template receiptTemplate = new Template("text", reader, configuration);
            StringWriter out = new StringWriter();
            receiptTemplate.process(map, out);
            receiptText = out.toString();
        }
        catch (Exception e)
        {
            LOG.warn("Unable to render message template:" + e.getMessage());
        }
        return receiptText;
    }

    private int getDiff(Date date){
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(date));
        int d2 = Integer.parseInt(formatter.format(new Date()));
        int age = (d2 - d1) / 10000;
        return age;
    }
}
