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

public class HelloWorldConsumer_Queue {

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


            while(true){
                System.out.println("Waiting for Message... ");
                MessageConsumer myMsgConsumer = mySess.createConsumer(myQueue);
                myConn.start();
                Message msg = myMsgConsumer.receive();

                if (msg instanceof TextMessage) {
                    TextMessage txtMsg = (TextMessage) msg;
                    System.out.println("Read Message: " + txtMsg.getText());
                    break;
                }
            }

            mySess.close();
            myConn.close();

        } catch (Exception jmse) {
            System.out.println("Exception occurred : " + jmse);
            jmse.printStackTrace();
        }
    }
}
