<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: 19413
  Date: 2021-01-07
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
</style>
<script type="text/javascript">
    $(document).ready(function(){
        $("ul li:eq(3)").addClass("active");
    });
    function alertSuccess(){
        alert("登记成功!");
        window.location="applyIn?action=toApplyRecord";
    }
    function alertFalse(){
        alert("登记失败!");
        window.location="applyIn?action=toApplyRecord";
    }
</script>

<div class="data_list">
    <div class="data_list_title">
      访客登记
    </div>
    <%
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        System.out.println();// new Date()为获取当前系统时间
        String nowDateTime = df.format(new Date());
    %>
    <form action="applyIn?action=save" method="post">
        <div class="data_form" >

            <table align="center">

                <tr>
                    <td><span style="color: red; ">*</span>访问者姓名：</td>
                    <td><input type="text" id="dormBuildName"  name="applyNameNew"   style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><span style="color: red; ">*</span>访问楼栋：</td>
                <td>
                    <select id="selectBuild" name="dest_visit_drom_idNew" style="width: 110px;">
                        <option value="">全部宿舍楼</option>
                        <c:forEach var="dormBuild" items="${dorms }">
                            <option value="${dormBuild.dorm_id }" ${buildToSelect==dormBuild.dorm_id?'selected':'' }>${dormBuild.dorm_name }</option>
                        </c:forEach>
                    </select>
                </td>
                </tr>
                <tr>
                    <td><span style="color: red; ">*</span>访问时间：</td>
                    <td><input type="text" id="name"  name="visit_timeNew"   value = "<%=nowDateTime%>"  readonly = "true" style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td>&nbsp;访问说明，原因、目的：</td>
                    <td><textarea id="reason" name="visitDestNew" rows="10"></textarea></td>
                </tr>
                <tr>
                    <p align="center" >${addVisitRecordStatue}</p>
                </tr>
            </table>
            <div align="center">
                <input type="submit" class="btn btn-primary" value="提交"/>
<%--                &nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>--%>
                &nbsp;<button class="btn btn-primary" type="button" onclick="javascript:window.location='applyIn?action=returnLogin'">返回</button>
            </div>
            <div align="center">
                <span id="error" style="color: red; ">${error }</span>
            </div>
            <p>${code}</p>
        </div>
    </form>
</div>
