<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ page import="team.family.dbs.bean.Admin" %>
<%@ page import="team.family.dbs.bean.DormManager" %>
<%@ page import="team.family.dbs.bean.Student" %>
<%
	if(request.getAttribute("user") == null){
		String userName = null;
		String password = null;
		String userType = null;
		String remember = null;
		//处理cookie。
		Cookie[] cookies=request.getCookies();
		for(int i = 0; cookies != null && i < cookies.length; ++ i){
			if(cookies[i].getName().equals("dormuser")){
				userName = cookies[i].getValue().split("-")[0];
				password = cookies[i].getValue().split("-")[1];
				userType = cookies[i].getValue().split("-")[2];
				remember = cookies[i].getValue().split("-")[3];
			}
		}
		if(userName == null){
			userName = "";
		}
		if(password == null){
			password = "";
		}
		//将user放入pageContext中。
		if(userType==null){
			userType = "";
		} else if("admin".equals(userType)){
			pageContext.setAttribute("user", new Admin(userName,password));
			pageContext.setAttribute("userType", 1);
		} else if("dormManager".equals(userType)) {
			pageContext.setAttribute("user", new DormManager(userName,password));
			pageContext.setAttribute("userType", 2);
		} else if("student".equals(userType)) {
			pageContext.setAttribute("user", new Student(userName,password));
			pageContext.setAttribute("userType", 3);
		}
		if("yes".equals(remember)) {
			pageContext.setAttribute("remember", 1);
		}
	}
%>
<!doctype html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>学生宿舍管理系统</title>
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/style/login.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/login.js"></script>
</head>
<body>
<div class="container">
	<%--设置post方式，同时利用onsubmit属性来调用checkForm方法判断是否提交表单--%>
	<form name="myForm" class="form-sign-in" action="login" method="post" onsubmit="return checkForm()">
        <h2 class="form-sign-in-heading">
			<span style="color: gray; ">学生宿舍管理系统</span>
		</h2>
		<%--选择身份信息--%>
		<select id="userTypes" name="userType" class="input-block-level" >
			<option value="" disabled selected hidden>选择登录身份</option>
			<option value="admin">系统管理员</option>
			<option value="dormManager">宿舍管理员</option>
			<option value="student">学生</option>
			<option value="repairPeople">维修人员</option>
		</select>
        <input id="userName" name="userName" value="${user.userName }" type="text" class="input-block-level" placeholder="用户名">
        <input id="password" name="password" value="${user.password }" type="password" class="input-block-level" placeholder="密码" >
		<label class="checkbox">
			<input id="remember" name="remember" type="checkbox" value="remember-me" ${remember==1?'checked':''}>记住我 &nbsp;&nbsp;&nbsp;&nbsp;
			<span id="error" style="color: red; ">${error }</span>
        </label>
		  &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button class="btn btn-large btn-primary" type="submit" style="align-content:center">登录</button>
		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a  href="applyIn?action=toApplyRecord">外来访问登记</a><!--直接跳转到填写表单-->
		<p align="center" style="padding-top: 15px;">Copyright © 2021-2021 He Zhifei,Shang Zhihao,Yang Gangmin</p>
	</form>
</div>
</body>
</html>