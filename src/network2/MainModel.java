package network2;

public class MainModel {
	
	
	private static Message messageSent;

	private static Node a,b,c,d,e;
	
	
	public static void main(String[] args) {
       
		
		
	UserUI Reader = new UserUI();	

	//This the message that we want to send 
	 

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
	
	
	
	
	messageSent = new Message( Reader.getMessage() ,d,a);
	
	// i still need to add things 
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
