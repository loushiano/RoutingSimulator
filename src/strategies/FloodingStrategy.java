package strategies;

import java.util.ArrayList;

import network2.Router;


public class FloodingStrategy implements Strategy {
	int count=0;
	

	@Override
	public boolean updateRoutingTable(ArrayList<Router> nodes) {
		
		for(Router node:nodes){
			for(Router node1:node.getRoutingTable().keySet()){
				if(node.equals(node1)){
					
				}else{
					node.getRoutingTable().get(node1).clear();
					for(Router j:node.getNeighbours()){
				node.getRoutingTable().get(node1).add(j);
					}
				}
			}
			
			
		}
		return true;
			
	}
		
		
	
		
		
		
}
