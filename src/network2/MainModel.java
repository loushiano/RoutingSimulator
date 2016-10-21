package network2;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MainModel implements Observer{
	
	
	private  Message messageSent;

	
	private ArrayList<Node> topology;
	
	public MainModel(){
		topology =new ArrayList<Node>();
		
	}
	
       public void ModelIt(){
		
		
	

	//This the message that we want to send 
	 

	// creating the five nodes 
	 Node a = new Node ("a");
	 
	 Node b = new Node ("b");
	 
	 Node c = new Node ("c");
	
	 Node d = new Node ("d");
	 
	 Node e = new Node ("e");
	 
	
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
	
	
	topology.add(a);
	topology.add(b);
	 topology.add(c);
	topology.add(d);
	topology.add(e);
	
	UserUI Reader = new UserUI(topology,this.toString());	
	
       }
	
	//UserUI Reader = new UserUI();	
	
	
	//messageSent = new Message( Reader.getMessage() ,d,a);
	
	// i still need to add things 
	
	


	public ArrayList<Node> getTopology() {
		return topology;
	}

	public void setTopology(ArrayList<Node> topology) {
		this.topology = topology;
	}
	public String toString(){
		String s="";
		for(Node no:topology){
			s+=no.toString();
			
		}
		return s;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}


	
	
	
	
	
	
	
	
	
	
	
	


