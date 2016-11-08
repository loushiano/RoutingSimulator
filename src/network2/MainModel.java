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

public class MainModel extends Observable {
	
	
	//Array list of nodes that represents the topology
	private ArrayList<Node> topology;
	//Array list of messages that represents the messages sent
	private ArrayList<Message> messagesSent;
	//table rate
	private int settableRate=0;
	//Random strategy
	private RandomStrategy randomStrategy;
	//random object
	private Random r;
	//counter for a method
	private int count=0;
	
	//the view that is to be updated when the model changes something
	public GUI gui;
	
	/*
	 * Create new object of topology
	 * Create new object of messagesSent
	 * Create new object of Random
	 */
	public MainModel(){
		topology =new ArrayList<Node>();
		messagesSent=new ArrayList<Message>();
		r=new Random();
		randomStrategy=new RandomStrategy();
	}
	
	/*
	 * Create three messages and make those have different source and destination nodes
	 */
    public void simualteMessages(){
    	String s="hello Sir, your simulation is about to start, messages are being simulated now";
    	setChanged();
    	notifyObservers(s);
    	
    	for(Node node:topology){
    		
    	   for(int i=0;i<3;i++){  
    		   Message message=new Message("i love Professor babak",node,getDestinationNode(node));
    		   node.addMessage(message);
    		   node.setRoutingTable(topology);
    	   }
    	}
    	   
    	   randomStrategy.updateRoutingTable(topology);
		}
    
    
    /*
     * Returns a different node than the node passed to it
     * @param node the given node
     */
    private Node getDestinationNode(Node node){
    	   Node node1=topology.get(r.nextInt(topology.size()));
    	   while(node1.equals(node)){
			    node1=topology.get(r.nextInt(topology.size()));
		   }
    	   return node1;   
    }
    
    /*
     * returns the network Topology
     */
	public ArrayList<Node> getTopology() {
		return topology;
	}
	
	/*
	 * Edit the network Topolgy.
	 */
	public void setTopology(ArrayList<Node> topology) {
		this.topology = topology;
	}
	
	/*
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String s="";
		for(Node no:topology){
			s+=no.toString();
			
		}
		return s;
	}
	
	/*
	 * simulates the messaging transfer, represents a step of the simulation
	 */
	private void simulation(){
		
			
			count++;
			//check the size of the messagesSent arrayList
			int size=messagesSent.size();
			//loop through the node and invoke transfer message to transfer messages between routers
			for(Node n: topology){
				Message message=n.transferMessage();
				if(message!=null){
					String s="A message has been transferred from "+message.getSource().getName() +" to "+message.getDestination().getName();
					messagesSent.add(message);
					//notify the view to show the string s
					setChanged();
					notifyObservers(s);
				}
			}
			//inject a message in the system at a user settable rate
			if(count==settableRate){
				injectNewMessage();
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
	 * add messages from the given array list of message to messsagesSent field
	 * @param transferMessage arraylist of Message
	 */
	private void AddMessages(ArrayList<Message> transferMessage)
	{
		if(transferMessage.size()==0){
			return;
		}
		
		for(Message message :transferMessage)
		{
			messagesSent.add(message);
		}
	}
	
	/*
	 *put a message in the network 
	 */
	private void injectNewMessage() {
		Node node = topology.get(r.nextInt(topology.size()));
		Message message =new Message("babak is the best",node,getDestinationNode(node));
		node.addMessage(message);
		
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
		MainModel model=new MainModel();
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
		topology.add(n);
		setChanged();
		notifyObservers(n);
		
	}
	/*
	 * add neighbors to each other after checking if both parameters are not null
	 * @param n1 the first neighbor
	 * @param n2 the second neighbor
	 */
	public void addNeighbours(Node n1, Node n2) {
		if(n1!=null && n2!=null){
			n1.addNeighbour(n2);
			n2.addNeighbour(n1);
			ArrayList<Node> nodes=new ArrayList<Node>();
			nodes.add(n1);
			nodes.add(n2);
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
		setChanged();
		notifyObservers(s);
	}

}

	
	
	
	
	
	
	
	
	
	
	
	


