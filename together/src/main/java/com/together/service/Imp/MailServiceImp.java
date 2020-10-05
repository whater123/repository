package com.together.service.Imp;


public interface MailServiceImp {
    void sendSimplemail(String to, String subject, String content);

    boolean isPerfect(String number);

    boolean isEmail(String string);

//    void insertMsg(Student student);
}
