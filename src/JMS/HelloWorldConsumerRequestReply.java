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

public class HelloWorldConsumerRequestReply {

    // public ConnectionFactory myConnFactory;
    public static  Connection myConn;
    public static Session mySess;


static class TextListener implements MessageListener {

    public boolean done = false;

public void onMessage(Message message) {
    if (message instanceof TextMessage) {
	System.out.println("Message received!!");
	done = true;
        TextMessage  requestMessage = (TextMessage) message;

        try {
            System.out.println("\tReading message: " + requestMessage.getText());

	    String contents = requestMessage.getText();
	    Destination replyDestination = message.getJMSReplyTo();
	    MessageProducer replyProducer = mySess.createProducer(replyDestination);

	    TextMessage replyMessage = mySess.createTextMessage();
	    replyMessage.setText("Reply to " + contents);
	    replyMessage.setJMSCorrelationID(requestMessage.getJMSMessageID());

            System.out.println("\tSending reply in 3 s ... ");
	    Thread.sleep(3000);
	    replyProducer.send(replyMessage);

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

            myConnFactory = new com.sun.messaging.ConnectionFactory();

            myConn = myConnFactory.createConnection();

            mySess = myConn.createSession(false, Session.AUTO_ACKNOWLEDGE);

            myQueue = new com.sun.messaging.Queue("RequestReply");


            MessageConsumer myMsgConsumer = mySess.createConsumer(myQueue);
            TextListener textListener = new TextListener();
            myMsgConsumer.setMessageListener(textListener);
            myConn.start();
            

	    System.out.println("Waiting for Request Message... ");            
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
