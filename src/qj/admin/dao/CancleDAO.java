package qj.admin.dao;

import java.util.List;

import qj.admin.pojo.CancleTask;

public interface CancleDAO {
	int getToatl();
	CancleTask get(int id);
	int add(CancleTask cancletsk);
	List<CancleTask> list(int start,int count);
	void handled(int id);
	void agreeReceiverCancle(int id);
	void refusereceiverCancle(int id);
	void agreeSenderCancle(int id);
	void refuseSenderCancle(int id);
}
