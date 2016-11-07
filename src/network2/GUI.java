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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/*
 * This class is responsible for representing the whole Network Topology for the user.
 */
public class GUI extends Observable implements ActionListener{
	private JFrame frame; // The fame
	private JButton createNode; //Button
	private JButton addNeighbour; //Button
	private JButton startSimulation; //Button
	private Container contentPane; // Content Pane
	private JPanel southPanel; //South Panel of the content Pane
	private JPanel northPanel; //North Panel of the content Pane
	private int x,y,x1,y1,x2,y2;
	private boolean CreateButtonClicked;
	private String l="";
	private ArrayList<Node> topology;
	private String letter="A";
	private CirclePanel circlePanel;
	private MainModel model;
	
	/*
	 * This is the constructor for running the GUI for the network TOpology
	 */
	public GUI(MainModel model){
		//Create the fame with specific features
		topology=new ArrayList<Node>();
		 frame = new JFrame("Network Topology");
		frame.setPreferredSize(new Dimension(420, 400));
		circlePanel=new CirclePanel();
		this.model=model;
		this.addObserver(model);
		frame.setResizable(false);
		}
		
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
		
		startSimulation = new JButton("Start Simulation");
		
		//Add actionListenr to Buttons
		createNode.addActionListener(this);

		startSimulation.addActionListener(this);
		
		//Set the layout manage of the South Panel to FlowLayout and add the buttons to the south Panel.
		//southPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		southPanel.add(createNode);
		
		southPanel.add(startSimulation);
		
		//Set the layout manage of the North Panel to Border Layout and add the area that represents the Topology to the panel.
		northPanel.setBackground(Color.white);
        northPanel.setOpaque(true);
        //northPanel.add(circlePanel);
		
        contentPane.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				   x = e.getX();
				     y = e.getY();
				    if(CreateButtonClicked){
				    	Circle circle=new Circle(new Point(x,y),20,letter);
				    	circlePanel.addCircle(circle);
				    	circlePanel.draw();
				    	Node node=new Node(letter);
				    	node.setCircle(circle);
				    	topology.add(node);
				    	CreateButtonClicked=false;
				    	frame.pack();
				    }

				    
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				x1=e.getX();
				y1=e.getY();
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				x2=e.getX();
				y2=e.getY();
				Node n1=null,n2=null;
				for(Node n:topology){
					if(n.getCircle().contains(new Point(x1,y1))){
						 n1=n;
						
					}
					if(n.getCircle().contains(new Point(x2,y2))){
						 n2=n;
					}
				}
				if(n1!=null && n2!=null){
					n1.addNeighbour(n2);
					n2.addNeighbour(n1);
					circlePanel.drawLine((int)n1.getCircle().getCenter().getX(),(int)n1.getCircle().getCenter().getY(),
							(int)n2.getCircle().getCenter().getX(),(int)n2.getCircle().getCenter().getY());
				}
				
			}

			
	    	
	    	
	    });
	    
		
		//Add the panels to the content pane.
		contentPane.add(southPanel, BorderLayout.SOUTH);
		
		
		//Set the frame to visible and pack it.
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	
	
	
		
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Create Node")){
			letter = JOptionPane.showInputDialog(frame, "enter the name of the Node");
			while(letter==null || letter.equals("")){
				letter = JOptionPane.showInputDialog(frame, "enter the name of the Node");
			}
			letter=letter.toUpperCase();
			CreateButtonClicked=true;
		}else if(e.getActionCommand().equals("Step")){
			setChanged();
			notifyObservers(e.getActionCommand());
		}else if(e.getActionCommand().equals("Start Simulation")){
				
			setChanged();
			notifyObservers(topology);
			
			createNode.setActionCommand("Step");
			createNode.setText("Step");
			startSimulation.setActionCommand("end Simulation");
			startSimulation.setText("End Simulation");
		}else if(e.getActionCommand().equals("end Simulation")){
			setChanged();
			notifyObservers(e.getActionCommand());
		}
	}
	

	
	

	public void close() {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		
	}

	

	
	
}
