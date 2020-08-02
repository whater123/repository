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
    if(this.num==1){
            divs[this.num].style.height=3500+"px";      
        }
    if(this.num==2){
            getNotice();
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
//管理员删除通知
function noticeDelete(x){
  var message = confirm("确认要删除吗?");
  if(message==true){
  var get = x.split('delete')[1];
  var id = document.getElementsByClassName('allTitle')[get-1].id;
  var deleteDate=document.getElementById('data'+get);
  var deleteTitle=document.getElementById(id);
  var notice = new Object();
  notice.id=id; 
  $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/manager/deleteNotice",
            data: JSON.stringify(notice),
            dataType: 'text',
            timeout: 600000,
            success:function(data){
             var thedata = JSON.parse(data);
             if(thedata.state==5){//删除成功
                $(deleteDate).remove();
                $(deleteTitle).remove();
                alert(thedata.msg);}
             else if(thedata.state==-1){//删除失败
                alert(thedata.msg);}
            },
            error:function(XMLHttpRequest){  //请求失败的回调方法
            alert("Error: "+XMLHttpRequest.status);
            }
        });
   }
}
//管理员修改通知
function noticeRevise(x){
    var get = x.split('revise')[1];
    var id = document.getElementsByClassName('allTitle')[get-1].id;
    var url = 'notice?id='+id;
    window.open(url);
}
//普通用户查看通知
function getNoticeById(x){
  var url = 'notice?id='+x;
  window.open(url);
}
//当切换至第三个div通知系统时
function getNotice(){
   $.ajax({
        type: "POST",
            url: "/userIsManager",
            dataType: 'text',
            timeout: 600000,
            success:function(xml){
            //是管理员 显示修改通知的按钮
            if(xml==="true"){
            $('#notice_publish').show();
            $('#revise_follow').show();
            $('#delete_follow').show();
            }
            $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/manager/getAllNotice",
            dataType: 'text',
            timeout: 600000,
            success: function (data){
                //防止因为多次点击出现重复
                var children = $('#title_follow').children();
                if(children.length==0){

                let notice = JSON.parse(data);
                var number = 1;
                for(var p in notice){
                $("#data_follow").append('<label id="data"></label>');
                document.getElementById('data').id='data'+number;
                $("#data"+number).text(notice[p].date.split(" ")[0]);

                $("#title_follow").append('<label id="title" class="allTitle"></label>');
                $("#title").text(notice[p].title);
                document.getElementById('title').onclick=function(){
                getNoticeById(''+this.id);}; //给标题绑定点击事件 查询具体通知
                document.getElementById('title').id=notice[p].id;

                $("#revise_follow").append('<input type="button" value="修改" id="revise">');
                document.getElementById('revise').id='revise'+number;

                $("#delete_follow").append('<input type="button" value="删除" id="delete">');
                document.getElementById('delete').onclick=function(){
                noticeDelete(''+this.id);}; //给删除键绑定点击事件 删除具体通知
                document.getElementById('delete').id='delete'+number;
                number++;
                }
               }
                },
            error:function(XMLHttpRequest){  //请求失败的回调方法
                alert("Error: "+XMLHttpRequest.status);
                  }
               });
            },
            error:function(XMLHttpRequest){  //请求失败的回调方法
                alert("Error: "+XMLHttpRequest.status);
            }
               });
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
            success: function(data){
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
                if(user.number==""||user.number==null||user.password==""||user.password==null){
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

//**//
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

    


