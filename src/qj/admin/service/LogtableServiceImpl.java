package qj.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import qj.admin.dao.AdminLogDAO;
import qj.admin.pojo.AdminLog;
@Repository("LogtableService")
public class LogtableServiceImpl implements LogtableService {

	@Autowired
	private AdminLogDAO adminlogDAO;
	@Override
	public void addLog(AdminLog adminlog) {
		// TODO Auto-generated method stub
		adminlogDAO.inserLog(adminlog);
	}

}
