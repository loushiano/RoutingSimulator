package network2;
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
	public RandomStrategy(Message message, Node source, Node destination) {
		super(message, source, destination);
		
	}
	/*
	 * transfers the message between the network routers randomly
	 * 
	 */
	public void transferMessage(){
		Random r=new Random();
		Node node=getSource();
		
		while(!node.equals(destination)){
			node.transferMessage();
			node=node.getNeighbours().get(r.nextInt(node.getNeighbours().size()));
			node.setMessage(message);
			message.incNumHops();
			System.out.println("message got transfered to node: "+node.getName());
			
			
		}
		
		
	}

}
