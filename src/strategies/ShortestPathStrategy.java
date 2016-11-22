package strategies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import network2.Router;

public class ShortestPathStrategy implements Strategy {
	private ArrayList<Router> visited;
	 private HashMap<Integer,Router> less;
	 int count=0;
	public ShortestPathStrategy() {
		visited=new ArrayList<Router>();
		less=new HashMap<Integer,Router>();
	}
	@Override
	public boolean updateRoutingTable(ArrayList<Router> nodes) {
		
		for(Router thisNode:nodes){
			for(Router destination:thisNode.getRoutingTable().keySet()){
				
				if(thisNode.equals(destination)){
					
				}else{
					visited.add(thisNode);
				thisNode.getRoutingTable().get(destination).add(getClosest(thisNode,destination));
				}
			}
			
			visited.clear();
		}
		return true;
	}

	private Router getClosest(Router thisNode, Router destination) {
			less.clear();
		if(thisNode.getNeighbours().contains(destination)){
			return destination;
		}else {
			for(Router n:thisNode.getNeighbours()){
				if(areConnected(n,destination)){
					visited.clear();
					visited.add(thisNode);
					less.put(count,n);
					count=0;
					
				}
				}
			

				
	}
		return getMinimum(less);
	}
	private Router getMinimum(HashMap<Integer,Router> less2) {
		int array[]=new int[less2.size()];
		int i=0;
		for(int n:less2.keySet()){
			array[i]=n;
			i++;
		}
		Arrays.sort(array);
		return less2.get(array[0]);
	}
	private boolean areConnected(Router n, Router destination) {
		visited.add(n);
		count++;
		if(n.getNeighbours().contains(destination)){
			return true;
		}
			for(Router n1:n.getNeighbours()){
				if(!visited.contains(n1)){
					return areConnected(n1,destination);
				}
			}
			return false;
	}
	
	

}
