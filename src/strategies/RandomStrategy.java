package strategies;
import java.util.ArrayList;
/*
 * class RandomStrategy takes the responsibility of transferring a message in a network of routers randomly.
 * @author Ibrahim Ali Fawaz
 * @version 1.0
 * @since 20/10/2016
 */
import java.util.Random;

import network2.Node;

public class RandomStrategy implements  Strategy {
/*
 *contructor doesnt do anything
 * 
 */
	public RandomStrategy() {
		
		
	}
	@Override
	public void updateRoutingTable(Object o){
		
		ArrayList<Node> nodes =(ArrayList<Node>) o;
		
		Random r=new Random();
		int i=0;
		Node j=null;
		for(Node node:nodes){
			for(Node node1:node.getRoutingTable().keySet()){
				i=r.nextInt(node.getNeighbours().size());
				j=node.getNeighbours().get(i);
				if(node.equals(node1)){
					
				}else{
				node.getRoutingTable().get(node1).add(j);
				}
			}
			
			
		}
		}
	}
		
	


