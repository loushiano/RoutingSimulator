package network2;
import java.util.ArrayList;
import java.util.Observable;

public class NetworkSimulator extends Observable {
	
	
	//Array list of nodes that represents the topology
	private Topology topology;
	
	//Array list of messages that represents the messages sent
	private ArrayList<Message> messagesSent;
	//table rate
	private int settableRate=0;
	//Random strategy
	private Strategy strategy;;
	
	private SimulationHandler simulation;
		
	
	/*
	 * Create new object of topology
	 * Create new object of messagesSent
	 * Create new object of Random
	 */
	public NetworkSimulator(){
		topology =new Topology(this);
		messagesSent=new ArrayList<Message>();
		
		strategy=new ShortestPathStrategy();
		simulation= new SimulationHandler(this,topology);
	}
	
	
	public Topology getTopology() {
		return topology;
	}
	/*
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		
		return topology.toString();
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
	/*
	 * sets the view that is an observer to the model
	 * @param gui the view that is an observer to the model
	 */
	public void setGUI(GUI gui) {
		
		addObserver(gui);
		
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
	 * starts the simulation by invoking the method simulate Messages
	 * @param i the user settable rate
	 */
	public void start(int i){
		settableRate=i;
		 for(Node node:topology.getTopology()){
			 node.setRoutingTable(topology.getTopology());
			 informView(node.toString());
		 }
		 strategy.updateRoutingTable(topology.getTopology());
		 for(Node n:topology.getTopology()){
			 for(Node n1:n.getRoutingTable().keySet()){
				 System.out.print(n1.getName()+":");
				 if(n.getRoutingTable().get(n1).size()==0){
					 System.out.print("null");
				 }else{
				 for(Node n2:n.getRoutingTable().get(n1)){
					 
						 System.out.print(n2.getName()+" ");
					 
				 }
				 }
				 
			 }
			 System.out.println();
		 }
	}
	public SimulationHandler getSimulation() {
		return simulation;
	}
	public void informView(Object n) {
		setChanged();
		notifyObservers(n);
		
	}

	public void addMessagesSent(Message message) {
		messagesSent.add(message);		
	}
	
	public int getSettableRate(){
		return settableRate;
	}
	
	//runs the simulation
	public static void main(String args[]){
		NetworkSimulator model=new NetworkSimulator();
		GUIController controler=new GUIController(model);
		GUI gui =new GUI(controler);
		controler.setGUI(gui);
		model.setGUI(gui);
		gui.createTopology();

	}


	public Strategy getStrategy() {
		// TODO Auto-generated method stub
		return strategy;
	}
	

}

	
	
	
	
	
	
	
	
	
	
	
	


