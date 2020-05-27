package qj.admin.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import qj.admin.pojo.AdminLog;
@Repository("AdminLogDAO")
public class AdminLogDAOImpl implements AdminLogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void inserLog(AdminLog adminlog) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(adminlog);
	}

}
