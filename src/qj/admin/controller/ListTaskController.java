package qj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opensymphony.xwork2.ActionContext;

import qj.admin.pojo.Task;
import qj.admin.service.TaskService;
import qj.util.Page;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/list")
public class ListTaskController {
	@Autowired
	TaskService taskService;
	@RequestMapping("/task")
	public String list(Map<String, Object> map,Integer start)
	{
		Page page;
		if(start==null)
		{
			page = new Page(0, 10);
		}else {
			page = new Page(start, 10);
		}
		int total=taskService.getTotal();
		page.setTotal(total);
		List<Task> tasks = taskService.listByPage(page);
		map.put("tasks", tasks);
		map.put("page", page);
		return "listTask";
		
	}
	@RequestMapping("/taskBytype")
	public String list(Map<String, Object>map,Integer start,Integer type)
	{
		Page page;
		if(start==null)
		{
			page = new Page(0, 10);
		}else {
			page= new Page(start, 10);
		}
		int total = taskService.getTotal(type);
		page.setTotal(total);
		List<Task> tasks = taskService.listByPage(page,type);
		map.put("tasks", tasks);
		map.put("page", page);
		map.put("type", type);
		return "listTaskByType";
	}
	@RequestMapping("/acceptTask")
	public String acceptTask(Map<String, Object>map,int id)
	{
		Task task;
		 task=taskService.acceptTask(id);
		 map.put("task", task);
		 return "taskDetail";
	}
}
