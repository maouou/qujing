package qj.admin.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import qj.admin.pojo.MessageType;

@Repository("MessageTypeDAO")
public class MessageTypeDAOImpl implements MessageTypeDAO{

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public MessageType get(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT m FROM MessageType AS m WHERE m.id like "+id;
		Query query = session.createQuery(hqlString);
		MessageType type =(MessageType)query.uniqueResult();
		return type;
	}
}
