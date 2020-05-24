package qj.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import qj.admin.pojo.FeedBack;
import qj.admin.pojo.Suit;
import qj.admin.pojo.Task;

@Repository("FeedBackDAO")
public class FeedBackDAOImpl implements FeedBackDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int getTotal() {
		// TODO 自动生成的方法存根
		Session session = sessionFactory.getCurrentSession();
		String hqString = "SELECT count(f) FROM FeedBack AS f";
		Query query = session.createQuery(hqString);
		int total = ((Long)query.uniqueResult()).intValue();
		return total;
	}

	@Override
	public FeedBack get(int id) {
		// TODO 自动生成的方法存根
		FeedBack feedBack;
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT f FROM FeedBack AS f WHERE f.id like " + id;
		Query query = session.createQuery(hqlString);
		feedBack = (FeedBack)query.uniqueResult();
		return feedBack;
	}

	@Override
	public List<FeedBack> list(int start, int count) {
		// TODO 自动生成的方法存根
		List<FeedBack> feedBacks = null;
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT f FROM FeedBack AS f WHERE f.flat != 1";
		Query query = session.createQuery(hqlString);
		query.setFirstResult(start);
		query.setMaxResults(count);
		feedBacks = query.list();
		return feedBacks;
	}

	@Override
	public void handled(int id) {
		// TODO 自动生成的方法存根
		FeedBack feedBack = get(id);
		feedBack.setFlat(1);
	}

	@Override
	public Task getTask(int id) {
		// TODO 自动生成的方法存根
		Task task = get(id).getTask();
		return task;
	}
	
}
