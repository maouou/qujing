package qj.admin.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import qj.admin.pojo.Admin;
import qj.admin.util.MQUtil;

@Repository("AdminDAO")
public class AdminDAOImpl implements AdminDAO{

	@Autowired
	SessionFactory sessionfactory;
	
	@Override
	public Admin get(String username) {
		// TODO 自动生成的方法存根
		Admin admin = null;
		Session session = sessionfactory.getCurrentSession();
		String hqlString = "SELECT a FROM Admin AS a WHERE a.username like '" + username + "'";
		Query query = session.createQuery(hqlString);
		admin = (Admin)query.uniqueResult();
		return admin;
	}

	@Override
	public boolean isExited(String username) {
		// TODO 自动生成的方法存根
		Admin admin = null;
		Session session = sessionfactory.getCurrentSession();
		String hqlString = "SELECT a FROM Admin AS a WHERE a.username like '" + username + "'";
		Query query = session.createQuery(hqlString);
		admin = (Admin)query.uniqueResult();
		if(admin == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean isMatched(String username, String pwd) {
		// TODO 自动生成的方法存根
		Admin admin = get(username);
		String password = admin.getPassword();
		System.out.println(password);
		ByteSource credentialsSalt = ByteSource.Util.bytes(admin.getUsername());
	    String password1 = new SimpleHash("MD5",pwd,
	                credentialsSalt,1024).toBase64();
	    System.out.println(password1);
	    if(password.equals(password1))
	    	 return true;
	    else
	    	 return false;
	}

}
