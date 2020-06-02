package qj.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import qj.admin.pojo.FeedBack;
import qj.admin.pojo.Suit;
import qj.admin.pojo.Task;
import qj.admin.pojo.User;
import qj.admin.service.AdminUserManageService;
import qj.admin.service.FeedBackService;
import qj.admin.service.MessageService;
import qj.admin.service.TaskService;
import qj.admin.util.Page;

@Controller
@RequestMapping("/admin/feedbackManage")
@CrossOrigin(origins="*",maxAge=3600)
public class AdminFeedBackManageController {
	@Autowired
	MessageService messageService;
	@Autowired
	FeedBackService feedBackService;
	@Autowired
	AdminUserManageService adminUserManageService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	TaskService taskService;
	@Autowired
	AdminUserManageController adminUserManageController;
	@Autowired
	HttpServletResponse response;
	
	@RequestMapping("/list")
	@ResponseBody
	public JSONArray list(/*Map<String , Object>map,Integer start*/)
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
		int total = feedBackService.getTotal();
		page.setTotal(total);*/
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		List<FeedBack> feedBacks = feedBackService.list();
		String jsonString = "[";
		for(int i = 0; i < feedBacks.size(); i++)
		{
			FeedBack feedBack = feedBacks.get(i);
			Task task = feedBack.getTask();
			if(task.getState() == 7)
				continue;
			if(task.getState()==1)
				continue;
			if(task.getState()==2)
				continue;
			User receiver = adminUserManageService.get(Integer.valueOf(feedBack.getTask().receiverid));
			User sender = adminUserManageService.get(Integer.valueOf(feedBack.getTask().senderid));
			String taskName = feedBack.getTask().name;
			String receiverName = receiver.getUsername();
			String senderName = sender.getUsername();
			String type = feedBack.getType().getType();
			String content = feedBack.getContent();
			String feedBackId = String.valueOf(feedBack.getId());
			String tempString = "{\"taskName\":\"" + taskName + "\",\"receiver\":\"" + receiverName + "\",\"sender\":\""
					     + senderName + "\",\"type\":\"" + type + "\",\"content\":\"" + content + "\",\"id\":\"" + feedBackId + "\"}";
			if(i == feedBacks.size()-1)
				jsonString = jsonString + tempString;
			else
				jsonString = jsonString + tempString + ",";
		}
		jsonString += "]";
		JSONArray jsonObject = JSONArray.parseArray(jsonString);
		/*map.put("feedBacks", feedBacks);
		map.put("page", page);*/
		return jsonObject;
	}
	
	@RequestMapping("/showdetail")
	@ResponseBody
	public JSONObject showDetail(int id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		FeedBack feedBack = null;
		feedBack = feedBackService.get(id);
		User receiver = adminUserManageService.get(Integer.valueOf(feedBack.getTask().receiverid));
		User sender = adminUserManageService.get(Integer.valueOf(feedBack.getTask().senderid));
		Task task = feedBackService.getTask(id);
		int receiverpoints = Integer.valueOf(task.points) + receiver.points;
		String taskName = feedBack.getTask().name;
		String taskContent = feedBack.getTask().content;
		String reportContent = feedBack.getContent();
		String receiverName = receiver.getUsername();
		String receiverstudentId = receiver.studentId;
		String senderName = sender.getUsername();
		String senderstudentId = sender.studentId;
		String reporterName = senderName;
		String reporterstudentId = senderstudentId;
		String receiverPoints = String.valueOf(receiverpoints);
		String suitID = String.valueOf(feedBack.getId());
		String senderPoints = String.valueOf(sender.getPoints());
		String jsonString= "{\"taskName\":\"" + taskName + "\",\"taskContent\":\"" + taskContent + "\",\"backContent\":\"" + reportContent
				+"\",\"receiverName\":\"" + receiverName + "\",\"receiverstudentId\":\"" + receiverstudentId + "\",\"senderName\":\"" + senderName
				+"\",\"senderstudentId\":\"" + senderstudentId + "\",\"backName\":\"" + reporterName + "\",\"backstudentId\":\"" + reporterstudentId
				+"\",\"backID\":\"" + suitID + "\",\"receiverPoints\":\"" + receiverPoints + "\",\"senderPoints\":\"" + senderPoints + "\"}";
		System.out.println(jsonString);
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		return jsonObject;
		/*request.setAttribute("receiverpoints", receiverpoints);
		request.setAttribute("receiver", receiver);
		request.setAttribute("sender", sender);
		request.setAttribute("feedBack", feedBack);
		return "admin/feedBackDetail";*/
	}
	
	@RequestMapping("/deletetask")
	@ResponseBody
	public JSONArray Handled(int id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		feedBackService.handled(id);
		Task task = feedBackService.getTask(id);
		taskService.delete(task);
		int points = Integer.valueOf(task.points) + adminUserManageService.get(Integer.valueOf(task.receiverid)).points;
		adminUserManageService.changePoints(Integer.valueOf(task.receiverid), points);
		messageService.add("您发布的" + task.name + "任务,经管理员审核因涉嫌违规，已被删除。", 0, 0, Integer.valueOf(task.receiverid), 0);
		messageService.add("您举报的" + task.name + "任务,经管理员审核因涉嫌违规，已被删除。感谢你的监督", 0, 0, Integer.valueOf(task.senderid), 0);
		return list();
	}
	
	@RequestMapping("/legaltask")
	@ResponseBody
	public JSONArray leagalTask(int id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		feedBackService.handled(id);
		Task task = feedBackService.getTask(id);
		messageService.add("您反馈的" + task.name + "任务,经管理员审核不存在欺诈行为，感谢您的理解与配合！", 0, 0, Integer.valueOf(task.senderid), 0);
		return list();
	}
	
	@RequestMapping("/openusermanage")
	@ResponseBody
	public JSONArray openUserManage(int receiverstudentId,int senderstudentId,int id,int receiverpoints,int senderpoints)
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		Task task = feedBackService.getTask(id);
		adminUserManageService.changePoints(receiverstudentId, Integer.valueOf(receiverpoints));
		adminUserManageService.changePoints(senderstudentId, Integer.valueOf(senderpoints));
		feedBackService.handled(id);
		taskService.delete(task);
		messageService.add("您的积分已被管理员调整为" + receiverpoints + "。原因请见上条，感谢您的理解与配合。", 0, 0, receiverstudentId, 0);
		messageService.add("您的积分已被管理员调整为" + receiverpoints + "。原因请见上条，感谢您的理解与配合。", 0, 0, senderstudentId, 0);
		return list();
	}
	
	@RequestMapping("/turnusermanage")
	@ResponseBody
	public JSONArray turnUserManage(int id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		feedBackService.setHandled(id);
		return adminUserManageController.list();
	}
}
