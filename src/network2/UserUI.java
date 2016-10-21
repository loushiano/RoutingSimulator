package network2;

import java.util.*;
public class UserUI {
	
	String message;
	String source;
	String destination;
	
	
	
	public UserUI(){
		
		System.out.println("Enter a Message: ");
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		String message = reader.nextLine(); 
		//System.out.println(n);
		
		System.out.println("Enter a Source: ");
		Scanner reader2 = new Scanner(System.in);  // Reading from System.in
		String source = reader2.nextLine(); 
		
		System.out.println("Enter a Destination: ");
		Scanner reader3 = new Scanner(System.in);  // Reading from System.in
		String destination = reader3.nextLine(); 
		
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
