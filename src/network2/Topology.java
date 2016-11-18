package network2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Topology {
	private ArrayList<Node> topology;
	private Random r;
	private NetworkSimulator sim;
	public ArrayList<Node> getTopology() {
		return topology;
	}
	public void setTopology(ArrayList<Node> topology) {
		this.topology = topology;
	}
	public Topology(NetworkSimulator sim){
		topology=new ArrayList<Node>();
		 r=new Random();
		 this.sim=sim;
	}
	/*
	 * adds a Node to the topology
	 * @param letter the name of the node
	 * @param circle the circle that represents the node
	 */
	public void addANode(String letter, Circle circle) {
		Node n=new Node(letter);
		n.setCircle(circle);
		topology.add(n);
		sim.informView(n);
		
	}
	/*
	 * deletes a node represented by the circle at the position of the point
	 * @param x the x-axis position
	 * @param y the y-axis position
	 */
	public void deleteNode(int x, int y) {
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
		sim.informView(s);
	}
	/*
	 *put a message in the network 
	 */
	public Message injectNewMessage() {
		
		Node node = topology.get(r.nextInt(topology.size()));
		Message message =new Message("babak is the best",node,getDestinationOfAMessage(node));
		node.addMessage(message);
		return message;
	}//sould be in simulaarto
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
    /*
	 * add neighbors to each other after checking if both parameters are not null
	 * @param n1 the first neighbor
	 * @param n2 the second neighbor
	 */
	public void addNeighbours(int x1,int y1,int x2,int y2) {
		Node n1=null,n2=null;
		Node n3=null;
		for(Node n:topology){
			//check if a circle contains the point where the mouse was clicked
			if(n.getCircle().contains(new Point(x1,y1))){
				 n1=n;
				n3=n;
			}
			//check if a circle contains the point where the mouse was released
			if(n.getCircle().contains(new Point(x2,y2)) && !n.equals(n3)){
				 n2=n;
			}
		}
	
		
		if(n1!=null && n2!=null){
			n1.addNeighbour(n2);
			n2.addNeighbour(n1);
			ArrayList<Node> nodes=new ArrayList<Node>();
			nodes.add(n1);
			nodes.add(n2);
		sim.informView(nodes);;
	}
		
}
	public Node getNextOne(Node n) {
		int i=topology.indexOf(n);
		if(i==topology.size()-1){
			return topology.get(0);
		}
		return topology.get(i+1);
	}
}
