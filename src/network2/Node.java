package network2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Node {
	
	private String name;
	private ArrayList<Node> neighbours;
	private ArrayList<Message> messages;
	private Circle circle;
	private HashMap<Node,Node> routingtable;
	public Node (String name){
		this.name=name;
		neighbours=new ArrayList<Node>();
		messages =new ArrayList<Message>();
		routingtable=new HashMap<Node,Node>();
		
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
	public Message transferMessage(){
		if(messages.size()==0){
			return null;
		}
		Message message=messages.remove(0);
		if(message.getDestination().equals(this)){
			return message;
		}
		for(Node n:routingtable.keySet()){
			if(message.getDestination().equals(n)){
				message.incNumHops();
				routingtable.get(n).addMessage(message);
			}
		}
		return null;
	}
	/*
	 * returns messages
	 */
	public ArrayList<Message> getMessages() {
		return messages;
	}
	
	/*
	 * Returns the info of the node 
	 */
	public String toString(){
		
		String s=this.getName()+" has neighbours: ";
		for(Node no:neighbours){
			s+=no.getName()+" ";
		}
		return s;
	}
	public Circle getCircle() {
		return circle;
	}
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	public HashMap<Node,Node> getRoutingTable(){
		return routingtable;
	}
	public void setRoutingTable(ArrayList<Node> topology) {
		for(Node n:topology){
			routingtable.put(n,null);
		}
		
	}
}
