package network2;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import view.Circle;

/*
 * This class is responsible for representing the network topology which consists of multiple
 * nodes connected together.
 */

public class Topology {
	private ArrayList<Router> topology;
	private Random r;
	private NetworkSimulator sim;
	public ArrayList<Router> getTopology() {
		return topology;
	}
	
	/*
	 * This method is used to set the topology 
	 */
	public void setTopology(ArrayList<Router> topology) {
		this.topology = topology;
	}
	
	/*
	 * constructor of Topology to initialize the fields and create objects if needed  
	 */
	public Topology(NetworkSimulator sim){
		topology=new ArrayList<Router>();
		 r=new Random();
		 this.sim=sim;
	}
	/*
	 * adds a Node to the topology
	 * @param letter the name of the node
	 * @param circle the circle that represents the node
	 */
	public void addANode(String letter, Circle circle) {
		Router n=new Router(letter);
		n.setCircle(circle);
		topology.add(n);
		sim.informView(n);
		
	}
	/*
	 * deletes a node represented by the circle at the position of the point
	 * @param x the x-axis position
	 * @param y the y-axis position
	 */
	public void deleteNode(int x, int y) {
		Router n=null;
		Router n2=null;
		String s="";
		for(Router node:topology){
			if(node.getCircle().contains(new Point(x,y))){
				n=node;
				s=node.getName();
			}
				
		}
		n2=n;
		if(n!=null){
			topology.remove(n);}
		if(n2!=null){	
		for(Router n1:topology){
				if(n1.getNeighbours().contains(n)){
					n1.getNeighbours().remove(n);
				}
			}
		
		}
		Router r[]=new Router[1];
		r[0]=n2;
		sim.informView(r);
	}
	/*
	 *put a message in the network 
	 */
	
	/*
     * Returns a different node than the node passed to it
     * @param node the given node
     */
    public Router getDestinationOfAMessage(Router node){
    	   Router node1=topology.get(r.nextInt(topology.size()));
    	   while(node1.equals(node)){
			    node1=topology.get(r.nextInt(topology.size()));
		   }
    	   return node1;   
    }
    /*
	 * add neighbors to each other after checking if both parameters are not null
	 * @param n1 the first neighbor
	 * @param n2 the second neighbor
	 * @returns true if operation is correct
	 */
	public boolean addNeighbours(int x1,int y1,int x2,int y2) {
		Router n1=null,n2=null;
		Router n3=null;
		for(Router n:topology){
			//check if a circle contains the point where the mouse was clicked
			if(n.getCircle().contains(new Point(x1,y1))){
				 n1=n;
				n3=n;
			}
			//check if a circle contains the point where the mouse was released
			if(n.getCircle().contains(new Point(x2,y2)) && !n.equals(n3)){
				 n2=n;
			}
		}
	
		
		if(n1!=null && n2!=null){
			if(!n1.getNeighbours().contains(n2)){
			n1.addNeighbour(n2);
			n2.addNeighbour(n1);
			ArrayList<Router> nodes=new ArrayList<Router>();
			nodes.add(n1);
			nodes.add(n2);
		sim.informView(nodes);
			}
	}
		return true;
		
}
	/*
	 * this method returns the next node of this given node 
	 * @param n the node that should be given to this method to get the next node from it.
	 * @return the next node .
	 */
	public Router getNextOne(Router n) {
		int i=topology.indexOf(n);
		if(i==topology.size()-1){
			return topology.get(0);
		}
		return topology.get(i+1);
	}
	/*
	 * exportXML is a methods that export the topology into an xml file in a give location
	 * @param path the path of the file to which we would like to export
	 */
		public void exportToXML(String f){
			String s="<Topology>";
			for(Router r:topology){
				s+="\n\t";
				s+=r.toXML(2);
			}
			s+="\n</Topology>";
			ArrayList<String> list = new ArrayList<String>();
			list.add(s);
			if(!f.contains(".xml")){
				f+="/file.xml";
				//sim.informView(n);
			}
				File file1=new File(f);
				Path file=file1.toPath();
			
			try {
				Files.write(file, list, Charset.forName("UTF-8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*
		 * returns a topology after parsing the xml file passed to it
		 * @param file to be parsed
		 * @return a topology after parsing the xml file passed to it
		 */
		public void importXMl(String file){
			ArrayList<String> s=null;	
			File f=new File(file);
			try {
					 s=XMLParser.readSAX(f);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(s==null){
					sim.informView(null);
				}else{
				sim.reset();
				setTopology(formTopology(s));
				}
				
		}
		private ArrayList<Router> formTopology(ArrayList<String> components) {
			String routers =components.get(0);
			String neighbours=components.get(1);
			String circles=components.get(2);
			String names[]=routers.split(" ");
			String neighbors[]=neighbours.split("2");
			String circle[]=circles.split(" ");
			ArrayList<Router> returnedRouters =new ArrayList<Router>();
			for(int i=0;i<names.length;i++){
				Router r=new Router(names[i]);
				int x=(int)Double.parseDouble(circle[i*2]);
				int y=(int)Double.parseDouble(circle[i*2+1]);
				Circle c=new Circle(new Point(x,y), names[i]);
				r.setCircle(c);
				returnedRouters.add(r);
			}
			for(int i=0;i<neighbors.length;i++){
				String temp[]=neighbors[i].split(" ");
				for(int j=0;j<temp.length;j++){
					
					Router node=null;
					for(Router r:returnedRouters){
						if(temp[j].equals(r.getName())){
							node=r;
						}
					}
					if(!returnedRouters.get(i).getNeighbours().contains(node)){
						if(node!=null){
						returnedRouters.get(i).getNeighbours().add(node);
						}
						}
					
				}
				System.out.println();
			}
			
			return returnedRouters;
		}
}
