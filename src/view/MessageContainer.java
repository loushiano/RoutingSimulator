package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
	
/*
 * This class contains the rectangle message in an arraylist.
 */
public class MessageContainer {
	private ArrayList<RectangleMessage> messages;
	private int width;
	private int height;
	private Circle circle;
	private int x;
	private int y;
	
	/*
	 * constructor of MessageContainer to initialize the fields and create objects if needed  
	 */
	public MessageContainer(Circle circle){
		messages=new ArrayList<RectangleMessage>();
		this.circle=circle;
		x=(int)circle.getCenter().getX()-width/2 ;
		y=(int)circle.getCenter().getY()+circle.getRadius()+10;
	}
	
	/*
	 * this method returns a list of messages
	 * @return messages is a list of messages 
	 */
	public ArrayList<RectangleMessage> getMessages(){
		return messages;
	} 
	
	/*
	 * this method is responsible to draw 
	 * @param  g is the graphics 
	*/
	public void drawRec(Graphics g){
		Graphics2D g2 =(Graphics2D)g;
    	g2.setRenderingHint(
    		    RenderingHints.KEY_ANTIALIASING,
    		    RenderingHints.VALUE_ANTIALIAS_ON);
    		g2.setRenderingHint(
    		    RenderingHints.KEY_TEXT_ANTIALIASING,
    		    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    		width=RectangleMessage.WIDTH;
    		height=RectangleMessage.HEIGHT;
    		g2.drawRect(x,y,width, height);
    		int i=0;
    		int j=0;
    		/*for(RectangleMessage rec:messages){
    			rec.setX(x+RectangleMessage.WIDTH*i);
    			rec.setX(y +i*RectangleMessage.HEIGHT);
    			
    			    			
    		}*/
		
	}
	
	/*
	 * this method is used to aDED MESSAGE 
	 * @param rec the rectangle messagte 
	 */
	public void addMessages(RectangleMessage rec){
		messages.add(rec);
	}
	

}
