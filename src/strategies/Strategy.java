package strategies;
/*
 * This class RandomStrategy is responsible for transfering the messages in the topology in a Randomly.
 * @author Osama Rachid
 */
import java.util.ArrayList;

import network2.Router;

/*
 * class RandomStrategy takes the responsibility of transferring a message in a network of routers randomly.
 * @author Ibrahim Ali Fawaz
 * @version 2.0
 * @since 20/10/2016
 */
public interface Strategy {
	
	
	

	
	
	/*
	 * updates the routing table of the nodes
	 * @param ArrayList<Router> nodes to be passed to the method and parsed by each strategies that implement this interface
	 * 
	 */
	public boolean updateRoutingTable(ArrayList<Router> nodes);
	/*
	 * returns a string representation of the name of the strategy
	 * @return a string representation of the name of the strategy
	 */
	public String getName();
	

}
