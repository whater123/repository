//切换普通用户和大佬注册页面
var normal = document.getElementById("information_normal");
var mogul = document.getElementById("information_mogul");
var change_l = document.getElementById("left");
var change_r = document.getElementById("right");
var tab = document.getElementById("tab");
change_l.onclick=function(){
	mogul.style.display="none";
	normal.style.display="block";
	tab.style.display="none"
}
change_r.onclick=function(){
	mogul.style.display="block";
	normal.style.display="none";
	tab.style.display="none"
}
//普通用户报名
$(document).ready(function(){
        $("#btn_normal").click(function(){
        var user = new Object();
        user.name = $("#name_normal").val();
        user.qq = $("#qq_normal").val();
        user.college = $("#college_normal").val();
        user.major = $("#major_normal").val();
        user.number = $("#number_normal").val();
        user.classroom = $("#classroom_normal").val();
        user.department = $("#department_normal").val();
        user.context = $("#context_normal").val();
        if(user.context==""||user.context==null){
        alert("个人简介不能为空");
        }
        else{
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/user/freshmanSignUp_normal",
            data: JSON.stringify(user),
            dataType: 'text',
            timeout: 600000,
            success: function (data){
                let stateCode = JSON.parse(data);
                //登录失败状态码为-1 管理员成功状态码为1 新生为2//
                alert(stateCode.msg);
                },
            error:function(XMLHttpRequest){  //请求失败的回调方法
                alert("Error: "+XMLHttpRequest.status);
            }
        });
      }}
   )});
//大佬报名
$(document).ready(function(){
        $("#btn_mogul").click(function(){
        var user = new Object();
        user.name = $("#name_mogul").val();
        user.qq = $("#qq_mogul").val();
        user.college = $("#college_mogul").val();
        user.major = $("#major_mogul").val();
        user.number = $("#number_mogul").val();
        user.classroom = $("#classroom_mogul").val();
        user.department = $("#department_mogul").val();
        user.specialty = $("#specialty_mogul").val();
        user.context = $("#context_mogul").val();
        if(user.context==""||user.context==null){
        alert("个人简介不能为空");
        }
        else if(user.specialty==""||user.specialty==null){
        alert("特长不能为空");
        }
        else{
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/user/freshmanSignUp_mogul",
            data: JSON.stringify(user),
            dataType: 'text',
            timeout: 600000,
            success: function (data){
                let stateCode = JSON.parse(data);
                //登录失败状态码为-1 管理员成功状态码为1 新生为2//
                alert(stateCode.msg);
                },
            error:function(XMLHttpRequest){  //请求失败的回调方法
                alert("Error: "+XMLHttpRequest.status);
            }
        });
      }}
   )});
    


