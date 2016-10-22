package network2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;

public class MainModel {
	
	
	//
	private ArrayList<Node> topology;
	//
	private ArrayList<Message> messagesSent;
	//
	private int settableRate=0;
	//
	private RandomStrategy randomStrategy;
	//
	private Random r;
	//
	private int count=0;
	
	Scanner in;
	
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
    	for(Node node:topology){
    	   for(int i=0;i<3;i++){  
    		   Message message=new Message("i love Professor baback",node,getDestinationNode(node));
    		   node.addMessage(message);
    	   }
		}
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
	 * @param strategy the type o algorithm passed as a string
	 */
	public void Simulation(String Strategy){
			count++;
			int size=messagesSent.size();
			AddMessages(randomStrategy.transferMessage(topology));
			if(count==settableRate){
				injectNewMessage();
				count=0;
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
		Message message =new Message("baback is the best",node,getDestinationNode(node));
		node.addMessage(message);
		
	}
	
	/*
	 * Start creating the network topology from the user inputs
	 */
	public void createTopology(){
		int numOfNodes;
		ArrayList<Node> nodes;
		for(;;){
			System.out.print("How many nodes do you want to create? "); //fix the issue of wrong value
			in = new Scanner(System.in);
			try{
				numOfNodes = new Integer(in.nextLine()); // store number of created nodes
				break;
			}catch(NumberFormatException e){
				System.out.println("Please insert a correct number...");
			}
		}	
		
		System.out.println(); 
		
		for(;;){
			System.out.print("Enter your settable rate to send messages from each node: "); //fix the issue of wrong value
			in = new Scanner(System.in);
			try{
				settableRate = new Integer(in.nextLine()); // store number of created nodes
				break;
			}catch(NumberFormatException e){
				System.out.println("Please insert a correct number...");
			}
		}
		
		
		//Initialize the nodes Array lists
		nodes = new ArrayList<Node>();
		
		//Add each node to the nodes array list
		for(int i = 0; i < numOfNodes; i++){
			System.out.print("Enter Node name: ");
			in = new Scanner(System.in);
			nodes.add(new Node(in.nextLine().trim()));
			
		}// end for loop
		
		//Now ask the user to add the neighbours nodes
		System.out.println("Please add neighbours to each node that you created in the following format...");
		System.out.println("Format: h, k, c, d");
		System.out.println("Your created nodes are: ");
		
		//Loop through your list of nodes and print the name of each node inside it.
		for(int x = 0; x < numOfNodes; x++){
			System.out.print(nodes.get(x).getName() + " ");
		}
		
		//Loop through the nodes and add their neighbours
		for(int x = 0; x < numOfNodes; x++){
			System.out.println();
			System.out.print(nodes.get(x).toString()); //print the representation of the current node (i.e a has neighbours: ...)
			in = new Scanner(System.in);
			
			//convert the inputs coming from the user (a, c, d, ....) to an array of characters
			char[] charArrays = in.nextLine().replaceAll(","," ").toCharArray();
			for(char c: charArrays){
				if(c != ' '){
					//add neighbours
					Node n=getNode(c,nodes);
					nodes.get(x).addNeighbour(n);
					n.addNeighbour(nodes.get(x));
				}
			}
			//print the representation of the current node (i.e a has neighbours: ...)
			System.out.println(nodes.get(x).toString());
		}
		
		//After add neighbours to each node, print that we are done from the network topology design
		System.out.println("You have created the network topology");
		in.close();
		topology=nodes;
		}// end UserUI
	
	
	/*
	 * Returns a node from nodes that is equal to given character
	 * @param c the given character
	 * @param nodes the given array list of nodes
	 */
	private Node getNode(char c,ArrayList<Node> nodes) {
		for(Node n:nodes){
			if(c==n.getName().charAt(0)){
				return n;
			}
		}
		return null;
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
		System.out.println("the current average Hops for the random Strategy is: "+j);
	}
	
	/*
	 * returns an arraylist of messages
	 */
	public ArrayList<Message> getMessagesSent() {
		return messagesSent;
	}
	
	/*
	 * sets an array list of messages
	 */
	public void setMessagesSent(ArrayList<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}
	
	//runs the simulation
	public static void main(String args[]){
		MainModel model=new MainModel();
		model.createTopology();
		model.simualteMessages();

		System.out.print("the simulation will start now at the user settableRate.Type in stop whenever you want to stop the simulation: ");
		Scanner in =new Scanner(System.in);
		int g =0;
		while(g<20){
		
		model.Simulation("Random Strategy");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g++;
		}
		}	
	}
	
	



	
	
	
	
	
	
	
	
	
	
	
	


