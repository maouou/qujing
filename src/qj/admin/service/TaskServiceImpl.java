package qj.admin.service;


import javax.print.attribute.standard.DateTimeAtProcessing;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.apache.struts2.util.TabbedPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qj.admin.dao.TaskDAO;
import qj.admin.dao.TasktypeDAO;
import qj.admin.pojo.Task;
import qj.admin.pojo.Tasktype;
import qj.util.Page;

@Service("TaskService")
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDAO taskDAO;
	@Autowired
	private TasktypeDAO tasktypeDAO;
	@Override
	public void add(String taskName,String taskDetail,String credit,String ddl,String taskType,String quick) {
		// TODO Auto-generated method stub
		Task task = new Task();
		Tasktype tasktype = new Tasktype();
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		Timestamp timestamp2 = new Timestamp(date.getTime()+Long.parseLong(ddl)*60000);		
		task.setName(taskName);
		task.setContent(taskDetail);
		task.setPoints(credit);
		tasktype = tasktypeDAO.get(Integer.parseInt(taskType));			
		task.setTaskType(tasktype);
		task.setPublishTime(timestamp);		
		task.setDeadline(timestamp2);
		
		task.setSenderid("221701237");//发布用户由登入时候获取，这里先随意
		
		if(quick!=null&&quick.equals("on"))
		{
			task.setExpedited(1);
		}
		else {
			task.setExpedited(0);
		}
		task.setSenderCancle(1);
		task.setReceiverCancle(0);
		taskDAO.add(task);
	}
	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		int total = taskDAO.getTotal();
		return total;
	}
	@Override
	public List listByPage(Page page) {
		// TODO Auto-generated method stub
		List tasks= taskDAO.list(page.getStart(), page.getCount());
		return tasks;
	}
	@Override
	public int getTotal(int type) {
		// TODO Auto-generated method stub
		int total = taskDAO.getTotal(type);
		return total;
	}
	@Override
	public List listByPage(Page page, int type) {
		// TODO Auto-generated method stub
		List tasks= taskDAO.list(page.getStart(), page.getCount(),type);
		return tasks;
	}
	@Override
	public Task acceptTask(int id) {
		// TODO Auto-generated method stub
		Task task=taskDAO.get(id);
		taskDAO.update(task);
		return task;
	}
	@Override
	public void delete(Task task) {
		taskDAO.delete(task);
		// TODO 自动生成的方法存根
		
	}

}
