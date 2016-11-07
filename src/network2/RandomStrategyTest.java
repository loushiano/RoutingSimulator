package network2;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RandomStrategyTest {

	private RandomStrategy rs;
	private Node n1, n2 ,n3;
	private ArrayList<Node> nodes;
	private ArrayList<Message> messages;
	@Before
	public void setUp() throws Exception {
		rs = new RandomStrategy();
		n1 = new Node("A");
		n1 = new Node("B");
		n3 = new Node("C");
		messages=new ArrayList<Message>();
		messages.add(new Message("Hello", n2, n1));
		messages.add(new Message("Hi", n1, n2));
		nodes = new ArrayList<Node>();
		//n2.addMessage(new Message("Hello", n3, n1));
		//n3.addMessage(new Message("Hi", n1, n2));
		nodes.add(n1);
		nodes.add(n2);
		nodes.add(n3);
	
	}

	@After
	public void tearDown() throws Exception {	
	}
	/* there is a null pointer exception in RAndomStrategy Class !?!*/
	@Test
	public void testTransferMessage() {
		assertNotEquals(rs.transferMessage(nodes), null);
		assertNotEquals(rs.transferMessage(nodes), messages);
		
	}
}
