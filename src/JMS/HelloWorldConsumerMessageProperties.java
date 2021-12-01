package JMS;
//Step 1:
//Import the JMS API classes.
import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Message;
import javax.jms.TextMessage;
//Import the classes to use JNDI.

import javax.jms.*;

class TextListenerMessageProperties implements MessageListener {

    public boolean done = false;
    public int     count = 0;


public void onMessage(Message message) {
    if (message instanceof TextMessage) {
	System.out.println("Message received!!");
        TextMessage  msg = (TextMessage) message;

        try {
            System.out.println("\tReading message: " + msg.getText());
        } catch (Exception e) {
            System.out.println("Exception in onMessage(): " + e.toString());
        }
	count++;
	if(count==2)
	    done = true;
    }

}

}


public class HelloWorldConsumerMessageProperties {

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

            MessageConsumer myMsgConsumer = mySess.createConsumer(myQueue, 
              "(releaseYear < 1980) OR (releaseYear > 1989)");


            TextListenerMessageProperties textListenerMessageProperties = new TextListenerMessageProperties();
            myMsgConsumer.setMessageListener(textListenerMessageProperties);
            myConn.start();
            
            
	    System.out.println("Waiting for Messages with condition (releaseYear < 1980) OR (releaseYear > 1989) ... ");            
            while(!textListenerMessageProperties.done){
		System.out.println("\tSleep for 1 s ... ");
		Thread.sleep(1000);
            }

            mySess.close();
            myConn.close();

        } catch (Exception jmse) {
            System.out.println("Exception occurred : " + jmse.toString());
            jmse.printStackTrace();
        }
    }


}
