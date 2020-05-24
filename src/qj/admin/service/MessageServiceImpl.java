package qj.admin.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qj.admin.dao.MessageDAO;
import qj.admin.dao.MessageTypeDAO;
import qj.admin.pojo.Message;
import qj.admin.pojo.MessageType;

@Service("MessageService")
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private MessageTypeDAO messageTypeDAO;

	@Override
	public void add(String content, int state, int MessageType, int receiveId, int publisherId) {
		Message message = new Message();
		MessageType messagetype = new MessageType();
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		message.setContent(content);
		message.setState(0);
		messagetype = messageTypeDAO.get(MessageType);
		message.setMessageType(messagetype);
		message.setReceiveId(receiveId);
		message.setPublisherId(publisherId);
		message.setTime(timestamp);
		messageDAO.add(message);
	}

}
