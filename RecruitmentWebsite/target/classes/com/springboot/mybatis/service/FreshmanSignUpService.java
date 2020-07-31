package com.springboot.mybatis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.mybatis.dao.FreshmanSignUpMapper;
import com.springboot.mybatis.pojo.InterviewData;
import com.springboot.mybatis.pojo.StateCode;
import com.springboot.mybatis.pojo.User;
import com.springboot.mybatis.service.Imp.FreshmanSignUpImp;
import com.springboot.mybatis.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FreshmanSignUpService implements FreshmanSignUpImp {
    @Autowired
    FreshmanSignUpMapper freshmanSignUpMapper;
    @Autowired
    JsonUtil jsonUtil;
    @Override
    public boolean isQqExisted(User user) {
        List<User> list = freshmanSignUpMapper.getAllSignUp();
        for(User i : list){
            if(user.getQq().equals(i.getQq())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isIdExisted(User user) {
        List<User> list = freshmanSignUpMapper.getAllSignUp();
        for(User i : list){
            if(user.getId().equals(i.getId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void insertSignUp(User user) {
        freshmanSignUpMapper.insertSignUp(user);
    }

    @Override
    public void insertSignUp_mogul(User user) {
        freshmanSignUpMapper.insertSignUp(user);
        freshmanSignUpMapper.insertSignUp_mogul(user);
    }

    @Override
    public void updateLoginId(String num, String id) {
        freshmanSignUpMapper.updateLoginId(num,id);
    }

    @Override
    public boolean isSignedUp(String number) {
        List<User> list = freshmanSignUpMapper.getAllRegister();
        for(User i : list){
            if(number.equals(i.getNumber())){
                if("0".equals(i.getId())){
                    return false;
                }
                else{
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public User getLoginUser(HttpServletRequest request) throws JsonProcessingException {
        HttpSession session = request.getSession();
        String str = (String) session.getAttribute("user");
        User user = (User) jsonUtil.getObject(str,User.class);
        return user;
    }
    @Override
    public User getContextUser(String context) throws JsonProcessingException {
        return (User) jsonUtil.getObject(context,User.class);

    }
    @Override
    public User mergeUser(User loginUser,User contextUser) throws JsonProcessingException {
        contextUser.setNumber(loginUser.getNumber());
        contextUser.setPassword(loginUser.getPassword());
        return contextUser;
    }
    @Override
    public User getUser_normal(String context,HttpServletRequest request) throws JsonProcessingException {
        User user = mergeUser(getLoginUser(request),getContextUser(context));
        user.setIsdalao("0");
        return user;
    }
    @Override
    public User getUser_mogul(String context,HttpServletRequest request) throws JsonProcessingException {
        User user = mergeUser(getLoginUser(request),getContextUser(context));
        user.setIsdalao("1");
        return user;
    }
    @Override
    public void setRandomId(User user){
        Random r = new Random();
        user.setId(String.valueOf(r.nextInt(1000)));
        while(this.isIdExisted(user) || "0".equals(user.getId())){
            user.setId(String.valueOf(r.nextInt(1000)));
        }
    }

    @Override
    public StateCode isUserRule(User user) {
        if(!isAllChinese(user.getName()) || isAllVoid(user.getName())){
            return new StateCode("-1","输入姓名不合法");
        }
        if(!isAllNumber(user.getQq()) || isAllVoid(user.getQq())){
            return new StateCode("-1","输入QQ号码不合法");
        }
        if(!isAllChinese(user.getCollege()) || isAllVoid(user.getCollege())){
            return new StateCode("-1","输入学院不合法");
        }
        if(!isAllChinese(user.getMajor()) || isAllVoid(user.getMajor())){
            return new StateCode("-1","输入专业不合法");
        }
        if(!isAllChinese(user.getDepartment()) || isAllVoid((user.getDepartment()))){
            return new StateCode("-1","输入部门不合法");
        }
        if(isAllAphabet(user.getClassroom()) || isAllVoid(user.getClassroom())){
            return new StateCode("-1","输入班级不合法");
        }
        return new StateCode("0","输入合法");
    }

    @Override
    public User deleteUserBlank(User user){
        user.setName(user.getName().replace(" ",""));
        user.setClassroom(user.getClassroom().replace(" ",""));
        user.setDepartment(user.getDepartment().replace(" ",""));
        user.setQq(user.getQq().replace(" ",""));
        user.setMajor(user.getMajor().replace(" ",""));
        user.setCollege(user.getCollege().replace(" ",""));
        return user;
    }
    @Override
    public User getUserByNumber(String number){
        String id = "0";
        List<User> registerUserList = freshmanSignUpMapper.getAllRegister();
        for(User i : registerUserList){
            if(number.equals(i.getNumber())) {
                id = i.getId();
                break;
            }
        }
        if("0".equals(id)){
            return null;
        }
        List<User> signUpUserList = freshmanSignUpMapper.getAllSignUp();
        for(User i : signUpUserList){
            if(id.equals(i.getId())){
                i.setSpecialty(freshmanSignUpMapper.getSpecialtyById(id));
                return i;
            }
        }
        return null;
    }
    @Override
    public InterviewData getInterviewDataById(String id){
        return (InterviewData) freshmanSignUpMapper.getInterviewDataById(id);
    }



    /*
    * 私有方法
    * */
    boolean isAllChinese(String str){
        str = str.replace(" ","");
        Pattern p_str = Pattern.compile("[\\u4e00-\\u9fa5]+");
        Matcher m = p_str.matcher(str.trim());
        if(m.find()&&m.group(0).equals(str)){
            return true;
        }
        return false;
    }
    boolean isAllNumber(String str){
        str = str.replace(" ","");
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher m = pattern.matcher(str);
        return m.matches();

    }
    boolean isAllAphabet(String str){
        String regex=".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(str);
        return m.matches();
    }
    boolean isAllVoid(String str){
        str = str.replace(" ","");
        if("".equals(str)){
            return true;
        }
        else {
            return false;
        }
    }

}
