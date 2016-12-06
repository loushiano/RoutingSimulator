package test;
/*
 * This class NetworkSimulatorTest is responsible for testing the network simulator.
 * @author Osama Rachid
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import network2.Message;
import network2.Router;

public class RouterTest {
	private Router n1,n2,n3,n4,n5;
	private ArrayList<Router> Routers1,Routers2,Routers3,Routers4,Routers5;
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
		
		//create Routers
		n1 = new Router("A");
		n2 = new Router("B");
		n3 = new Router("C");
		n4 = new Router("A");
		n5 = new Router("J");
		
		//add messages to Router 1
		n1.addMessage(m1);
		
		//add messages to Router 2
		n2.addMessage(m1);
		n2.addMessage(m3);
		
		//add messages to Router 3
		n3.addMessage(m2);
		
		//add messages to Router 5
		n5.addMessage(m1);
		n5.addMessage(m2);
		n5.addMessage(m3);
		
		//set n1 neighbours
		Routers1 = new ArrayList<Router>();
		Routers1.add(n2);
		Routers1.add(n5);
		n1.setNeighbours(Routers1);

		
		//set n2 neighbours
		Routers2 = new ArrayList<Router>();
		Routers2.add(n1);
		Routers2.add(n4);
		n2.setNeighbours(Routers2);
		
		//set n4 neighbours
		Routers3 = new ArrayList<Router>();
		Routers3.add(n3);
		Routers3.add(n2);
		n4.setNeighbours(Routers3);
		
		//set n3 neighbours
		Routers4 = new ArrayList<Router>();
		Routers4.add(n4);
		n3.setNeighbours(Routers4);
		
		//set n5 neighbours
		Routers5 = new ArrayList<Router>();
		Routers5.add(n1);
		Routers5.add(n2);
		Routers5.add(n3);
		n5.setNeighbours(Routers5);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRouter() {
		n1 = new Router("G");
		n2 = new Router("K");
		n3 = new Router("G");
		
		assertNotEquals(n1.toString(),n2.toString());
		assertEquals(n1.toString(),n3.toString());
		
	
	}

	@Test
	public void testGetName() {
		assertNotNull(n1.getName());
		assertEquals(n1.getName(), n4.getName());
		assertNotNull(n1.getName(), "Hi");
	}

	@Test
	public void testSetName() {
		
	
		assertNotNull(n1.getName());
		n1.setName("H");
		assertEquals(n1.getName(), "H");
	}

	@Test
	public void testGetNeighbours() {
		assertNotNull(n3.getNeighbours());
		assertEquals(n4.getNeighbours(), Routers3);
		assertNotEquals(n2.getNeighbours(), n3.getNeighbours());
		assertEquals(n2.getNeighbours(), n2.getNeighbours());
		assertEquals(n4.getNeighbours(), Routers3);
	}

	@Test
	public void testSetNeighbours() {
		//n1 and n5 Neighbours are already set above in setUP
		assertNotNull(n1.getNeighbours());
		assertEquals(n1.getNeighbours(), Routers1);
		assertNotEquals(n1.getNeighbours(), n5.getNeighbours());
		assertEquals(n5.getNeighbours(), Routers5);
	}

	@Test
	public void testAddNeighbour() {
		n4.addNeighbour(n5);
		assertNotNull(n4.getNeighbours());
		assertEquals(n4.getNeighbours(), Routers3);
		assertNotEquals(n4.getNeighbours(), Routers2);
	}

	@Test
	public void testAddMessage() {
		
		n1.addMessage(m5);
		messages1.add(m5);
		assertNotNull(n1.getMessages());
		assertEquals(n1.getMessages(), messages1);
		messages1 = new ArrayList<Message>();
		messages1.add(m1);
		messages1.add(m2);
		messages1.add(m3);
		assertEquals(n5.getMessages(),messages1);
		assertNotEquals(n5.getMessages(), n1.getMessages());
	}


	@Test

	public void testGetMessages() {
		assertNotNull(n2.getMessages());
		ArrayList<Message> ms= new ArrayList<Message>();
		ms.add(m1);
		assertEquals(n1.getMessages(), ms);
		assertEquals(n5.getMessages(), messages);
		assertNotEquals(n3.getMessages(), n4.getMessages());
	}

	@Test
	public void testToString() {
		assertNotNull(n1.toString());
		assertNotEquals(n1.toString(), n2.toString());
		assertEquals(n1.toString(), "A has neighbours: B J ");
		assertEquals(n4.toString(), "A has neighbours: C B ");
		assertNotEquals(n1.toString(), n4.toString());
	}

}
