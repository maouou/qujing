package qj.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qj.admin.dao.FeedBackDAO;
import qj.admin.pojo.FeedBack;
import qj.admin.pojo.Task;
import qj.admin.pojo.User;
import qj.util.Page;

@Service("FeedBackService")
public class FeedBackServiceImpl implements FeedBackService{

	@Autowired
	FeedBackDAO feedBackDAO;
	@Autowired
	TaskService taskservice;
	@Override
	public int getTotal() {
		// TODO 自动生成的方法存根
		int total = feedBackDAO.getTotal();
		return total;
	}
	@Override
	public FeedBack get(int id) {
		// TODO 自动生成的方法存根
		FeedBack feedBack = null;
		feedBack = feedBackDAO.get(id);
		return feedBack;
	}
	@Override
	public void handled(int id) {
		// TODO 自动生成的方法存根
		feedBackDAO.handled(id);
		
	}
	@Override
	public List listByPage(Page page) {
		// TODO 自动生成的方法存根
		List feedBacks = feedBackDAO.list(page.getStart(), page.getCount());
		return feedBacks;
	}
	@Override
	public Task getTask(int id) {
		// TODO 自动生成的方法存根
		Task task = feedBackDAO.getTask(id);
		return task;
	}
	@Override
	public void setHandled(int id) {
		// TODO 自动生成的方法存根
		feedBackDAO.handled(id);
		Task task = getTask(id);
		taskservice.delete(task);
	}
	@Override
	public List list() {
		// TODO 自动生成的方法存根
		int total = getTotal();
		List<FeedBack> feedBacks = feedBackDAO.list(0, total);
		return feedBacks;
	}
	
}
