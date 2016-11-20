package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

public class Line {
	private int x1,x2,y1,y2;
	
		public Line(int x1,int y1,int x2,int y2){
			this.x1=x1;
			this.y1=y1;
			this.x2=x2;
			this.y2=y2;
		}
		public void draw(Graphics g){
			Graphics2D g2 =(Graphics2D)g;
			g2.setRenderingHint(
	    		    RenderingHints.KEY_ANTIALIASING,
	    		    RenderingHints.VALUE_ANTIALIAS_ON);
	    		g2.setRenderingHint(
	    		    RenderingHints.KEY_TEXT_ANTIALIASING,
	    		    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
	    	Stroke oldStroke = g2.getStroke();
	    	g2.setStroke(new BasicStroke(5));
	    	
			
			g2.setColor(Color.black);
			g2.drawLine(x1, y1, x2, y2);
			g2.setStroke(oldStroke);
		}
		public int getX1() {
			return x1;
		}
		public int getX2() {
			return x2;
		}
		public int getY1() {
			return y1;
		}
		public int getY2() {
			return y2;
		}
		
}
