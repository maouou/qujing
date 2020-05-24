package qj.admin.pojo;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @version 1.0
 */
@Entity
@Table(name = "feedback")
public class FeedBack {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String content;
	@ManyToOne
	@JoinColumn(name="taskId")
    @Fetch(value = FetchMode.JOIN)
	private Task task;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    public Date time;
	@ManyToOne
	@JoinColumn(name="type")
    @Fetch(value = FetchMode.JOIN)
	private FeedBackType type;
	//0->未被处理、1->已被处理
	private int flat;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public FeedBackType getType() {
		return type;
	}
	public void setType(FeedBackType type) {
		this.type = type;
	}
	public int getFlat() {
		return flat;
	}
	public void setFlat(int flat) {
		this.flat = flat;
	}
}
