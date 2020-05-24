package qj.admin.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import qj.admin.pojo.Suit;
import qj.admin.pojo.Task;

@Repository("SuitDAO")
public class SuitDAOImpl implements SuitDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public int add(Suit suit) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(suit);
		return 0;
	}

	@Override
	public int update(Task task) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Suit get(int id) {
		// TODO 自动生成的方法存根
		Suit suit;
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT s FROM Suit As s WHERE s.id like " + id;
		Query query = session.createQuery(hqlString);
		suit = (Suit)query.uniqueResult();
		return suit;
	}

	@Override
	public int getTotal() {
		// TODO 自动生成的方法存根
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT count(s) FROM Suit AS s";
		Query query = session.createQuery(hqlString);
		int total = ((Long)query.uniqueResult()).intValue();
		return total;
	}

	@Override
	public List<Suit> list(int start, int count) {
		// TODO 自动生成的方法存根
		List<Suit> suits = null;
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT s FROM Suit AS s WHERE s.flat like 0";
		Query query = session.createQuery(hqlString);
		query.setFirstResult(start);
		query.setMaxResults(count);
		suits = query.list();
		return suits;
	}

	@Override
	public List<Suit> list(int start, int count, int type) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void handleed(int id) {
		// TODO 自动生成的方法存根
		Suit suit = get(id);
		suit.setFlat(1);
		
	}

	@Override
	public Task getTask(int id) {
		// TODO 自动生成的方法存根
		Task task = get(id).getTask();
		return task;
	}

}
