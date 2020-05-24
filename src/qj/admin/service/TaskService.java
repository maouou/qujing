package qj.admin.service;

import java.util.List;

import qj.admin.pojo.Task;
import qj.util.Page;


public interface TaskService {
	public void add(String taskName,String taskDetail,String credit,String ddl,String taskType,String quick);
	public int getTotal();
	public int getTotal(int type);
	public List listByPage(Page page);
	public List listByPage(Page page,int type);
	public Task acceptTask(int id);
	public void delete(Task task);
}
