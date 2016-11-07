package network2;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Controler implements ActionListener, MouseListener {
	
	private int x;
	private int y,x1,y1,x2,y2;
	private boolean CreateButtonClicked=false;
	private String letter;
	private MainModel model;
	private GUI gui;
	
	public Controler(MainModel model){
		this.model=model;
		
	}
	
	public void setGUI(GUI gui){
		this.gui=gui;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
	     y = e.getY();
	    if(CreateButtonClicked){
	    	Circle circle=new Circle(new Point(x,y),20,letter);
	    	model.addNode(letter,circle);
	    	
	    	
	    	CreateButtonClicked=false;
		
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
		for(Node n:model.getTopology()){
			if(n.getCircle().contains(new Point(x1,y1))){
				 n1=n;
				
			}
			if(n.getCircle().contains(new Point(x2,y2))){
				 n2=n;
			}
		}
		model.addNeighbours(n1,n2);
		
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Create Node")){
			letter = JOptionPane.showInputDialog(gui.getFrame(), "Enter the name of the Node");
			while(letter==null || letter.equals("")){
				letter = JOptionPane.showInputDialog(gui.getFrame(), "Enter the name of the Node");
				
			}
			letter=letter.toUpperCase();
			CreateButtonClicked=true;
			
		}else if(e.getActionCommand().equals("Step")){
			
		}else if(e.getActionCommand().equals("Start Simulation")){
				
			
			
			//createNode.setActionCommand("Step");
			//createNode.setText("Step");
			//startSimulation.setActionCommand("end Simulation");
			//startSimulation.setText("End Simulation");
		}else if(e.getActionCommand().equals("end Simulation")){
			
		}
	}
	

}
