<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/release/task.do">
	<label>名称:</label><input name="taskName" type="text" placeholder="请简单提现取货地与目的地，例如：顺丰到29#"></br>
	<label>描述:</label><textarea name="taskDetail" rows="5" cols="20"></textarea></br>
	<label>支付积分:</label><input name="credit" type="text"></br>
	<label>截止时间:</label><input name="ddl" type="datetime-local"></br>
	<label>服务类型</label>
	<select name="taskType">
 	 	<option value ="1">快递代取</option>
  		<option value ="2">文件代送</option>
  		<option value="3">食堂代买</option>
  		<option value="4">物品代购</option>
  		<option value="5">其他</option>
	</select></br>
	<input name="quick" type="checkbox"><span>是否发布为加急</span></br>
	<input type="submit" value="发布"></br>
</form>
<form action="${pageContext.request.contextPath}/list/task.do" method="post">
	<input type="submit" value="查看任务">
	</form>
<form action="${pageContext.request.contextPath}/admin/usermanage/list.do" method="post">
	<input type="submit" value="管理员用户管理" /> 
	</form>
<form action="${pageContext.request.contextPath}/admin/reportmanage/list.do" method="post">
	<input type="submit" value="管理员举报管理" /> 
	</form>
<form action="${pageContext.request.contextPath}/admin/feedbackManage/list.do" method="post">
	<input type="submit" value="管理员反馈管理" /> 
	</form>
<form action="${pageContext.request.contextPath}/admin/canclemanage/list.do" method="post">
	<input type="submit" value="管理员取消管理" /> 
	</form>	
</body>
</html>