package com.together.controller;

import com.together.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**获取和验证 验证码的controller
 * @author w
 */
@Controller
public class VerificationController {

    @Autowired
    VerificationService verificationService;

    @RequestMapping(value = "/getImage" ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String text(HttpServletRequest request) throws JSONException {
        //还需要把userId和code存在redis里面用于验证，验证完成后无论是正确或是错误都要删除
        //没有写service的接口
        //前端显示的方法在login.jsp
        HttpSession session = request.getSession();
        //以session做id
        String userId = session.getId();

        JSONObject jsonObject = new JSONObject();
        String code = verificationService.getCode();
        //设置了过期时间为10分钟
        String image = verificationService.getImage(session.getId(),code);
        jsonObject.put("image",image);
        return jsonObject.toString();
    }

    @RequestMapping("/submit")
    @ResponseBody
    public String text2(HttpServletRequest request, String v){
        HttpSession session = request.getSession();
        String id = session.getId();

        String code = verificationService.getCode(id);
        //删除存储的数据
        verificationService.delKey(id);
        if (v.equalsIgnoreCase(code)){
            //修改
            return "输入正确";
        }
        else {
            return "错误";
        }
    }
}

