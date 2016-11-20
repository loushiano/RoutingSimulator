package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import network2.Message;
import network2.Node;

public class MessageTest {
	private Node n1, n2;
	private Message m;
	
	@Before
	public void setUp() throws Exception {
		
		n1 = new Node("A");
		n2 = new Node("B");
		m = new Message("Hi", n1,n2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMessage() {
		assertNotEquals(m, null);
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
		assertNotEquals(m.getDestination(), null);
		assertEquals(m.getDestination(), n2);
	}

	@Test
	public void testSetDestination() {
		m.setDestination(n1);
		assertNotEquals(m.getDestination(), null);
		assertEquals(m.getDestination(), n1);
	}

	@Test
	public void testGetSource() {
		assertNotEquals(m.getSource(), null);
		assertEquals(m.getSource(), n1);
	}

	@Test
	public void testSetSource() {
		m.setSource(n2);
		assertNotEquals(m.getSource(), null);
		assertEquals(m.getSource(), n2);
	}

	@Test
	public void testGetMessage() {
		assertNotEquals(m.getMessage(),null);
		assertEquals(m.getMessage(), "Hi");
	}

	@Test
	public void testSetMessage() {
		m.setMessage("Hello");
		assertNotEquals(m.getMessage(),null);
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

}
