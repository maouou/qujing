package qj.admin.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import qj.admin.pojo.CancleType;

@Repository("CancleTypeDAO")
public class CancleTypeDAOImpl implements CancleTypeDAO{
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public CancleType get(int id) {
		// TODO 自动生成的方法存根
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT c FROM CancleType AS c WHERE c.id like "+id;
		Query query = session.createQuery(hqlString);
		CancleType type =(CancleType)query.uniqueResult();
		return type;
	}

}
