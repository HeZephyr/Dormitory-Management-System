<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function dormBuildDelete(dormBuildId) {
		if(confirm("您确定要删除这个宿舍楼吗？")) {
			window.location="dormBuild?action=delete&dormBuildId="+dormBuildId;
		}
	}
	$(document).ready(function(){
		$("ul li:eq(3)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			宿舍楼管理
		</div>
		<form name="myForm" class="form-search" method="post" action="dormBuild?action=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='dormBuild?action=preSave'">添加</button>
				<span class="data_search">
					名称:&nbsp;&nbsp;<input id="s_dormBuildName" name="s_dormBuildName" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_dormBuildName }">
					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th width="15%">编号</th>
						<th>名称</th>
						<th>简介</th>
						<th width="20%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach  varStatus="i" var="dormBuild" items="${dormBuildList }">
					<tr>
						<td>${i.count+(page-1)*pageSize }</td>
						<td>${dormBuild.dorm_name }</td>
						<td>${dormBuild.remark==null||dormBuild.remark==""?"无":dormBuild.remark }</td>
							<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='dormBuild?action=preSave&dormBuildId=${dormBuild.dorm_id }'">修改</button>&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="dormBuildDelete(${dormBuild.dorm_id})">删除</button></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><span style="color: red; ">${error }</span></div>
		<div class="pagination pagination-centered">
			<ul>
				${pageCode }
			</ul>
		</div>
</div>