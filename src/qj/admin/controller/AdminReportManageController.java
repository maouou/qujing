package qj.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.dialect.PointbaseDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import qj.admin.pojo.Suit;
import qj.admin.pojo.Task;
import qj.admin.pojo.User;
import qj.admin.service.AdminUserManageService;
import qj.admin.service.MessageService;
import qj.admin.service.SuitService;
import qj.admin.service.TaskService;
import qj.util.Page;

@Controller
@RequestMapping("/admin/reportmanage")
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
		List<Suit> suits = suitService.list();
		String jsonString = "[";
		for(int i = 0; i < suits.size(); i++)
		{
			Suit suit = suits.get(i);
			Task task= suit.getTask();
			if(task.receiverCancle == 2)
				continue;
			if(task.receiverCancle == 0 && task.senderCancle == 1)
				continue;
			if(task.receiverCancle == 0 && task.senderCancle == 0)
				continue;
			User receiver = adminUserManageService.get(Integer.valueOf(suit.getTask().receiverid));
			User sender = adminUserManageService.get(Integer.valueOf(suit.getTask().senderid));
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
		/*map.put("suits", suits);
		map.put("page", page);
		return "admin/adminReportManage";*/
		return jsonObject;
	}
	@RequestMapping("showreport")
	@ResponseBody
	public JSONObject showReport(int id)
	{
		Suit suit = null;
		suit = suitService.get(id);
		User receiver = adminUserManageService.get(Integer.valueOf(suit.getTask().receiverid));
		User sender = adminUserManageService.get(Integer.valueOf(suit.getTask().senderid));
		Task task = suitService.getTask(id);
		int receiverpoints = Integer.valueOf(task.points) + receiver.points;
		String taskName = suit.getTask().name;
		String taskContent = suit.getTask().content;
		String reportContent = suit.getContent();
		String receiverName = receiver.getUsername();
		String receiverIDNumber = receiver.IDNumber;
		String senderName = sender.getUsername();
		String senderIDNumber = sender.IDNumber;
		String reporterName = senderName;
		String reporterIDNumber = senderIDNumber;
		String receiverPoints = String.valueOf(receiverpoints);
		String suitID = String.valueOf(suit.getId());
		String senderPoints = String.valueOf(sender.getPoints());
		String jsonString= "{\"taskName\":\"" + taskName + "\",\"taskContent\":\"" + taskContent + "\",\"reportContent\":\"" + reportContent
				+"\",\"receiverName\":\"" + receiverName + "\",\"receiverIDNumber\":\"" + receiverIDNumber + "\",\"senderName\":\"" + senderName
				+"\",\"senderIDNumber\":\"" + senderIDNumber + "\",\"reporterName\":\"" + reporterName + "\",\"reporterIDNumber\":\"" + reporterIDNumber
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
	public JSONArray deleteTask(int id)
	{
		suitService.setHandeled(id);
		Task task = suitService.getTask(id);
		taskservice.delete(task);
		int points = Integer.valueOf(task.points) + adminUserManageService.get(Integer.valueOf(task.receiverid)).points;
		adminUserManageService.changePoints(Integer.valueOf(task.receiverid), points);
		messageService.add("您发布的" + task.name + "任务,经管理员审核因涉嫌违规，已被删除。", 0, 0, Integer.valueOf(task.receiverid), 0);
		messageService.add("您举报的" + task.name + "任务,经管理员审核因涉嫌违规，已被删除。感谢你的监督", 0, 0, Integer.valueOf(task.senderid), 0);
		return list();
	}
	@RequestMapping("legaltask")
	@ResponseBody
	public JSONArray leagalTask(int id)
	{
		suitService.setHandled(id);
		Task task = suitService.getTask(id);
		messageService.add("您反馈的" + task.name + "任务,经管理员审核不存在违规行为，感谢您的理解与配合！", 0, 0, Integer.valueOf(task.senderid), 0);
		return list();
	}
	
	@RequestMapping("turnusermanage")
	@ResponseBody
	public JSONArray turnUserManage(int id)
	{
		suitService.setHandeled(id);
		Task task = suitService.getTask(id);
		taskservice.delete(task);
		return adminUserManageController.list();
	}
	
	@RequestMapping("openusermanage")
	@ResponseBody
	public JSONArray openUserManage(int receiverIDNumber,int senderIDNumber,int id)
	{
		String receiverPoints = request.getParameter("receiverpoints");
		String senderPoints = request.getParameter("senderpoints");
		Task task = suitService.getTask(id);
		adminUserManageService.changePoints(receiverIDNumber, Integer.valueOf(receiverPoints));
		adminUserManageService.changePoints(senderIDNumber, Integer.valueOf(senderPoints));
		suitService.setHandeled(id);
		taskservice.delete(task);
		messageService.add("您的积分已被管理员调整为" + receiverPoints + "。原因请见上条，感谢您的理解与配合。", 0, 0, receiverIDNumber, 0);
		messageService.add("您的积分已被管理员调整为" + receiverPoints + "。原因请见上条，感谢您的理解与配合。", 0, 0, senderIDNumber, 0);
		return list();
	}
}
