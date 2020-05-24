package qj.admin.service;

import java.util.List;

import qj.admin.pojo.CancleTask;
import qj.util.Page;

public interface CancleTaskService {
	int add(CancleTask cancleTask);
	int getTotal();
	List listByPage(Page page);
	List list();
	CancleTask get(int id);
	void agreeReceiverCancle(int id);
	void refuseReceiverCancle(int id);
	void agreeSenderCancle(int id);
	void refuseSenderCancle(int id);
}
