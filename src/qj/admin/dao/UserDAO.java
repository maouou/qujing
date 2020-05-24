package qj.admin.dao;

import java.util.List;

import qj.admin.pojo.User;

public interface UserDAO {
	int getTotal();
	void update(User user,int type);
	void delete(User user);
	void resetpassword(User user);
	void changePoints(User user, int points);
	User get(int IDNumber);
	List<User> list();
	List<User> list(int start, int count);
	boolean isExist(int IDNumber);
	boolean isExist(String userName);
	User get(String userName);
}
