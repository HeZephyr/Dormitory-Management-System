<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">

$(document).ready(function(){
	$("ul li:eq(2)").addClass("active");
	$('.datatable').dataTable( {        				
		 "oLanguage": {
				"sUrl": "/DormManage/media/zh_CN.json"
		 },
		"bLengthChange": false, //改变每页显示数据数量
		"bFilter": false, //过滤功能
		"aoColumns": [
			null,
			null,
			null,
			null,
			null,
			{ "asSorting": [ ] },
			{ "asSorting": [ ] }
		]
	});
});

window.onload = function(){ 
	$("#DataTables_Table_0_wrapper .row-fluid").remove();
};
	function studentDelete(studentId) {
		if(confirm("您确定要删除这个学生吗？")) {
			window.location="student?action=delete&studentId="+studentId;
		}
	}
</script>
<style type="text/css">
	.span6 {
		width:0px;
		height: 0px;
		padding-top: 0px;
		padding-bottom: 0px;
		margin-top: 0px;
		margin-bottom: 0px;
	}

</style>
<div class="data_list">
		<div class="data_list_title">
			学生管理
		</div>
		<form name="myForm" class="form-search" method="post" action="student?action=search" style="padding-bottom: 0px">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='student?action=preSave'">添加</button>
				<span class="data_search">
					<select id="academyToSelect" name="academyToSelect" style="width: 110px;">
					<option value="">全部学院</option>
					<c:forEach var="academy" items="${academyList }">
						<option value="${academy.academy }" ${academyToSelect==academy.academy?'selected':'' }>${academy.academy }</option>
					</c:forEach>
					</select>
					<select id="buildToSelect" name="buildToSelect" style="width: 110px;">
					<option value="">全部宿舍楼</option>
					<c:forEach var="dormBuild" items="${dormBuildList }">
						<option value="${dormBuild.dorm_id }" ${buildToSelect==dormBuild.dorm_id?'selected':'' }>${dormBuild.dorm_name }</option>
					</c:forEach>
					</select>
					<select id="searchType" name="searchType" style="width: 80px;">
					<option value="name">姓名</option>
					<option value="number" ${searchType eq "number"?'selected':'' }>学号</option>
					<option value="dorm" ${searchType eq "dorm"?'selected':'' }>寝室</option>
						<option value="academy" ${searchType eq "academy"?'selected':'' }>学院</option>
					</select>
					&nbsp;<input id="s_studentText" name="s_studentText" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_studentText }">
					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
					<!-- <th>编号</th> -->
						<th>编号</th>
					<th>学号</th>
					<th>姓名</th>
					<th>性别</th>
						<th>电话</th>
						<th>户籍</th>
						<th>学院</th>
						<th>专业班级</th>
						<th>宿舍楼</th>
						<th>寝室号</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach  varStatus="i" var="student" items="${studentList }">
					<tr>
						 <td>${i.count+(page-1)*pageSize }</td>
						<td>${student.studentId }</td>
						<td>${student.userName }</td>
						<td>${student.sex }</td>
						<td>${student.tel }</td>
						<td>${student.native_place}</td>
						<td>${student.academy}</td>
						<td>${student.major_and_class}</td>
						<td>${student.dorm_name==null?"无":student.dorm_name }</td>
						<td>${student.room_id }</td>

						<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='student?action=preSave&studentId=${student.studentId }'">修改</button>&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="javascript:window.location='student?action=delete&studentId=${student.studentId }'">删除</button></td>
<%--							<button class="btn btn-mini btn-danger" type="button" onclick="studentDelete(${student.studentId })">删除</button></td>--%>
<%--						&academyList=${academyList}&dormBuildList=${dormBuildList}--%>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red">${error }</font></div>
		<%-- <div class="pagination pagination-centered">
			<ul>
				${pageCode }
			</ul>
		</div> --%>
</div>