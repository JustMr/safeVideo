<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>SafeStudy_注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/style-clear.css">
	<link rel="stylesheet" type="text/css" href="css/style-login.css">
	
  </head>
  
  <body>
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
	<!-- 注册框 -->
	<div id="reg_mid">
		<div id="reg_box">
			<div id="reg_tips">
				<p>请不要使用一下非法字符，否则无法注册成功。</p>
				<p>"~", "`", "!", "#", "$", "%", "^", "&amp;", "*", "{", "}", "[", "]", "(", ")"," "(空格)</p>
				<p>":", ";", "'", "|", "\\\\", "&lt;", "&gt;", "?", "/", "	&lt;&lt;", "&gt;&gt;", "||", "//"</p>
				<p>"admin", "administrators", "administrator", "管理员", "系统管理员"</p>
				<p>"select", "delete", "update", "insert", "create", "drop", "alter", "trancate"</p>
			</div>
			<div id="reg_title">
				<label>注册账号</label>
			</div>
			<div id="regid" class="regstyle" >
				<input id="regidinp" name="regidinp" class="reg_input" type="text" placeholder="请输入邮箱">
				<div id="span0">
					<span>请您输入QQ、163、126、gmail等常用邮箱</span>
				</div>
			</div>
			<div id="regphone" class="regstyle">
				<div id="Verification1">
					<input id="regcheck1" name="regcheck1" class="regcheck" type="text" placeholder="验证码">
					<input id="phonecheck" name="phonecheck" type="button" class="checkbut" value="获取验证码">
					<div>
						<span></span>
					</div>
				</div>
			</div>
			<div id="infoBox">
				<div id="regname" class="register_box regstyle">
				<input id="name_input" class="reg_input" name="name_input" type="text" placeholder="昵称">
				<div>
					<span></span>
				</div>
			</div>
			<div id="repwd" class="register_box regstyle">
				<input id="pwd_input" class="reg_input" name="pwd_input" type="password" placeholder="密码" autocomplete="off">
				<div>
					<span></span>
				</div>
			</div>
			<div id="repwd1" class="register_box regstyle">
				<input id="pwd_input1" class="reg_input" name="pwd_input1" type="password" placeholder="确认密码" autocomplete="off">
				<div>
					<span id="errorPwd"></span>
				</div>
			</div>
			</div>
			<div id="reglawread" class="register_box">
				<input id="lawread" type="checkbox" checked="checked">
				<label>我已认真阅读并同意</label>
				<a href="javascript:void(0);">《AidaShop服务条款》</a>
			</div>
			<div id="regsurebut" class="register_box regstyle">
				<input id="regbut" type="button" value="立即注册" disabled="disabled" onclick="return register();">
			</div>
			<div id="signin"><a href="index.jsp">已有账号，立即登录</a></div>
			</div>
		</div>
		<script type="text/javascript" src="js/js-register.js"></script>
	<!-- 尾部 -->
        <div class="simplefooter">
            <p>Copyright © SafeStudy 2007-2017，All Rights Reserved</p>
        </div>
  </body>
</html>
