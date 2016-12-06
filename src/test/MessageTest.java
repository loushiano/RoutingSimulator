package test;
/*
 * This class MessageTest is responsible for testing the message class
 * @author Ali Fawaz
 */

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import network2.Message;
import network2.Router;

public class MessageTest {
	private Router n1, n2,n3;
	private Message m;
	private ArrayList<Router> visited;
	
	@Before
	public void setUp() throws Exception {
		
		n1 = new Router("A");
		n2 = new Router("B");
		n3 = new Router("C");
		m = new Message("Hi", n1,n2);
		visited = new ArrayList<Router>();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//Test Message
	@Test
	public void testMessage() {
		assertNotNull(m);
		assertEquals(m.getMessage(), "Hi");
		assertEquals(m.getSource(), n1);
		assertEquals(m.getDestination(), n2);
	}
	
	//Test getNumPacks
	@Test
	public void testGetNumPacks() {
		assertEquals(m.getNumPacks(), 0);
		m.incNumPacks();
		assertEquals(m.getNumPacks(), 1);
	}
	
	//Test IncNumPacks
	@Test
	public void testIncNumPacks() {
		m.incNumPacks();
		assertEquals(m.getNumPacks(), 1);
		m.incNumPacks();
		assertEquals(m.getNumPacks(), 2);
	}
	
	//Test getDestination
	@Test
	public void testGetDestination() {
		assertNotNull(m.getDestination());
		assertEquals(m.getDestination(), n2);
	}

	//Test setDestination
	@Test
	public void testSetDestination() {
		m.setDestination(n1);
		assertNotNull(m.getDestination());
		assertEquals(m.getDestination(), n1);
	}
	//Test getSource
	@Test
	public void testGetSource() {
		assertNotNull(m.getSource());
		assertEquals(m.getSource(), n1);
	}

	//Test setSource
	@Test
	public void testSetSource() {
		m.setSource(n2);
		assertNotNull(m.getSource());
		assertEquals(m.getSource(), n2);
	}
	
	//Test getMessage
	@Test
	public void testGetMessage() {
		assertNotNull(m.getMessage());
		assertEquals(m.getMessage(), "Hi");
	}
	
	//Test setMessage
	@Test
	public void testSetMessage() {
		m.setMessage("Hello");
		assertNotNull(m.getMessage());
		assertEquals(m.getMessage(), "Hello");
	}
	
	//Test GetNumHops
	@Test
	public void testGetNumHops() {
		assertEquals(m.getNumHops(),0);
		m.incNumHops();
		assertEquals(m.getNumHops(),1);
	}
	
	//Test IncNumHops
	@Test
	public void testIncNumHops() {
		m.incNumHops();
		assertEquals(m.getNumHops(),1);
		m.incNumHops();
		assertEquals(m.getNumHops(),2);
		m.incNumHops();
		assertEquals(m.getNumHops(),3);
	}
	
	//Test getVisited
	@Test
	public void testGetVisited() {
		assertEquals(visited, m.getVisited());
		assertEquals(visited, m.getVisited());
		assertNotEquals(null, m.getVisited());
		m.addVisited(n3);
		visited.add(n3);
		assertEquals(visited.get(0), m.getVisited().get(0));
	}
	
	
	//Test addVisited
	@Test
	public void testAddVisited() {
		m.addVisited(null);
		assertEquals(null, m.getVisited().get(0));
		m.addVisited(n3);
		visited.add(n3);
		assertEquals(visited.get(0), m.getVisited().get(1));
	}
	
	
	//Test isSuccess
	@Test
	public void testIsSuccess() {
		assertEquals(false,m.isSuccess());
		m.setSuccess(true);
		assertEquals(true,m.isSuccess());
	}
	
	//Test setSuccess 
	@Test
	public void testSetSuccess() {
		m.setSuccess(false);
		assertEquals(false,m.isSuccess());
		m.setSuccess(true);
		assertEquals(true,m.isSuccess());
	}

}
