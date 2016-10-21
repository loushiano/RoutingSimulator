package network2;

import java.util.*;
public class UserUI extends Observable {
	
	String message, source, destination, algorithm;
	Scanner in;
	int numOfMessages;
	
	
	
	public UserUI(ArrayList<Node> nodes, String representation){
		
		System.out.print("Enter the number of messeges to be sent: ");
		in = new Scanner(System.in);
		numOfMessages = new Integer(in.nextLine()); // store the amount of messages to be sent
		
		System.out.print("Enter a Message: ");
		in = new Scanner(System.in);  // Reading the message
		
		message = in.nextLine(); //store the message
		//System.out.println(n);
		
		System.out.print("Enter a Source: ");
		in = new Scanner(System.in);  // Reading source
		
		source = in.nextLine();  //store the source
		
		System.out.print("Enter a Destination: ");
		in = new Scanner(System.in);  // Reading destination
		
		destination = in.nextLine(); //store the destination

		//Ask the user for the type of the routing algorithm to be used in the network
		System.out.println("The following are the types of Routing algorithm in this network communication:");
		System.out.println("1. Random");
		System.out.println("2. Flooding");
		System.out.println("3. Shortest Path");
		System.out.println("4. Our Method");
		System.out.print("Please enter a routing algorithm number to proceed: ");
		
		for(;;){
			in = new Scanner(System.in); // Reading the routing algorithm
			algorithm = in.nextLine(); 
			
			boolean isAlgorithm = false; // a boolean to check if the user inserted a correct value of an algorithm
			
			//Check which algorithm did the user choose.
			switch(algorithm){
			case "1": 
				System.out.println("You have choosed Random algorithm");
				isAlgorithm = true;
				break;
			case "2": 
				System.out.println("You have choosed Flooding algorithm");
				isAlgorithm = true;
				break;
			case "3": 
				System.out.println("You have choosed Shortest Path algorithm");
				isAlgorithm = true;
				break;
			case "4": 
				System.out.println("You have choosed Our Method algorithm");
				isAlgorithm = true;
				break;			
			default: 
				System.out.print("Please insert an appropriate value: ");
				break;
			}	
			if(isAlgorithm) break; // Quit from the infinite loop.	
		}
		
		System.out.println("Do you want to send the message? (Yes/No): ");
		in = new Scanner(System.in);  // Reading command
		
		if(in.nextLine().toLowerCase().equals("yes")){
			Node s = new Node(source) ;
			Node d = new Node(destination); 
			Message m = new Message(message, d, s);
			RandomStrategy randomStrategy = new RandomStrategy(m, s, d);
			randomStrategy.transferMessage();
		}
		
	}
	
	/*
	 * Returns the message 
	 */
	public String getMessage() {
		return message;
	}
	
	/*
	 * @param message the message to be sent
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/*
	 * Returns the a string for the source
	 */
	public String getSource() {
		return source;
	}
	
	/*
	 * @param source the source node that will be set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	/*
	 * Returns a string for the destination
	 */
	public String getDestination() {
		return destination;
	}
	
	/*
	 * @param destination the destination node that will be set.
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
}
