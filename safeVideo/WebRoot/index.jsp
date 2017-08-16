<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/style-clear.css">
	<link rel="stylesheet" type="text/css" href="css/style-login.css">
	
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#loginname").bind("input propertychange",valuechange);
		$("#signpwd").bind("input propertychange",valuechange);
	});
	function valuechange(){
		$("#meserrorul").empty();
	}
	function validate(){
		var patt0 = /^\w+@\w+(\.\w+)+$/;	//邮箱正则判断
		var patt1 = /^1[3|4|5|7|8]\d{9}$/;	//手机号正则判断
		var patt2 = /^[0-9]*$/;				//包含非数字符号
		var account = $("#loginname").val().trim();
		var pwd = $("#signpwd").val().trim();
		var flag = 0;
		var acctype=null;
		if(account==""||pwd=="") {
			alert("账号/密码不能为空！");
		}else if (patt0.test(account)) {
			flag = 2;
			acctype = "email";
		}else if (patt2.test(account)) {
			if(account.length==14){
				flag = 3;
				acctype = "id";
			}else if(account.length==11) {
				if(patt1.test(account)) {
					flag = 1;
					acctype = "tel";
				}
			}else {
				alert("账号输入有误！");
			}
		}else {
			alert("账号输入有误！");
		}
		
		if (flag!=0) {
			$.ajax({
				url:'${pageContext.request.contextPath }/user/login.action',
				type:'post',
				dataType:'json',
				data:{type:acctype,account:account,pwd:pwd},
				success:function(data) {
					console.log(data);
					if (data.success==true) {
						window.location.href="${pageContext.request.contextPath }/jsp/home.jsp";
					}else {
						$("#meserrorul").empty();
						var $li;
						if (data.msg=="noact") {
							$li = "<li>账号未激活,请前去邮箱激活</li>";
						}else {
							$li = "<li>账号/密码错误</li>";
						}
						$("#meserrorul").append($li);
					}
				}
			});
		}
	}
	</script>
	
  </head>
  
  <body>
  	<div class="container">
        <!-- 头部 -->
        <div id="wrap_contain">
            <div id="login_title">
                <div id="logo">
                    <a href="home.jsp" style="float: left;">
                        <img src="images/TDsafe01.jpg">
                    </a>
                    <a id="logname" style="font-weight: 500;line-height: 48px;">安全交流网站</a>
                </div>
                <div id="title_right">
                	<a href="index.jsp">请登录</a>
                    <span class="fr">您好，欢迎光临SafeStudy,</span>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <!-- 登录框 -->
        <div id="contain_mid">
            <div id="contain_mid_login">
                <img alt="SafeStudy-安全交流" src="images/TDsafe01.jpg" style="width: 315px;margin-left: 200px;">
                <div id="loginbox">
                    <div id="loginbox_mid">
                        <div id="div_title">
                            <span id="box_title">登录到AIDALShop</span>
                            <div id="reg">
                                <a href="${pageContext.request.contextPath }/jsp/register/register.jsp" >立即注册</a>
                            </div>
                        </div>
                        <div class="clear"></div>
                           <div class="login_row login_row_text">
                               <label id="login_lab_user" class="login_lab"></label>
                               <input id="loginname" class="itext" type="text" name="account" tabindex="1" autocomplete="off"
                                      placeholder="邮箱/用户名/已验证手机">
                           </div>
                           <div class="login_row login_row_text">
                               <label id="login_lab_pwd" class="login_lab"></label>
                               <input id="signpwd" class="itext" type="password" name="pwd" tabindex="2" autocomplete="off"
                                      placeholder="密码">
                           </div>
                           <div class="login_row">
                               <input id="autologin" type="checkbox" name="autologin" tabindex="3">
                               <label>自动登录</label>
                               <span id="spanfor"><a target="_blank">忘记密码？</a></span>
                           </div>
                           <div class="login_row" id="but_div">
                           	<input id="loginbut" type="button" name="login_sub" value="登       录" tabindex="4" onclick="validate();">
                           </div>
                           <div id="meserror">
                               <ul id="meserrorul">
                               </ul>
                           </div>
                           <div id="partnerlogin" style="display: none;">
                               <span>合作网站账号登录</span>
                               <div id="partnericon">
                                   <a id="qqlogin" title="QQ" target="_blank" href="***"></a>
                               </div>
                           </div>
                    </div>
                </div>
            </div>
            <div class="clear"></div>

        </div>
        <!-- 尾部 -->
        <div class="simplefooter">
            <p>Copyright © SafeStudy 2007-2017，All Rights Reserved</p>
        </div>
    </div>
  </body>
</html>
