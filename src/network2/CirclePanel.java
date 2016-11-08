package network2;

import java.awt.*;
import java.util.ArrayList;


import javax.swing.*;

/**
 * A class that draws all the shapes the user wants to draw
 * 
 * @author Ibrahim Ali Fawaz
 *
 */
public class CirclePanel extends JPanel {
	private ArrayList<Circle> circles;//ArrayList of circles
	private ArrayList<Line> lines;//ArrayList of lines
	/*
	 * Constructor that initialize the fields of this class
	 */
	public CirclePanel(){
		circles=new ArrayList<Circle>();
		lines=new ArrayList<Line>();
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
			this.setBackground(Color.WHITE);
		if(circles.size()!=0){
			for(Circle c:circles){
				drawCircle(c,g);
			}
		}
		
		if(lines.size()!=0){
			for(Line l:lines){
				l.draw(g);
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
		circles.add(circle);
		
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
	
	
	
	
	

}