<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
function handleReport(id)
{
		 var x = window.screen.height;
		 var y = window.screen.width;
	    var h = 500;
	    var w = 800;
	    var model = "title=word,resizable=yes,scrollbars=yes,height=" + h + ",width=" + w + ",status=yes,toolbar=no,menubar=no,location=no,top=" + (x-h)/2 + ",left=" + (y-w)/2;
	    var url = "";

	    url = "${pageContext.request.contextPath}/admin/reportmanage/showreport.do?id=" + id;//弹出窗口的页面内容
	    var oOpen = window.open(url,"adviceDetail",model);
	    oOpen.focus();
}
function handleReport1(id)
{
	location.href = "${pageContext.request.contextPath}/admin/reportmanage/showreport.do?id=" + id;
}
</script>
<div class="workingArea">
    <div class="listDataTableDiv">
        <table
                class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>任务名</th>
                <th>收货方</th>
                <th>送货方</th>
                <th>类型</th>
                <th>描述</th>
                <th> </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${suits}" var="s">
                <tr>
                    <td>${s.task.name}</td>                
                    <td>${s.task.receiverid}</td>
                    <td>${s.task.senderid}</td>
                    <td>${s.type.type}</td>
                    <td>${s.content}</td>
                    <td><a onclick="handleReport1(${s.id})">处理</a></td>
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