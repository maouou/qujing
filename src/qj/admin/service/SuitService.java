package qj.admin.service;

import java.util.List;

import qj.admin.pojo.Suit;
import qj.admin.pojo.Task;
import qj.util.Page;

public interface SuitService {
	int add(Suit suit);
	int update(Task task);
	public int getTotal();
	public List listByPage(Page page);
	public List list();
	public Suit get(int id);
	public void setHandeled(int id);
	public Task getTask(int id);
	public void setHandled(int id);
}
