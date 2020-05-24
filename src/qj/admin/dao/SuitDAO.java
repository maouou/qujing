package qj.admin.dao;

import java.util.List;

import qj.admin.pojo.Suit;
import qj.admin.pojo.Task;

public interface SuitDAO {
	int getTotal();
	Suit get(int id);
	int add(Suit  suit);
	int update(Task task);
	List<Suit> list(int start,int count);
	List<Suit> list(int start,int count,int type);
	void handleed(int id);
	Task getTask(int id);
}
