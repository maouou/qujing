package qj.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.dialect.PointbaseDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import qj.admin.dao.TaskDAO;
import qj.admin.dao.UserDAO;
import qj.admin.pojo.Suit;
import qj.admin.pojo.Task;
import qj.admin.pojo.User;
import qj.admin.service.AdminUserManageService;
import qj.admin.service.MessageService;
import qj.admin.service.SuitService;
import qj.admin.service.TaskService;
import qj.admin.util.MQUtil;
import qj.admin.util.Page;

@Controller
@RequestMapping("/admin/reportmanage")
@CrossOrigin(origins="*",maxAge=3600)
public class AdminReportManageController {
	@Autowired
	SuitService suitService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	TaskService taskservice;
	@Autowired 
	AdminUserManageService adminUserManageService;
	@Autowired
	MessageService messageService;
	@Autowired
	AdminUserManageController adminUserManageController;
	@Autowired
	HttpServletResponse response;
	@Autowired
	UserDAO userdao;
	@Autowired
	TaskDAO taskdao;
	
	@RequestMapping("/list")
	@ResponseBody
	public JSONArray list(/*Map<String, Object>map,Integer start*/)
	{
		/*Page page;
		if(start == null)
		{
			page = new Page(0, 10);
		}
		else
		{
			page = new Page(start, 10);
		}
		int total = suitService.getTotal();
		page.setTotal(total);*/
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		List<Suit> suits = suitService.list();
		String jsonString = "[";
		for(int i = 0; i < suits.size(); i++)
		{
			Suit suit = suits.get(i);
			Task task= suit.getTask();
			User receiver = adminUserManageService.get(Integer.valueOf(suit.getTask().senderid));
			User sender = adminUserManageService.get(Integer.valueOf(suit.getTask().receiverid));
			String taskName = suit.getTask().name;
			String receiverName = receiver.getUsername();
			String senderName = sender.getUsername();
			String type = suit.getType().getType();
			String content = suit.getContent();
			String suitId = String.valueOf(suit.getId());
			String tempString = "{\"taskName\":\"" + taskName + "\",\"receiver\":\"" + receiverName + "\",\"sender\":\""
					     + senderName + "\",\"type\":\"" + type + "\",\"content\":\"" + content + "\",\"id\":\"" + suitId + "\"}";
			if(i == suits.size()-1)
				jsonString = jsonString + tempString;
			else
				jsonString = jsonString + tempString + ",";
		}
		jsonString += "]";
		JSONArray jsonObject = JSONArray.parseArray(jsonString);
		System.out.println(jsonString);
		/*map.put("suits", suits);
		map.put("page", page);
		return "admin/adminReportManage";*/
		return jsonObject;
	}
	@RequestMapping("showreport")
	@ResponseBody
	public JSONObject showReport(int id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		Suit suit = null;
		suit = suitService.get(id);
		User receiver = adminUserManageService.get(Integer.valueOf(suit.getTask().senderid));
		User sender = adminUserManageService.get(Integer.valueOf(suit.getTask().receiverid));
		Task task = suitService.getTask(id);
		int receiverpoints = Integer.valueOf(task.points) + receiver.points;
		String taskName = suit.getTask().name;
		String taskContent = suit.getTask().content;
		String reportContent = suit.getContent();
		String receiverName = receiver.getUsername();
		String receiverstudentId = receiver.studentId;
		String senderName = sender.getUsername();
		String senderstudentId = sender.studentId;
		String reporterName = senderName;
		String reporterstudentId = senderstudentId;
		String receiverPoints = String.valueOf(receiverpoints);
		String suitID = String.valueOf(suit.getId());
		String senderPoints = String.valueOf(sender.getPoints());
		String jsonString= "{\"taskName\":\"" + taskName + "\",\"taskContent\":\"" + taskContent + "\",\"reportContent\":\"" + reportContent
				+"\",\"receiverName\":\"" + receiverName + "\",\"receiverstudentId\":\"" + receiverstudentId + "\",\"senderName\":\"" + senderName
				+"\",\"senderstudentId\":\"" + senderstudentId + "\",\"reporterName\":\"" + reporterName + "\",\"reporterstudentId\":\"" + reporterstudentId
				+"\",\"suitID\":\"" + suitID + "\",\"receiverPoints\":\"" + receiverPoints + "\",\"senderPoints\":\"" + senderPoints + "\"}";
		System.out.println(jsonString);
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		return jsonObject;
		/*request.setAttribute("receiverpoints", receiverpoints);
		request.setAttribute("receiver", receiver);
		request.setAttribute("sender", sender);
		request.setAttribute("suit", suit);
		return "admin/reportDetail";*/
	}
	@RequestMapping("deletetask")
	@ResponseBody
	public JSONArray deleteTask(int id) throws IOException, TimeoutException
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		suitService.setHandeled(id);
		Task task = suitService.getTask(id);
		MQUtil.send("method=update&target=task&id=" + task.id);
		MQUtil.send("method=update&target=user&studentId=" + task.receiverid);
		MQUtil.send("method=update&target=user&studentId=" + task.senderid);
		taskservice.delete(task);
		int points = Integer.valueOf(task.points) + adminUserManageService.get(Integer.valueOf(task.senderid)).points;
		adminUserManageService.changePoints(Integer.valueOf(task.senderid), points);
		userdao.reduceReceivedTaskNumber(userdao.get(task.receiverid));
		userdao.addReportedNumber(userdao.get(task.senderid));
		messageService.add("您发布的" + task.name + "任务,经管理员审核因涉嫌违规，已被删除。", 0, 0, Integer.valueOf(task.senderid), 0);
		messageService.add("您举报的" + task.name + "任务,经管理员审核因涉嫌违规，已被删除。感谢你的监督", 0, 0, Integer.valueOf(task.receiverid), 0);
		return list();
	}
	@RequestMapping("legaltask")
	@ResponseBody
	public JSONArray leagalTask(int id) throws IOException, TimeoutException
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		suitService.setHandled(id);
		Task task = suitService.getTask(id);
		MQUtil.send("method=update&target=task&id=" + task.id);
		taskdao.reset(task);
		messageService.add("您反馈的" + task.name + "任务,经管理员审核不存在违规行为，感谢您的理解与配合！", 0, 0, Integer.valueOf(task.receiverid), 0);
		return list();
	}
	
	@RequestMapping("turnusermanage")
	@ResponseBody
	public JSONArray turnUserManage(int id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		suitService.setHandeled(id);
		Task task = suitService.getTask(id);
		taskservice.delete(task);
		return adminUserManageController.list();
	}
	
	@RequestMapping("openusermanage")
	@ResponseBody
	public JSONArray openUserManage(int receiverstudentId,int senderstudentId,int id) throws IOException, TimeoutException
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		String receiverPoints = request.getParameter("receiverpoints");
		String senderPoints = request.getParameter("senderpoints");
		Task task = suitService.getTask(id);
		MQUtil.send("method=update&target=task&id=" + task.id);
		MQUtil.send("method=update&target=user&studentId=" + receiverstudentId);
		MQUtil.send("method=update&target=user&studentId=" + senderstudentId);
		adminUserManageService.changePoints(receiverstudentId, Integer.valueOf(receiverPoints));
		adminUserManageService.changePoints(senderstudentId, Integer.valueOf(senderPoints));
		suitService.setHandeled(id);
		taskservice.delete(task);
		userdao.reduceReceivedTaskNumber(userdao.get(task.receiverid));
		userdao.addReportedNumber(userdao.get(task.senderid));
		messageService.add("您的积分已被管理员调整为" + receiverPoints + "。原因请见上条，感谢您的理解与配合。", 0, 0, receiverstudentId, 0);
		messageService.add("您的积分已被管理员调整为" + receiverPoints + "。原因请见上条，感谢您的理解与配合。", 0, 0, senderstudentId, 0);
		return list();
	}
}
