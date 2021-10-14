package JMS;

import com.sun.messaging.ConnectionConfiguration;
import javax.jms.*;

public class HelloWorldClase_Consumidor {
    
    public static void main(String[] args) {
        try {
            ConnectionFactory myConnFactory;
            Queue myQueue;
            
            // No-standard way of getting Context Factory
            myConnFactory = new com.sun.messaging.ConnectionFactory();
            Connection myConn = myConnFactory.createConnection();
            Session mySess = myConn.createSession
            (false, Session.AUTO_ACKNOWLEDGE);
            // No-standard way of getting destination
            myQueue = mySess.createQueue("clase");
            
            // Consumer
            MessageConsumer myMsgConsumer = mySess.createConsumer(myQueue);
            myConn.start();
            Message msg = myMsgConsumer.receive();
            if (msg instanceof TextMessage) {
                TextMessage txtMsg = (TextMessage) msg;
                System.out.println("Read Message: " + txtMsg.getText());
            }
            
            mySess.close();
            myConn.close();
            
        } catch (Exception jmse) {
            System.out.println("Exception occurred : " + jmse);
            jmse.printStackTrace();
        }
    }
}