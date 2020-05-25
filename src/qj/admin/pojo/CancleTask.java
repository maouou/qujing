package qj.admin.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cancletask")
public class CancleTask {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String content;
	@ManyToOne
	@JoinColumn(name="taskId")
    @Fetch(value = FetchMode.JOIN)
	private Task task;
	//0->未被处理、1->已被处理
	private int flat;
	@ManyToOne
	@JoinColumn(name="type")
    @Fetch(value = FetchMode.JOIN)
	private CancleType type;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date time;
	private Integer version;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date updateTime;
	public int getId() {
		return id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	public int getFlat() {
		return flat;
	}
	public void setFlat(int flat) {
		this.flat = flat;
	}
	public CancleType getType() {
		return type;
	}
	public void setType(CancleType type) {
		this.type = type;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
