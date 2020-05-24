<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script>
    $(function () {
        $("ul.pagination li.disabled a").click(function () {
            return false;
        });
    });

</script>

<nav>
    <ul class="pagination">
        <li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>
            <a href="<%=reqUri%>?start=0${page.param}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>

        <li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>
            <a href="<%=reqUri%>?start=${page.start-page.count}${page.param}" aria-label="Previous">
                <span aria-hidden="true">&lsaquo;</span>
            </a>
        </li>

        <c:forEach begin="0" end="${page.totalPage-1}" varStatus="status">
            <c:if test="${status.count*page.count-page.start<=20 && status.count*page.count-page.start>=-10}">
                <li <c:if test="${status.index*page.count==page.start}">class="disabled"</c:if>>
                    <a
                            href="<%=reqUri%>?start=${status.index*page.count}${page.param}"
                            <c:if test="${status.index*page.count==page.start}">class="current"</c:if>
                    >${status.count}</a>
                </li>
            </c:if>
        </c:forEach>

        <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>
            <a href="<%=reqUri%>?start=${page.start+page.count}${page.param}" aria-label="Next">
                <span aria-hidden="true">&rsaquo;</span>
            </a>
        </li>
        <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>
            <a href="<%=reqUri%>?start=${page.last}${page.param}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>
