//banner切换//
var lis = document.getElementsByClassName('lis');
var divs = document.getElementsByClassName('middle-z');
divs[0].style.display="block";
divs[1].style.display="none";
divs[2].style.display="none";
divs[3].style.display="none";
divs[4].style.display="none";
for(i = 0; i<5 ; i++)
{
	lis[i].num=i;
	lis[i].onclick=function(){
    for(j = 0 ; j<5 ; j++){
    	if(i!=j){
    	divs[j].style.display="none";	
    	}
    divs[this.num].style.display="block";	
    }
    if (this.num==1){
     		divs[this.num].style.height=3500+"px";
     	}
	}
}
//切换登录和注册界面
var login = document.getElementById("m-5-r-bottom-1");
var register = document.getElementById("m-5-r-bottom-2");
var change_l = document.getElementById("change-l");
var change_r = document.getElementById("change-r");
var pic = document.getElementById('pic');
change_l.onclick=function(){
	register.style.display="none";
	login.style.display="block";
	pic.src="../static/img/纪念碑谷1.jpg";
}
change_r.onclick=function(){
	register.style.display="block";
	login.style.display="none";
	pic.src="../static/img/纪念碑谷2.jpg";
}
/**不同部门的报名表跳转**/
function jumpto(e){
    console.log(e.id);
    var name = e.firstElementChild.innerHTML;
    console.log(name);
    var url = 'application?name='+name;
    window.open(url);
} 
//登录和注册
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
            url: "/login",
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
            url: "/register",
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
//判断是报名还是查看报名信息(网申)
$(document).ready(function(){
    $("#getSignBtn").click(function(){
    $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/getSession",
            dataType: 'text',
            timeout: 600000,
            success: function (data){
                let user = JSON.parse(data);
                //返回的值全为空 说明未登陆//
                if(user.number==""||user.number==null){
                  alert('您还未登陆 请先登陆');       
                }
                else{        
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/user/ifSignUp",
                    dataType: 'text',
                    timeout: 600000,
                    success: function (data){
                      let ifLogin = JSON.parse(data);
                      //参数为0未报名 参数为1已报名
                      if(ifLogin.state==-1){
                        var url = 'form?if=no';
                        window.open(url);
                      }else if(ifLogin.state==3){
                        var url = 'form?if=yes';
                        window.open(url);                   
                        }
                      },              
                    error:function(XMLHttpRequest){  //请求失败的回调方法
                    alert("Error: "+XMLHttpRequest.status);
                 }});}   
            },
                    error:function(XMLHttpRequest){  //请求失败的回调方法
                    alert("Error: "+XMLHttpRequest.status);} 
    });
  }); 
});

//
window.onload=function()
{
 var knowmore = document.getElementById('KnowMore');
 var lis = document.getElementsByClassName('lis');//首页顶部的各个板块
   //console.log(knowmore.style.height);
   var logo=document.getElementById('KnowMorelogo');
   var text = document.getElementById('KnowMoretext');
   knowmore.onmouseover = function()
{
	knowmore.style.height='80px';
	logo.style.display = 'block';
	text.style.display = 'block';
}
lis[3].onmouseover = function()
{
	knowmore.style.height='80px';
	logo.style.display = 'block';
	text.style.display = 'block';
}
knowmore.onmouseout = function()
{
	knowmore.style.height= '0px';
	logo.style.display = 'none';
	text.style.display = 'none';
}
lis[3].onmouseout =function()
{
	knowmore.style.height = '0px';
	logo.style.display = 'none';
	text.style.display = 'none';
}
}

    


