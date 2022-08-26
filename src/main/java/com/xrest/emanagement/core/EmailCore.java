package com.xrest.emanagement.core;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;

@Configuration
public class EmailCore {

    @Bean
    public  JavaMailSenderImpl getJavaMailSender() {
      try{
          JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
          javaMailSender.setPort(587);
          javaMailSender.setHost("smtp.gmail.com");
          javaMailSender.setUsername("lxm2rmt@gmail.com");
          javaMailSender.setPassword("mnqflizlgsqxodzq");
          Properties props = javaMailSender.getJavaMailProperties();
          props.setProperty("mail.transport.protocol", "smtp");
          props.setProperty("mail.host", "smtp.gmail.com");
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.port", "587");
          props.put("mail.debug", "true");
          props.put("mail.smtp.starttls.enable", "true");
          props.put("mail.smtp.host", "smtp.gmail.com");
          props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
          return  javaMailSender;
      } catch (Exception ex) {
          ex.printStackTrace();
      }
      return null;
    }

}
