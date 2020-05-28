package qj.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qj.admin.aspect.LogAnno;
import qj.admin.dao.SuitDAO;
import qj.admin.pojo.Suit;
import qj.admin.pojo.Task;
import qj.util.Page;

@Service("SuitService")
public class SuitServiceImpl implements SuitService {

	@Autowired
	SuitDAO suitDAO;
	@Autowired
	TaskService taskservice;
	@Override
	public int add(Suit suit) {
		// TODO Auto-generated method stub
		suitDAO.add(suit);
		return 0;
	}

	@Override
	public int update(Task task) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotal() {
		// TODO 自动生成的方法存根
		int total = suitDAO.getTotal();
		return total;
	}

	@Override
	public List listByPage(Page page) {
		// TODO 自动生成的方法存根
		List suits = suitDAO.list(page.getStart(), page.getCount());
		return suits;
	}

	@Override
	public Suit get(int id) {
		// TODO 自动生成的方法存根
		Suit suit = null;
		suit = suitDAO.get(id);
		return suit;
	}

	@Override
	@LogAnno(operatorType = "用户举报属实，删除任务")
	public void setHandeled(int id) {
		// TODO 自动生成的方法存根
		suitDAO.handleed(id);
		Task task = getTask(id);
		taskservice.delete(task);
	}

	@Override
	public Task getTask(int id) {
		// TODO 自动生成的方法存根
		Task task = suitDAO.getTask(id);
		return task;
	}

	@Override
	@LogAnno(operatorType = "撤销用户举报")
	public void setHandled(int id) {
		suitDAO.handleed(id);
	}

	@Override
	public List list() {
		// TODO 自动生成的方法存根
		int total = getTotal();
		List<Suit>suits =suitDAO.list(0, total);
		return suits;
	}

}
