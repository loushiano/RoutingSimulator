package network2;

public class MainModel {
	
	
	private static Message messageSent;
	private static String sysc;
	private static Node a,b,c,d,e;
	
	
	public static void main(String[] args) {
       

	//This the message that we want to send 
	 sysc = "sysc3110 is amazing course";

	// creating the five nodes 
	 a = new Node ("a");
	 b = new Node ("b");
	 c = new Node ("c");
	 d = new Node ("d");
	 e = new Node ("e");
	
	// Adding neighbors to Node a 
	a.addNeighbour(b);
	a.addNeighbour(c);
	
	// Adding neighbors to Node b 
	b.addNeighbour(a);
	b.addNeighbour(e);
	
	// Adding neighbors to Node c 
	c.addNeighbour(a);
	c.addNeighbour(d);
	
	// Adding neighbors to Node d 
	d.addNeighbour(b);
	d.addNeighbour(c);
	
	// Adding neighbors to Node e 
	e.addNeighbour(a);
	e.addNeighbour(b);
	
	
	
	
	messageSent = new Message(sysc ,d,a);
	
	// i still need to add things 
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
