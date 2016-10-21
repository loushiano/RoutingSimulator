package network2;

import java.util.ArrayList;

public class Node {
	
	private String name;
	private ArrayList<Node> neighbours;
	private Message message;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Node> getNeighbours() {
		return neighbours;
	}
	public void setNeighbours(ArrayList<Node> neighbours) {
		this.neighbours = neighbours;
	}
	public void addNeighbour(Node node){
		neighbours.add(node);
	}
	public Node (String name){
		this.name=name;
		neighbours=new ArrayList<Node>();
		
	}
	public void setMessage(Message message){
		this.message=message;
	}
	public void transferMessage(){
		message=null;
	}
	
	
}
