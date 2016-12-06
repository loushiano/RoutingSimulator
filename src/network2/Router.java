package network2;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import strategies.FloodingStrategy;
import view.Circle;


/*
 * This class is responsible for representing a node in the network topology. This Node
 * has the messages and also the neighbour nodes and routingTable. This node also can be represented as a circle in the view.
 * 
 * @author Ahmad Ayyoub
 */
public class Router {
	
	private String name;
	private ArrayList<Router> neighbours;
	private ArrayList<Message> messages;
	private Circle circle;
	private HashMap<Router,ArrayList<Router>> routingtable;
	private ArrayList<Message> storedMessages;
	
	private ArrayList<ArrayList<Message>> oldies;
	private SimulationHandler sim;
	
	public Router (String name){
		this.name=name;
		neighbours=new ArrayList<Router>();
		messages =new ArrayList<Message>();
		routingtable=new HashMap<Router,ArrayList<Router>>();
		storedMessages=new ArrayList<Message>();
		oldies=new ArrayList<ArrayList<Message>>();
		
	}
	/*
	 * returns an arrayList of arrayList of messages, this is used for the undo message
	 * @return an arrayList of arrayList of messages, this is used for the undo message
	 * @See undo in SimulationHandler
	 */
	public ArrayList<ArrayList<Message>> getOldies() {
		return oldies;
	}
	/*
	 * gets the name of the node 
	 * @returns the name of the node  
	 */
	public String getName() {
		return name;
	}
	/*
	 * sets the name of this node 
	 * @param name of the node
	 */
	public void setName(String name) {
		this.name = name;
	}
	/*
	 * gets the neighbors of this node 
	 * @returns the  neighbors of the node 
	 */
	public ArrayList<Router> getNeighbours() {
		return neighbours;
	}
	
	/*
	 * sets the setNeighbours of this node 
	 * @param neighbours is neighbors of the node  
	 */
	public void setNeighbours(ArrayList<Router> neighbours) {
		this.neighbours = neighbours;
	}
	/*
	 * @param node the given node
	 * add a neighbour of node
	 */
	public void addNeighbour(Router node){
		neighbours.add(node);
		
	}
	
	/*
	 * adds a neighborto this node  
	 * @param node is the node that we want to add it as neighbor to this node .  
	 */
	public void addMessage(Message message){
		this.messages.add(message);
		sim.InformNetworkSimulator(this);
	}
	
	/*
	 * transferMessage()method is used to transfer the message 
	 */
	public void transferMessage(){
		
		ArrayList<Message> oldMessages=new ArrayList<Message>();
			for(Message m:messages){
				Message m1=new Message(m.getMessage(),m.getSource(),m.getDestination());
				oldMessages.add(m1);
			}
			oldies.add(oldMessages);
		if(messages.size()==0){
			
			return;
		}
		for(Message message : messages){
		if(sim.getSumlationStrategy() instanceof FloodingStrategy){
		if(!message.getVisited().contains(this)){
			message.addVisited(this);
		}
		}
		int i=0;
		for(Router n:routingtable.keySet()){
			
			
			if(message.getDestination().equals(n)){
					
					
				for(Router n1:routingtable.get(n)){
						if(!message.getVisited().contains(n1)){
					i++;
					
					sim.incrementPackets();
					message.incNumHops();
					if(sim.getSumlationStrategy() instanceof FloodingStrategy){
					message.addVisited(n1);}
						if(n1.equals(message.getDestination())){
						
							String s="message"+message.getMessage() +" has been Succesfully transfered from "+message.getSource().getName() +" to its destination: "+message.getDestination().getName()+" after "+message.getNumHops()+" hops";
							sim.InformNetworkSimulator(s);
							sim.messageTransferred(message);
							
						}else{
							n1.addStoredMessage(message);
						
						}
						}
				}
			}
		}
		if(i==0){
			String s="message"+message.getMessage()+"got discarded by router "+getName();
			sim.InformNetworkSimulator(s);
		}
		}
		messages.clear();
		
	}
	/*
	 * adds a message to the stored message to be added to the router after one step
	 * @param message to add to the stored messages
	 */
	private void addStoredMessage(Message message) {
		storedMessages.add(message);
		
	}
	
	/*
	 * returns messages
	 * @return messages in this router
	 */
	public ArrayList<Message> getMessages() {
		return messages;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		
		String s=this.getName()+" has neighbours: ";
		for(Router no:neighbours){
			s+=no.getName()+" ";
		}
		return s;
	}
	/*
	 * returns the circle that represents this node
	 * @return the circle that represents this node
	 */
	public Circle getCircle() {
		return circle;
	}
	/*
	 * sets the circle that represents this node
	 * @param circle that represents this node
	 */
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	/*
	 * returns the routing table of this node
	 * @return the routing table of this node
	 */
	public HashMap<Router,ArrayList<Router>> getRoutingTable(){
		return routingtable;
	}
	/*
	 * sets the routing table of this node
	 * @param topology of the system
	 */
	public void setRoutingTable(ArrayList<Router> topology) {
		for(Router n:topology){
			routingtable.put(n,new ArrayList<Router>());
		}
		
	}
	/*
	 * this method returns the the stored messages in the router 
	 * @return the stored messages 
	 */
	public ArrayList<Message> getStoredMessages() {
		// TODO Auto-generated method stub
		return storedMessages;
	}
	/*
	 * to set the simulationHandler that simulates the behavior of the network
	 * @param simulationHandler the simulationHandler that simulates the behavior of the network
	 */
	public void setSimlation(SimulationHandler simulationHandler) {
		this.sim=simulationHandler;
		
	}
	/*
	 * method that return a string that represents the router in xml form
	 * @return String representation of the xml formal of the router
	 */
	public String toXML(int i) {
			String tab="";
			String tab1="";
			for(int j=0;j<i;j++){
				tab+="\t";
				if(j<i-1){
					tab1+="\t";
				}
			}
			
		String s="<router>" +"\n"+tab+"<name>"+ getName() +"</name>\n" + tab+ getNeighbors(tab) +circle.toXML(i+1)+"\n"+tab1+"</router>";
		return s;
	}
	/*
	 * returns a string representation of the xml tags of the neighbors of this node
	 * @param tab the spacing of the neighbor tag
	 * @return a string representation of the xml tags of the neighbors of this node
	 */
	private String getNeighbors(String tab) {
		String s="";
		for(Router r:neighbours){
			s+="<neighbor>"+r.getName()+"</neighbor>\n";
			s+=tab;
		}
		return s;
	}
	
	
	
	
	
}
