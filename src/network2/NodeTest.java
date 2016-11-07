package network2;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NodeTest {
	private Node n1,n2,n3,n4,n5;
	private ArrayList<Node> nodes1,nodes2,nodes3,nodes4,nodes5;
	private ArrayList<Message> messages, messages1;
	private Message m1,m2,m3,m5;
	
	@Before
	public void setUp() throws Exception {
		
		
		//create an array list of messages
		messages = new ArrayList<Message>();
		messages1 = new ArrayList<Message>();
		m1 = new Message("Message1: Hello", n1, n2);
		m2 = new Message("Message2: Hi", n3, n4);
		m3 = new Message("Message3: Network", n2, n4);
		m5 = new Message("Message1: Hello", n1,n2);
		
		//create three messages
		messages.add(m1);
		messages.add(m2);
		messages.add(m3);
		
		messages1.add(m1);
		
		//create nodes
		n1 = new Node("A");
		n2 = new Node("B");
		n3 = new Node("C");
		n4 = new Node("A");
		n5 = new Node("J");
		
		//add messages to node 1
		n1.addMessage(m1);
		
		//add messages to node 2
		n2.addMessage(m1);
		n2.addMessage(m3);
		
		//add messages to node 3
		n3.addMessage(m2);
		
		//add messages to node 5
		n5.addMessage(m1);
		n5.addMessage(m2);
		n5.addMessage(m3);
		
		//set n1 neighbours
		nodes1 = new ArrayList<Node>();
		nodes1.add(n2);
		nodes1.add(n5);
		n1.setNeighbours(nodes1);

		
		//set n2 neighbours
		nodes2 = new ArrayList<Node>();
		nodes2.add(n1);
		nodes2.add(n4);
		n2.setNeighbours(nodes2);
		
		//set n4 neighbours
		nodes3 = new ArrayList<Node>();
		nodes3.add(n3);
		nodes3.add(n2);
		n4.setNeighbours(nodes3);
		
		//set n3 neighbours
		nodes4 = new ArrayList<Node>();
		nodes4.add(n4);
		n3.setNeighbours(nodes4);
		
		//set n5 neighbours
		nodes5 = new ArrayList<Node>();
		nodes5.add(n1);
		nodes5.add(n2);
		nodes5.add(n3);
		n5.setNeighbours(nodes5);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNode() {
		n1 = new Node("G");
		n2 = new Node("K");
		n3 = new Node("G");
		assertNotEquals(n1, null);
		assertNotEquals(n1.toString(),n2.toString());
		assertEquals(n1.toString(),n3.toString());
		n1 = null;
		assertEquals(n1, null);
	}

	@Test
	public void testGetName() {
		assertNotEquals(n1.getName(), null);
		assertEquals(n1.getName(), n4.getName());
		assertNotEquals(n1.getName(), "Hi");
	}

	@Test
	public void testSetName() {
		assertNotEquals(n1.getName(),null);
		n1.setName(null);
		assertEquals(n1.getName(),null);
		n1.setName("H");
		assertEquals(n1.getName(), "H");
	}

	@Test
	public void testGetNeighbours() {
		assertNotEquals(n3.getNeighbours(),null);
		assertEquals(n4.getNeighbours(), nodes3);
		assertNotEquals(n2.getNeighbours(), n3.getNeighbours());
		assertEquals(n2.getNeighbours(), n2.getNeighbours());
		assertEquals(n4.getNeighbours(), nodes3);
	}

	@Test
	public void testSetNeighbours() {
		//n1 and n5 Neighbours are already set above in setUP
		assertNotEquals(n1.getNeighbours(),null);
		assertEquals(n1.getNeighbours(), nodes1);
		assertNotEquals(n1.getNeighbours(), n5.getNeighbours());
		assertEquals(n5.getNeighbours(), nodes5);
	}

	@Test
	public void testAddNeighbour() {
		n4.addNeighbour(n5);
		assertNotEquals(n4.getNeighbours(), null);
		assertEquals(n4.getNeighbours(), nodes3);
		assertNotEquals(n4.getNeighbours(), nodes2);
	}

	@Test
	public void testAddMessage() {
		
		n1.addMessage(m5);
		messages1.add(m5);
		assertNotEquals(n1.getMessages(), null);
		assertEquals(n1.getMessages(), messages1);
		messages1 = new ArrayList<Message>();
		messages1.add(m1);
		messages1.add(m2);
		messages1.add(m3);
		assertEquals(n5.getMessages(),messages1);
		assertNotEquals(n5.getMessages(), n1.getMessages());
	}

	@Test
	public void testTransferMessage() {
		assertNotEquals(n5.transferMessage(), null);
		assertEquals(n5.transferMessage(), messages.get(1));
		assertEquals(n5.transferMessage(), messages.get(2));
		assertEquals(n5.transferMessage(), null);
	}

	@Test
	public void testGetMessages() {
		assertNotEquals(n2.getMessages(), null);
		ArrayList<Message> ms= new ArrayList<Message>();
		ms.add(m1);
		assertEquals(n1.getMessages(), ms);
		assertEquals(n5.getMessages(), messages);
		assertNotEquals(n3.getMessages(), n4.getMessages());
	}

	@Test
	public void testToString() {
		assertNotEquals(n1.toString(), null);
		assertNotEquals(n1.toString(), n2.toString());
		assertEquals(n1.toString(), "A has neighbours: B J ");
		assertEquals(n4.toString(), "A has neighbours: C B ");
		assertNotEquals(n1.toString(), n4.toString());
	}

}
