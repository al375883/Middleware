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

class TextListenerObject implements MessageListener {

    public boolean done = false;

public void onMessage(Message message) {
    if (message instanceof ObjectMessage) {
	System.out.println("ObjectMessage received!!");	
	done = true;
        ObjectMessage  objMessage = (ObjectMessage) message;

        try {
            System.out.println("\tReading ObjectMessage\n\t"
			       + ((ObjToSend)objMessage.getObject()).printObj());
        } catch (Exception e) {
            System.out.println("Exception in onMessage(): " + e.toString());
        }
    }

}

}


public class HelloWorldConsumerObject {

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
            TextListenerObject textListenerObject = new TextListenerObject();
            myMsgConsumer.setMessageListener(textListenerObject);
            myConn.start();
            
	    System.out.println("Waiting for Message ... ");            
            while(!textListenerObject.done){
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
