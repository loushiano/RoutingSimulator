package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
/*
 * This class RectangleMessage is responsible for representing rectangle message at the top of the router.
 * @author Osama Rachid.                                                    
 */
public class RectangleMessage {
	private String message;
	private int x,y;
	public static final int WIDTH=200;
	public static final int HEIGHT=30;
	
	public  RectangleMessage(String message,Circle circle,int numOfMessages){
		
		
		this.message=message;
		x=(int)circle.getCenter().getX();
		y=(int)circle.getCenter().getY()-circle.getRadius()-40-HEIGHT*numOfMessages;
	}
	/*
	 * this method will draw the rectangle. using graphics
	 * 
	 */
	public void setX(int x) {
		this.x = x;
	}
	/*
	 * this method will set location x of this RM
	 * 
	 */
	public void addY(int y) {
		this.y =this.y- HEIGHT*y;
	}
	/*
	 * this method will set location y of this RM
	 * 
	 */
	public void drawRectange(Graphics g){
		Graphics2D g2 =(Graphics2D)g;
    	g2.setRenderingHint(
    		    RenderingHints.KEY_ANTIALIASING,
    		    RenderingHints.VALUE_ANTIALIAS_ON);
    		g2.setRenderingHint(
    		    RenderingHints.KEY_TEXT_ANTIALIASING,
    		    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    		

    	
    	
    	g2.setColor(Color.GRAY);
    	g2.fillRect(x-WIDTH/2,y,WIDTH,HEIGHT);
    	Stroke oldStroke = g2.getStroke();
    	g2.setStroke(new BasicStroke(3));
    	g2.setColor(Color.BLACK);
    	g2.drawRect(x-WIDTH/2,y,WIDTH,HEIGHT);
    	g2.setStroke(oldStroke);
    	g2.setColor(Color.white);
		Font font =new Font(Font.SANS_SERIF,Font.BOLD,10);
		g2.setFont(font);
		g2.drawString(message,x-WIDTH/2+3,y+HEIGHT/2);
		
	}
	
}
