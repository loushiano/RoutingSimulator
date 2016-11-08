package network2;

import java.awt.*;
import java.util.ArrayList;


import javax.swing.*;

/**
 * A simplistic example of painting in a panel. 
 * A circle is drawn in the centre of the panel, and is resized when the frame is resized.
 * That "magical" resize occurs because when the frame is resized, 
 * a repaint() method is invoked on it, which in turn invokes repaint() on all of its own components 
 * (and in this case the CirclePanel). repaint() then invokes paintComponent(), thus redrawing the circle
 * according to the new dimensions of the panel.
 * 
 * @author Ibrahim Ali Fawaz
 *
 */
public class CirclePanel extends JPanel {
	private int x,y;
	private ArrayList<Circle> circles;
	private boolean drawLine=false;
	private ArrayList<Line> lines;
	public CirclePanel(){
		circles=new ArrayList<Circle>();
		lines=new ArrayList<Line>();
	}
	
	public void setXY(int x,int y){
		this.x=x;
		this.y=y;
		
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
	public void drawCircle(Circle circle,Graphics g) {
	  
	    g.setColor(Color.BLUE);
	    circle.draw(g);
	   
	}
	public void addCircle(Circle circle) {
		circles.add(circle);
		
	}
	public void draw() {
		repaint();
		
	}

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