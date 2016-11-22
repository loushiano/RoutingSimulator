package test;


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

	@Test
	public void testMessage() {
		assertNotNull(m);
		assertEquals(m.getMessage(), "Hi");
		assertEquals(m.getSource(), n1);
		assertEquals(m.getDestination(), n2);
	}

	@Test
	public void testGetNumPacks() {
		assertEquals(m.getNumPacks(), 0);
		m.incNumPacks();
		assertEquals(m.getNumPacks(), 1);
	}

	@Test
	public void testIncNumPacks() {
		m.incNumPacks();
		assertEquals(m.getNumPacks(), 1);
		m.incNumPacks();
		assertEquals(m.getNumPacks(), 2);
	}

	@Test
	public void testGetDestination() {
		assertNotNull(m.getDestination());
		assertEquals(m.getDestination(), n2);
	}

	@Test
	public void testSetDestination() {
		m.setDestination(n1);
		assertNotNull(m.getDestination());
		assertEquals(m.getDestination(), n1);
	}

	@Test
	public void testGetSource() {
		assertNotNull(m.getSource());
		assertEquals(m.getSource(), n1);
	}

	@Test
	public void testSetSource() {
		m.setSource(n2);
		assertNotNull(m.getSource());
		assertEquals(m.getSource(), n2);
	}

	@Test
	public void testGetMessage() {
		assertNotNull(m.getMessage());
		assertEquals(m.getMessage(), "Hi");
	}

	@Test
	public void testSetMessage() {
		m.setMessage("Hello");
		assertNotNull(m.getMessage());
		assertEquals(m.getMessage(), "Hello");
	}

	@Test
	public void testGetNumHops() {
		assertEquals(m.getNumHops(),0);
		m.incNumHops();
		assertEquals(m.getNumHops(),1);
	}

	@Test
	public void testIncNumHops() {
		m.incNumHops();
		assertEquals(m.getNumHops(),1);
		m.incNumHops();
		assertEquals(m.getNumHops(),2);
		m.incNumHops();
		assertEquals(m.getNumHops(),3);
	}

	@Test
	public void testGetVisited() {
		assertEquals(visited, m.getVisited());
		assertEquals(visited, m.getVisited());
		assertNotEquals(null, m.getVisited());
		m.addVisited(n3);
		visited.add(n3);
		assertEquals(visited.get(0), m.getVisited().get(0));
	}

	@Test
	public void testAddVisited() {
		m.addVisited(null);
		assertEquals(null, m.getVisited().get(0));
		m.addVisited(n3);
		visited.add(n3);
		assertEquals(visited.get(0), m.getVisited().get(1));
	}

	@Test
	public void testIsSuccess() {
		assertEquals(false,m.isSuccess());
		m.setSuccess(true);
		assertEquals(true,m.isSuccess());
	}

	@Test
	public void testSetSuccess() {
		m.setSuccess(false);
		assertEquals(false,m.isSuccess());
		m.setSuccess(true);
		assertEquals(true,m.isSuccess());
	}

}
