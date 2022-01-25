<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html lang="zh">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>宿舍管理系统</title>
	<link href="${pageContext.request.contextPath}/style/dorm.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<link href="${pageContext.request.contextPath}/style/main.css" rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div class="container-fluid" style="padding-right: 0px;padding-left: 0px;">
	<div region="north" style="height: 100px;background-image: url('images/bgIndex.jpg')">
		<div align="left" style="width: 80%;height:100px ;float: left;padding-top: 40px;padding-left: 30px;" >
			<span style="color: white; font-size: x-large; ">宿舍管理系统</span></div>
		<div style="padding-top: 70px;padding-right: 20px;">当前用户：&nbsp;
			<span style="color: red; ">${currentUser.userName }</span></div>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span2 bs-docs-sidebar" >
			<ul class="nav nav-list bs-docs-sidenav">
				<li><a href="blank"><i class="icon-chevron-right"></i>首页</a></li>
				<li><a href="student?action=list"><i class="icon-chevron-right"></i>学生查看</a></li>
				<li><a href="applyIn?action=list"><i class="icon-chevron-right"></i>来访记录</a></li>
				<li><a href="ChangeRoomRecordServlet?action=ManagerList"><i class="icon-chevron-right"></i>换寝申请</a></li>
				<li><a href="absenceRecord?action=list"><i class="icon-chevron-right"></i>晚归记录</a></li>
				<li><a href="password?action=preChange"><i class="icon-chevron-right"></i>修改密码</a></li>
				<li><a href="login.jsp"><i class="icon-chevron-right"></i>退出系统</a></li>
			</ul>
		</div>
		<div class="span10">
			<jsp:include page="${mainPage==null?'admin/welcome.jsp':mainPage}"></jsp:include>
		</div>
	</div>
</div>
</body>
</html>