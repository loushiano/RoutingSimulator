package network2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import view.Circle;

public class Node {
	
	private String name;
	private ArrayList<Node> neighbours;
	private ArrayList<Message> messages;
	private Circle circle;
	private HashMap<Node,ArrayList<Node>> routingtable;
	public Node (String name){
		this.name=name;
		neighbours=new ArrayList<Node>();
		messages =new ArrayList<Message>();
		routingtable=new HashMap<Node,ArrayList<Node>>();
		
		
	}
	/*
	 * gets the name of the node 
	 * Returns the name of the node  
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
	 * Returns the  neighbors of the node 
	 */
	public ArrayList<Node> getNeighbours() {
		return neighbours;
	}
	
	/*
	 * sets the setNeighbours of this node 
	 * @param neighbours is neighbors of the node  
	 */
	public void setNeighbours(ArrayList<Node> neighbours) {
		this.neighbours = neighbours;
	}
	/*
	 * @param node the given node
	 * add a neighbour of node
	 */
	public void addNeighbour(Node node){
		neighbours.add(node);
		
	}
	
	/*
	 * adds a neighborto this node  
	 * @param node is the node that we want to add it as neighbor to this node .  
	 */
	public void addMessage(Message message){
		this.messages.add(message);
	}
	
	/*
	 * transferMessage()method is used to transfer the message 
	 */
	public ArrayList<Message> transferMessage(NetworkSimulator sim){
		ArrayList<Message> returned=new ArrayList<Message>();
		
		if(messages.size()==0){
			
			return returned;
		}
		
		Message message=messages.remove(messages.size()-1);
		if(!message.getVisited().contains(this)){
			message.addVisited(this);
		}
		for(Node n:routingtable.keySet()){
			
			
			if(message.getDestination().equals(n)){
					
					if(routingtable.get(n).size()==0){
						sim.informView("message"+message.getMessage()+"got discarded by "+this.getName());
						return null;
					}
				for(Node n1:routingtable.get(n)){
					
					
					LinkedList<Object> transfer=new LinkedList<Object>();
					transfer.add(message);
					transfer.add(this);
					transfer.add(routingtable.get(n));
					sim.informView(transfer);
					//System.out.println("message"+message.getMessage()+" has been traveled from "+this.getName() +" to "+n1.getName());
					message.incNumHops();
					message.addVisited(n1);
						if(n1.equals(message.getDestination())){
							returned.add(message);
							//message.setSuccess(true);
						}else{
							//returned.add(message);
							//message.setSuccess(false);
							n1.addMessage(message);
						}
				}
			}
		}
		return returned;
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
		for(Node no:neighbours){
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
	public HashMap<Node,ArrayList<Node>> getRoutingTable(){
		return routingtable;
	}
	/*
	 * sets the routing table of this node
	 * @param topology of the system
	 */
	public void setRoutingTable(ArrayList<Node> topology) {
		for(Node n:topology){
			routingtable.put(n,new ArrayList<Node>());
		}
		
	}
	
	
	
}
