package network2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import strategies.FloodingStrategy;
import strategies.RandomStrategy;
import strategies.ShortestPathStrategy;
import strategies.SoftriatorsStrategy;
import strategies.Strategy;
import view.GUI;

/*
 * This class acts as the model of the graphical user interface. This class is responsible for
 * implementing the algorithm behind the GUI. This class takes care of updating the view
 * of the GUI. 
 * */
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
	
	/*
	 * this method returns the Topology
	 */
	public Topology getTopology() {
		return topology;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		
		return topology.toString();
	}
	/*
	 * this method calculates the hops
	 */
	public void hopsMetrix() {
		double j=0;
		for(Message i:messagesSent){
			j+=i.getNumHops();
			
		}
		j=j/messagesSent.size();
		String s="the current average Hops for the random Strategy is: "+ j;
		informView(s);
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
	 * @param j to check what strategy will be used 
	 */
	public void start(int i,int j){
		settableRate=i;
		if(j==0){
		strategy=new RandomStrategy();	
		}else if(j==1){
			strategy=new FloodingStrategy();	
		}else if (j==2){
			strategy=new ShortestPathStrategy();	
		}else{
			strategy=new SoftriatorsStrategy();
		}
		 for(Router node:topology.getTopology()){
			 node.setRoutingTable(topology.getTopology());
			 informView(node.toString());
		 }
		 strategy.updateRoutingTable(topology.getTopology());
		 
	}
	
	/*
	 * this method is responsible  for getting the simulation handler
	 * @return the Simulation Handler variable
	 */
	public SimulationHandler getSimulation() {
		return simulation;
	}
	
	/*
	 *this method is responsible for informing the view that is related to this network simulation
	 *@param n the object that we want to pass 
	 */
	public void informView(Object n) {
		setChanged();
		notifyObservers(n);
		
	}
	
	/*
	 *this method is used by the network simulator to add this message to the list of messages 
	 *@param: message that we want to add 
	 */
	public void addMessagesSent(Message message) {
		messagesSent.add(message);		
	}
	/*
	 * this method return the settable Rate of  user
	 * @return : settable Rate
	 */
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

	/*
	 * this method return the strategy that related to this network simulator 
	 * @return: strategy 
	 */
	public Strategy getStrategy() {
		// TODO Auto-generated method stub
		return strategy;
	}


	public void packetsMetrix() {
		String s="total number of packets transmitted is " +simulation.getNumberOfPackets();
		informView(s);
		
	}
	
	

}

	
	
	
	
	
	
	
	
	
	
	
	


