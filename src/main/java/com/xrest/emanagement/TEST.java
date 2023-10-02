package com.xrest.emanagement;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.InternetAddress;
import java.util.Properties;
import java.util.Random;

public class TEST {
//    private static JavaMailSenderImpl javaMailSender;
//
//    public TEST(JavaMailSenderImpl javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }

    //main sender working
    public static void main (String a[]) {
       Runnable runnable = ()->{
           send("");
       };
       Thread thread = new Thread(runnable);
       thread.setName("Mail Sender Thread");
       thread.start();
    }
    public static   String send(String email) {
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
        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(new InternetAddress("Company"));
            mimeMessageHelper.setTo("sonam.shrestha@sbsolutionsnepal.com");
            mimeMessageHelper.setSubject("Hello World");
            mimeMessageHelper.setText("YOYO HELLO WORLD " + new Random().nextInt(9999));
        });
        return  "";
    }
}
