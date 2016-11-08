package network2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/*
 * This class is responsible for representing the whole Network Topology for the user.
 */
public class GUI implements Observer{
	private JFrame frame; // The fame
	private JButton createNode; //Button
	private JButton step,delete,end,addNeighbour; //Button
	private JButton startSimulation; //Button
	private Container contentPane; // Content Pane
	private JPanel southPanel; //South Panel of the content Pane
	private JPanel northPanel; //North Panel of the content Pane
	private CirclePanel circlePanel;//a panel in which we will draw shapes
	private Controler controler;//the controler that lsitens to the gui
	private ArrayList<JButton> buttons;//list of buttons to be passed to the controller
	private JTextArea area;//Jtext area to display output in
	/*
	 * This is the constructor for running the GUI for the network TOpology
	 */
	public GUI(Controler controler){
		//Create the fame with specific features
		this.controler=controler;
		setFrame(new JFrame("Network Topology"));
		getFrame().setPreferredSize(new Dimension(600, 600));
		buttons=new ArrayList<JButton>();
		circlePanel=new CirclePanel();
		
		
		getFrame().setResizable(false);
		}
		/*
		 * creates the topology based on a user entry, with all the nodes and connections
		 */
		public void createTopology(){
		
		//Panels of the Content Pane
		southPanel = new JPanel();
		northPanel = new JPanel();
		frame.setContentPane(circlePanel);
		//Get the content pane from the frame.
		contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		//Create the buttons required for representing the topology.
		createNode = new JButton("Create Node");
		buttons.add(createNode);
		startSimulation = new JButton("Start");
		buttons.add(startSimulation);
		step = new JButton("Step");
		delete = new JButton("Delete");
		end = new JButton("End");
		addNeighbour=new JButton("Add Neighbour");
		buttons.add(step);
		buttons.add(delete);
		buttons.add(end);
		buttons.add(addNeighbour);
		//Add actionListenr to Buttons
		createNode.addActionListener(controler);
		step.addActionListener(controler);
		step.setEnabled(false);
		startSimulation.addActionListener(controler);
		startSimulation.setEnabled(false);
		addNeighbour.addActionListener(controler);
		delete.addActionListener(controler);
		end.addActionListener(controler);
		//add a jtext area to display the results for the user
		 area=new JTextArea(10,52);
		//Set the layout manage of the South Panel to FlowLayout and add the buttons to the south Panel.
		
		southPanel.setPreferredSize(new Dimension(140,300));
		southPanel.setLayout(new FlowLayout(0,15,30));
		southPanel.add(createNode);
		southPanel.add(addNeighbour);
		
		southPanel.add(delete);
		southPanel.add(startSimulation);
		southPanel.add(step);
		southPanel.add(end);
		
		JScrollPane pane1 =
		            new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
			northPanel.add(pane1);
		//Set the layout manage of the North Panel to Border Layout and add the area that represents the Topology to the panel.
		
        //northPanel.add(circlePanel);
		
        contentPane.addMouseListener(controler);

		//Add the panels to the content pane.
		contentPane.add(southPanel, BorderLayout.EAST);
		contentPane.add(northPanel,BorderLayout.SOUTH );
		//Set the frame to visible and pack it.
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void close() {
		getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof Node){
			Node node=(Node)arg1;
			circlePanel.addCircle(node.getCircle());
	    	circlePanel.draw();	
		}else if(arg1 instanceof ArrayList) {
			ArrayList<Node> nodes=(ArrayList<Node>)arg1;
			Node n1=nodes.get(0);
			Node n2=nodes.get(1);
			circlePanel.drawLine((int)n1.getCircle().getCenter().getX(),(int)n1.getCircle().getCenter().getY(),
					(int)n2.getCircle().getCenter().getX(),(int)n2.getCircle().getCenter().getY());
			startSimulation.setEnabled(true);
		}else if(arg1 instanceof String){
			String s=(String)arg1;
			if(s.length()<4){
			circlePanel.delete(s);
			}else {
				area.append(s);
				area.append("\n");
			}
			
		}else{
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
		
	}

	public ArrayList<JButton> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<JButton> buttons) {
		this.buttons = buttons;
	}
	public String getLetter() {
		String letter;
		letter = JOptionPane.showInputDialog(getFrame(), "Enter the name of the Node");
		while(letter==null || letter.equals("")){
			letter = JOptionPane.showInputDialog(getFrame(), "Enter the name of the Node");
			
		}
		return letter.toUpperCase();
	}
	public int getSettable() {
		int i=0;
		String letter;
		letter = JOptionPane.showInputDialog(getFrame(), "Enter the settable rate and press ok to start the simulation");
		while(letter==null || letter.equals("")){
			letter = JOptionPane.showInputDialog(getFrame(), "Enter the settable rate and press ok to start the simulation");
			
		}
		
		return Integer.parseInt(letter);
	}
}
