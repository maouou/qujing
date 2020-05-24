package qj.admin.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import qj.admin.pojo.Tasktype;

@Repository("TasktypeDAO")
public class TasktypeDAOImpl implements TasktypeDAO {
	@Autowired
	private SessionFactory sessionFactory;
	public Tasktype get(int id)
	{
		Tasktype tasktype;
		String hqlString = "SELECT t from Tasktype AS t WHERE t.id like "+id;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hqlString);
		tasktype = (Tasktype)query.uniqueResult();
		return tasktype;
	}
}
