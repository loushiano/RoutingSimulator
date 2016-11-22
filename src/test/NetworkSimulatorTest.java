package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import network2.Message;
import network2.NetworkSimulator;
import network2.SimulationHandler;
import network2.Topology;
import strategies.ShortestPathStrategy;
import strategies.Strategy;
public class NetworkSimulatorTest {
	private NetworkSimulator ns;
	private Topology topology;
	private ArrayList<Message> messagesSent;
	private int settableRate;
	private SimulationHandler simulation;
	private Strategy strategy;;
	
	@Before
	public void setUp() throws Exception {
		ns = new NetworkSimulator();
		settableRate=0;
		topology =new Topology(ns);
		messagesSent=new ArrayList<Message>();
		
		strategy=new ShortestPathStrategy();
		simulation= new SimulationHandler(ns,topology);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNetworkSimulator() {
		assertNotNull(ns.getTopology());
		assertNotNull(ns.getMessagesSent());
		assertNotNull(ns.getSimulation());
		assertNotNull(ns.getSettableRate());
		assertNotNull(ns.getStrategy());
	}

	@Test
	public void testGetTopology() {
		assertNotNull(ns.getTopology());
	}

	@Test
	public void testGetMessagesSent() {
		assertNotNull(ns.getMessagesSent());
	}

	@Test
	public void testGetSimulation() {
		assertNotNull(ns.getSimulation());
	}

	@Test
	public void testGetSettableRate() {
		assertNotNull(ns.getSettableRate());
	}

	@Test
	public void testGetStrategy() {
		assertNotNull(ns.getStrategy());
	}

}
