package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import network2.GUIController;
import network2.Message;
import network2.Router;
/*
 * This class GUI is responsible for representing user interface of the network topology.
 * @author Ahmad Ayyoub
 */
public class GUI implements Observer{
	private JFrame frame; // The fame
	private JButton createNode; //Button
	private JButton step,delete,end,addNeighbour,undo; //Button
	private JButton startSimulation,shortest,random,flooding,softri,setSettable; //Button
	private Container contentPane; // Content Pane
	private JPanel rightPanel; //South Panel of the content Pane
	private JPanel southPanel; //North Panel of the content Pane
	private CirclePanel circlePanel;//a panel in which we will draw shapes
	private GUIController controler;//the controler that lsitens to the gui
	private ArrayList<JButton> buttons;//list of buttons to be passed to the controller
	private JTextArea hopsArea,packetsArea;//Jtext area to display output in
	private ArrayList<JButton> stratButtons;
	private JMenuBar menuBar;
	private int strategyInt=0;
	private int rate=3;
	/*
	 * This is the constructor for running the GUI for the network TOpology
	 * @param controler that listens to this view
	 */
	public GUI(GUIController controler){
		//Create the fame with specific features
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.controler=controler;
		setFrame(new JFrame("Network Topology"));
		//getFrame().setPreferredSize(new Dimension(1200, 700));
		buttons=new ArrayList<JButton>();
		circlePanel=new CirclePanel();
		stratButtons= new ArrayList<JButton>();
		
		getFrame().setResizable(true);
		}
		/*
		 * creates the topology based on a user entry, with all the nodes and connections
		 */
		public void createTopology(){
		
		//Panels of the Content Pane
		rightPanel = new JPanel();
		southPanel = new JPanel(new FlowLayout(0,15,5));
		frame.setContentPane(circlePanel);
		//Get the content pane from the frame.
		contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		menuBar=new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu menu =new JMenu("File");
		JMenuItem save =new JMenuItem("save");
		save.addActionListener(controler);
		JMenuItem load =new JMenuItem("load");
		load.addActionListener(controler);
		menu.add(save);
		menu.add(load);
		menuBar.add(menu);
		//contentPane.setPreferredSize(new Dimension(frame.getWidth(),3*frame.getHeight()/6));
		//Create the buttons required for representing the topology.
		createNode = new JButton("Create Node");
		createNode.setText("Add Node");
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
		undo=new JButton("Undo");
		undo.addActionListener(controler);
		buttons.add(undo);
		setSettable =new JButton("Set Rate");
		buttons.add(setSettable);
		
		shortest=new JButton("ShortestPathStrategy");
		random =new JButton("RandomStrategy");
		flooding =new JButton("FloodingStrategy");
		softri =new JButton("SoftriatorsStrategy");
		stratButtons.add(shortest);
		stratButtons.add(random);
		stratButtons.add(flooding);
		stratButtons.add(softri);
		end.setEnabled(false);
		//Add actionListenr to Buttons
		createNode.addActionListener(controler);
		step.addActionListener(controler);
		step.setEnabled(false);
		startSimulation.addActionListener(controler);
		startSimulation.setEnabled(false);
		addNeighbour.addActionListener(controler);
		delete.addActionListener(controler);
		end.addActionListener(controler);
		shortest.addActionListener(controler);
		random.addActionListener(controler);
		flooding.addActionListener(controler);
		softri.addActionListener(controler);
		setSettable.addActionListener(controler);
		//add a jtext area to display the results for the user 
		//Set the layout manage of the South Panel to FlowLayout and add the buttons to the south Panel.
		
		rightPanel.setPreferredSize(new Dimension(140,300));
		southPanel.setPreferredSize(new Dimension(180,300));
		rightPanel.setLayout(new FlowLayout(0,15,30));
		southPanel.setLayout(new FlowLayout(0,15,30));
		rightPanel.add(createNode);
		rightPanel.add(addNeighbour);
		
		rightPanel.add(delete);
		rightPanel.add(startSimulation);
		rightPanel.add(step);
		rightPanel.add(end);
		rightPanel.add(undo);
		rightPanel.add(setSettable);
		undo.setEnabled(false);
		frame.pack();
		
		
		//Set the layout manage of the North Panel to Border Layout and add the area that represents the Topology to the panel.
		/*JScrollPane pane1 =
	            new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane1.setPreferredSize(new Dimension(200,200));
		*/
		
		
		
        //add a mouse listener to the content pane which is the controler
		
		JLabel label =new JLabel("choose a strategy:");
	
		
		southPanel.add(label);
		southPanel.add(random);
		random.setEnabled(false);
		southPanel.add(flooding);
		
		southPanel.add(shortest);
		southPanel.add(softri);
		//initialize the text areas
		JLabel label2 =new JLabel("Hops Metrix:");
		JLabel label3 =new JLabel("Packets Metrix:");
		hopsArea= new JTextArea(2,16);
		packetsArea =new JTextArea(2,16);
		southPanel.add(label2);
		southPanel.add(hopsArea);
		southPanel.add(label3);
		southPanel.add(packetsArea);
		
		//southPanel.add(pane1,BorderLayout.EAST);
		
        contentPane.addMouseListener(controler);
        
		//Add the panels to the content pane.
		contentPane.add(rightPanel, BorderLayout.EAST);
		contentPane.add(southPanel,BorderLayout.WEST );
		//Set the frame to visible and pack it.
		frame.setVisible(true);
		frame.pack();
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		/*
		 * closes the application
		 */
	public void close() {
		getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
		
	}
	/*
	 * returns the frame of gui
	 * @return the frame of gui
	 */
	public JFrame getFrame() {
		return frame;
	}
	/*
	 * sets the frame of the gui
	 * @param frame to be set to the frame of this class
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof Router){
			Router node=(Router)arg1;
			circlePanel.addCircle(node.getCircle());
			circlePanel.addRect(node);
	    	circlePanel.draw();	
		}else if(arg1 instanceof ArrayList) {
			ArrayList<Router> nodes=(ArrayList<Router>)arg1;
			Router n1=nodes.get(0);
			Router n2=nodes.get(1);
			int x1=(int)n1.getCircle().getCenter().getX();
			int y1=(int)n1.getCircle().getCenter().getY();
			int x2=(int)n2.getCircle().getCenter().getX();
			int y2=(int)n2.getCircle().getCenter().getY();
			circlePanel.drawLine(x1,y1,x2,y2);
			startSimulation.setEnabled(true);
		}else if(arg1 instanceof Router[]){
			String s=((Router[])arg1)[0].getName();
			
			circlePanel.delete(s);
			
			
		}else if (arg1 instanceof String[]){
			String[] s=(String[])arg1;
			hopsArea.setText(s[0]);
		}else if (arg1 instanceof String){
		String s =(String) arg1;
		packetsArea.setText(s);
		
		}else{
			resetGui();
		}
		
	}
	/*
	 * returns an ArrayList of buttons
	 * @return an ArrayList of buttons
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	/*
	 * sets the ArrayList of buttons
	 * @param buttons an ArrayList of buttons
	 */
	public void setButtons(ArrayList<JButton> buttons) {
		this.buttons = buttons;
	}
	/*
	 * returns the string that the user enter which represents the name of the node
	 * @return the string that the user enter which represents the name of the node
	 */
	public String getLetter() {
		String letter;
		letter = JOptionPane.showInputDialog(getFrame(), "Enter the name of the Node,then press anywhere on the screen to locate the Node");
		while(letter==null || letter.equals("")){
			letter = JOptionPane.showInputDialog(getFrame(), "Enter the name of the Node,then press anywhere on the screen to locate the Node");
			
		}
		
		return letter.toUpperCase();
	}
	/*
	 * returns the user settable rate of the simulation
	 * @return  the user settable rate of the simulation
	 */
	public int getSettable() {
		
		
		
		return rate;
	}
	
	/*
	 * this method is responsible to get the number strategy 
	 * @return the number of strategy 
	 */
	public int getStrategy() {
		 
		   return strategyInt;
	}
	public void resetGui() {
		hopsArea.setText(null);
		packetsArea.setText(null);
		circlePanel.getCircles().clear();
		circlePanel.getLines().clear();
		circlePanel.draw();
		
	}
	/*
	 * returns a string representation of the path chosen by the user to open a saved file
	 * @return  a string representation of the path chosen by the user to open a saved file
	 */
	public String promptForFolder( Component parent )
	{
	    JFileChooser fc = new JFileChooser();
	    fc.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES);

	    if( fc.showOpenDialog( parent ) == JFileChooser.APPROVE_OPTION )
	    {
	        return fc.getSelectedFile().getAbsolutePath();
	    }

	    return null;
	}
	/*
	 *method used to save file into the system 
	 *@param parent the frame of the application
	 *@return a string representation of the url of the path that the file was saved into 
	 */
	public String saveFile( Component parent )
	{
		
	    JFileChooser fc = new JFileChooser();
	    fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
	    String s=null;
	    //if( fc.showSaveDialog( parent ) == JFileChooser.SAVE_DIALOG )
	    //{
	      //  return fc.getSelectedFile().getAbsolutePath();
	    //}
	    int rVal = fc.showSaveDialog(frame);
	      if (rVal == JFileChooser.APPROVE_OPTION) {
	        
	        s=fc.getCurrentDirectory().toString()+"/"+fc.getSelectedFile().getName();
	      }
	      if (rVal == JFileChooser.CANCEL_OPTION) {
	    	  JOptionPane.showMessageDialog(frame,"you have pressed cancel,nothing will get saved");
	      }
	    

	    return s;
	}
	/*
	 * propmpts the user with a message 
	 * @string the message to be prompted to the user
	 */
	public void promptUser(String string) {
		JOptionPane.showMessageDialog(frame,string);
		
	}
	/*
	 * return an arrayList of the strategy buttons
	 * @return an arrayList of the strategy buttons
	 */
	public ArrayList<JButton> getStratButtons(){
		return stratButtons;
	}
	/*
	 * sets the strategy integer that will be used in the network simulator to decide which strategy to apply on the simulation
	 * @i the integer to be set to the strategy Integer
	 */
	public void setStrategy(int j){
		strategyInt=j;
	}	
	/*
	 * method that asks the user to enter a settable rate for the simulaton
	 */
	public void setSettable(){
		String letter;
		letter = JOptionPane.showInputDialog(getFrame(), "Enter the settable rate of the simulation");
		while(letter==null || letter.equals("")){
			letter = JOptionPane.showInputDialog(getFrame(), "Enter the settable rate of the simulation");
			
		}
		rate=Integer.parseInt(letter);
	}
	
}
