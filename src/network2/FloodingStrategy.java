package network2;

import java.util.ArrayList;


public class FloodingStrategy implements Strategy {
	int count=0;
	

	@Override
	public void updateRoutingTable(Object o) {
		Node node =(Node) o;
			Message message=null;
			if(node.getMessages().size()!=0){
				
					message=node.getMessages().get(node.getMessages().size()-1);
				
				}
			if(message!=null){
			node.getRoutingTable().clear();
			node.getRoutingTable().put(node,null);
			if(!message.getDestination().equals(node)){
			node.getRoutingTable().put(message.getDestination(),node.getNeighbours());
			node.getRoutingTable().get(message.getDestination()).remove(message.getPreviousNode());
			}
			}
			
			}
			/*for(Node node1:node.getRoutingTable().keySet()){
				if(node1.equals(node)){
					
				}else{
					for(Node node2:node.getNeighbours()){
						if(!message.getPreviousNode().equals(node2)){
						node.getRoutingTable().get(node1).add(node2);
						}
					}
				}
			}*/	
			
		
		public Node getLastNeighbor(Node node,ArrayList<Node> nodes){
			Node returned=null;
			for(Node n:nodes){
				for(Node n1:node.getNeighbours()){
				if(n1.equals(n)){
					returned=n;
				}
				}
			}
			return returned;
		}
		
		
		




	
	

}
