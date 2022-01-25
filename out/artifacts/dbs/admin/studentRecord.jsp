<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
$(document).ready(function(){
	$("ul li:eq(4)").addClass("active");
	$('.form_date').datetimepicker({
	    language:  'en',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});
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
	$("#DataTables_Table_0_wrapper .row-fluid").remove();
});

window.onload = function(){ 
	$("#DataTables_Table_0_wrapper .row-fluid").remove();
};
	function recordDelete(recordId) {
		if(confirm("您确定要删除这条记录吗？")) {
			window.location="absenceRecord?action=delete&recordId="+recordId;
		}
	}
</script>

<div class="data_list">
		<div class="data_list_title">
			晚归记录
		</div>
		<form name="myForm" class="form-search" method="post" action="absenceRecord?action=search" style="padding-bottom: 0px">
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
					<th>日期</th>
					<th>学号</th>
					<th>姓名</th>
						<th>学院</th>
						<th>专业班级</th>
					<th>宿舍楼</th>
					<th>寝室</th>
					<th>备注</th>
					<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach  varStatus="i" var="record" items="${recordList }">
					<tr>
						<td>${record.absenceTime }</td>
						<td>${record.studentId }</td>
						<td>${record.name }</td>
						<td>${record.academy}</td>
						<td>${record.major_and_class}</td>
						<td>${record.dorm_name==null?"无":record.dorm_name }</td>
						<td>${record.room_id }</td>
						<td>${record.remark }</td>
						<td>
							<button class="btn btn-mini btn-danger" type="button" onclick="recordDelete(${record.recordId })">删除</button></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red">${error }</font></div>
</div>