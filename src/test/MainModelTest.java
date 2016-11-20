package test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import network2.Message;
import network2.NetworkSimulator;
import network2.Node;
import view.Circle;
import view.GUI;

public class MainModelTest {
	private NetworkSimulator main1, main2;
	private ArrayList<Node> topology1, topology2;
	private ArrayList<Message> messages;
	private Node source1, source2, destination1, destination2;
	private Message m1,m2,m3;
	
	@Before
	public void setUp() throws Exception {
		main1 = new NetworkSimulator();
		main2 = new NetworkSimulator();
		
		source1 = new Node("A");
		source2 = new Node("B");
		destination1 = new Node("C");
		destination2 = new Node("D");
		
		topology1 = new ArrayList<Node>(); 
		//Add nodes to this topology1
		topology1.add(source1);
		topology1.add(source2);
		topology1.add(destination1);
		
		topology2 = new ArrayList<Node>(); 
		//Add nodes to this topology1
		topology2.add(source1);
		topology2.add(source2);
		topology2.add(destination1);
		
		messages = new ArrayList<Message>();
		m1 = new Message("Message1: Hello", source1, destination1);
		m1 = new Message("Message2: Hi", source2, destination2);
		m1 = new Message("Message3: Network", source1, destination2);
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * 
	 * */
	@Test
	public void testMainModel() {
		assertNotEquals(main1, null);
		assertNotEquals(main1.getTopology(), null);
		main1.setTopology(topology1);
		//Check if setting the new topology is not null too.
		assertNotEquals(main1.getTopology(), null);
		assertEquals(main1.getTopology(), topology1);
	}

	@Test
	public void testSimualteMessages() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTopology() {
		assertNotEquals(main1.getTopology(),null);
		ArrayList<Node> topology2 = new ArrayList<Node>();
		assertEquals(main1.getTopology(), topology2);
		topology2.add(new Node("K"));
		assertNotEquals(main1.getTopology(), topology2);
	}

	@Test
	public void testSetTopology() {
		main1.setTopology(topology1);
		assertNotEquals(main1.getTopology(), null);
		assertEquals(main1.getTopology(), topology1);
	}
	

	@Test
	public void testToString() {
		main1.setTopology(topology1);
		main2.setTopology(topology2);
		assertNotEquals(main1.toString(), null);
		assertNotEquals(main2.toString(), null);
		assertEquals(main1.toString(), main2.toString());
	}

	

	

	

	@Test
	public void testGetMessagesSent() {
		assertEquals(main1.getMessagesSent(),main2.getMessagesSent());
		messages.add(m1);
		messages.add(m2);
		messages.add(m3);
		main1.setMessagesSent(messages);
		assertNotEquals(main1.getMessagesSent(),null);
		assertEquals(main1.getMessagesSent(),messages);
	}

	@Test
	public void testSetMessagesSent() {
		main1.setMessagesSent(null);
		assertEquals(main1.getMessagesSent(), null);
		main2.setMessagesSent(messages);
		assertEquals(main2.getMessagesSent(), messages);
		assertNotEquals(main1.getMessagesSent(),main2.getMessagesSent());
	}
	@Test
	public void testSimulateMessages() {
		ArrayList<Node> topology=new ArrayList<Node>();
		topology.add(new Node("A"));
		
		main1.setTopology(topology);
		main1.simualteMessages();
		for(Node n:topology1){
			assertEquals(3,n.getMessages().size());
		}
	}
	@Test
	public void testAddNode() {
		
		Circle c=new Circle(new Point(3,3),20,"A");
		main1.addNode("A", c);
		assertEquals(c,main1.getTopology().get(0).getCircle());
		
	}
	@Test
	public void testSetGUI() {	
		GUI gui=new GUI(null);
		main1.setGUI(gui);
		assertEquals(gui,main1.gui);
	}
	@Test
	public void testAddNeighbours() {	
		Node n1=new Node("a");
		Node n2=new Node("b");
		main1.addNeighbours(n1,n2);
		assertTrue(n1.getNeighbours().contains(n2));
		assertTrue(n2.getNeighbours().contains(n1));
		
	}
	
	
	

}
