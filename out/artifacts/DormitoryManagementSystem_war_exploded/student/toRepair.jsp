<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: 19413
  Date: 2021-01-07
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
    function checkForm(){
        var userName=document.getElementById("userName").value;
        var password=document.getElementById("password").value;
        var rPassword=document.getElementById("rPassword").value;
        var dormBuildId=document.getElementById("dormBuildId").value;
        document.getElementById()
        var dormName=document.getElementById("dormName").value;
        var sex=document.getElementById("sex").value;
        var tel=document.getElementById("tel").value;
        if(userName==""||password==""||rPassword==""||sex==""||tel==""||dormBuildId==""||dormName==""){
            document.getElementById("error").innerHTML="信息填写不完整！";
            return false;
        } else if(password!=rPassword){
            document.getElementById("error").innerHTML="密码填写不一致！";
            return false;
        }
        return true;
    }

    $(document).ready(function(){
        $("ul li:eq(2)").addClass("active");
    });
</script>
<div class="data_list">
    <div class="data_list_title">
        填写维修申请
    </div>

    <%
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        System.out.println();// new Date()为获取当前系统时间
        String nowDate = df.format(new Date());
    %>
    <form action="repair?action=save" method="post" onsubmit="return checkForm()">
        <div class="data_form" >

            <table align="center">

                <tr>
                    <td><font color="red">*</font>姓名：</td>
                    <td><input type="text" id="userName"  name="userNameNew"   style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>寝室：</td>
                    <td><input type="text" id="dormName"  name="room_idNew"   style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>时间：</td>
                    <td><input type="text" id="rPassword"  name="record_timeNew" value="<%=nowDate%>"  style="margin-top:5px;height:30px;" /></td>
                </tr>

                <tr>
                <tr>
                    <td>&nbsp;维修备注：</td>
                    <td><textarea id="detail" name="repair_remarkNew" rows="10">${dormBuild.remark }</textarea></td>
                </tr>
                </tr>
            </table>
            <div align="center">
                <input type="submit" class="btn btn-primary" value="提交申请"/>
<%--                &nbsp;<button class="btn btn-primary" type="button" onclick="javascript:window.location='repair?action=save'">提交申请</button>--%>
            </div>
            <div align="center">
                <font id="error" color="red">${error }</font>
            </div>
        </div>
    </form>
</div>
