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
            dataType: 'json',
            timeout: 600000,
            success: function (data){
                let user = JSON.parse(data);
                var number = 1;
                for(var p in user){

                $("#name_follow").after('<label id=name></label>');
                $("#name").text(user.name);
                document.getElementById('name').onclick=function(){
                getInfById(''+this.id);}; //给名字绑定点击事件 查询信息
                document.getElementById('name').id='name'+number;

                $("#id_follow").after('<input type="text" id="id">');
                $("#id").val(user[p].id);
                document.getElementById('id').id='id'+number;

                $("#interviewState_follow").after('<select id="interviewState"><option value="通过">通过</option><option value="未通过">未通过</option></select>'); 
                $("#interviewState").val(user[p].interviewState);
                document.getElementById('interviewState').id='interviewState'+number;

                $("#bigWordState_follow").after('<select id="bigWordState"><option value="通过">通过</option><option value="未通过">未通过</option></select>'); 
                $("#bigWordState").val(user[p].bigWordState);
                document.getElementById('bigWordState').id='bigWordState'+number;

                $("#finalState_follow").after('<select id="finalState"><option value="通过">通过</option><option value="未通过">未通过</option></select>'); 
                $("#finalState").val(user[p].finalState);
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
     