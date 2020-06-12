package qj.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import qj.admin.pojo.Task;

@Repository("TaskDAO")
public class TaskDAOImpl implements TaskDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT count(t) FROM Task AS t";
		Query query = session.createQuery(hqlString);
		int total = ((Long)query.uniqueResult()).intValue();
		return total;
	}

	@Override
	public void add(Task task) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(task);
		
	}

	@Override
	public void update(Task task) {
		// TODO Auto-generated method stub
		task.setState(2);
		task.setReceiverid("221701237");
		Session session = sessionFactory.getCurrentSession();
		session.update(task);
	}

	@Override
	public void delete(Task task) {
		// TODO Auto-generated method stub
		task.setState(7);
		Session session = sessionFactory.getCurrentSession();
		session.update(task);
	}

	@Override
	public void reset(Task task) {
		// TODO Auto-generated method stub
		task.setState(2);
		Session session = sessionFactory.getCurrentSession();
		session.update(task);
	}

	@Override
	public Task get(int id) {
		// TODO Auto-generated method stub
		Task task;
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT t FROM Task AS t WHERE t.id like "+id;
		Query query = session.createQuery(hqlString);
		task = (Task)query.uniqueResult();
		return task;
	}

	@Override
	public List<Task> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> list(int start, int count) {
		// TODO Auto-generated method stub
		List<Task> tasks=null;
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT t FROM Task AS t WHERE t.senderCancle=0 AND t.receiverCancle=1";
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(count);
		tasks = query.list();
		return tasks;
	}

	@Override
	public boolean isExist(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Task get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task get(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotal(int type) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT count(t) FROM Task AS t WHERE t.taskType.id like "+type+" AND t.senderCancle=0 AND t.receiverCancle=1";
		Query query = session.createQuery(hqlString);
		int total = ((Long)query.uniqueResult()).intValue();
		return total;
	}

	@Override
	public List<Task> list(int start, int count, int type) {
		// TODO Auto-generated method stub
		List<Task> tasks=null;
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT t FROM Task AS t WHERE t.taskType.id like "+type+" AND t.senderCancle=0 AND t.receiverCancle=1";
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(count);
		tasks = query.list();
		return tasks;
	}

}
