//分割参数
var url = window.location.href;
var argsIndex=url.split("?")[1]
var Index = argsIndex.split("&");
var id = Index[0].split("id=")[1];
var ifRevise = Index[1].split("revise=")[1];
var ifPublish = Index[2].split("publish=")[1];
//若是发布通知
var obj = new Object();
obj.id=id;
if(ifPublish=='yes'){
  $('#all').hide();
  $('#all_Publish').show();
}
if(ifPublish=='no'){
  $('#all').show();
  $('#all_Publish').hide();
  //出现通知主体
  $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://182.61.16.76:8080/RecruitmentWebsite/manager/getNoticeById",
            data:JSON.stringify(obj),
            dataType: 'text',
            timeout: 600000,
            success: function (data){
                let notice = JSON.parse(data);
                $("#title").html(notice.title);
                $("#data").html(notice.data);
                $("#context").text(notice.context);
               },              
            error:function(XMLHttpRequest){  //请求失败的回调方法
                alert("Error: "+XMLHttpRequest.status);
               }
        });
  if(ifRevise=='yes'){
    //判断是否为管理员 是管理员出现能修改的文本框
     $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://182.61.16.76:8080/RecruitmentWebsite/userIsManager",
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
  }
}
//管理员修改通知
$(document).ready(function(){
        $("#notice_revise").click(function(){
        var text = $("#context_revise").val();
        obj.context = text;
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://182.61.16.76:8080/RecruitmentWebsite/manager/updateNotice",
            data:JSON.stringify(obj),
            dataType: 'text',
            timeout: 600000,
            success:function(data){
             var thedata = JSON.parse(data);
             if(thedata.state==5){//修改成功
                alert(thedata.msg);
                $("#context").text(text);
            }
             else if(thedata.state==-1){//修改失败
                alert(thedata.msg);}
            },
            error:function(XMLHttpRequest){  //请求失败的回调方法
            alert("Error: "+XMLHttpRequest.status);
            }            
           });
       
     })
})
//管理员发布通知
$(document).ready(function(){
        $("#notice_publish").click(function(){
        var title = $("#title_publish").val();
        var text = $("#context_publish").val();
        var Notice = new Object();
        Notice.title=title;
        Notice.context=text;
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://182.61.16.76:8080/RecruitmentWebsite/manager/addNotice",
            data:JSON.stringify(Notice),
            dataType: 'text',
            timeout: 600000,
            success:function(data){
             var thedata = JSON.parse(data);
             alert(thedata.msg);
            },
            error:function(XMLHttpRequest){  //请求失败的回调方法
            alert("Error: "+XMLHttpRequest.status);
            }            
           });
       
     })
})  






