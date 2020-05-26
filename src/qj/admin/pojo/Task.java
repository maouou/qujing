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
 * @author ozg
 */
@Entity
@Table(name = "task")
public class Task {
    //task的ID，自增
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    //任务名
    public String name;
    //完成任务可获得积分
    public String points;
	//发布时间
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    public Date time;
    //任务结束时间
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    public Date deadline;
    //是否加急,0:未加急,1:加急
    public Integer expedited;
    //任务状态
    public Integer state;
    //任务详细内容
    public String content;
    //任务类型，1：快递代取，2：文件代送，3：文件代取，4：食堂代买，5：物品代购，6：其他
    @ManyToOne
    @JoinColumn(name="ttid")
    @Fetch(value = FetchMode.JOIN)
    public Tasktype taskType;
    //发布者id
    public String senderid;
    //接收者id
    public String receiverid;
    //数据修改时间
    private Date updateTime;
    //版本号
    private Integer version;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public Integer getExpedited() {
		return expedited;
	}
	public void setExpedited(Integer expedited) {
		this.expedited = expedited;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Tasktype getTaskType() {
		return taskType;
	}
	public void setTaskType(Tasktype taskType) {
		this.taskType = taskType;
	}
	public String getSenderid() {
		return senderid;
	}
	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}
	public String getReceiverid() {
		return receiverid;
	}
	public void setReceiverid(String receiverid) {
		this.receiverid = receiverid;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public void setState(Integer state)
	{
		this.state=state;
	}
	public Integer getState()
	{
		return state;
	}
    
}
