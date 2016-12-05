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
	private ArrayList<Integer> oldPackets;
	private ArrayList<Message> injectedMessages;
	private int numberOfPackets=0;
	private int numberOfSteps=0;
	private ArrayList<Message> undoMessages;
	
	public SimulationHandler(NetworkSimulator sim,Topology topology){
		this.sim=sim;
		this.topology=topology;
		oldPackets=new ArrayList<Integer>();
		injectedMessages=new ArrayList<Message>();
		undoMessages=new ArrayList<Message>();
		setStoredMessages(new HashMap<Router,ArrayList<Message>>());
	}
	
	
	
	/*
	 * simulates the messaging transfer, represents a step of the simulation
	 */
	public boolean step(){
		
			oldPackets.add(getPackets());
			
				
			
			//invoke transferMessage to transfer messages between routers
			
				for(Router node: topology.getTopology()){
					k++;
			
				
				node.transferMessage();
				
				if(sim.getStrategy() instanceof RandomStrategy || sim.getStrategy() instanceof SoftriatorsStrategy ){
					
				
					if(k==10){
						sim.getStrategy().updateRoutingTable(topology.getTopology());
						k=0;
					}
				}
						
				
				
				}
				
			//inject a message in the system at a user settable rate
			if(numberOfSteps%(sim.getSettableRate()-1)==0 && numberOfSteps!=0){
				Message message1;
				if(undoMessages.size()>0){
					message1=undoMessages.remove(undoMessages.size()-1);
					for(Router r:topology.getTopology()){
						if(r.equals(message1.getSource())){
							r.addMessage(message1);
						}
					}
					injectedMessages.add(new Message(message1.getMessage(),message1.getSource(),message1.getDestination()));
				}else{
				message1=injectNewMessage(numMessages++);
				injectedMessages.add(new Message(message1.getMessage(),message1.getSource(),message1.getDestination()));
				}
				String s="a new message has been successfuly injected with source: "+message1.getSource().getName()+" and destination: "+ message1.getDestination().getName();
				sim.informView(s);
				
			}
			for(Router r:topology.getTopology()){
				
				for(Message m:r.getStoredMessages()){
					r.addMessage(m);
					
				}
				
				r.getStoredMessages().clear();
				sim.informView(r);
			}
			sim.hopsMetrix();
			sim.packetsMetrix();
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
			k--;
			r.getMessages().clear();
			sim.informView(r);
			if(r.getOldies().size()!=0){
			messages=r.getOldies().remove(r.getOldies().size()-1);
			for(Message m:messages){
				
				r.addMessage(m);
			}
			}
			
		}
		if(injectedMessages.size()>0){
			undoMessages.add(injectedMessages.remove(injectedMessages.size()-1));
		}
		setPackets(oldPackets.remove(oldPackets.size()-1));
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
    	
    	
    	for(Router node:topology.getTopology()){
    		node.setSimlation(this);
    	   for(int i=0;i<2;i++){
    		  
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
	 * This method is invoked after each message has been transfered form a source to a destination
	 * @param message that has been transfered from its source to its destination
	 */

	public void messageTransferred(Message message) {
		if(!sim.getMessagesSent().contains(message)){
		sim.getMessagesSent().add(message);
		}
		
		
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
	 * returns the number of packets
	 * @return the number of packets
	 */
	public int getPackets(){
		return numberOfPackets;
	}
	/*
	 * sets the number of Packets
	 * @param num the number of packets to be set
	 */
	public void setPackets(int num){
		numberOfPackets=num;
		
	}


	/*
	 * returns the number of steps including the deduction due to the undo method
	 * @return the number of steps including the deduction due to the undo method
	 */
	public int getSteps() {
		
		return numberOfSteps;
	}


	/*
	 * sets the number of messages,used in the reset method in the NetworkSimulator
	 * @param i the number of messages to be set
	 */
	public void setNumMessages(int i) {
		numMessages=i;
		
	}
	/*
	 * sets the number of steps,used in the reset method in the NetworkSimulator
	 * @param i the number of steps to be set
	 */
	public void setSteps(int i){
		numberOfSteps=i;
	}


	
}
