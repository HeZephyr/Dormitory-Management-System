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
			维修记录
		</div>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
					<th>维修编号</th>
					<th>申请人</th>
					<th>宿舍</th>
						<th>申请时间</th>
						<th>维修备注</th>
					<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach  varStatus="i" var="record" items="${repairRecordList }">
					<tr>
						<td>${record.record_id }</td>
						<td>${record.userName }</td>
						<td>${record.room_id }</td>
						<td>${record.record_time}</td>
						<td>${record.repair_remark}</td>
						<td>
							<button class="btn btn-mini btn-danger" type="button" onclick="javascript:window.location='repair?action=del&record_id=${record.record_id}'">处理</button>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red">${error }</font></div>
</div>