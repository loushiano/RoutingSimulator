package test;
/*
 * This class NetworkSimulatorTest is responsible for testing the network simulator.
 * @author Suhaib Habboush
 */
import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import network2.Message;
import network2.NetworkSimulator;
import network2.Router;
import network2.Topology;
import view.Circle;

public class TopologyTest {
	
	
	private NetworkSimulator sim;
	private Topology t;
	private ArrayList<Router> topology1;
	private Message m;
	private Router n1,n2,n3;
	private Circle c1,c2;
	
	
	@Before
	public void setUp() throws Exception {
		sim = new NetworkSimulator();
		t = new Topology(sim);
		topology1 = new ArrayList<Router>();
		n1 = new Router("A");
		n2 = new Router("B");
		n3 = new Router("C");
		m = new Message("0",n1,n2);
		c1=new Circle(new Point(3,3),"A");
		c2=new Circle(new Point(4,4),"B");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetTopology() {
		assertNotNull(t.getTopology());
		ArrayList<Router> topology2 = new ArrayList<Router>();
		assertEquals(t.getTopology(), topology2);
		topology2.add(new Router("K"));
		assertNotEquals(t.getTopology(), topology2);
	}

	@Test
	public void testSetTopology() {
		t.setTopology(topology1);
		assertNotNull(t.getTopology());
		assertEquals(t.getTopology(), topology1);
	}


	@Test
	public void testTopology() {
		assertNotNull(t);
		assertNotNull(t.getTopology());
		t.setTopology(topology1);
		//Check if setting the new topology is not null too.
		assertNotNull(t.getTopology());
		assertEquals(t.getTopology(), topology1);
	}

	@Test
	public void testAddARouter() {
		Circle c=new Circle(new Point(3,3),"A");
		t.addANode("A", c);
		assertEquals(c,t.getTopology().get(0).getCircle());
	}

	@Test
	public void testDeleteRouter() {
		Circle c=new Circle(new Point(4,3),"A");
		t.addANode("A", c);
		assertEquals(c,t.getTopology().get(0).getCircle());
		t.deleteNode(4, 3);
		assertEquals(true,t.getTopology().isEmpty());
	}


	@Test
	public void testGetDestinationOfAMessage() {
		ArrayList<Router> topology2 = new ArrayList<Router>();
		topology2.add(n1);
		topology2.add(n2);
		t.setTopology(topology2);
		assertEquals(n2,t.getDestinationOfAMessage(n1));
	}

	@Test
	public void testAddNeighbours() {
		assertEquals(true,t.addNeighbours(4,4,3,3));
	}

	@Test
	public void testGetNextOne() {
		ArrayList<Router> topology2 = new ArrayList<Router>();
		topology2.add(n1);
		topology2.add(n2);
		t.setTopology(topology2);
		assertEquals(n2,t.getNextOne(n1));
	}
}
