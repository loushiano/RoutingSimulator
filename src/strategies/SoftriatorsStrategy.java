package strategies;

import java.util.ArrayList;
import java.util.Random;

import network2.Router;

public class SoftriatorsStrategy implements Strategy{

	@Override
	public boolean updateRoutingTable(ArrayList<Router> nodes) {

		
		Random r=new Random();
		int i=0;
		Router j=null;
		for(Router node:nodes){
			for(Router node1:node.getRoutingTable().keySet()){
				i=r.nextInt(node.getNeighbours().size());
				j=node.getNeighbours().get(i);
				if(node.equals(node1)){
					
				}else{
					node.getRoutingTable().get(node1).clear();
					if(node.getNeighbours().contains(node1)){
						node.getRoutingTable().get(node1).add(node1);
					}else{
				node.getRoutingTable().get(node1).add(j);
					}
				}
			}
			
			
		}
		return true;
		}

	@Override
	public String getName() {
		return "SoftriatorsStrategy";
	}
		
	}


