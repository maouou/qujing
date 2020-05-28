package qj.admin.dao;

import qj.admin.pojo.Admin;

public interface AdminDAO {
	Admin get(String username);
	boolean isExited(String username);
	boolean isMatched(String username,String pwd);
}
