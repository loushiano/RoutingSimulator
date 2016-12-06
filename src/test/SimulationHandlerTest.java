package test;
/*
 * This class SimulationHandlerTest is responsible for testing the simulation handler.
 * 
 * @author Ahmad Ayyoub
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import network2.Message;
import network2.NetworkSimulator;
import network2.Router;
import network2.SimulationHandler;
import network2.Topology;

public class SimulationHandlerTest {
	private SimulationHandler sh;
	private  NetworkSimulator sim;
	private Topology topology;
	private Router n1,n2;
	private ArrayList<Message> ms;
	
	@Before
	public void setUp() throws Exception {
		ms  = new ArrayList<Message>();
		ms.add(new Message("Hi",n1,n2));
		ms.add(new Message("Hi2313",n1,n2));
		ms.add(new Message("Hi213213",n1,n2));
		sim = new NetworkSimulator();
		sim.addMessagesSent(new Message("Hi213213",n1,n2));
		sim.addMessagesSent(new Message("Hi2313",n1,n2));
		topology = new Topology(sim);
		n1 = new Router("A");
		n2 = new Router("B");
		n1.addMessage(new Message("Hi2313",n1,n2));
		n1.addMessage(new Message("Hi",n1,n2));
		ArrayList<Router> topology2 = new ArrayList<Router>();
		topology2.add(n1);
		topology2.add(n2);
		topology.setTopology(topology2);
		sh = new SimulationHandler(sim,topology);

	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	//Test simulation Handler.
	@Test
	public void testSimulationHandler() {
		assertNotNull(sh);
	}
	
	
	//Test step
	@Test
	public void testStep() {
		boolean x = sh.step();
		assertEquals(true, x);
	}

}
