package qj.admin.pojo;

import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @version 1.0
 * @author ozg
 */
@Entity
@Table(name = "user")
public class User {
    //用户ID
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    //用户登录邮箱
    public String email;
    //用户名
    public String username;
    //学号
    public String studentId;
    //密码
    public String password;
    //用户所拥有的积分
    public Integer points;
    //用户账户状态，0:未激活（注销状态），1：激活，2： 冻结
    public Integer state;
    //账户的详细信息
    public String content;
    //用户账户冻结状态的开始时间
    @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
    public Date startTime;
    //用户账户冻结状态的结束时间
    @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
    public Date endTime;
    //用户已接收任务数
    public Integer receiveTaskNumber;
    //用户恶意接收的任务数量
    public Integer maliciousAcceptanceNumber;
    //用户被成功举报的数量
    public Integer reportedNumber;

    //接收的任务
    @Transient
    public List<Task> acceptTask;
    //发布的任务
    @Transient
    public List<Task> publishTask;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getReceiveTaskNumber() {
		return receiveTaskNumber;
	}
	public void setReceiveTaskNumber(Integer receiveTaskNumber) {
		this.receiveTaskNumber = receiveTaskNumber;
	}
	public Integer getMaliciousAcceptanceNumber() {
		return maliciousAcceptanceNumber;
	}
	public void setMaliciousAcceptanceNumber(Integer maliciousAcceptanceNumber) {
		this.maliciousAcceptanceNumber = maliciousAcceptanceNumber;
	}
	public Integer getReportedNumber() {
		return reportedNumber;
	}
	public void setReportedNumber(Integer reportedNumber) {
		this.reportedNumber = reportedNumber;
	}
	public List<Task> getAcceptTask() {
		return acceptTask;
	}
	public void setAcceptTask(List<Task> acceptTask) {
		this.acceptTask = acceptTask;
	}
	public List<Task> getPublishTask() {
		return publishTask;
	}
	public void setPublishTask(List<Task> publishTask) {
		this.publishTask = publishTask;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", username=" + username + ", IDNumber=" + studentId
				+ ", password=" + password + ", points=" + points + ", state=" + state + ", content=" + content
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", receiveTaskNumber=" + receiveTaskNumber
				+ ", maliciousAcceptanceNumber=" + maliciousAcceptanceNumber + ", reportedNumber=" + reportedNumber
				+ ", acceptTask=" + acceptTask + ", publishTask=" + publishTask + "]";
	}
}
