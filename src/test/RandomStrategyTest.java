package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import network2.Message;
import network2.NetworkSimulator;
import network2.Node;
import strategies.RandomStrategy;

public class RandomStrategyTest {

	private RandomStrategy rs;
	private Node n1, n2 ,n3;
	private ArrayList<Node> nodes;
	private ArrayList<Message> messages;
	@Before
	public void setUp() throws Exception {
		rs = new RandomStrategy();
		n1 = new Node("A");
		n1 = new Node("B");
		n3 = new Node("C");
		messages=new ArrayList<Message>();
		messages.add(new Message("Hello", n2, n1));
		messages.add(new Message("Hi", n1, n2));
		nodes = new ArrayList<Node>();
		nodes.add(n1);
		nodes.add(n2);
		nodes.add(n3);
		NetworkSimulator model=new NetworkSimulator();
		
		
	}

	@After
	public void tearDown() throws Exception {	
	}
	/* there is a null pointer exception in RAndomStrategy Class !?!*/
	@Test
	public void testUpdateRoutingTable() {
		
		rs.updateRoutingTable(nodes);
		for(Node n:nodes){
			for(Node n1:n.getRoutingTable().keySet()){
				if(n1.equals(n)){
					assertNull(n1.getRoutingTable().get(n1));
				}else {
					assertNotNull(n1.getRoutingTable().get(n1));
				}
				
			}
		}
		
		}
}
