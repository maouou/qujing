package qj.admin.controller;

import java.io.IOException;
import java.nio.channels.ScatteringByteChannel;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.service.spi.Startable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import qj.admin.dao.TaskDAO;
import qj.admin.dao.UserDAO;
import qj.admin.pojo.CancleTask;
import qj.admin.pojo.Task;
import qj.admin.pojo.User;
import qj.admin.service.AdminUserManageService;
import qj.admin.service.CancleTaskService;
import qj.admin.service.MessageService;
import qj.admin.util.MQUtil;
import qj.admin.util.Page;

@Controller
@RequestMapping("/admin/canclemanage")
@CrossOrigin(origins="*",maxAge=3600)
public class AdminCancleManageController {
	
	//测试一下
	@Autowired
	CancleTaskService cancleTaskService;
	@Autowired
	MessageService messageService;
	@Autowired 
	AdminUserManageService adminUserManageService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	UserDAO userdao;
	@Autowired
	TaskDAO taskdao;
	
	@RequestMapping("/list")
	@ResponseBody
	public JSONArray list(/*Map<String, Object>map, Integer start*/)
	{
		/*Page page;
		if(start == null)
		{
			page = new Page(0, 10);
		}
		else
		{
			page = new Page(start , 10);
		}
		page.setTotal(cancleTaskService.getTotal());*/
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		List<CancleTask>cancleTasks = cancleTaskService.list();
		String jsonString = "[";
		for(int i = 0 ; i < cancleTasks.size() ; i++)
		{
			CancleTask cancleTask = cancleTasks.get(i);
			Task task = cancleTask.getTask();
			if(task.getState() == 1)
				continue;
			String type = "";
			if(task.getState() == 4)
				type = "送货方取消";
			if(task.getState() == 3)
				type = "收货方取消";
			String taskName = task.getName();
			User receiver = adminUserManageService.get(Integer.valueOf(task.senderid));
			User sender = adminUserManageService.get(Integer.valueOf(task.receiverid));
			String senderName = sender.getUsername();
			String receiverName = receiver.getUsername();
			String content = cancleTask.getContent();
			String cancleId = String.valueOf(cancleTask.getId());
			String tempString = "{\"taskName\":\"" + taskName + "\",\"receiver\":\"" + receiverName + "\",\"sender\":\""
				     + senderName + "\",\"type\":\"" + type + "\",\"content\":\"" + content + "\",\"id\":\"" + cancleId + "\"}";
			if(i == cancleTasks.size()-1)
				jsonString = jsonString + tempString;
			else
				jsonString = jsonString + tempString + ",";
		}
		jsonString += "]";
		System.out.print(jsonString);
		JSONArray jsonObject = JSONArray.parseArray(jsonString);
		return jsonObject;
		/*map.put("cancleTasks", cancleTasks);
		map.put("page", page);
		return "admin/adminCancleManage";*/
	}
	
	@RequestMapping("/showDetail")
	@ResponseBody
	public JSONObject showDetail(int id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		System.out.println("显示取消详情");
		CancleTask cancleTask = null;
		cancleTask = cancleTaskService.get(id);
		User receiver = adminUserManageService.get(Integer.valueOf(cancleTask.getTask().senderid));
		User sender = adminUserManageService.get(Integer.valueOf(cancleTask.getTask().receiverid));
		String senderName = sender.getUsername();
		Task task = cancleTask.getTask();
		int receiverpoints = Integer.valueOf(task.points) + receiver.points;
		String taskName = task.name;
		String taskContent = task.content;
		String reportContent = cancleTask.getContent();
		String receiverName = receiver.getUsername();
		String receiverstudentId = receiver.studentId;
		String senderstudentId = sender.studentId;
		String points = String.valueOf(task.points);
		String receiverPoints = String.valueOf(receiverpoints);
		String suitID = String.valueOf(cancleTask.getId());
		String senderPoints = String.valueOf(sender.getPoints());
		String type = "";
		if(task.getState()==4)
			type = "1";
		if(task.getState()==3)
			type = "2";
		String typeName = "";
		String reporterName = "";
		String reporterstudentId = "";
		if(task.getState()==3)
		{
			typeName = "收货方取消";
			reporterName = receiverName;
			reporterstudentId = receiverstudentId;
		}
			
		if(task.getState()==4)
		{
			typeName = "送货方取消";
			reporterName = senderName;
			reporterstudentId = senderstudentId;
		}
			
		String jsonString= "{\"taskName\":\"" + taskName + "\",\"points\":\"" + points + "\",\"taskContent\":\"" + taskContent + "\",\"cancleContent\":\"" + reportContent
				+"\",\"receiverName\":\"" + receiverName + "\",\"receiverstudentId\":\"" + receiverstudentId + "\",\"senderName\":\"" + senderName
				+"\",\"senderstudentId\":\"" + senderstudentId + "\",\"cancleName\":\"" + reporterName + "\",\"canclestudentId\":\"" + reporterstudentId
				+"\",\"cancleID\":\"" + suitID + "\",\"receiverPoints\":\"" + receiverPoints + "\",\"senderPoints\":\"" + senderPoints + "\",\"type\":\"" + type
				+"\",\"typeName\":\"" + typeName + "\"}";
		System.out.println(jsonString);
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		return jsonObject;
		/*request.setAttribute("receiver", receiver);
		request.setAttribute("sender", sender);
		request.setAttribute("cancleTask", cancleTask);
		return "admin/cancleDetail";*/
	}
	
	@RequestMapping("/cancle")
	@ResponseBody
	public JSONArray cancleTask(int type,int receiverpoints,int senderpoints,int id) throws IOException, TimeoutException
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		if(type == 1)
			return agreeReceiverCancle(id, receiverpoints, senderpoints);
		if(type == 2)
			return agreeSenderCancle(id, receiverpoints, senderpoints);
		else
			return null;
	}
	
	@RequestMapping("/refusecancle")
	@ResponseBody
	public JSONArray refuseCancle(int type,int id) throws IOException, TimeoutException
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		if(type == 1)
			return refuseReceiverCancle(id);
		if(type ==2)
			return refuseSenderCancle(id);
		else
			return null;
	}
	
	@RequestMapping("/agreeReceiverCancle")
	@ResponseBody
	public JSONArray agreeReceiverCancle(int id,int receiverpoints,int senderpoints) throws IOException, TimeoutException
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		CancleTask cancleTask = cancleTaskService.get(id);
		String receiverstudentId = cancleTask.getTask().receiverid;
		String senderstudentId = cancleTask.getTask().senderid;
		MQUtil.send("method=update&target=task&id=" + cancleTaskService.get(id).getTask().id);
		MQUtil.send("method=update&target=user&studentId=" + receiverstudentId);
		MQUtil.send("method=update&target=user&studentId=" + senderstudentId);
		cancleTaskService.agreeReceiverCancle(id);
		adminUserManageService.changePoints(Integer.valueOf(receiverstudentId), receiverpoints);
		adminUserManageService.changePoints(Integer.valueOf(senderstudentId), senderpoints);
		userdao.reduceReceivedTaskNumber(userdao.get(cancleTask.getTask().receiverid));
		taskdao.delete(cancleTask.getTask());
		System.out.println("删除任务了已经");
		messageService.add("您提交的对'" + cancleTask.getTask().name + "'任务的取消申请已被通过。积分已调整。", 0, 0, Integer.valueOf(receiverstudentId), 0);
		messageService.add("您接收的'" + cancleTask.getTask().name + "'任务，收货方已取消，相应积分已调整，感谢您的理解与配合。", 0, 0, Integer.valueOf(senderstudentId), 0);
		return list();
	}
	
	@RequestMapping("/refuseReceiverCancle")
	@ResponseBody
	public JSONArray refuseReceiverCancle(int id) throws IOException, TimeoutException
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		CancleTask cancleTask = cancleTaskService.get(id);
		MQUtil.send("method=update&target=task&id=" + cancleTaskService.get(id).getTask().id);
		String receiverstudentId = cancleTask.getTask().receiverid;
		cancleTaskService.refuseReceiverCancle(id);
		taskdao.reset(cancleTask.getTask());
		messageService.add("您提交的对'" + cancleTask.getTask().name + "'任务的取消申请，经审核沟通不予通过，感谢您的理解与配合。", 0, 0, Integer.valueOf(receiverstudentId), 0);
		return list();
	}
	
	@RequestMapping("/agreeSenderCancle")
	@ResponseBody
	public JSONArray agreeSenderCancle(int id,int receiverpoints,int senderpoints) throws IOException, TimeoutException
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		CancleTask cancleTask = cancleTaskService.get(id);
		String receiverstudentId = cancleTask.getTask().receiverid;
		String senderstudentId = cancleTask.getTask().senderid;
		MQUtil.send("method=update&target=task&id=" + cancleTaskService.get(id).getTask().id);
		MQUtil.send("method=update&target=user&studentId=" + receiverstudentId);
		MQUtil.send("method=update&target=user&studentId=" + senderstudentId);
		cancleTaskService.agreeSenderCancle(id);
		adminUserManageService.changePoints(Integer.valueOf(receiverstudentId), receiverpoints);
		adminUserManageService.changePoints(Integer.valueOf(senderstudentId), senderpoints);
		userdao.reduceReceivedTaskNumber(userdao.get(cancleTask.getTask().receiverid));
		System.out.println("删除任务了已经");
		taskdao.delete(cancleTask.getTask());
		messageService.add("您发布的'" + cancleTask.getTask().name + "'任务，送货方已取消，相应积分已调整，感谢您的理解与配合。", 0, 0, Integer.valueOf(receiverstudentId), 0);
		messageService.add("您提交的对'" + cancleTask.getTask().name + "'任务的取消申请已被通过。积分已调整。", 0, 0, Integer.valueOf(senderstudentId), 0);
		return list();
	}
	
	@RequestMapping("/refuseSenderCancle")
	@ResponseBody
	public JSONArray refuseSenderCancle(int id) throws IOException, TimeoutException
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		CancleTask cancleTask = cancleTaskService.get(id);
		MQUtil.send("method=update&target=task&id=" + cancleTaskService.get(id).getTask().id);
		String senderstudentId = cancleTask.getTask().senderid;
		cancleTaskService.refuseSenderCancle(id);
		taskdao.reset(cancleTask.getTask());
		messageService.add("您提交的对'" + cancleTask.getTask().name + "'任务的取消申请，经审核沟通不予通过，请您尽快完成任务。感谢您的理解与配合！", 0, 0, Integer.valueOf(senderstudentId), 0);
		return list();
	}
}
