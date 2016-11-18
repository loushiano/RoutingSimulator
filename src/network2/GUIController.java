package network2;
/*
 * A class Controler that listens to the GUI class and perform actions based on that
 * @author Ali Alsaaidi
 * 
 */
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class GUIController implements ActionListener, MouseListener {
	
	private int x;
	private int y,x1,y1,x2,y2;
	private boolean CreateButtonClicked=false;
	private String letter;
	private NetworkSimulator model;
	private GUI gui;
	private Topology topology;
	private SimulationHandler simulation;
	private boolean deleteNode;
	private boolean adding;
	/*
	 * Constructor of the Controller initializes the model field of the class
	 * @param model the models that communicates with the controller 
	 */
	public GUIController(NetworkSimulator model){
		this.model=model;
		topology=model.getTopology();
		simulation=model.getSimulation();
	}
	/*
	 * a method that sets the gui that the controller controls
	 * @param gui the gui that the controller controls
	 */
	public void setGUI(GUI gui){
		this.gui=gui;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
	     y = e.getY();
	     //get the position of the mouse when its clicked
	    if(CreateButtonClicked){
	    	//if the user have pressed the create Node button we create a circle
	    	Circle circle=new Circle(new Point(x,y),30,letter);
	    	//pass the circle to the model along side with the letter that it contains
	    	topology.addANode(letter,circle);
	    	
	    	
	    	CreateButtonClicked=false;
		
	 }
	    if(deleteNode){
	    	//if the user have pressed delete node we invoke the method in the model to delete it 
	    	topology.deleteNode(x,y);
	    	deleteNode=false;
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
		if(adding==true){
		x1=e.getX();
		y1=e.getY();
	//get Position of the mouse when its pressed
		}else{
			
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(adding==true){
		//get the position where the mouse was released
		x2=e.getX();
		y2=e.getY();
		
		//x1=0;
		//x2=0;
		//y1=0;
		//y2=0;
		//add the two circles to each other in model addNeighbours method
		topology.addNeighbours(x1,y1,x2,y2);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Create Node")){
			
			letter=gui.getLetter();
			CreateButtonClicked=true;
			
		}else if(e.getActionCommand().equals("Step")){
			simulation.step();
		}else if(e.getActionCommand().equals("Start")){
				int settable =gui.getSettable();
			
			simulation.simualteMessages();
			model.start(settable);
			ArrayList<JButton> buttons=gui.getButtons(); 
			for(JButton b:buttons){
				if(b.getActionCommand().equals("Step")){
					b.setEnabled(true);
				}else if(b.getActionCommand().equals("Create Node")||b.getActionCommand().equals("Delete")||b.getActionCommand().equals("Add Neighbour")){
					b.setEnabled(false);
				}
			}
			//createNode.setActionCommand("Step");
			//createNode.setText("Step");
			//startSimulation.setActionCommand("end Simulation");
			//startSimulation.setText("End Simulation");
		}else if(e.getActionCommand().equals("End")){
			model.endSimulation();
		}else if(e.getActionCommand().equals("Delete")){
			deleteNode=true;
		}else if(e.getActionCommand().equals("Add Neighbour")){
			adding=true;
		}
	}
	

}
