<%@page import="qj.admin.pojo.FeedBack"%>
<%@page import="qj.admin.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="qj.admin.pojo.Suit" %>
<!DOCTYPE html>
<style type="html/css">
	.changeUserPoints
	{
		position:relative;
		top: 20px; 
		left: 30px;
		background-color:#a6ffa6;
	}
</style>
<script>
function home()
{
	location.href="${pageContext.request.contextPath}/releaseTesk.jsp";
}
function deleteTask(id)
{
	
	if(confirm("确定仅删除该被举报任务吗？"))
	{
		location.href="${pageContext.request.contextPath}/admin/feedbackManage/deletetask.do?id=" + id;
	}
}
function turnBack()
{
	location.href="${pageContext.request.contextPath}/admin/feedbackManage/list.do";
}
function noHandle(id)
{
	if(confirm("确定该任务未违规吗？"))
	{
		location.href="${pageContext.request.contextPath}/admin/feedbackManage/legaltask.do?id=" + id;
	}
}
function turnToUserManage(id)
{
	if(confirm("您确定删除该任务，并转到调整用户积分吗？"))
	{
		location.href="${pageContext.request.contextPath}/admin/feedbackManage/turnusermanage.do?id=" + id;
	}
	
}
function openUserManage()
{
	if(confirm("您确定删除该任务，并对用户积分进行调整吗？"))
	{
		var a=document.getElementById("changeUserPoints");
		a.removeAttribute("hidden");
	}
}
function closeUserManage()
{
	var a=document.getElementById("changeUserPoints");
	a.setAttribute("hidden",true);
}
</script>
<div>
	<%
		FeedBack feedBack = (FeedBack)request.getAttribute("feedBack");
		User receiver = (User)request.getAttribute("receiver");
		User sender = (User)request.getAttribute("sender");
		int receiverpoints = (int)request.getAttribute("receiverpoints");
	%>
	<div id="taskname">
	任务名：<h2><%=feedBack.getTask().name %></h2>
	</div>
	<div id="taskcontent">
	任务描述：<h2><%=feedBack.getTask().content %></h2>
	</div>
	<div id="receiver">
	收货人：<h2><%=feedBack.getTask().receiverid %>   <%=receiver.username %></h2>
	</div>
	<div id="sender">
	送货人：<h2><%=feedBack.getTask().senderid %>   <%=sender.username %></h2>
	</div>
	<div id="cause">
	举报理由：<h2><%=feedBack.getContent() %></h2>
	</div>
	<div id="btn_deletetask">
	<button onclick="deleteTask(<%=feedBack.getId() %>)">删除任务</button>
	</div>
	<!-- 跳转进行用户处理 -->
	<!--  <div id="btn_usermanage">
	<button onclick="turnToUserManage(<%=feedBack.getId() %>)">用户管理</button>
	</div> -->
	<!-- 页面内嵌用户处理 -->
	<div id="btn_usermanage">
	<button onclick="openUserManage()">用户管理</button>
	</div>
	
	<div id="btn_nohandle">
	<button onclick="noHandle(<%=feedBack.getId() %>)"> 未违规 </button>
	</div>
	<div id="btn_goback">
	<button onclick="turnBack()">  返回  </button>
	</div>
	<div id="btn_home">
	<button onclick="home()">  主页  </button>
	</div>
	<div id="changeUserPoints" hidden="true" style="position:absolute;
		height:400px;
		width:500px;
		top: 200px; 
		left: 300px;
		background-color:#a6ffa6;
		border:3px solid;
		margin:0 20px">
		<form id="changePointsForm" method="post" action="${pageContext.request.contextPath}/admin/feedbackManage/openusermanage.do?receiverIDNumber=<%=receiver.IDNumber %>&senderIDNumber=<%=sender.IDNumber %>&id=<%=feedBack.getId() %>">
			<h2>收货方：<%=receiver.username %></h2>
			剩余积分：<%=receiverpoints %><br />
			调整后积分：<br />
			<input type="text" name="receiverpoints" /><br />
			<h2>送货方：<%=sender.username %></h2>
			剩余积分：<%=sender.points %><br />
			调整后积分：<br />
			<input type="text" name="senderpoints" /><br />
			<input type="submit" value="确定并调整" />
		</form>
	</div>
</div>

