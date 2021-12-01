package JMS;
//Step 1:
//Import the JMS API classes.
import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Message;
//Import the classes to use JNDI.

import javax.jms.*;

class TextListenerMapMessage implements MessageListener {

    public boolean done = false;


public void onMessage(Message message) {
    if (message instanceof MapMessage) {
	System.out.println("Message received!!");
	done = true;
        MapMessage  mapMessage = (MapMessage) message;

        try {
            System.out.println("\tReading MapMessage in a different order"
                + " from the way it was generated:");
            System.out.println("\t Sender: " 
                + mapMessage.getString("Sender"));
            System.out.println("\t Double: "
                + mapMessage.getDouble("A Double"));
            System.out.println("\t Integer: " 
                + mapMessage.getInt("An Integer"));
        } catch (Exception e) {
            System.out.println("Exception in onMessage(): " + e.toString());
        }
    }

}

}


public class HelloWorldConsumerMapMessage {

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

            MessageConsumer myMsgConsumer = mySess.createConsumer(myQueue);
            TextListenerMapMessage textListener = new TextListenerMapMessage();
            myMsgConsumer.setMessageListener(textListener);
            myConn.start();
                        
	    System.out.println("Consumer ready: "+myMsgConsumer.toString());
	    System.out.println("Waiting for Message ... ");            
            while(!textListener.done){
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
