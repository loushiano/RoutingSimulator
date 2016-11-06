package network2;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/*
 * This class is responsible for representing the whole Network Topology for the user.
 */
public class GUI implements ActionListener{
	private JFrame frame; // The fame
	private JButton createNode; //Button
	private JButton addNeighbour; //Button
	private JButton startSimulation; //Button
	private Container contentPane; // Content Pane
	private JPanel southPanel; //South Panel of the content Pane
	private JPanel northPanel; //North Panel of the content Pane
	private JTextArea area;
	
	/*
	 * This is the constructor for running the GUI for the network TOpology
	 */
	public GUI(){
		//Create the fame with specific features
		JFrame frame = new JFrame("Network Topology");
		frame.setSize(400, 400); 
		frame.setResizable(false);
		
		//Area for representing the Network Topology
		area = new JTextArea(40,40);
		
		//Panels of the Content Pane
		southPanel = new JPanel();
		northPanel = new JPanel();
		
		//Get the content pane from the frame.
		contentPane = frame.getContentPane();
		
		//Create the buttons required for representing the topology.
		createNode = new JButton("Create Node");
		addNeighbour = new JButton("Add Neighbour");
		startSimulation = new JButton("Start Simulation");
		
		//Add actionListenr to Buttons
		createNode.addActionListener(this);
		addNeighbour.addActionListener(this);
		startSimulation.addActionListener(this);
		
		//Set the layout manage of the South Panel to FlowLayout and add the buttons to the south Panel.
		southPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		southPanel.add(createNode);
		southPanel.add(addNeighbour);
		southPanel.add(startSimulation);
		
		//Set the layout manage of the North Panel to Border Layout and add the area that represents the Topology to the panel.
		northPanel.setLayout(new BorderLayout());
		northPanel.add(area);
		
		//Add the panels to the content pane.
		contentPane.add(southPanel, BorderLayout.SOUTH);
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		//Set the frame to visible and pack it.
		frame.setVisible(true);
		frame.pack();
	}
	
	/*
	 * The main function will run the graphical user interface of the network Topology.
	 */
	public static void main(String args[]) { 
		GUI gui = new GUI();
	}
		
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Create Node")){
			System.out.println("hi");
		}else if(e.getActionCommand().equals("Add Neighbour")){
			System.out.println("Hello");
		}else if(e.getActionCommand().equals("Start Simulation")){
			System.out.println(" Ali fawaz is a fat gay");
		}
	}
}
