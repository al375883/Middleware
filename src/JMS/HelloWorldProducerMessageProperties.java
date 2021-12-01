package JMS;
//Step 1:
//Import the JMS API classes.
import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;
//Import the classes to use JNDI.


public class HelloWorldProducerMessageProperties {

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

            TextMessage myTextMsg = mySess.createTextMessage();
            myTextMsg.setText("releaseYear: 1979");
	    myTextMsg.setIntProperty("releaseYear", 1979);
            System.out.println("Sending Message -> " + myTextMsg.getText());
            myMsgProducer.send(myTextMsg);

            TextMessage myTextMsg2 = mySess.createTextMessage();
            myTextMsg2.setText("releaseYear: 1982");
	    myTextMsg2.setIntProperty("releaseYear", 1982);
            System.out.println("Sending Message -> " + myTextMsg2.getText());
            myMsgProducer.send(myTextMsg2);

            TextMessage myTextMsg3 = mySess.createTextMessage();
            myTextMsg3.setText("releaseYear: 1990");
	    myTextMsg3.setIntProperty("releaseYear", 1990);
            System.out.println("Sending Message -> " + myTextMsg3.getText());
            myMsgProducer.send(myTextMsg3);

            mySess.close();
            myConn.close();

        } catch (Exception jmse) {
            System.out.println("Exception occurred : " + jmse.toString());
            jmse.printStackTrace();
        }
    }
}
