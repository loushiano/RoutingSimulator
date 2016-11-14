package network2;

import java.util.ArrayList;

public class FloodingStrategy implements Strategy {

	@Override
	public void printResult(Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRoutingTable(ArrayList<Node> nodes) {
		
		for(Node node:nodes){
			for(Node node1:node.getRoutingTable().keySet()){
				if(node1.equals(node)){
					
				}else{
					
				}
				
				
			}
			
			
			
		}
		
	}

}
