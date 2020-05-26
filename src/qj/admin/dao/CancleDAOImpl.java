package qj.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import qj.admin.pojo.CancleTask;
import qj.admin.pojo.Task;

@Repository("CancleDAO")
public class CancleDAOImpl implements CancleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private TaskDAO taskDAO;
	@Override
	public int getToatl() {
		// TODO 自动生成的方法存根
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT count(c) FROM CancleTask AS c WHERE c.flat like 0";
		Query query = session.createQuery(hqlString);
		int total = ((Long)query.uniqueResult()).intValue();
		return total;
	}

	@Override
	public CancleTask get(int id) {
		// TODO 自动生成的方法存根
		CancleTask cancleTask;
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT c FROM CancleTask AS c WHERE c.id like " + id;
		Query query = session.createQuery(hqlString);
		cancleTask = (CancleTask)query.uniqueResult();
		return cancleTask;
	}

	@Override
	public int add(CancleTask cancletsk) {
		// TODO 自动生成的方法存根
		Session session = sessionFactory.getCurrentSession();
		session.save(cancletsk);
		return 0;
	}

	@Override
	public List<CancleTask> list(int start, int count) {
		// TODO 自动生成的方法存根
		List<CancleTask> cancleTasks = null;
		Session session = sessionFactory.getCurrentSession();
		String hqString = "SELECT c FROM CancleTask AS c WHERE c.flat like 0";
		Query query = session.createQuery(hqString);
		query.setFirstResult(start);
		query.setMaxResults(count);
		cancleTasks = query.list();
		return cancleTasks;
	}

	@Override
	public void handled(int id) {
		// TODO 自动生成的方法存根
		CancleTask cancleTask = get(id);
		cancleTask.setFlat(1);
		
	}


	@Override
	public void agreeReceiverCancle(int id) {
		// TODO 自动生成的方法存根
		CancleTask cancleTask = get(id);
		Task task = cancleTask.getTask();
		taskDAO.delete(task);
	}

	@Override
	public void refusereceiverCancle(int id) {
		// TODO 自动生成的方法存根
		CancleTask cancleTask = get(id);
		Task task = cancleTask.getTask();
		task.setState(2);
	}

	@Override
	public void agreeSenderCancle(int id) {
		// TODO 自动生成的方法存根
		CancleTask cancleTask = get(id);
		Task task = cancleTask.getTask();
		task.setState(3);
	}

	@Override
	public void refuseSenderCancle(int id) {
		// TODO 自动生成的方法存根
		CancleTask cancleTask = get(id);
		Task task = cancleTask.getTask();
		task.setState(1);
	}

}
