<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
function comfiredresetpassword(IDNumber)
{
	if(confirm("你确定要重置该用户的密码嘛？"))
	{
		 location.href="${pageContext.request.contextPath}/admin/usermanage/resetpassword.do?IDNumber=" + IDNumber;
		 alert("密码重置成功！");
	}
}
function comfireddeleteaccount(IDNumber)
{
	if(confirm("你确定要注销该用户嘛？"))
	{
		 location.href="${pageContext.request.contextPath}/admin/usermanage/deleteaccount.do?IDNumber=" + IDNumber;
		 alert("该用户已被注销。");
	}
}
function comfiredstopaccount(IDNumber)
{
	if(confirm("你确定要封禁该用户嘛？"))
	{
		 location.href="${pageContext.request.contextPath}/admin/usermanage/stopaccount.do?IDNumber=" + IDNumber;
		 alert("该用户已被封禁。");
	}
}
function comfiredwakeaccount(IDNumber)
{
	if(confirm("你确定要激活该用户嘛？"))
	{
		 location.href="${pageContext.request.contextPath}/admin/usermanage/wakeaccount.do?IDNumber=" + IDNumber;
		 alert("该用户已被激活。");
	}
}
function changepoints(IDNumber,points)
{
	var newpoints = prompt("该用户的现有积分为：" + points + "。\n请输入调整后的积分：","");
	if(newpoints)
	{
		location.href="${pageContext.request.contextPath}/admin/usermanage/changepoints.do?IDNumber=" + IDNumber + "&points=" + newpoints;
		alert("该用户积分已被调整为：" + newpoints) + "。";	
	}
}
</script>
<div class="workingArea">
    <div class="listDataTableDiv">
        <table
                class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>序号</th>
                <th>用户名</th>
                <th>积分</th>
                <th> </th>
                <th> </th>
                <th> </th>
                <th> </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="u">
                <tr>
                    <td>${u.id}</td>                
                    <td>${u.username}</td>
                    <td>${u.points}</td>
                    <c:if test="${u.state != 1}">
                    <td><a onclick="comfiredwakeaccount(${u.IDNumber})">激活用户</a></td>
                    </c:if>
                    <c:if test="${u.state == 1}">
                    <td><a onclick="comfiredresetpassword(${u.IDNumber})">重置密码</a></td>
                    <td><a onclick="comfireddeleteaccount(${u.IDNumber})">注销</a></td>
                    <td><a onclick="comfiredstopaccount(${u.IDNumber})">封禁</a></td>
                    <td><a onclick="changepoints(${u.IDNumber},${u.points })">积分调整</a></td>
                    </c:if>
                </tr>
			</c:forEach>
            </tbody>
        </table>
    </div>
    <% String reqUri="list.do"; %>
   <div class="pageDiv">
       <%@include file="adminPage.jsp"%>
    </div>
</div>