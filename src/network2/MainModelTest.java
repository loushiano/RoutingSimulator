package network2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainModelTest {
	private MainModel main1;
	
	@Before
	public void setUp() throws Exception {
		main1 = new MainModel();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMainModel() {
		assertNotEquals(main1.getTopology(), null);

	}

	@Test
	public void testSimualteMessages() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTopology() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTopology() {
		fail("Not yet implemented");
	}
	
	//

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testSimulation() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateTopology() {
		fail("Not yet implemented");
	}

	@Test
	public void testHopsMetrix() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMessagesSent() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMessagesSent() {
		fail("Not yet implemented");
	}

}
