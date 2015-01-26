package com.epam.jms.producer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSession;

import org.apache.log4j.Logger;
import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.JMSFactoryType;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;

public class BanksJMSProducer {
    private static final Logger LOG = Logger.getLogger(BanksJMSProducer.class);

    private static BanksJMSProducer instance = null;
    private static ConnectionFactory factory;
    private Topic topic;
    private Connection connection;
    private Session session;

    private BanksJMSProducer() {
        TransportConfiguration transportConfiguration = 
                new TransportConfiguration(NettyConnectorFactory.class.getName());
        factory = (ConnectionFactory)
                HornetQJMSClient.createConnectionFactoryWithoutHA(JMSFactoryType.CF, transportConfiguration);
        topic = HornetQJMSClient.createTopic("logs");
    }

    public static BanksJMSProducer getInstance() {
        if(instance == null) {
           instance = new BanksJMSProducer();
        }
        return instance;
     }

    public void sendTextMessage(String text) {
        try {
            connection = factory.createConnection("i7eter","1");
            session = connection.createSession(false,
                        TopicSession.AUTO_ACKNOWLEDGE);

            MessageProducer producer = session.createProducer(topic);

            //Sending TextMessage to the Topic 
            TextMessage message = session.createTextMessage();
            message.setText(text);
            producer.send(message);

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
