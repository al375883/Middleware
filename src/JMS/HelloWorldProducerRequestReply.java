package JMS;
//Step 1:
//Import the JMS API classes.
import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Message;
import javax.jms.TextMessage;
//Import the classes to use JNDI.

import javax.jms.*;

public class HelloWorldProducerRequestReply {

static class TextListener implements MessageListener {

    public boolean done = false;


public void onMessage(Message message) {
    if (message instanceof TextMessage) {
	done = true;
        TextMessage  requestMessage = (TextMessage) message;

        try {
            System.out.println("\tReceived reply: " + requestMessage.getText());

        } catch (Exception e) {
            // System.out.println("Exception in onMessage(): " + e.toString());
	    e.printStackTrace();
        }
    }

}

}

    public static void main(String[] args) {

        try {

            ConnectionFactory myConnFactory;
            Queue myQueue;
	    Destination replyQueue;
            myConnFactory = new com.sun.messaging.ConnectionFactory();

            Connection myConn = myConnFactory.createConnection();

            Session mySess = myConn.createSession(false, Session.AUTO_ACKNOWLEDGE);

            myQueue = new com.sun.messaging.Queue("RequestReply");
	    replyQueue = new com.sun.messaging.Queue("replyQueue");

            MessageProducer myMsgProducer = mySess.createProducer(myQueue);

            TextMessage myTextMsg = mySess.createTextMessage();
            myTextMsg.setText("Hello World");
            System.out.println("Sending Message: " + myTextMsg.getText());
	    myTextMsg.setJMSReplyTo(replyQueue);
            myMsgProducer.send(myTextMsg);

            MessageConsumer myMsgConsumer = mySess.createConsumer(replyQueue);
            TextListener textListener = new TextListener();
            myMsgConsumer.setMessageListener(textListener);
            myConn.start();

	    System.out.println("Waiting for Reply Message... ");            
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
