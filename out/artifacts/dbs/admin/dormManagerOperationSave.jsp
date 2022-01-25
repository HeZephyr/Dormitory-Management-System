<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	//检查表单是否填写正确。
	function checkForm(){
		let userName=document.getElementById("userName").value;
		let password=document.getElementById("password").value;
		let rPassword=document.getElementById("rPassword").value;
		let name=document.getElementById("name").value;
		let sex=document.getElementById("sex").value;
		let tel=document.getElementById("tel").value;
		if(userName == "" || password == "" || rPassword == "" || name == "" || sex == "" || tel == ""){
			document.getElementById("error").innerHTML="信息填写不完整！";
			return false;
		} else if(password!=rPassword){
			document.getElementById("error").innerHTML="密码填写不一致！";
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$("ul li:eq(1)").addClass("active");
	});
</script>
<div class="data_list">
	<div class="data_list_title">
		<%--根据是否存在id来判断是修改还是添加，显示不同的提示--%>
		<c:choose>
			<c:when test="${dormManager.dormmasterId != null }">
				修改管理员
			</c:when>
			<c:otherwise>
				添加管理员
			</c:otherwise>
		</c:choose>
	</div>
	<form action="dormManager?action=save&dormManagerId=${dormManager.dormmasterId }" method="post" onsubmit="return checkForm()">
		<div class="data_form" >
			<table align="center">
				<%--因为修改不能修改id，所以判断是否存在宿管id。--%>
				<c:if test="${dormManager.dormmasterId==null}">
					<tr>
						<td>
							<span style="color: red; ">*</span>宿管工号:
						</td>
						<td>
							<input type="text" id="dormmasterId" name="dormmasterId" value="${dormManager.dormmasterId }"/>
						</td>
					</tr>
				</c:if>
				<tr>
					<td>
						<span style="color: red; ">*</span>姓名：
					</td>
					<td>
						<input type="text" id="userName"  name="userName" value="${dormManager.userName }"  style="margin-top:5px;height:30px;" />
					</td>
				</tr>
				<tr>
					<td>
						<span style="color: red; ">*</span>密码：
					</td>
					<td>
						<input type="password" id="password"  name="password" value="${dormManager.password }"  style="margin-top:5px;height:30px;" />
					</td>
				</tr>
				<tr>
					<td>
						<span style="color: red; ">*</span>重复密码：
					</td>
					<td>
						<input type="password" id="rPassword"  name="rPassword" value="${dormManager.password }"  style="margin-top:5px;height:30px;" />
					</td>
				</tr>
				<tr>
					<td>
						<span style="color: red; ">*</span>性别：
					</td>
					<td>
						<select id="sex" name="sex" style="width: 90px;">
							<option value="">请选择...</option>
							<option value="男" ${dormManager.sex eq "男"?'selected':'' }>男</option>
							<option value="女" ${dormManager.sex eq "女"?'selected':'' }>女</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<span style="color: red; ">*</span>管理宿舍楼：
					</td>
					<td>
						<input type="text" id="dorm_id"  name="dorm_id" value="${dormManager.dorm_id }"  style="margin-top:5px;height:30px;" />
					</td>
				</tr>
				<tr>
					<td>
						<span style="color: red; ">*</span>联系电话：
					</td>
					<td><input type="text" id="tel"  name="tel" value="${dormManager.tel }"  style="margin-top:5px;height:30px;" /></td>
				</tr>
			</table>
			<div align="center">
				<input type="submit" class="btn btn-primary" value="保存"/>
				&nbsp;
				<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
			</div>
			<div align="center">
				<%--显示错误信息--%>
				<span id="error" style="color: red; ">
					${error }
				</span>
			</div>
		</div>
	</form>
</div>