<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
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
                <th> </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cancleTasks}" var="c">
                <tr>
                    <td>${c.task.name}</td>                
                    <td>${c.task.receiverid}</td>
                    <td>${c.task.senderid}</td>
                    <td>${c.type.type}</td>
                    <td><a href="${pageContext.request.contextPath}/admin/canclemanage/showDetail.do?id=${c.id}">审核</a></td>
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