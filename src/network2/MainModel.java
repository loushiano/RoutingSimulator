package network2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class MainModel implements Observer{
	
	
	private  ArrayList<String[]> messagesToSend;

	
	private ArrayList<Node> topology;
	private HashMap<String,Node> topologies;
	private ArrayList<Message> messagesSent;
	private String strategy;
	private boolean flag=true;
	
	public MainModel(){
		topology =new ArrayList<Node>();
		messagesToSend=new ArrayList<String[]>();
		messagesSent=new ArrayList<Message>();
		topologies=new HashMap<String,Node>();
	}
	
       public void modelIt(){

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
	topologies.put(a.getName(),a);
	topologies.put(b.getName(),b);
	topologies.put(c.getName(),c);
	topologies.put(d.getName(),d);
	topologies.put(e.getName(),e);
	
	UserUI Reader = new UserUI(this);	
	
   }
	


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
	public void applyStrategy(ArrayList<String[]> messages){
		while(flag){
			
		}
		RandomStrategy random=new RandomStrategy();
		for(String[] i:messages){
			Message message=new Message(i[0],topologies.get(i[1]),topologies.get(i[2]));
			random.transferMessage(message);
			messagesSent.add(message);
		}
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		 messagesToSend=(ArrayList<String[]>)arg1;
		flag=false;
	}
	
	
	

	private void hopsMetrix( ArrayList<Message> messages) {
		int j=0;
		for(Message i:messages){
			j+=i.getNumHops();
			
		}
		j=j/messages.size();
		System.out.println("the average Hops for the random Strategy is: "+j);
	}
	public static void main(String args[]){
		MainModel mode=new MainModel();
		mode.modelIt();
		mode.applyStrategy(mode.getMessagesToSend());
		mode.hopsMetrix(mode.getMessagesSent());
		
		
		
	}

	public ArrayList<String[]> getMessagesToSend() {
		return messagesToSend;
	}

	public void setMessagesToSend(ArrayList<String[]> messagesToSend) {
		this.messagesToSend = messagesToSend;
	}

	public ArrayList<Message> getMessagesSent() {
		return messagesSent;
	}

	public void setMessagesSent(ArrayList<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}
	
	
	
}


	
	
	
	
	
	
	
	
	
	
	
	


