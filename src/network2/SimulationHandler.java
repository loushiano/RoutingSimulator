package network2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import strategies.Strategy;
import strategies.RandomStrategy;
import strategies.SoftriatorsStrategy;

/*
 * This class is responsible for managing the network simulation. 
 */
public class SimulationHandler {

	private int count=0;
	private int k=0;
	private int numMessages=0;
	private  NetworkSimulator sim;
	private Topology topology;
	private HashMap<Router,ArrayList<Message>> storedMessages;
	private int numberOfPackets=0;
	private int numberOfSteps;
	
	public SimulationHandler(NetworkSimulator sim,Topology topology){
		this.sim=sim;
		this.topology=topology;
		setStoredMessages(new HashMap<Router,ArrayList<Message>>());
	}
	
	
	
	/*
	 * simulates the messaging transfer, represents a step of the simulation
	 */
	public boolean step(){
		
			
			count++;
				for(Router r:topology.getTopology()){
					
					for(Message m:r.getStoredMessages()){
						r.addMessage(m);
						
					}
					
					r.getStoredMessages().clear();
				}
			
			//invoke transferMessage to transfer messages between routers
			
				for(Router node: topology.getTopology()){
					k++;
			
				
				node.transferMessage(this);
				
				if(sim.getStrategy() instanceof RandomStrategy || sim.getStrategy() instanceof SoftriatorsStrategy ){
					
				
					if(k==10){
						sim.getStrategy().updateRoutingTable(topology.getTopology());
						k=0;
					}
				}
						
				
				
				}
			//inject a message in the system at a user settable rate
			if(count==sim.getSettableRate()){
				Message message1=injectNewMessage(numMessages++);
				count=0;
				String s="a new message has been successfuly injected with source: "+message1.getSource().getName()+" and destination: "+ message1.getDestination().getName();
				sim.informView(s);
			}
			
			numberOfSteps++;
			return true;
			
	}
	/*
	 * this message is the undo message, it is the opposite of step
	 */
	public void undo(){
		if(numberOfSteps>0){
		ArrayList<Message> messages;
		for(Router r:topology.getTopology()){
			r.getMessages().clear();
			if(r.getOldies().size()!=0){
			messages=r.getOldies().get(r.getOldies().size()-1);
			for(Message m:messages){
				m.getVisited().clear();
				r.addMessage(m);
			}
			}
		}
		numberOfSteps--;
		}
		
	}
	
	/*
	 * this method checks for messages in the network
	 */
	private void checkForMessages() {
		for(Router n:topology.getTopology()){
			if(n.getMessages().size()!=0){
				return;
			}
			
		}
		sim.informView("No more messages to Transfer");	
	}



	/*
	 * Create three messages and make those have different source and destination nodes
	 */
    public void simualteMessages(){
    	String s="hello Sir, your simulation is about to start, messages are being simulated now";
    	sim.informView(s);
    	
    	for(Router node:topology.getTopology()){
    		
    	   for(int i=0;i<1;i++){
    		  
    		   Message message=new Message(""+numMessages,node,topology.getDestinationOfAMessage(node));
    		   //System.out.println("message source: " +message.getSource().getName()+" dest: "+message.getDestination().getName());
    		   node.addMessage(message);
    		   numMessages++;
    	   }
    	}
    	 
    	  
		}
    
    /*
     * this method is used to inform the network simulator class
     * @param o the object that will be passed to the network simulator
     */
    public void InformNetworkSimulator(Object o){
	sim.informView(o);	
	}

    /*
     * this method returns the stored messages 
     * @return the store messages 
     */
	public HashMap<Router,ArrayList<Message>> getStoredMessages() {
		return storedMessages;
	}

	
	/*
	 *This method sets the stored messages
	 *@param StoredMessages the messages that will be stored
	 */
	public void setStoredMessages(HashMap<Router,ArrayList<Message>> storedMessages) {
		this.storedMessages = storedMessages;
	}

	
	/*
	 * this method return the current  type of strategy 
	 * @return type of strategy 
	 */
	public Strategy getSumlationStrategy() {
		// TODO Auto-generated method stub
		return sim.getStrategy();
	}

	/*
	 * This method is responsible to transfer  messages between routers 
	 */

	public void messageTransferred(Message message) {
		sim.getMessagesSent().add(message);
		sim.hopsMetrix();
		sim.packetsMetrix();
		
	}
	/*
	 * this method returns the number of packets 
	 * @return number of packets 
	 */
	public int getNumberOfPackets() {
		return numberOfPackets;
	}


	/*
	 * This method injects a message 
	 * @param k is the message to be sent
	 */
	
	public Message injectNewMessage(int k) {
			Random r=new Random();
		Router node = topology.getTopology().get(r.nextInt(topology.getTopology().size()));
		Message message =new Message(""+k,node,topology.getDestinationOfAMessage(node));
		node.getStoredMessages().add(message);
		return message;
	}


	/*
	 * this method increments the number of packets 
	 */
	public void incrementPackets() {
		numberOfPackets++;
	
	
	}


	/*
	 * returns the number of steps including the deduction due to the undo method
	 * @return the number of steps including the deduction due to the undo method
	 */
	public int getSteps() {
		
		return numberOfSteps;
	}


	
}
