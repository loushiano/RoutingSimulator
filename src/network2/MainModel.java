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
	 * 
	 */
	public MainModel(){
		topology =new ArrayList<Node>();
		messagesSent=new ArrayList<Message>();
		r=new Random();
	}
	/*
	 * 
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
     * 
     * 
     */
    private Node getDestinationNode(Node node){
    	   Node node1=topology.get(r.nextInt(topology.size()));
    	   while(node1.equals(node)){
			    node1=topology.get(r.nextInt(topology.size()));
		   }
    	   return node1;   
    }
    
    /*
     * 
     */
	public ArrayList<Node> getTopology() {
		return topology;
	}
	/*
	 * 
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
	 * 
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
	 * 
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
	 * 
	 * 
	 */
	private void injectNewMessage() {
		Node node = topology.get(r.nextInt(topology.size()));
		Message message =new Message("baback is the best",node,getDestinationNode(node));
		node.addMessage(message);
		
	}
	
	/*
	 * 
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
		
		System.out.println(); //Print a space Hahaha
		
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
					nodes.get(x).addNeighbour(new Node(c + ""));
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
	 * 
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
	 * 
	 */
	public ArrayList<Message> getMessagesSent() {
		return messagesSent;
	}
	
	/*
	 * 
	 */
	public void setMessagesSent(ArrayList<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}
	
	
	
	
	
	public static void main(String args[]){
		MainModel model=new MainModel();
		model.createTopology();
		model.simualteMessages();
		boolean flag=true;
		
		Scanner in =new Scanner(System.in);
		System.out.print("the simulation will start now at the user settableRate.Type in stop whenever you want to stop the simulation: ");
		
		while(!in.nextLine().equals("stop")){
		model.Simulation("Random Strategy");
		}
		
		
		
	}
	
	
}


	
	
	
	
	
	
	
	
	
	
	
	


