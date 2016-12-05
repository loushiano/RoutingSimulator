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
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;

import view.Circle;
import view.GUI;

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
	private ArrayList<JButton> buttons;
	private boolean notPressed;
	/*
	 * Constructor of the Controller initializes the model field of the class
	 * @param model the models that communicates with the controller 
	 */
	public GUIController(NetworkSimulator model){
		this.model=model;
		topology=model.getTopology();
		simulation=model.getSimulation();
		 buttons=new ArrayList<JButton>();
	}
	/*
	 * a method that sets the gui that the controller controls
	 * @param gui the gui that the controller controls
	 */
	public void setGUI(GUI gui){
		this.gui=gui;
		buttons=gui.getButtons();
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
	     y = e.getY();
	     //get the position of the mouse when its clicked
	    if(CreateButtonClicked){
	    	//if the user have pressed the create Node button we create a circle
	    	Circle circle=new Circle(new Point(x,y),letter);
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
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(adding==true){
		x1=e.getX();
		y1=e.getY();
	//get Position of the mouse when its pressed
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
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
		adding=false;
		}
		notPressed=true;
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Add Node")){
			
			letter=gui.getLetter();
			CreateButtonClicked=true;
			
		}else if(e.getActionCommand().equals("Step")){
			simulation.step();
			for(JButton b:buttons){
				if(b.getActionCommand().equals("Undo")){
					b.setEnabled(true);
				}
			}
		}else if(e.getActionCommand().equals("Start")){
				int settable =gui.getSettable();
				int strategy =gui.getStrategy();
				gui.promptUser("the simulation will start now!");
			simulation.simualteMessages();
			model.start(settable,strategy);
			((JButton)e.getSource()).setEnabled(false);
			 backToInitial();
			
		}else if(e.getActionCommand().equals("End")){
			gui.promptUser("the current Simulation is going to end, everything will be reset!");
			
			for(JButton b:buttons){
				if(b.getActionCommand().equals("Step")||b.getActionCommand().equals("End") ||b.getActionCommand().equals("Undo") ){
					b.setEnabled(false);
				}else if(b.getActionCommand().equals("Add Node")||b.getActionCommand().equals("Delete")||b.getActionCommand().equals("Add Neighbour") || b.getActionCommand().equals("Set Rate") ){
					b.setEnabled(true);
				}
			}
			for(JButton p:gui.getStratButtons() ){
				
					p.setEnabled(true);
				
			}
			
			model.reset();
		}else if(e.getActionCommand().equals("Delete")){
			deleteNode=true;
		}else if(e.getActionCommand().equals("Add Neighbour")){
			adding=true;
		}else if (e.getActionCommand().equals("Undo")){
			simulation.undo();
			if(simulation.getSteps()==0){
				((JButton)e.getSource()).setEnabled(false);
			}
		}else if(e.getActionCommand().equals("save")){
			String s=gui.saveFile(gui.getFrame());
			if(s!=null){
			topology.exportToXML(s);
			}
		}else if(e.getActionCommand().equals("load")){
			model.reset();
			String s=gui.promptForFolder(gui.getFrame());
			if(s!=null){
			if(s.contains(".xml")){
				model.reset();
			model.load(s);
			}else{
				gui.promptUser("you have not chosen an xml file");
			}
			}else{
				
			}
			
		}else if (e.getActionCommand().equals("Set Rate")){
			gui.setSettable();
		
		}else if (e.getActionCommand().equals("RandomStrategy")){
			gui.setStrategy(0);
			for(JButton p:gui.getStratButtons() ){
				if(p.getActionCommand().equals(e.getActionCommand())){
					p.setEnabled(false);
				}else{
					p.setEnabled(true);
				}
			}
		}else if (e.getActionCommand().equals("FloodingStrategy")){
			gui.setStrategy(1);
			for(JButton p:gui.getStratButtons() ){
				if(p.getActionCommand().equals(e.getActionCommand())){
					p.setEnabled(false);
				}else{
					p.setEnabled(true);
				}
			}
		}else if (e.getActionCommand().equals("ShortestPathStrategy")){
			gui.setStrategy(2);
			for(JButton p:gui.getStratButtons() ){
				if(p.getActionCommand().equals(e.getActionCommand())){
					p.setEnabled(false);
				}else{
					p.setEnabled(true);
				}
			}
		}else if (e.getActionCommand().equals("SoftriatorsStrategy")){
			gui.setStrategy(2);
			for(JButton p:gui.getStratButtons() ){
				if(p.getActionCommand().equals(e.getActionCommand())){
					p.setEnabled(false);
				}else{
					p.setEnabled(true);
				}
			}
		}
	}
	public void backToInitial(){
		for(JButton b:buttons){
			if(b.getActionCommand().equals("Step")||b.getActionCommand().equals("End") ){
				b.setEnabled(true);
			}else if(b.getActionCommand().equals("Add Node")||b.getActionCommand().equals("Delete")||b.getActionCommand().equals("Add Neighbour") ||b.getActionCommand().equals("Set Rate") ){
				b.setEnabled(false);
			}
		}
		for(JButton p:gui.getStratButtons() ){
			
				p.setEnabled(false);
			
		}
		
	}		

}
