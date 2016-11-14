package network2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Topology {
	private ArrayList<Node> topology;
	private Random r;
	public ArrayList<Node> getTopology() {
		return topology;
	}
	public void setTopology(ArrayList<Node> topology) {
		this.topology = topology;
	}
	public Topology(){
		topology=new ArrayList<Node>();
		 r=new Random();
	}
	public void addANode(Node n) {
		topology.add(n);
		
	}
	public String deleteNode(int x, int y) {
		Node n=null;
		Node n2=null;
		String s="";
		for(Node node:topology){
			if(node.getCircle().contains(new Point(x,y))){
				n=node;
				s=node.getName();
			}
				
		}
		n2=n;
		if(n!=null){
			topology.remove(n);}
		if(n2!=null){	
		for(Node n1:topology){
				if(n1.getNeighbours().contains(n)){
					n1.getNeighbours().remove(n);
				}
			}
		
		}
		return s;
	}
	/*
	 *put a message in the network 
	 */
	public void injectNewMessage() {
		
		Node node = topology.get(r.nextInt(topology.size()));
		Message message =new Message("babak is the best",node,getDestinationOfAMessage(node));
		node.addMessage(message);
		
	}
	/*
     * Returns a different node than the node passed to it
     * @param node the given node
     */
    public Node getDestinationOfAMessage(Node node){
    	   Node node1=topology.get(r.nextInt(topology.size()));
    	   while(node1.equals(node)){
			    node1=topology.get(r.nextInt(topology.size()));
		   }
    	   return node1;   
    }
	public ArrayList<Node> addNeighbours(Node n1, Node n2) {
		if(n1!=null && n2!=null){
			n1.addNeighbour(n2);
			n2.addNeighbour(n1);
			ArrayList<Node> nodes=new ArrayList<Node>();
			nodes.add(n1);
			nodes.add(n2);
		return nodes;
	}
		return null;
}
}
