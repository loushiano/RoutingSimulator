package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import network2.Message;
import network2.Router;

/**
 * A class that draws all the shapes the user wants to draw
 * 
 * @author Ibrahim Ali Fawaz
 *
 */
public class CirclePanel extends JPanel {
	private ArrayList<Circle> circles;//ArrayList of circles
	private ArrayList<Line> lines;//ArrayList of lines
	private RectangleMessage rectangle;
	private ArrayList<Point2D> points;
	
	private int counter;
	private Timer _timer;
	
	/*
	 * Constructor that initialize the fields of this class
	 */
	public CirclePanel(){
		circles=new ArrayList<Circle>();
		lines=new ArrayList<Line>();
		rectangle=null;
		points=new ArrayList<Point2D>();
		
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
			this.setBackground(Color.WHITE);
		
		
		if(lines.size()!=0){
			for(Line l:lines){
				l.draw(g);
			}
		}
		if(circles.size()!=0){
			for(Circle c:circles){
				drawCircle(c,g);
			}
		}
		
		
		
	}
	/*
	 * draws a circle on the panel
	 * @param circle to be drawn
	 * @param g the graphics of the panel
	 */
	public void drawCircle(Circle circle,Graphics g) {
	  
	    g.setColor(Color.BLUE);
	    circle.draw(g);
	   
	}
	/*
	 * adds a circle to the circles of the panel
	 * @param circle to be added to the circles of the panel
	 */
	public void addCircle(Circle circle) {
		if(!circles.contains(circle)){
		circles.add(circle);
		}
	}
	/*
	 * invokes repaint to show all missing shapes
	 */
	public void draw() {
		repaint();
		
	}
	/*
	 * draws a line in the panel
	 * @param x1 the x-axis position of the first point
	 * @param y1 the y-axis position of the first point
	 * @param x2 the x-axis position of the second point
	 * @param y2 the y-axis position of the second point
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		
		Line line =new Line(x1,y1,x2,y2);
		lines.add(line);
		for(Circle c:circles){
			if(c.contains(new Point(x1,y1))||c.contains(new Point(x2,y2)) ){
				
				c.addLine(line);
			}
		}
		repaint();
	}
	/*
	 * deletes the circle that holds the string that is passed to it, alongside with the lines inside that circle
	 * @param s the string inside the circle to be deleted
	 */
	public void delete(String s) {
		Circle ci=null;
		ArrayList<Line> lines1=new ArrayList<Line>();
		for(Circle c:circles){
			if(c.containsLetter(s.toUpperCase())){
				
				for(Line l:lines){
					if(c.containsLine(l)){
						
						lines1.add(l);
						}
				}
				
				ci=c;
			}
		}
		circles.remove(ci);
		for(Line l:lines1){
			
			lines.remove(l);
		}
		repaint();
	}

	boolean b=true;
	
	
	/*
	 * this method transfers the Rectangle message from this circle (this node) to one of the node in the given array list of nodes
	 * @param message the given message 
	 * @param circle the circle that represents the node
	 * @param nodes is the array list of nodes 
	 */
	/*public void moveMessage(String message, Circle circle, ArrayList<Router> nodes) {
		points=new ArrayList<Point2D>();
		for(Router n: nodes){
			Circle circle2=n.getCircle();
		
			
			int x=(int)circle.getCenter().getX();
			int y=(int)circle.getCenter().getY();
			rectangle=new RectangleMessage(message,x,y);
		Line line =getLine(circle,circle2);
		Point2D current;
		for (Iterator<Point2D> it = new LineIterator(line); it.hasNext();) {
		    current = it.next();
		    points.add(current);
		}
		}
		 counter=0;
		 
		 
		timer.start();
			
		
			 	
		}	 
		    
		*/	 
	
		    	
		    

	 Timer timer = new Timer(3, new ActionListener() {
		 /*'
		  * (non-Javadoc)
		  * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		  */
		    @Override
		    public void actionPerformed(ActionEvent ae) {
		    	if(counter<points.size()-1){
		    		
		    			
		    	rectangle.setX((int)points.get(++counter).getX());
				//rectangle.setY((int)points.get(counter).getY());
		    	repaint();
		    	
		    	}else{
		    	stop();
		    	}
		    	}
		        
		    
		});

			
		
		
		 
		
		
		
	
	 /*
	  * this method gets the x and y coordinates of the  line 
	  * @param circle the first given circle 
	  * @param circle2 the second given circle  
	  */
	private Line getLine(Circle circle, Circle circle2) {
		for(Line line: circle.getLines()){
			if(circle2.containsLine(line)){
				if(line.getX1()!=circle.getCenter().getX()){
					return new Line((int)circle.getCenter().getX(),(int)circle.getCenter().getY(),(int)circle2.getCenter().getX(),(int)circle2.getCenter().getY());
				}else{
					return line;
				}
			}
		}
		return null;
	}
	
	/*
	 * this method is responsible to stop the timer 
	 */
	private void stop() {
			timer.stop();
		 rectangle=null;
		repaint();
		b=false;
		
	}
	/*
	 * this method adds all the messages that are in a router to the circle that corresponds to that router,to be then displayed above that circle
	 * @param the router to which the messages correspondss
	 */
	public void addRect(Router node) {
		
		node.getCircle().getRectangles().clear();
		int i=0;
		for(Message m:node.getMessages()){
			String message="Message:"+m.getMessage()+" source: "+ m.getSource().getName()+" destination: "+ m.getDestination().getName();
			
		RectangleMessage rect=new RectangleMessage(message,node.getCircle(),i);
		
		node.getCircle().addRect(rect);
		i++;
	}

	
	}
	/*
	 * return the circles of the panel
	 * @return the circles of the panel
	 */
	public ArrayList<Circle> getCircles() {
		return circles;
	}
	/*
	 * return the lines of the panel
	 * @return the lines of the panel
	 */
	public ArrayList<Line> getLines(){
		return lines;
	}

}