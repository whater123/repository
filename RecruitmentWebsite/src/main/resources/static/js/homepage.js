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

    


