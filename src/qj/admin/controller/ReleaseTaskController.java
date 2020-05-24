package qj.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import qj.admin.service.TaskService;

@Controller
@RequestMapping("/release")
public class ReleaseTaskController {
	@Autowired
	TaskService taskService;
	@RequestMapping(value = "/task")
	public String releaseTask(String taskName,String taskDetail,String credit,String ddl,String taskType,String quick)
	{
		taskService.add(taskName, taskDetail, credit, ddl, taskType, quick);
		return "success";
	}
}
