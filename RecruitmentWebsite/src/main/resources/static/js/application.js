//这一步得到传的参
var url = decodeURI(window.location.href);
var argsIndex = url .split("?name=");
var arg = argsIndex[1];
$('#logo_name').html(arg);
//固定选择从查询的条件
if(arg==='大佬'){
        $("#major_follow").after('<div id="department_follow">志愿部门:<select id="department_search"><option value=""></option><option value="硬件技术部">硬件技术部</option><option value="服务技术部">服务技术部</option><option value="算法应用部">算法应用部</option><option value="前端开发部">前端开发部</option><option value="移动开发部">移动开发部</option><option value="应用策划部">应用策划部</option><option value="游戏程序部">游戏程序部</option><option value="游戏策划部">游戏策划部</option><option value="游戏美术部">游戏美术部</option><option value="财务部">财务部</option></select></div>');
        $("#department_follow").after('<div>是否为大佬:<label id="isdalao_search"></label></div>');
        $("#isdalao_search").text('是'); 
}
else{
        $("#major_follow").after('<div id="department_follow">志愿部门:<label id="department_search"></label></div>');
        $("#department_search").text(arg);
        $("#department_follow").after('<div>是否为大佬:<select id="isdalao_search"><option value=""></option><option value="1">是</option><option value="0">否</option></select></div>'); 
}
//通过id值得到学生信息的函数
function getInfById(x){
  var get = 'id' + x.split('name')[1]; 
  var id = document.getElementById(get);
  var url = 'form?if='+id.value;
  window.open(url);
}              
//显示所有名单的函数                
$(document).ready(function(){
        $("#show_all").click(function(){
          document.getElementById('left-search').style.display='none';
          document.getElementById('left-pic').style.display='inline-block';
          var msg = new Object();
          if(arg==='大佬'){
          msg.isdalao = 1;}//0表示不再 1表示在
          else{
          msg.department = arg;}  
          $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/app/manager/queryStudents",
            data: JSON.stringify(msg),
            dataType: 'text',
            timeout: 600000,
            success: function (data){
                //防止因为多次点击出现重复
                var allName=document.getElementsByClassName('name_');
                var allId=document.getElementsByClassName('id_');
                var allState=document.getElementsByClassName('state_');
                var allFinalState=document.getElementsByClassName('finalState_');
                $(allName).remove();
                $(allId).remove();
                $(allState).remove();
                $(allFinalState).remove();
              
                let user = JSON.parse(data);
                var number = 1;
                for(var p in user){

                $("#name_follow").after('<label id="name" class="name_"></label>');
                $("#name").text(user[p].name);
                document.getElementById('name').onclick=function(){
                getInfById(''+this.id);}; //给名字绑定点击事件 查询信息
                document.getElementById('name').id='name'+number;

                $("#id_follow").after('<input type="text" id="id" class="id_">');
                $("#id").val(user[p].id);
                document.getElementById('id').id='id'+number;

                $("#state_follow").after('<select id="state" class="state_"><option value="0">待审核/待审核</option><option value="1">已通过/待审核</option><option value="2">已通过/已通过</option><option value="3">未通过/待审核</option><option value="4">已通过 /未通过</option></select>'); 
                $("#state").val(user[p].stageState);
                document.getElementById('state').id='state'+number;

                $("#finalState_follow").after('<label id="finalState" class="finalState_">'); 
                $("#finalState").text(user[p].finalState);
                document.getElementById('finalState').id='finalState'+number;
       
                number++;
                }
               

             },
            error:function(XMLHttpRequest){  //请求失败的回调方法
                alert("Error: "+XMLHttpRequest.status);
            }
        });
      }
   )});
//选择查询隐藏图片 显示查询页面
$(document).ready(function(){
  $("#search").click(function(){
      document.getElementById('left-search').style.display='inline-block';
      document.getElementById('left-pic').style.display='none';
  });
});
//选择查询按钮
$(document).ready(function(){
  $("#search_2").click(function(){
            var msg = new Object();
            var text = $("#name_search").val();
            if(text!=''&&text!=null){
              msg.name=text;
            }
            //固定选择的条件
            if(arg==='大佬'){
               msg.isdalao = 1;
               text= $("#department_search").val();
               if(text!=''&&text!=null){
               msg.department=text;
               }
            }
            else{
               msg.department = arg;
               text= $("#isdalao_search").val();
               if(text!=''&&text!=null){
               msg.isdalao=text;
               }
            }
            text= $("#number_search").val();
            if(text!=''&&text!=null){
              msg.number=text;
            }
            text= $("#classroom_search").val();
            if(text!=''&&text!=null){
              msg.classroom=text;
            }
            text= $("#id_search").val();
            if(text!=''&&text!=null){
              msg.id=text;
            }
            text= $("#major_search").val();
            if(text!=''&&text!=null){
              msg.major=text;
            }
            text= $("#college_search").val();
            if(text!=''&&text!=null){
              msg.college=text;
            }
            text= $("#stageState_search").val();
            if(text!=''&&text!=null){
              msg.stageState=text;
            }
            $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/app/manager/queryStudents",
            data: JSON.stringify(msg),
            dataType: 'text',
            timeout: 600000,
            success: function (data){
                let user = JSON.parse(data);
                var allName=document.getElementsByClassName('name_');
                var allId=document.getElementsByClassName('id_');
                var allState=document.getElementsByClassName('state_');
                var allFinalState=document.getElementsByClassName('finalState_');
                $(allName).remove();
                $(allId).remove();
                $(allState).remove();
                $(allFinalState).remove();
                var number = 1;
                for(var p in user){

                $("#name_follow").after('<label id="name" class="name_"></label>');
                $("#name").text(user[p].name);
                document.getElementById('name').onclick=function(){
                getInfById(''+this.id);}; //给名字绑定点击事件 查询信息
                document.getElementById('name').id='name'+number;

                $("#id_follow").after('<input type="text" id="id" class="id_">');
                $("#id").val(user[p].id);
                document.getElementById('id').id='id'+number;

                $("#state_follow").after('<select id="state" class="state_"><option value="0">待审核/待审核</option><option value="1">已通过/待审核</option><option value="2">已通过/已通过</option><option value="3">未通过/待审核</option><option value="4">已通过 /未通过</option></select>'); 
                $("#state").val(user[p].stageState);
                document.getElementById('state').id='state'+number;

                $("#finalState_follow").after('<label id="finalState" class="finalState_">'); 
                $("#finalState").text(user[p].finalState);
                document.getElementById('finalState').id='finalState'+number;
       
                number++;
                }
               },
            error:function(XMLHttpRequest){  //请求失败的回调方法
                alert("Error: "+XMLHttpRequest.status);
            }
        });
  })
});
//修改报名信息                
$(document).ready(function(){
      $("#save_all").click(function(){
      var message = confirm("确认要保存本次修改吗?");
      if(message==true){
      var newIds = document.getElementsByClassName('id_');
      var newState = document.getElementsByClassName('state_');
      var newFinalState = document.getElementsByClassName('finalState_');
          var msg = new Object();
        if(document.getElementById('left-search').style.display!='none'){
           var text = $("#name_search").val();
            if(text!=''&&text!=null){
              msg.name=text;
            }
            text= $("#number_search").val();
            if(text!=''&&text!=null){
              msg.number=text;
            }
            text= $("#college_search").val();
            if(text!=''&&text!=null){
              msg.college=text;
            }
            text= $("#classroom_search").val();
            if(text!=''&&text!=null){
              msg.classroom=text;
            }
            text= $("#id_search").val();
            if(text!=''&&text!=null){
              msg.id=text;
            }
            text= $("#major_search").val();
            if(text!=''&&text!=null){
              msg.major=text;
            }
            text= $("#department_search").val();
            if(text!=''&&text!=null){
              msg.department=text;
            }
            text= $("#isdalao_search").val();
            if(text!=''&&text!=null){
              msg.isdalao=text;
            }
            text= $("#stageState_search").val();
            if(text!=''&&text!=null){
              msg.stageState=text;
            }
        }
        else{
          if(arg==='大佬'){
          msg.isdalao = 1;}//0表示不再 1表示在
          else{
          msg.department = arg;} 
        } 
          $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/app/manager/queryStudents",
            data: JSON.stringify(msg),
            dataType: 'text',
            timeout: 600000,
            success: function (data){
                let user = JSON.parse(data);
                var length = user.length-1;
                var length_ = user.length-1;
                for(var p in user){
                if(newIds[length].value!=user[p].id)
                {
                    var Obj = new Object();
                    Obj.id=user[p].id;
                    Obj.targetId=newIds[length].value;
                    $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "app/manager/editId",
                    data: JSON.stringify(Obj),
                    dataType: 'text',
                    timeout: 600000,
                    success: function (data){
                      thedata = JSON.parse(data);
                      alert(thedata.msg);
                    },
                    error:function(XMLHttpRequest){  //请求失败的回调方法
                    alert("Error: "+XMLHttpRequest.status);
                    }
                  }); 
                }
                if(newState[length].value!=user[p].stageState)
                {
                    var Obj = new Object();
                    var arrayObj = new Array();
                    arrayObj.push(user[p].id);
                    Obj.ids=arrayObj;
                    Obj.status=newState[length].value;
                    $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/app/manager/editStatus",
                    data: JSON.stringify(Obj),
                    dataType: 'text',
                    timeout: 600000,
                    success: function (data){
                      thedata = JSON.parse(data);
                      alert(thedata.msg);
                        $.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: "/app/manager/queryStudents",
                            data: JSON.stringify(msg),
                            dataType: 'text',
                            timeout: 600000,
                            success:function(data){
                                let user_ = JSON.parse(data);
                                for(var p in user_){
                                    newFinalState[length_].innerText=user_[p].finalState;
                                    length_--;
                                }},
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
                length--;
                }
               },
            error:function(XMLHttpRequest){  //请求失败的回调方法
                alert("Error: "+XMLHttpRequest.status);
            }  
       });
     }
 });
})
     