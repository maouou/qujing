package qj.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qj.admin.dao.AdminDAO;
import qj.admin.pojo.Admin;

@Service("AdminService")
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminDAO adminDAO;
	@Override
	public boolean isMatched(String username, String pwd) {
		// TODO 自动生成的方法存根
		return adminDAO.isMatched(username, pwd);
	}

	@Override
	public Admin get(String username) {
		// TODO 自动生成的方法存根
		return adminDAO.get(username);
	}

	@Override
	public boolean isExited(String username) {
		// TODO 自动生成的方法存根
		return adminDAO.isExited(username);
	}
	

}
