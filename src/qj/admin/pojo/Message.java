package qj.admin.pojo;

import java.util.Date;
import java.util.List;

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

/**
 * @version 1.1
 * @author ozg
 * 1.1：
 * 把邮箱关联用户改为用户ID
 *
 */
@Entity
@Table(name = "message")
public class Message {
    //Message的ID，自增
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    //消息的详细内容
    public String content;
    //是否已读,0:未读,1:已读
    public int messageState;
    //消息类型，1：系统通知，2：用户通知
	@ManyToOne
	@JoinColumn(name="messageType")
    @Fetch(value = FetchMode.JOIN)
    public MessageType messageType;
    //接收消息的用户邮箱
    public Integer receiveId;
    //发送消息的用户邮箱(系统消息为 0)
    public Integer publisherId;
    //时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date time;
    //空的构造方法
    public Message() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return messageState;
    }

    public void setState(Integer state) {
        this.messageState = state;
    }

    public MessageType getType() {
        return messageType;
    }

    public void setType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }


    @Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", messageState=" + messageState + ", messageType="
				+ messageType + ", receiveId=" + receiveId + ", publisherId=" + publisherId + ", time=" + time + "]";
	}
	public int getMessageState() {
		return messageState;
	}
	public void setMessageState(int messageState) {
		this.messageState = messageState;
	}
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public void setId(int id) {
		this.id = id;
	}
}
