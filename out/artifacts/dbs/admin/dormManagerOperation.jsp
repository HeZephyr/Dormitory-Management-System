<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function dormManagerDelete(dormManagerId) {
		if(confirm("您确定要删除这个宿管吗？")) {
			alert(dormManagerId);
			window.location="dormManager?action=delete&dormManagerId="+dormManagerId;
		}
	}
	
	$(document).ready(function(){
		$("ul li:eq(1)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			宿舍管理员管理
		</div>
		<form name="myForm" class="form-search" method="post" action="dormManager?action=search">
				<%--添加按钮，点击后跳转实现preSave动作。--%>
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='dormManager?action=preSave'">添加</button>
				<span class="data_search">
					<select id="searchType" name="searchType" style="width: 80px;">
						<option value="name" ${searchType eq "userName"?'selected':'' }>姓名</option>
						<option value="userName" ${searchType eq "userName"?'selected':'' }>用户名</option>
					</select>
					&nbsp;<input id="s_dormManagerText" name="s_dormManagerText" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_dormManagerText }">
					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<%--利用table标签展示数据。--%>
			<table class="table table-hover table-striped table-bordered">
				<tr>
					<th>编号</th>
					<th>工号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>电话</th>
					<th>宿舍楼</th>
					<th>操作</th>
				</tr>
				<c:forEach  varStatus="i" var="dormManager" items="${dormManagers }">
					<tr>
						<td>${i.count+(page-1)*pageSize }</td>
						<td>${dormManager.dormmasterId}</td>
						<td>${dormManager.userName }</td>
						<td>${dormManager.sex }</td>
						<td>${dormManager.tel }</td>
						<td>${dormManager.dorm_id==null?"无":dormManager.dorm_id }</td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='dormManager?action=preSave&dormManagerId=${dormManager.dormmasterId }&password=${dormManager.password }'">修改</button>&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="javascript:window.location='dormManager?action=delete&dormManagerId=${dormManager.dormmasterId}'">删除</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div align="center">
			<span style="color: red; ">${error }</span>
		</div>
		<div class="pagination pagination-centered">
			<ul>
				<%--pageCode为一系列无序列表的页面管理代码--%>
				${pageCode }
			</ul>
		</div>
</div>