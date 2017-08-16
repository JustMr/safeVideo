/**
 * Created by Liu Shuangbo on 2016/11/21.
 */
var regidinp = document.getElementById("regidinp");
var pwdIn = document.getElementById("pwd_input");
var pwdInput1 = document.getElementById("pwd_input1");
var regbut = document.getElementById("regbut");
var count = 0; //防止重复提交
var flag = 0; //密码，满足标识0:不满足，1:满足
var flag1 = 0; //用户名，满足标识0:不满足，1:满足
var flag2 = 1; //非法字符，满足标识0:存在，1:不存在

window.onload = regcheck;
regidinp.onpropertychange = regidinp.oninput = regidinp.onchange = regcheck;
pwdInput1.onpropertychange = pwdInput1.oninput = pwdInput1.onchange = pwdcheck;
pwdIn.onpropertychange = pwdIn.oninput = pwdIn.onchange = pwdcheck;
/*------register.jsp 注册提交参数------------*/
function register() {
	var regidinp1 = document.getElementById("regidinp");
	var pwdInput = document.getElementById("pwd_input").value;
	var nameInput = document.getElementById("name_input").value;
	
	if(flag==0){
		var pwdInput1Val = pwdInput1.value;
		if (pwdInput==""&&pwdInput1Val=="") {
			alert("密码不能为空");
		}else {
			alert("两次密码不相同");
		}
		return false;
	}else if(nameInput=="") {
		alert("昵称不能为空");
		return false;
	}else{
		checkQuote();
		if(count==0&&flag==1&&flag1==1&&flag2==1){
			//input输入满足要求，提交添加申请
			count = count + 1;
			var value = regidinp1.value;
			var xhr;
			if (window.ActiveXObject) {
				//测试ActiveX是否存在
				try {
					xhr = new ActiveXObject("Microsoft.XMLHTTP");//低版本IE
				} catch (e) {
					xhr = new ActiveXObiect("Msxml2.XMLHTTP");//高版本IE
				}
				
			} 
			else if (window.XMLHttpRequest) {
				//测试XHR是否已经被定义
				xhr = new XMLHttpRequest();
			}
			else {
				//如果不支持AJAX则抛出错误
				throw new Error("Ajax is not supported by this browser");
			}
			xhr.open("GET", "./register/activesub.action?email=" + value+"&nickName="+encodeURI(encodeURI(nameInput))+"&pwd="+encodeURI(encodeURI(pwdInput)),true);
			xhr.send(null);
			//就绪状态处理器
			xhr.onreadystatechange = function() {
				if(this.readyState == 4) {			//忽略除DONE状态之外的所有状态
					if (this.status >= 200 && this.status <300) {
						//成功
						console.log("data:"+xhr.responseText);
						var data = JSON.parse(xhr.responseText);
						if (data.success==true) {
							location.href="./jsp/register/registeresult.jsp?value="+value;
						}else {
							if (data.msg=="unactivated") {
								alert("邮箱待激活，请前去邮箱激活");
							}else {
								alert("邮箱已注册，请直接登录");
							}
						}
					}
					else {
						//出错
						location.href="error.html";
					}
				}
			};
		}else {
			console.log("count:"+count);
			if(flag2 == 0) {
				alert("包含非法字符");
			}
			return false;
		}
	}
}
/* register.jsp 相同密码判断*/
function pwdcheck() {
	var pwdInput = document.getElementById("pwd_input").value;
	var pwdInput1Val = pwdInput1.value;
	var errorPwd = document.getElementById("errorPwd");
	if(pwdInput!=pwdInput1Val){
		errorPwd.innerHTML = "两次密码不相同";
		flag=0;
	}else{
		if (pwdInput==""&&pwdInput1Val=="") {
			errorPwd.innerHTML = "密码不能为空";
			flag=0;
		}else {
			errorPwd.innerHTML = "密码相同";
			flag=1;
		}
	}
}
/*register.jsp 邮箱、电话号码正则判断*/
function regcheck () {
	var patt0 = /^\w+@\w+(\.\w+)+$/;	//邮箱正则判断
//	var patt1 = /^1[3|4|5|7|8]\d{9}$/;	//手机号正则判断
	var patt2 = /^[0-9]*$/;				//包含非数字符号
	var regidinp1 = document.getElementById("regidinp");
	var span0 = document.getElementById("span0");
	var regphone = document.getElementById("regphone");
	var regbox = document.getElementById("reg_box");
	var infoBox = document.getElementById("infoBox");
	
	if(!patt2.test(regidinp1.value)) {
		if(!patt0.test(regidinp1.value)) {
			//包含非数字字符，且不是邮箱格式
			span0.innerHTML = "<span>请输入正确的邮箱格式</span>";
			regbox.style.height = "300px";
			regphone.style.display = "none";
			infoBox.style.display = "none";
			if(regidinp1.value.length == 0) {
				span0.innerHTML = "<span>请您输入QQ、163、126、gmail等常用邮箱，或者11位手机号。</span>";
			}
			flag1=0;
			regbut.style.cursor = "not-allowed";
			regbut.disabled = "disabled";
		}else {
			//包含非数字字符，且满足邮箱格式,向服务器发起验证请求
			var value = regidinp1.value;
			var xhr;
			if (window.ActiveXObject) {
				//测试ActiveX是否存在
				try {
					xhr = new ActiveXObject("Microsoft.XMLHTTP");//低版本IE
				} catch (e) {
					xhr = new ActiveXObiect("Msxml2.XMLHTTP");//高版本IE
				}
				
			} 
			else if (window.XMLHttpRequest) {
				//测试XHR是否已经被定义
				xhr = new XMLHttpRequest();
			}
			else {
				//如果不支持AJAX则抛出错误
				throw new Error("Ajax is not supported by this browser");
			}
			xhr.open("GET", "./register/check.action?email=" + value,true);
			xhr.send(null);
			//就绪状态处理器
			xhr.onreadystatechange = function() {
				if(this.readyState == 4) {			//忽略除DONE状态之外的所有状态
					if (this.status >= 200 && this.status <300) {
						//成功
						console.log("data:"+xhr.responseText);
						var data = JSON.parse(xhr.responseText);
						if(data.success==false){
							span0.innerHTML = "<span>邮箱可以使用</span>";
							regbox.style.height = "550px";
							regphone.style.display = "none";
							infoBox.style.display = "block";
							regbut.style.cursor = "pointer";
							regbut.disabled = false;
							flag1=1;
						}else {
							if (data.msg=="activated") {
								span0.innerHTML = "<span>该邮箱已注册</span>";
								regbox.style.height = "300px";
								regphone.style.display = "none";
								infoBox.style.display = "none";
							}else {
								span0.innerHTML = "<span>该邮箱还未激活，请去邮箱激活即可</span>";
								regbox.style.height = "300px";
								regphone.style.display = "none";
								infoBox.style.display = "none";
							}
							regbut.style.cursor = "not-allowed";
							regbut.disabled = "disabled";
							flag1=0;
						}
					}
					else {
						//出错
						location.href="error.html";
						flag1=0;
						regbut.style.cursor = "not-allowed";
						regbut.disabled = "disabled";
					}
				}
			};
		}
	} else {
//		if(!patt1.test(regidinp1.value)) {
//			//不包含非数字字符，且不是手机号格式
//			span0.innerHTML = "<span>请输入正确的11位手机号码</span>";
//			regbox.style.height = "300px";
//			regphone.style.display = "none";
//			infoBox.style.display = "none";
//			if(regidinp1.value.length == 0) {
//				span0.innerHTML = "<span>请您输入QQ、163、126、gmail等常用邮箱，或者11位手机号。</span>";
//			}
//		} else {
//			//不包含非数字字符，且是手机号格式
//			span0.innerHTML = "<span>当前手机号码可以使用</span>";
//			regbox.style.height = "600px";
//			regphone.style.display = "block";
//			infoBox.style.display = "block";
//		}
		//不包含非数字字符，非邮箱
		span0.innerHTML = "<span>请输入正确的邮箱格式</span>";
		regbox.style.height = "300px";
		regphone.style.display = "none";
		infoBox.style.display = "none";
		if(regidinp1.value.length == 0) {
			span0.innerHTML = "<span>请您输入QQ、163、126、gmail等常用邮箱</span>";
		}
		regbut.style.cursor = "not-allowed";
		regbut.disabled = "disabled";
		flag1=0;
	}
};
/*------------------- 非法字符过滤 -------------------*/
var inputTag = document.getElementsByClassName("reg_input");
function checkQuote(){
    var items = new Array("~", "`", "!", "#", "$", "%", "^", "&", "*", "{", "}", "[", "]", "(", ")"," ");
    items.push(":", ";", "'", "|", "\\\\", "<", ">", "?", "/", "<<", ">>", "||", "//");
    items.push("admin", "administrators", "administrator", "管理员", "系统管理员");
    items.push("select", "delete", "update", "insert", "create", "drop", "alter", "trancate");
    for(var j=0;j<inputTag.length;j++) {
    	var str = inputTag[j].value;
    	str = str.toLowerCase();
    	console.log(str);
    	for (var i = 0; i < items.length; i++) {
            if (str.indexOf(items[i]) >= 0) {
//            	alert("包含非法字符");
                flag2=0;
                return false;
            }
        }
    	flag2=1;
	}
}