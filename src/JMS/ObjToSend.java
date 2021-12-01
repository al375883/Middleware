package JMS;

import java.io.Serializable;

class ObjToSend implements Serializable {

    private String str = "Object ";
    private int    id;

    public ObjToSend(int id){
	this.id = id;
    }

    public String printObj(){

	return str + id;
    }

}
