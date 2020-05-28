package qj.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qj.admin.aspect.LogAnno;
import qj.admin.dao.CancleDAO;
import qj.admin.pojo.CancleTask;
import qj.admin.pojo.User;
import qj.admin.util.Page;

@Service("CancleTaskService")
public class CancleTaskServiceImpl implements CancleTaskService{

	@Autowired
	CancleDAO cancleDAO;
	@Override
	@LogAnno(operatorType = "添加取消任务")
	public int add(CancleTask cancleTask) {
		// TODO 自动生成的方法存根
		cancleDAO.add(cancleTask);
		return 0;
	}

	@Override
	public int getTotal() {
		// TODO 自动生成的方法存根
		int total = cancleDAO.getToatl();
		return total;
	}

	@Override
	public List listByPage(Page page) {
		// TODO 自动生成的方法存根
		List cancleTasks = cancleDAO.list(page.getStart(), page.getCount());
		return cancleTasks;
	}

	@Override
	public CancleTask get(int id) {
		// TODO 自动生成的方法存根
		CancleTask cancleTask = cancleDAO.get(id);
		return cancleTask;
	}

	@Override
	@LogAnno(operatorType = "同意接收者取消任务")
	public void agreeReceiverCancle(int id) {
		// TODO 自动生成的方法存根
		cancleDAO.handled(id);
		cancleDAO.agreeReceiverCancle(id);
		
	}

	@Override
	@LogAnno(operatorType = "拒绝接收者取消任务")
	public void refuseReceiverCancle(int id) {
		// TODO 自动生成的方法存根
		cancleDAO.handled(id);
		cancleDAO.refusereceiverCancle(id);
	}

	@Override
	@LogAnno(operatorType = "同意发布者取消任务")
	public void agreeSenderCancle(int id) {
		// TODO 自动生成的方法存根
		cancleDAO.handled(id);
		cancleDAO.agreeSenderCancle(id);
		
	}

	@Override
	@LogAnno(operatorType = "拒绝发布者取消任务")
	public void refuseSenderCancle(int id) {
		// TODO 自动生成的方法存根
		cancleDAO.handled(id);
		cancleDAO.agreeSenderCancle(id);
	}

	@Override
	public List list() {
		// TODO 自动生成的方法存根
		int total = getTotal();
		List<CancleTask> cancleTasks = cancleDAO.list(0, total);
		return cancleTasks;
	}

}
