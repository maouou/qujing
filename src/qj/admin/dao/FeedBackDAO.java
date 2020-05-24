package qj.admin.dao;

import java.util.List;

import qj.admin.pojo.FeedBack;
import qj.admin.pojo.Task;

public interface FeedBackDAO {
	int getTotal();
	FeedBack get(int id);
	List<FeedBack> list(int start, int count);
	void handled(int id);
	Task getTask(int id);
}
