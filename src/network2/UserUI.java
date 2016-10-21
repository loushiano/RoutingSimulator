package network2;

import java.util.*;
public class UserUI extends Observable {
	
	String message, source, destination, algorithm;
	Scanner in;
	int numOfMessages;
	ArrayList<String[]> messages;
	String array[];

	public UserUI(MainModel model){
		//Add the current model to the observer list
		addObserver(model);
		messages =new ArrayList<String[]>();
		//print out the topology network for the user
		System.out.println(model.toString());
		
		System.out.print("Enter the number of messeges to be sent: ");
		in = new Scanner(System.in);
		numOfMessages = new Integer(in.nextLine()); // store the amount of messages to be sent
		
		for(int i = 1; i <= numOfMessages; i++){
			System.out.print("Enter message number "+ i + ": ");
			in = new Scanner(System.in);
			message=in.nextLine();
			
			System.out.print("Enter a Source: ");
			in = new Scanner(System.in);  // Reading source
			source = in.nextLine();
			System.out.print("Enter a Destination: ");
			in = new Scanner(System.in);  // Reading destination
			
			destination = in.nextLine(); //store the destination
			array =new String[3];
			
			array[0]=message;
			array[1]=source;
			array[2]=destination;
			messages.add(array);
			
		}
		
	

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
		
		System.out.println("the messages will be send in the algrithm specified. Have a good day");
		
		setChanged();
		notifyObservers(messages);
		}
}


