package qj.admin.service;

import java.util.List;

import org.springframework.web.context.support.ServletRequestHandledEvent;

import qj.admin.pojo.FeedBack;
import qj.admin.pojo.Task;
import qj.admin.util.Page;

public interface  FeedBackService {
	public int getTotal();
	public FeedBack get(int id);
	public void handled(int id);
	public List listByPage(Page page);
	public List list();
	public Task getTask(int id);
	public void setHandled(int id);
}
