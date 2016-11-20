package strategies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import network2.Node;

public class ShortestPathStrategy implements Strategy {
	private ArrayList<Node> visited;
	 private HashMap<Integer,Node> less;
	 int count=0;
	public ShortestPathStrategy() {
		visited=new ArrayList<Node>();
		less=new HashMap<Integer,Node>();
	}
	@Override
	public void updateRoutingTable(Object o) {
		ArrayList<Node> nodes=(ArrayList<Node>)o;
		for(Node thisNode:nodes){
			for(Node destination:thisNode.getRoutingTable().keySet()){
				
				if(thisNode.equals(destination)){
					
				}else{
					visited.add(thisNode);
				thisNode.getRoutingTable().get(destination).add(getClosest(thisNode,destination));
				}
			}
			
			visited.clear();
		}
		
	}

	private Node getClosest(Node thisNode, Node destination) {
			less.clear();
		if(thisNode.getNeighbours().contains(destination)){
			return destination;
		}else {
			for(Node n:thisNode.getNeighbours()){
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
	private Node getMinimum(HashMap<Integer,Node> less2) {
		int array[]=new int[less2.size()];
		int i=0;
		for(int n:less2.keySet()){
			array[i]=n;
			i++;
		}
		Arrays.sort(array);
		return less2.get(array[0]);
	}
	private boolean areConnected(Node n, Node destination) {
		visited.add(n);
		count++;
		if(n.getNeighbours().contains(destination)){
			return true;
		}
			for(Node n1:n.getNeighbours()){
				if(!visited.contains(n1)){
					return areConnected(n1,destination);
				}
			}
			return false;
	}
	
	

}
