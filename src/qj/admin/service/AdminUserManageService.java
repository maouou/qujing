package qj.admin.service;

import java.util.List;

import qj.admin.pojo.User;
import qj.util.Page;

public interface AdminUserManageService {
	public int getTotal();
	public List listByPage(Page page);
	public List list();
	public void handleUser(int IDNumber, int type);
	public void changePoints(int IDNumber, int points);
	public User get(int IDNumber);
}
