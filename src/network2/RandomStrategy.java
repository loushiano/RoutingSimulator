package network2;
import java.util.ArrayList;
/*
 * class RandomStrategy takes the responsibility of transferring a message in a network of routers randomly.
 * @author Ibrahim Ali Fawaz
 * @version 1.0
 * @since 20/10/2016
 */
import java.util.Random;

public class RandomStrategy implements  Strategy {
/*
 *contructor doesnt do anything
 * 
 */
	public RandomStrategy() {
		
		
	}
	/*
	 * transfers the message between the network routers randomly
	 * 
	 */
	public void updateRoutingTable(ArrayList<Node> nodes){
		Random r=new Random();
		int i=0;
		Node j=null;
		for(Node node:nodes){
			for(Node node1:node.getRoutingTable().keySet()){
				i=r.nextInt(node.getNeighbours().size());
				j=node.getNeighbours().get(i);
				if(node.equals(node1)){
					
				}else{
				node.getRoutingTable().put(node1,j);
				}
			}
			
			
			
		}
	}
		
	@Override
	public void printResult(Message message) {
		// TODO Auto-generated method stub
		
	}

}
