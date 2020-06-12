package qj.admin.dao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.eclipse.jdt.internal.compiler.env.IModule.IService;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.internal.ast.tree.SelectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import qj.admin.pojo.User;
import qj.admin.util.MQUtil;

@Repository("UserDAO")
public class UserDAOImpl implements UserDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int getTotal() {
		// TODO 自动生成的方法存根
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT count(u) FROM User AS u";
		Query query = session.createQuery(hqlString);
		int total = ((Long)query.uniqueResult()).intValue();
		System.out.println(total);
		return total;
	}
	
	@Override
	public void update(User user, int type) {
		//type1->reset password
		//type=1重置密码
		//type=2冻结
		Session session = sessionFactory.getCurrentSession();
		/*try {
			MQUtil.send("method=update&studentId=" + user.studentId);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/
		if(type == 1&&user.getState()==1)
		{
			  ByteSource credentialsSalt = ByteSource.Util.bytes(user.studentId);
		        String pwd = new SimpleHash("MD5","123456ABc",
		                credentialsSalt,1024).toBase64();
			user.setPassword(pwd);	
		}
		//type2->delete
		if(type == 2&&user.getState()==1)
		{
			user.setState(0);
		}
		//tyepe3->stop
		if(type == 3&&user.getState()==1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			Date date = new Date();
			date = calendar.getTime();
			user.setEndTime(date);
			user.setState(2);
		}
		//type4->wake
		if(type == 4&&user.getState()==2)
		{
			user.setEndTime(null);
			user.setState(1);
		}
		session.update(user);
	}

	@Override
	public void delete(User user) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public User get(int studentId) {
		// TODO 自动生成的方法存根
		User user;
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT u FROM User As u WHERE u.studentId like '" + studentId + "'";
		Query query = session.createQuery(hqlString);
		user = (User)query.uniqueResult();
		return user;
	}

	@Override
	public List<User> list() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	@Transactional
	public List<User> list(int start, int count) {
		// TODO 自动生成的方法存根
		System.out.println("调用DAO.list方法了，users的长度为：");
		List<User> users = null;
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "SELECT u FROM User As u";
		Query query = session.createQuery(hqlString);
		query.setFirstResult(start);
		query.setMaxResults(count);
		users = query.list();
		System.out.println("完成DAO.list方法了，users的长度为：" + users.size());
		return users;
	}

	@Override
	public boolean isExist(int studentId) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean isExist(String userName) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public User get(String userName) {
		// TODO 自动生成的方法存根
		return get(Integer.valueOf(userName));
	}

	@Override
	public void resetpassword(User user) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void changePoints(User user, int points) {
		// TODO 自动生成的方法存根
		/*try {
			MQUtil.send("method=update&studentId=" + user.studentId);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/
		user.setPoints(points);
		System.out.println("修改用户积分");
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}

	@Override
	public void reduceReceivedTaskNumber(User user) {
		// TODO 自动生成的方法存根
		Session session = sessionFactory.getCurrentSession();
		int tempNumber = user.receiveTaskNumber;
		tempNumber = tempNumber - 1;
		user.setReceiveTaskNumber(tempNumber);
		session.update(user);
	}

	@Override
	public void addReportedNumber(User user) {
		// TODO 自动生成的方法存根
		Session session = sessionFactory.getCurrentSession();
		int tempNumber = user.reportedNumber;
		tempNumber = tempNumber + 1;
		user.setReportedNumber(tempNumber);
		session.update(user);
	}

	@Override
	public void addMaliciousAcceptanceNumber(User user) {
		// TODO 自动生成的方法存根
		Session session = sessionFactory.getCurrentSession();
		int tempNumber = user.maliciousAcceptanceNumber;
		tempNumber = tempNumber + 1;
		user.setMaliciousAcceptanceNumber(tempNumber);
		session.update(user);
	}
	
}
