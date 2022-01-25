<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
$(document).ready(function(){
	$("ul li:eq(1)").addClass("active");
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
		]
	});
	$("#DataTables_Table_0_wrapper .row-fluid").remove();
});

window.onload = function(){ 
	$("#DataTables_Table_0_wrapper .row-fluid").remove();
};
</script>

<div class="data_list">
		<div class="data_list_title">
			维修申请列表
		</div>

		<form name="myForm" class="form-search" method="post" action="absenceRecord?action=search" style="padding-bottom: 0px">
				<span class="data_search">

					&nbsp;
				</span>
		</form>
		<div>
			<button  class="btn btn-info" onclick="javascript:window.location='repair?action=addRepair'" >添加申请</button>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th>日期</th>
						<th>姓名</th>
						<th>寝室</th>
						<th>维修备注</th>
						<th>维修状态</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach  varStatus="i" var="record" items="${repairRecordStudentList }">
					<tr>
						<td>${record.record_time }</td>
						<td>${record.userName }</td>
						<td>${record.room_id }</td>
						<td>${record.repair_remark}</td>
						<td>
							<c:choose>
							<c:when test="${record.is_deal == 0}">
								未维修或正在维修中
							</c:when>
								<c:otherwise>
									维修完成
								</c:otherwise>
							</c:choose>

						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red">${error }</font></div>
</div>