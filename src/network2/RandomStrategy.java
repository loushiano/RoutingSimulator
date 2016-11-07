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
	public ArrayList<Message> transferMessage(ArrayList<Node> nodes){
		ArrayList<Message> messages=new ArrayList<Message>();
		Random r=new Random();
		int i=0;
		Message message=null;
		for(Node n:nodes){
			if(n.getMessages().size()==0){
				
			}else{
			message=n.transferMessage();
			i=r.nextInt(n.getNeighbours().size());
			n=n.getNeighbours().get(i);
			n.addMessage(message);
			message.incNumHops();
			if(message.getDestination().equals(n)){
				 messages.add(n.getMessages().remove(n.getMessages().size()-1));
				 System.out.println("A message got transferred to node "+n.getName()+" successfuly after "+message.getNumHops()+" hops");
			}else{
				
			}
		}
		}
		
		return messages;
	}
	@Override
	public void printResult(Message message) {
		// TODO Auto-generated method stub
		
	}

}
