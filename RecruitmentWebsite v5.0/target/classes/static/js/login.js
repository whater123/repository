//登录
$(document).ready(function(){
        $("#loginBtn").click(function(){
        var user = new Object();
        user.number = $("#number").val();
        user.password = $("#password").val();
        if(user.number==""||user.number==null){
        $("#login-p").html("登录账户不能为空");
        }
        else if(user.password==""||user.password==null){
        $("#login-p").html("登录密码不能为空");	
        }
        else{
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://182.61.16.76:8080/RecruitmentWebsite/login",
            data: JSON.stringify(user),
            dataType: 'text',
            timeout: 600000,
            success: function (data){
                let stateCode = JSON.parse(data);
                //登录失败状态码为-1 管理员成功状态码为1 新生为2//
                if(stateCode.state=="-1"){
                    alert(stateCode.msg);
                }else if(stateCode.state == "1"){
                	$("#middle-5-before").hide();
                    $("#middle-5-after-login2").show();
                    alert(stateCode.msg);
                }else if(stateCode.state == "2"){
                    alert(stateCode.msg);
                    $("#middle-5-before").hide();
                    $("#middle-5-after-login1").show();
                }},
            error:function(XMLHttpRequest){  //请求失败的回调方法
                alert("Error: "+XMLHttpRequest.status);
            }
        });
      }}
   )});
//注册
$(document).ready(function(){
        $("#registerBtn").click(function(){
        var user = new Object();
        user.number = $("#mynumber").val();
        user.password = $("#mypassword").val();
        if(user.number==""||user.number==null){
        $("#register-p").html("注册账户不能为空");
        }
        else if(user.password==""||user.password==null){
        $("#register-p").html("登录密码不能为空");	
        }
        else{
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://182.61.16.76:8080/RecruitmentWebsite/register",
            data: JSON.stringify(user),
            dataType: 'text',
            timeout: 600000,
            success: function (data){
                let stateCode = JSON.parse(data);
                //注册失败状态码为-1 注册成功状态码为0//
                if(stateCode.state=="-1"){
                    alert(stateCode.msg);
                }else if(stateCode.state == "0"){
                    alert(stateCode.msg);
                }},
            error:function(XMLHttpRequest){  //请求失败的回调方法
                alert("Error: "+XMLHttpRequest.status);
            }
        });
      }}
   )});

