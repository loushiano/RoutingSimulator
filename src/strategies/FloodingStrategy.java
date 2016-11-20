package strategies;

import java.util.ArrayList;

import network2.Message;
import network2.Node;


public class FloodingStrategy implements Strategy {
	int count=0;
	

	@Override
	public void updateRoutingTable(Object o) {
		Node node=null;
		if(o instanceof ArrayList){
			node=((ArrayList<Node>)o).get(0);
		}else{	
			node =(Node) o;
		}
		Message message=null;
		if(node.getMessages().size()!=0){
			message=node.getMessages().get(node.getMessages().size()-1);
		}
		if(message!=null){
			node.getRoutingTable().clear();
			node.getRoutingTable().put(node,null);
			if(!message.getDestination().equals(node)){
				ArrayList<Node> nodes =new ArrayList<Node>();
				for(Node n:node.getNeighbours()){
				nodes.add(n);
				}
				node.getRoutingTable().put(message.getDestination(),nodes);
				for(Node n:message.getVisited()){ 
					node.getRoutingTable().get(message.getDestination()).remove(n);
				}
			}
		}
			
	}
		
		
	
		
		
		
}
