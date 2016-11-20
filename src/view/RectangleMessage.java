package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

public class RectangleMessage {
	private String message;
	private int x,y;
	public static final int WIDTH=30;
	public static final int HEIGHT=20;
	public  RectangleMessage(String message,int x,int y){
		this.x=x;
		this.y=y;
		this.message=message;
		
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void drawRectange(Graphics g){
		Graphics2D g2 =(Graphics2D)g;
    	g2.setRenderingHint(
    		    RenderingHints.KEY_ANTIALIASING,
    		    RenderingHints.VALUE_ANTIALIAS_ON);
    		g2.setRenderingHint(
    		    RenderingHints.KEY_TEXT_ANTIALIASING,
    		    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    		

    	
    	Stroke oldStroke = g2.getStroke();
    	g2.setStroke(new BasicStroke(10));
    	g2.setColor(Color.BLACK);
    	g2.fillRect(x-WIDTH/2,y-HEIGHT/2,WIDTH,HEIGHT);
    	g2.setStroke(oldStroke);
    	g2.setColor(Color.WHITE);
		Font font =new Font(Font.SANS_SERIF,Font.BOLD,10);
		g2.setFont(font);
		g2.drawString(message,x,y);
		
	}
	
}
