package network2;
import java.util.ArrayList;
/*
 * class RandomStrategy takes the responsibility of transferring a message in a network of routers randomly.
 * @author Ibrahim Ali Fawaz
 * @version 1.0
 * @since 20/10/2016
 */
import java.util.Random;

public class RandomStrategy extends Strategy {
/*
 * Initializes the message,source and destination fields of its super class
 * @param message - a message to be transferred randomly
 * @param source - a source node from which the message has to be transferred
 * @param destination - a destination node to which the message has to be transferred
 */
	public RandomStrategy() {
		
		
	}
	/*
	 * transfers the message between the network routers randomly
	 * 
	 */
	public void transferMessage(Message message,int count){
		Random r=new Random();
		Node node=message.getSource();
		node.setMessage(message);
		int i=0;;
		while(!node.equals(message.getDestination())){
			node.transferMessage();
			i=r.nextInt(node.getNeighbours().size());
			System.out.print("message "+count+" got transfered form "+ node.getName());
			node=node.getNeighbours().get(i);
			node.setMessage(message);
			message.incNumHops();
			System.out.println(" to node: "+node.getName());
			
			
		}
		
		printResult(message);
	}

}
