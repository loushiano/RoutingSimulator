package strategies;
import java.util.ArrayList;
/*
 * class RandomStrategy takes the responsibility of transferring a message in a network of routers randomly.
 * @author Ibrahim Ali Fawaz
 * @version 1.0
 * @since 20/10/2016
 */
import java.util.Random;

import network2.Router;
/*
 * This class RandomStrategy is responsible for transfering the messages in the topology in a Randomly.
 * @author Ahmad Ayyoub
 */
public class RandomStrategy implements  Strategy {
/*
 *contructor doesnt do anything
 * 
 */
	public RandomStrategy() {
		
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see strategies.Strategy#updateRoutingTable(java.util.ArrayList)
	 */
	@Override
	public boolean updateRoutingTable(ArrayList<Router> nodes){
		
		
		Random r=new Random();
		int i=0;
		Router j=null;
		for(Router node:nodes){
			for(Router node1:node.getRoutingTable().keySet()){
				i=r.nextInt(node.getNeighbours().size());
				j=node.getNeighbours().get(i);
				if(node.equals(node1)){
					
				}else{
					node.getRoutingTable().get(node1).clear();
				node.getRoutingTable().get(node1).add(j);
				}
			}
			
			
		}
		return true;
		}

	@Override
	public String getName() {
		return "RandomStratey";
	}
	}
		
	


