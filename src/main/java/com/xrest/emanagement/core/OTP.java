package com.xrest.emanagement.core;

import com.xrest.emanagement.entity.User;
import com.xrest.emanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.util.Random;
@Component
public class OTP implements Runnable{
    private final   JavaMailSenderImpl javaMailSender;
    private final   UserRepository userRepository;

    private String email;
    @Autowired
    public OTP( JavaMailSenderImpl javaMailSender, UserRepository userRepository) {
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void run() {
        Random random = new Random();
        Integer otp = random.nextInt(9999);
            User user = userRepository.findUserByEmail(email);
            user.setOtp(otp.toString());
            user.setVersion(1);
            user.setVerified(Boolean.FALSE);
            userRepository.save(user);
        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setSubject("OTP Verification");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setFrom(new InternetAddress(javaMailSender.getUsername()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Your OTP is: ");
            stringBuilder.append(otp);
            stringBuilder.append(" This is an automated Email Please Do Not Reply");
            mimeMessageHelper.setText(stringBuilder.toString());
        });

    }
}
