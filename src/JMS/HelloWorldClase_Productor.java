package JMS;

import com.sun.messaging.ConnectionConfiguration;
import javax.jms.*;

public class HelloWorldClase_Productor {
    
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
            
            MessageProducer myMsgProducer = mySess.createProducer(myQueue);
            
            TextMessage myTextMsg = mySess.createTextMessage();
            myTextMsg.setText("Hola clase");
            System.out.println("Sending Message: " + myTextMsg.getText());
            myMsgProducer.send(myTextMsg);
            
            mySess.close();
            myConn.close();
            
        } catch (Exception jmse) {
            System.out.println("Exception occurred : " + jmse);
            jmse.printStackTrace();
        }
    }
}