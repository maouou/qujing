package qj.admin.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import qj.admin.pojo.Message;

@Repository("MessageDAO")
public class MessageDAOImpl implements MessageDAO{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void add(Message message) {
		// TODO 自动生成的方法存根
		Session session = sessionFactory.getCurrentSession();
		session.save(message);
	}
	
}
