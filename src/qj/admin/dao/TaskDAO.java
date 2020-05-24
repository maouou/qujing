package qj.admin.dao;

import java.util.List;

import qj.admin.pojo.Task;


public interface TaskDAO {
	int getTotal();
	int getTotal(int type);
    void add(Task task);
    void update(Task user);
    void delete(Task task) ;
    void reset(int id);
    Task get(int id);
    List<Task> list();
    List<Task> list(int start, int count) ;
    List<Task> list(int start, int count,int type) ;
    boolean isExist(String name) ;
    Task get(String name) ;
    Task get(String name, String password) ;
}
