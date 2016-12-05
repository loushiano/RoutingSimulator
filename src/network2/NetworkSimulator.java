package network2;
import java.io.File;
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
		
	public enum STRATEGIES
	{RandomStrategy(new RandomStrategy()),FloodingStrategy(new FloodingStrategy()),
		ShortestPathStrategy(new ShortestPathStrategy()),SoftriatorsStrategy(new SoftriatorsStrategy());
		
		private Strategy strategy;
		 STRATEGIES(Strategy strategy) {
		        this.strategy = strategy;
		    }
		 public Strategy getStrategy() {
		        return strategy;
		    }
	
	};
	
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
		if(messagesSent.size()>0){
		double j=0;
		for(Message i:messagesSent){
			j+=i.getNumHops();
			
		}
		j=j/messagesSent.size();
		
		
		String s= ""+j;
		String arr[]= new String[1];
		arr[0]=s;
		informView(arr);
		}
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
	 * starts the simulation by invoking the method simulate Messages
	 * @param i the user settable rate
	 * @param j to check what strategy will be used 
	 */
	public void start(int i,int j){
		settableRate=i;
		setStrategy(j);
		 for(Router node:topology.getTopology()){
			 node.setRoutingTable(topology.getTopology());
			 
		 }
		 strategy.updateRoutingTable(topology.getTopology());
		 
	}
	/*
	 * method that sets the strategy used for the simulation
	 * @param j int that is used to decide which strategy to use for the simulation 
	 */
	private void setStrategy(int j) {
		for(STRATEGIES st:STRATEGIES.values()){
			if(st.ordinal()==j){
				strategy=st.getStrategy();
			}
		}
		
		
		
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
		String s="" +simulation.getNumberOfPackets();
		informView(s);
		
	}
	/*
	 * resets everything in the network
	 */
	public void reset() {
		topology.getTopology().clear();
		simulation.setSteps(0);
		simulation.setNumMessages(0);
		informView(null);
		
		
	}
	/*
	 * method that loads the saved topology into the simulation
	 * @param file which contains the xml representation of the topology
	 */
	public void load(String file) {
		topology.importXMl(file);
		ArrayList<Router> oldes=new ArrayList<Router>();
		for(Router r:topology.getTopology()){
			informView(r);
			for(Router r1:r.getNeighbours()){
				if(r1!=null){
				ArrayList<Router> neigh=new ArrayList<Router>();
				if(!oldes.contains(r)){
				neigh.add(r);
				neigh.add(r1);
				informView(neigh);
				}
				}
				
			}
			oldes.add(r);
		}
		
	}
	
	

}

	
	
	
	
	
	
	
	
	
	
	
	


