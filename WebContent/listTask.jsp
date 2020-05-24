<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="workingArea">
    <div class="listDataTableDiv">
        <table
                class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>任务名称</th>
                <th>积分</th>
                <th>截止时间</th>
                <th width="53px">是否加急</th>
                <th width="80px">接单</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tasks}" var="t">
                <tr>
                    <td>${t.id}</td>                
                    <td>${t.name}</td>
                    <td>${t.points}</td>
                    <td>${t.deadline}</td>
                    <td>${t.expedited}</td>
                    <td><a href="admin_productImage_list?product.id=${t.id}"><span
                            class="glyphicon glyphicon-picture"></span></a></td>
                </tr>
			</c:forEach>
            </tbody>
        </table>
    </div>
    <% String reqUri="task.do"; %>
    <div class="pageDiv">
        <%@include file="adminPage.jsp"%>
    </div>
</div>
