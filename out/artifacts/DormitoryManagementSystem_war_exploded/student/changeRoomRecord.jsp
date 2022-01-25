<%--
  Created by IntelliJ IDEA.
  User: 19413
  Date: 2021-01-07
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
    $(document).ready(function(){
        $("ul li:eq(2)").addClass("active");
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
</script>

<div class="data_list">
    <div class="data_list_title">
        申请换寝记录
    </div>
    <form name="myForm" class="form-search" method="post" action="absenceRecord?action=search" style="padding-bottom: 0px">
        <button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='ChangeRoomRecordServlet?action=preSave'">申请换寝</button>
        <span class="data_search">

					</select>
            &nbsp;<input id="s_studentText" name="s_studentText" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_studentText }">
<%--					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>--%>
				</span>
    </form>
    <div>
        <table class="table table-striped table-bordered table-hover datatable">
            <thead>
            <tr>
                <th>日期</th>
                <th>学号</th>
                <th>姓名</th>
                <th>原宿舍</th>
                <th>想去的宿舍</th>
                <th>申请理由</th>
                <th>审批状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach  varStatus="i" var="record" items="${allChangeRoomRecordList }">
                <tr>
                    <td>${record.record_time }</td>
                    <td>${record.applyStudentId }</td>
                    <td>${record.applyUserName }</td>
                    <td>${record.oldRoom_id}</td>
                    <td>${record.newRoom_id}</td>
                    <td>${record.applyReason}</td>
                    <td>
<%--                        <c:choose>--%>
<%--                            <c:when test="${record.isAgree== 0} ">--%>
<%--                                未审批--%>
<%--                            </c:when>--%>
<%--                            <c:when test="${record.isAgree == 1} ">--%>
<%--                                同意--%>
<%--                            </c:when>--%>
<%--                            <c:when test="${record.isAgree== 2} ">--%>
<%--                                不同意--%>
<%--                            </c:when>--%>
<%--                            <c:otherwise>--%>
<%--                                ${record.isAgree== 0}到底是多少--%>
<%--                            </c:otherwise>--%>
<%--                         </c:choose>--%>
                        <c:if test="${record.isAgree== 0}" >未审批</c:if>
                        <c:if test="${record.isAgree== 1}">同意</c:if>
                        <c:if test="${record.isAgree== 2}">不同意</c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div align="center"><font color="red">${error }</font></div>
</div>
