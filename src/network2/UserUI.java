package network2;

import java.util.*;
public class UserUI {
	
	String message, source, destination, algorithm;
	Scanner in;
	int numOfMessages;
	
	
	
	public UserUI(){
		
		System.out.print("Enter a Message: ");
		in = new Scanner(System.in);  // Reading the message
		System.out.println();
		
		message = in.nextLine(); //store the message
		//System.out.println(n);
		
		System.out.print("Enter a Source: ");
		in = new Scanner(System.in);  // Reading source
		System.out.println();
		
		source = in.nextLine();  //store the source
		
		System.out.print("Enter a Destination: ");
		in = new Scanner(System.in);  // Reading destination
		System.out.println();
		
		destination = in.nextLine(); //store the destination
		
		System.out.print("Enter the amount of messages to be sent: ");
		in = new Scanner(System.in);  // Reading the amount of messages to be sent
		System.out.println();
		
		numOfMessages = new Integer(in.nextLine()); // store the amount of messages to be sent
		
		
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
		
	}

	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	
}
