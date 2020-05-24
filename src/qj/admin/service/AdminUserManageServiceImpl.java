package qj.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qj.admin.dao.UserDAO;
import qj.admin.pojo.User;
import qj.util.Page;

@Service("AdminUserManageService")
public class AdminUserManageServiceImpl implements AdminUserManageService{
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public int getTotal() {
		// TODO 自动生成的方法存根
		int total = userDAO.getTotal();
		return total;
	}

	@Override
	public List listByPage(Page page) {
		List users = userDAO.list(page.getStart(), page.getCount());
		return users;
	}

	@Override
	public void handleUser(int IDNumber, int type) {
		User user = userDAO.get(IDNumber);
		userDAO.update(user, type);
	}

	@Override
	public void changePoints(int IDNumber, int points) {
		// TODO 自动生成的方法存根
		User user = userDAO.get(IDNumber);
		userDAO.changePoints(user, points);
	}

	@Override
	public User get(int IDNumber) {
		// TODO 自动生成的方法存根
		User user;
		user = userDAO.get(IDNumber);
		return user;
	}

	@Override
	public List list() {
		// TODO 自动生成的方法存根
		System.out.println("调用servicelist方法了");
		int total = getTotal();
		System.out.println("进入servicelist方法了，total = " + total);
		List<User> users = userDAO.list(0, total);
		System.out.println("完成servicelist方法了，users的长度为：" + users.size());
		return users;
	}

}
