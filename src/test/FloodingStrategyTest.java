package test;
/*
 * This class RandomStrategy is responsible for transfering the messages in the topology in a flooding way.
 * @author Ahmad Ayyoub
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import network2.Message;
import network2.Router;

import strategies.FloodingStrategy;

public class FloodingStrategyTest {
	private FloodingStrategy fs;
	private ArrayList<Router> a;
	private Router a1,a2,a3,a4,a5;
	
	
	@Before
	public void setUp() throws Exception {
		fs = new FloodingStrategy();
		a= new ArrayList<Router>();
		a1 = new Router("A");
		a2 = new Router("B");
		a3 = new Router("C");
		a4 = new Router("D");
		a5 = new Router("O");
		a.add(a1);
		a.add(a2);
		a.add(a3);
		a.add(a4);
		a.add(a5);
		a1.addMessage(new Message("Hi", a2,a3));
		a1.addMessage(new Message("Hello", a2,a3));
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//Test updateRoutingRable
	@Test
	public void testUpdateRoutingTable() {
		assertNotNull(a);;
		assertEquals(true, fs.updateRoutingTable(a));

	}

}
