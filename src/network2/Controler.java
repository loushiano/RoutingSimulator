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
import javax.swing.JOptionPane;

public class Controler implements ActionListener, MouseListener {
	
	private int x;
	private int y,x1,y1,x2,y2;
	private boolean CreateButtonClicked=false;
	private String letter;
	private MainModel model;
	private GUI gui;
	private boolean deleteNode;
	/*
	 * Constructor of the Controller initializes the model field of the class
	 * @param model the models that communicates with the controller 
	 */
	public Controler(MainModel model){
		this.model=model;
		
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
	    	Circle circle=new Circle(new Point(x,y),20,letter);
	    	//pass the circle to the model along side with the letter that it contains
	    	model.addNode(letter,circle);
	    	
	    	
	    	CreateButtonClicked=false;
		
	 }
	    if(deleteNode){
	    	//if the user have pressed delete node we invoke the method in the model to delete it 
	    	model.deleteNode(x,y);
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
		x1=e.getX();
		y1=e.getY();
	//get Position of the mouse when its pressed	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//get the position where the mouse was released
		x2=e.getX();
		y2=e.getY();
		Node n1=null,n2=null;
		for(Node n:model.getTopology()){
			//check if a circle contains the point where the mouse was clicked
			if(n.getCircle().contains(new Point(x1,y1))){
				 n1=n;
				
			}
			//check if a circle contains the point where the mouse was released
			if(n.getCircle().contains(new Point(x2,y2))){
				 n2=n;
			}
		}
		x1=0;
		x2=0;
		y1=0;
		y2=0;
		//add the two circles to each other in model addNeighbours method
		model.addNeighbours(n1,n2);
		
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Create Node")){
			
			letter=gui.getLetter();
			CreateButtonClicked=true;
			
		}else if(e.getActionCommand().equals("Step")){
			model.step();
		}else if(e.getActionCommand().equals("Start")){
				int settable =gui.getSettable();
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
			
		}
	}
	

}