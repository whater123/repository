//出现通知主体
var url = window.location.href;
var argsIndex = url .split("?id=");
var id = argsIndex[1];
var obj = new Object();
obj.id=id;
$.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/manager/getNoticeById",
            data:JSON.stringify(obj),
            dataType: 'text',
            timeout: 600000,
            success: function (data){
                alert(data);
                let notice = JSON.parse(data);
                $("#title").html(notice.title);
                $("#data").html(notice.data);
                $("#context").text(notice.context);
               },              
            error:function(XMLHttpRequest){  //请求失败的回调方法
                alert("Error: "+XMLHttpRequest.status);
               }
           });
//判断是否为管理员 是管理员出现能修改的文本框
$.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/userIsManager",
            dataType: 'text',
            timeout: 600000,
            success:function(xml){
            //是管理员 显示修改通知的按钮
            if(xml==="true"){
            $('#bottom').show();
            }},
            error:function(XMLHttpRequest){  //请求失败的回调方法
                alert("Error: "+XMLHttpRequest.status);
            }
           });
$(document).ready(function(){
        $("#notice_revise").click(function(){
        var text = $("#context_revise").val();
        obj.context = text;
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/manager/updateNotice",
            data:JSON.stringify(obj),
            dataType: 'text',
            timeout: 600000,
            success:function(data){
             var thedata = JSON.parse(data);
             if(thedata.state==5){//修改成功
                alert(thedata.msg);}
             else if(thedata.state==-1){//修改失败
                alert(thedata.msg);}
            },
            error:function(XMLHttpRequest){  //请求失败的回调方法
            alert("Error: "+XMLHttpRequest.status);
            }            
           });
       
     })
})  






