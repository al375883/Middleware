package JMS;
//Step 1:
//Import the JMS API classes.
import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Message;
import javax.jms.TextMessage;
//Import the classes to use JNDI.
import javax.naming.*;
import java.util.*;

import javax.jms.*;



public class HelloWorldProducerObject {

    /**
     * Main method.
     *
     * @param args	not used
     *
     */
    public static void main(String[] args) {

        try {

            ConnectionFactory myConnFactory;
            Queue myQueue;

            myConnFactory = new com.sun.messaging.ConnectionFactory();

            Connection myConn = myConnFactory.createConnection();

            Session mySess = myConn.createSession(false, Session.AUTO_ACKNOWLEDGE);

            myQueue = new com.sun.messaging.Queue("world");

            MessageProducer myMsgProducer = mySess.createProducer(myQueue);

    	    ObjectMessage objectMessage = mySess.createObjectMessage();
	    ObjToSend object = new ObjToSend(1);
	    objectMessage.setObject(object);

	    System.out.println("Sending ObjectMessage ... ");
	    myMsgProducer.send(objectMessage);

	    ObjToSend object2 = new ObjToSend(2);
	    objectMessage.setObject(object2);

	    System.out.println("Sending ObjectMessage ... ");
	    myMsgProducer.send(objectMessage);

            mySess.close();
            myConn.close();

        } catch (Exception jmse) {
            System.out.println("Exception occurred : " + jmse.toString());
            jmse.printStackTrace();
        }
    }
}
