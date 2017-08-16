<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>SafeStudy_邮箱激活</title>
    
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
    <div id='regsult_wrap'>
    	<c:if test="${obj.success==true}">
    		恭喜您，${obj.obj}${obj.msg }
    	</c:if>
    	<c:if test="${obj.success==false}">
    		对不起,${obj.msg }
    	</c:if>
    </div>
    <!-- 尾部 -->
    <div class="simplefooter">
        <p>Copyright © SafeStudy 2007-2017，All Rights Reserved</p>
    </div>
  </body>
</html>
