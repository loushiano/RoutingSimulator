package network2;

import java.util.ArrayList;

import strategies.FloodingStrategy;

public class SimulationHandler {

	private int count=0;
	private int k=0;
	private int numMessages=0;
	private  NetworkSimulator sim;
	private Topology topology;
	private Node node;
	
	public SimulationHandler(NetworkSimulator sim,Topology topology){
		this.sim=sim;
		this.topology=topology;
		
	}
	
	
	
	/*
	 * simulates the messaging transfer, represents a step of the simulation
	 */
	public void step(){
		
			k++;
			count++;
			
			
			//invoke transferMessage to transfer messages between routers
			
				
			
				
				ArrayList<Message> messages=node.transferMessage(sim);
				while(messages==null){
					
					sim.getStrategy().updateRoutingTable(node);
					messages=node.transferMessage(sim);
				}
				if(sim.getStrategy() instanceof FloodingStrategy){
					
					sim.getStrategy().updateRoutingTable(topology.getNextOne(node));
					
				}else{
					if(k==4){
						sim.getStrategy().updateRoutingTable(topology.getTopology());
						k=0;
					}
				}
				
				if(messages.size()!=0){
					for(Message message:messages){
						
					String s="A message has been Succesfully transfered from "+message.getSource().getName() +" to its destination: "+message.getDestination().getName()+" after "+message.getNumHops()+" hops";
					sim.addMessagesSent(message);
					//notify the view to show the string s
					sim.informView(s);
					
						sim.hopsMetrix();
						}
					
					}
				
				
			
			//inject a message in the system at a user settable rate
			if(count==sim.getSettableRate()){
				Message message1=topology.injectNewMessage(numMessages++);
				count=0;
				String s="a new message has been successfuly injected with source: "+message1.getSource().getName()+" and destination: "+ message1.getDestination().getName();
				sim.informView(s);
			}
			checkForMessages();
			
			node=topology.getNextOne(node);
	}
	private void checkForMessages() {
		for(Node n:topology.getTopology()){
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
    	
    	for(Node node:topology.getTopology()){
    		
    	   for(int i=0;i<2;i++){
    		  
    		   Message message=new Message(""+numMessages,node,topology.getDestinationOfAMessage(node));
    		   //System.out.println("message source: " +message.getSource().getName()+" dest: "+message.getDestination().getName());
    		   node.addMessage(message);
    		   numMessages++;
    	   }
    	}
    	   node =topology.getTopology().get(0);
    	  
		}
}
