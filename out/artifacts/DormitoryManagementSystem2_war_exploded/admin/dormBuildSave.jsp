<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var dormBuildName=document.getElementById("dormBuildName").value;
		if(dormBuildName==null||dormBuildName==""){
			document.getElementById("error").innerHTML="名称不能为空！";
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$("ul li:eq(3)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${dormBuild.dorm_id!=null }">
				修改宿舍楼
			</c:when>
			<c:otherwise>
				添加宿舍楼
			</c:otherwise>
		</c:choose>
		</div>
		<form action="dormBuild?action=save&dormBuildId=${dormBuild.dorm_id}" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
<%--				<input type="hidden" id="dormBuildId" name="dormBuildId" value="${dormBuild.dormBuildId }"/>--%>

					<table align="center">
						<c:choose>
							<c:when test="${dormBuild.dorm_id==null }">
								<tr><td><font color="red">*</font>宿舍楼id：</td>
									<td><input type="text" id="dormBuildId" name="dormBuildNew" value="${dormBuild.dorm_id }"/></td></tr>
							</c:when>
							<c:otherwise>

							</c:otherwise>
						</c:choose>
						<tr>
							<td><font color="red">*</font>名称：</td>
							<td><input type="text" id="dormBuildName"  name="dormBuildName" value="${dormBuild.dorm_name }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td>&nbsp;简介：</td>
							<td><textarea id="detail" name="detail" rows="10">${dormBuild.remark }</textarea></td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
					</div>
					<div align="center">
						<font id="error" color="red">${error }</font>
					</div>
			</div>
		</form>
</div>