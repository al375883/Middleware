package JMS;
//Step 1:
//Import the JMS API classes.
import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.Queue;
//Import the classes to use JNDI.

import javax.jms.*;

public class HelloWorldProducerMapMessage {

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

    	    MapMessage mapMessage = mySess.createMapMessage();
    	    mapMessage.setString("Sender", myMsgProducer.toString());
    	    mapMessage.setInt("An Integer", 3456);
    	    mapMessage.setDouble("A Double", 1.23456789);
            System.out.println("Showing the MapMessage content");
            System.out.println("\t Sender: " 
                + mapMessage.getString("Sender"));
            System.out.println("\t Integer: " 
                + mapMessage.getInt("An Integer"));
            System.out.println("\t Double: "
                + mapMessage.getDouble("A Double"));
            System.out.println("Sending the MapMessage ...");
	    myMsgProducer.send(mapMessage);

            mySess.close();
            myConn.close();

        } catch (Exception jmse) {
            System.out.println("Exception occurred : " + jmse.toString());
            jmse.printStackTrace();
        }
    }
}
