package com.epam;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSession;

import org.apache.log4j.Logger;
import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.JMSFactoryType;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.junit.Before;
import org.junit.Test;

import com.epam.j2eejms.model.User;

public class BanksJMSTest {
    private static final Logger LOG = Logger.getLogger(BanksJMSTest.class);

    private ConnectionFactory factory;

    @Before
    public void setUp() throws Exception {
        TransportConfiguration transportConfiguration = 
                new TransportConfiguration(NettyConnectorFactory.class.getName());
        factory = (ConnectionFactory)
                HornetQJMSClient.createConnectionFactoryWithoutHA(JMSFactoryType.CF, transportConfiguration);
    }

    @Test
    public void test() {

        //The name of topic. Should match one in standalone.xml
        Topic topic = HornetQJMSClient.createTopic("users");
        Connection connection = null;
        Session session = null;
        try {
            connection = factory.createConnection("i7eter","1");
            session = connection.createSession(false,
                        TopicSession.AUTO_ACKNOWLEDGE);

            MessageProducer producer = session.createProducer(topic);

            //1. Sending TextMessage to the Topic 
            TextMessage message = session.createTextMessage();
            message.setText("Text message from EJB console client");
            producer.send(message);
            LOG.info("TextMessage is sent");

            //2. MapMessage
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setInt("luckyValue", 13);
            producer.send(mapMessage);
            LOG.info("MapMessage is sent");

            //3. ByteMessage
            BytesMessage byteMsg = session.createBytesMessage();
            byteMsg.writeBytes("Message from EJB console client".getBytes());
            producer.send(byteMsg);
            LOG.info("ByteMessage is sent");

            //4. ObjectMessage
            ObjectMessage objMessage = session.createObjectMessage();
            User user = new User();
            user.setUsername("i7eter");
            objMessage.setObject(user);
            producer.send(objMessage);
            LOG.info("ObjectMessage is sent");

        } catch (JMSException e) {
            LOG.info(e.getMessage());
        } finally {
            try {
                connection.close();
                session.close();
            } catch (JMSException e) {
                LOG.info(e.getMessage());
            }
        }
    }

}
