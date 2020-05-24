package qj.admin.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import qj.admin.pojo.SuitType;

@Repository("SuittypeDAO")
public class SuittypeDAOImpl implements SuittypeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public SuitType get(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT s FROM SuitType AS s WHERE s.id like "+id;
		Query query = session.createQuery(hqlString);
		SuitType type =(SuitType)query.uniqueResult();
		return type;
	}

}
