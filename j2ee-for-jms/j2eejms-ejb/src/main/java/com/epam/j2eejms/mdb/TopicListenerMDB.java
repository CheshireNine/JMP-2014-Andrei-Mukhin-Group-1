package com.epam.j2eejms.mdb;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.epam.j2eejms.model.User;

/**
 * Message-Driven Bean implementation class for: TopicListenerMDB
 */
@MessageDriven(
        activationConfig = { @ActivationConfigProperty(
                propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
            @ActivationConfigProperty(
                    propertyName = "destination", propertyValue = "topic/logs")
		})
public class TopicListenerMDB implements MessageListener {
    private static final Logger LOG = Logger.getLogger(TopicListenerMDB.class);
    /**
     * Default constructor. 
     */
    public TopicListenerMDB() {
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage) message;
                LOG.info("[" + new Date() + "] Recieved TextMessage: " + msg.getText());
            } else if (message instanceof MapMessage) {
                MapMessage msg = (MapMessage) message;
                LOG.info("[" + new Date() + "] Recieved MapMesage with luckyValue: " + msg.getInt("luckyValue"));
            } else if (message instanceof BytesMessage) {
                BytesMessage msg = (BytesMessage)message;
                byte data[]=new byte[(int)msg.getBodyLength()];
                msg.readBytes(data);
                LOG.info("[" + new Date() + "] Recieved bytes message: " + (new String(data, "UTF-8")));
            } else if (message instanceof ObjectMessage) {
                ObjectMessage msg = (ObjectMessage) message;
                if(msg.getObject() instanceof User) {
                    User user = (User) msg.getObject();
                    LOG.info("[" + new Date() + "] Recieved User object with username: " + user.getUsername());
                }
            } else {
                LOG.info("Not a valid message for this Topic MDB");
            }
 
        } catch (JMSException | UnsupportedEncodingException e) {
            LOG.error(e.getMessage());
        }
        
    }

}
