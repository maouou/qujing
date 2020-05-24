package qj.admin.pojo;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
    public Date publishTime;
    //任务结束时间
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    public Date deadline;
    //是否加急,0:未加急,1:加急
    public Integer expedited;
    //发布者确认,0:未确认,1:确认
    public Integer senderAccomplish;
    //接收者确认,0:未确认,1:确认
    public Integer receiverAccomplish;
    //任务反馈
    //public Comment comment;
    //任务主题
    //public String theme;
    //任务详细内容
    public String content;
    //任务类型，1：快递代取，2：文件代送，3：文件代取，4：食堂代买，5：物品代购，6：其他
    @ManyToOne
    @JoinColumn(name="ttid")
    @Fetch(value = FetchMode.JOIN)
    public Tasktype taskType;
    //任务类型细分
    //public Integer taskNextType;
    //发布者取消任务, 0:未取消,1:取消
    public Integer senderCancle;
    //接收者取消任务, 0:未取消,1:取消
    public Integer receiverCancle;
    //发布者id
    public String senderid;
    //接收者id
    public String receiverid;
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
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
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
	public Integer getSenderAccomplish() {
		return senderAccomplish;
	}
	public void setSenderAccomplish(Integer senderAccomplish) {
		this.senderAccomplish = senderAccomplish;
	}
	public Integer getReceiverAccomplish() {
		return receiverAccomplish;
	}
	public void setReceiverAccomplish(Integer receiverAccomplish) {
		this.receiverAccomplish = receiverAccomplish;
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
	public Integer getSenderCancle() {
		return senderCancle;
	}
	public void setSenderCancle(Integer senderCancle) {
		this.senderCancle = senderCancle;
	}
	public Integer getReceiverCancle() {
		return receiverCancle;
	}
	public void setReceiverCancle(Integer receiverCancle) {
		this.receiverCancle = receiverCancle;
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
    
}
