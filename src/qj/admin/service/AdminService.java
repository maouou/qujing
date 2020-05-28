package qj.admin.service;

import qj.admin.pojo.Admin;

public interface AdminService {
	boolean isMatched(String username,String pwd);
	Admin get(String username);
	boolean isExited(String username);
}
