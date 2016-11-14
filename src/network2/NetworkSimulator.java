package network2;
/*
 * the main Model that simulates the routing message transferring 
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

public class NetworkSimulator extends Observable {
	
	
	//Array list of nodes that represents the topology
	private Topology topology;
	//Array list of messages that represents the messages sent
	private ArrayList<Message> messagesSent;
	//table rate
	private int settableRate=0;
	//Random strategy
	private Strategy strategy;
	//random object
	private Random r;
	//counter for a method
	private int count=0;
	private Simulation simulation;
	//the view that is to be updated when the model changes something
	public GUI gui;
	
	/*
	 * Create new object of topology
	 * Create new object of messagesSent
	 * Create new object of Random
	 */
	public NetworkSimulator(){
		topology =new Topology();
		messagesSent=new ArrayList<Message>();
		r=new Random();
		strategy=new RandomStrategy();
		simulation=new Simulation();
	}
	
	/*
	 * Create three messages and make those have different source and destination nodes
	 */
    public void simualteMessages(){
    	String s="hello Sir, your simulation is about to start, messages are being simulated now";
    	setChanged();
    	notifyObservers(s);
    	
    	for(Node node:topology.getTopology()){
    		
    	   for(int i=0;i<3;i++){  
    		   Message message=new Message("i love Professor babak",node,topology.getDestinationOfAMessage(node));
    		   node.addMessage(message);
    		   node.setRoutingTable(topology.getTopology());
    	   }
    	}
    	   
    	   strategy.updateRoutingTable(topology.getTopology());
		}
    
    
    
    
    	
	
	
	/*
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		
		return topology.toString();
	}
	//s
	/*
	 * simulates the messaging transfer, represents a step of the simulation
	 */
	private void simulation(){
		
			
			count++;
			//check the size of the messagesSent arrayList
			int size=messagesSent.size();
			//loop through the node and invoke transfer message to transfer messages between routers
			for(Node n: topology.getTopology()){
				Message message=n.transferMessage();
				if(message!=null){
					String s="A message has been transferred from "+message.getSource().getName() +" to "+message.getDestination().getName()+" after "+message.getNumHops()+" hops";
					messagesSent.add(message);
					//notify the view to show the string s
					setChanged();
					notifyObservers(s);
				}
			}
			//inject a message in the system at a user settable rate
			if(count==settableRate){
				topology.injectNewMessage();
				count=0;
				String s="a new message has been successfuly injected";
				setChanged();
				notifyObservers(s);
			}
			
			if(size!=messagesSent.size()){
				hopsMetrix();
			}	
			
			}
	
	
	
	
	
	
	
	
	
	/*
	 * calculates the hops
	 */
	public void hopsMetrix() {
		double j=0;
		for(Message i:messagesSent){
			j+=i.getNumHops();
			
		}
		j=j/messagesSent.size();
		String s="the current average Hops for the random Strategy is: "+j;
		setChanged();
		notifyObservers(s);
	}
	
	/*
	 * returns an arraylist of messages
	 */
	public ArrayList<Message> getMessagesSent() {
		return messagesSent;
	}
	//
	/*
	 * sets an array list of messages
	 */
	public void setMessagesSent(ArrayList<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}
	
	//runs the simulation
	public static void main(String args[]){
		NetworkSimulator model=new NetworkSimulator();
		Controler controler=new Controler(model);
		GUI gui =new GUI(controler);
		controler.setGUI(gui);
		model.setGUI(gui);
		gui.createTopology();

	}
	/*
	 * sets the view that is an observer to the model
	 * @param gui the view that is an observer to the model
	 */
	public void setGUI(GUI gui) {
		this.gui=gui;
		addObserver(this.gui);
		
	}
	/*
	 * method that is invoked by the controller to end the simulation
	 */
	public void endSimulation() {
		String s="the simulation has ended";
		setChanged();
		notifyObservers(s);
		setChanged();
		notifyObservers(null);
		
		
	}
	/*
	 * adds a Node to the topology
	 * @param letter the name of the node
	 * @param circle the circle that represents the node
	 */
	public void addNode(String letter, Circle circle) {
		Node n=new Node(letter);
		n.setCircle(circle);
		topology.addANode(n);
		setChanged();
		notifyObservers(n);
		
	}
	/*
	 * add neighbors to each other after checking if both parameters are not null
	 * @param n1 the first neighbor
	 * @param n2 the second neighbor
	 */
	public void addNeighbours(int x1,int y1,int x2,int y2) {
			Node n1=null,n2=null;
			for(Node n:topology.getTopology()){
				//check if a circle contains the point where the mouse was clicked
				if(n.getCircle().contains(new Point(x1,y1))){
					 n1=n;
					
				}
				//check if a circle contains the point where the mouse was released
				if(n.getCircle().contains(new Point(x2,y2))){
					 n2=n;
				}
			}
			ArrayList<Node> nodes=topology.addNeighbours(n1,n2);
			if(nodes!=null){
			setChanged();
			notifyObservers(nodes);
			}
		
	}
	/*
	 * steps through the simulation invoking the method simulation
	 */
	public void step() {
		simulation();
		
	}
	/*
	 * starts the simulation by invoking the method simulate Messages
	 * @param i the user settable rate
	 */
	public void start(int i){
		settableRate=i;
		simualteMessages();
	}
	/*
	 * deletes a node represented by the circle at the position of the point
	 * @param x the x-axis position
	 * @param y the y-axis position
	 */
	public void deleteNode(int x, int y) {
		String s=topology.deleteNode(x ,y);
		
		setChanged();
		notifyObservers(s);
	}

}

	
	
	
	
	
	
	
	
	
	
	
	


