package network2;

public class SimulationHandler {

	private int count=0;
	private int k=0;
	private  NetworkSimulator sim;
	private Topology topology;
	
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
			//check the size of the messagesSent arrayList
			
			//loop through the node and invoke transfer message to transfer messages between routers
			for(Node n: topology.getTopology()){
				
				Message message=n.transferMessage();
				if(sim.getStrategy() instanceof FloodingStrategy){
					((FloodingStrategy)sim.getStrategy()).updateRoutingTable(topology.getNextOne(n));
					
				}else{
					if(k==4){
						sim.getStrategy().updateRoutingTable(topology.getTopology());
						k=0;
					}
				}
				
				if(message!=null){
					String s="A message has been transferred from "+message.getSource().getName() +" to "+message.getDestination().getName()+" after "+message.getNumHops()+" hops";
					sim.addMessagesSent(message);
					//notify the view to show the string s
					sim.informView(s);
					
						sim.hopsMetrix();
						
					
					
				}
				
			}
			//inject a message in the system at a user settable rate
			if(count==sim.getSettableRate()){
				Message message=topology.injectNewMessage();
				count=0;
				String s="a new message has been successfuly injected with source: "+message.getSource().getName()+" and destination: "+ message.getDestination().getName();
				sim.informView(s);
			}
			checkForMessages();
			
			
	}
	private void checkForMessages() {
		for(Node n:topology.getTopology()){
			if(n.getMessages().size()!=0){
				return;
			}
			sim.informView("No more messages to Transfer");	
		}
		
	}



	/*
	 * Create three messages and make those have different source and destination nodes
	 */
    public void simualteMessages(){
    	String s="hello Sir, your simulation is about to start, messages are being simulated now";
    	sim.informView(s);
    	int k=0;
    	for(Node node:topology.getTopology()){
    		
    	   for(int i=0;i<2;i++){
    		  
    		   Message message=new Message("i love Professor babak"+i+""+k,node,topology.getDestinationOfAMessage(node));
    		   //System.out.println("message source: " +message.getSource().getName()+" dest: "+message.getDestination().getName());
    		   node.addMessage(message);
    		   k++;
    	   }
    	}
    	   
    	  
		}
}
