//这一步得到传的参
var url = decodeURI(window.location.href);
var argsIndex = url .split("?name=");
var arg = argsIndex[1];
$('#logo_name').html(arg);

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
          var msg = new Object();
          if(arg==='大佬'){
          msg.isdalao = 1;}//0表示不再 1表示在
          else{
          msg.department = arg;}  
          $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/manager/queryStudents",
            data: JSON.stringify(msg),
            dataType: 'text',
            timeout: 600000,
            success: function (data){
                //防止因为多次点击出现重复
                var children = $('#name_follow').siblings();
                if(children.length==0){
              
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

                $("#finalState_follow").after('<label id="finalState">'); 
                $("#finalState").text(user[p].finalState);
                document.getElementById('finalState').id='finalState'+number;
       
                number++;
                }
               }

             },
            error:function(XMLHttpRequest){  //请求失败的回调方法
                alert("Error: "+XMLHttpRequest.status);
            }
        });
      }
   )});

//修改报名信息                
$(document).ready(function(){
      $("#save_all").click(function(){
      var message = confirm("确认要保存本次修改吗?");
      if(message==true){
      var newIds = document.getElementsByClassName('id_');
      var newState = document.getElementsByClassName('state_');
          var msg = new Object();
          if(arg==='大佬'){
          msg.isdalao = 1;}//0表示不再 1表示在
          else{
          msg.department = arg;}  
          $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/manager/queryStudents",
            data: JSON.stringify(msg),
            dataType: 'text',
            timeout: 600000,
            success: function (data){
                let user = JSON.parse(data);
                var length = user.length-1;
                for(var p in user){
                if(newIds[length].value!=user[p].id)
                {
                    var Obj = new Object();
                    Obj.id=user[p].id;
                    Obj.targetId=newIds[length].value;
                    $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/manager/editId",
                    data: JSON.stringify(Obj),
                    dataType: 'text',
                    timeout: 600000,
                    success: function (data){
                        alert("id修改成功！");
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
                    url: "/manager/editStatus",
                    data: JSON.stringify(Obj),
                    dataType: 'text',
                    timeout: 600000,
                    success: function (data){
                        alert("状态修改成功！");
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
     