<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: 19413
  Date: 2021-01-07
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
    function checkForm(){
        var dormBuildName=document.getElementById("dormBuildName").value;
        if(dormBuildName==null||dormBuildName==""){
            document.getElementById("error").innerHTML="请将信息填写完整！";
            return false;
        }
        return true;
    }

    $(document).ready(function(){
        $("ul li:eq(3)").addClass("active");
    });
</script>

<%
    //获取当前时间
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String nowTime = sdf.format(new Date());
%>
<div class="data_list">
    <div class="data_list_title">
        <h1 align="center">换寝申请</h1>
    </div>
    <form action="ChangeRoomRecordServlet?action=save" method="post" onsubmit="return checkForm()">
        <div class="data_form" >
            <table align="center">

                <tr>
                    <td><font color="red">*</font>学号：</td>
                    <td><input type="text" id="dormBuildName"  name="applyStudentIdNew" value="${currentStudent.studentId}" style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>姓名：</td>
                    <td><input type="text" id="dormBuildName"  name="applyUserNameNew"   value="${currentStudent.userName}" style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>原来宿舍：</td>
                    <td><input type="text" id="dormBuildName"  name="oldRoom_idNew"   value="${currentStudent.room_id}" style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>现在宿舍：</td>
                    <td><input type="text" id="dormBuildName"  name="newRoom_idNew"   style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>申请时间：</td>
                    <td><input type="text" id="dormBuildName"  name="record_timeNew"  value = "<%=nowTime%>" readonly = "true" style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td>&nbsp;申请理由：</td>
                    <td><textarea id="detail" name="applyReasonNew" rows="10"></textarea></td>
                </tr>
            </table>
            <div align="center">
                <input type="submit" class="btn btn-primary" value="提交申请"/>
                &nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
            </div>
            <div align="center">
                <font id="error" color="red">${error }</font>
            </div>
        </div>
    </form>
</div>
