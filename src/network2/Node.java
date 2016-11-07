package network2;

import java.util.ArrayList;

public class Node {
	
	private String name;
	private ArrayList<Node> neighbours;
	private ArrayList<Message> messages;
	
	public Node (String name){
		this.name=name;
		neighbours=new ArrayList<Node>();
		messages =new ArrayList<Message>();
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
	 * adds a Message this node  
	 * @param node is the node that we want to add it as neighbor to this node .  
	 */
	public void addMessage(Message message){
		this.messages.add(message);
	}
	
	/*
	 * transferMessage()method is used to transfer the message 
	 */
	public Message transferMessage(){
		return messages.remove(0);
		
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
	
}
