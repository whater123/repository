package com.together.service;


import com.together.service.Imp.MailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MailService implements MailServiceImp {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;
//    @Autowired
//    StudentMapper studentMapper;

    @Override
    public void sendSimplemail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        mailSender.send(mailMessage);
    }

    @Override
    public boolean isPerfect(String number) {
//        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("number",number);
//        Student student = studentMapper.selectOne(queryWrapper);
//        return student != null;
        return true;
    }

//    @Override
//    public void insertMsg(Student student) {
//        studentMapper.insert(student);
//    }

    @Override
    public boolean isEmail(String string) {
        if (string == null) {
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        return m.matches();
    }
}
