package view;
/*
 * This class Circle is responsible for representing the circle in the topology.
 * @author Ali Fawaz
 */
import java.awt.*;   // for Graphics
import java.util.ArrayList;

import network2.Message;
import network2.Router;



//Each Circle object represents a circle in the 2D plane

//with a given center and radius.

public class Circle {
	private final int RADIUS=30;
	private Point center;   // fields
	private int radius;
	private String letter;
	private ArrayList<Line> lines;
	private ArrayList<RectangleMessage> rectangles;

	/*
	 *  constructor
	 *  @param centre the center of the circle
	 *  @param radius the radius of the center
	 *  @param letter the letter that the circle holds
	 */
	public Circle(Point center,String letter) {

		this.center = center;

		this.radius = RADIUS;
		this.letter=letter;
		lines=new ArrayList<Line>();
		rectangles=new ArrayList<RectangleMessage>();

	}

	/*
	 * 
	 * Returns the area of this circle.
	 * @return the are of this circle
	 */

	public double getArea() {

		return Math.PI * Math.pow(this.radius, 2);

	}
	/*
	 * returns the arrayList of rectangle shapes that represent the messages of a router
	 * @return the arrayList of rectangle shapes that represent the messages of a router
	 */
	public ArrayList<RectangleMessage> getRectangles() {
		return rectangles;
	}

	/*
	 *  Returns the circumference of this circle (distance around the circle).
	 *  @return the circumference of this circle (distance around the circle).
	 */

	public Point getCenter() {
		return center;
	}
	/*
	 * sets the center of the circle
	 * @param center of the circle
	 */
	public void setCenter(Point center) {
		this.center = center;
	}
	/*
	 * returns the circumference of the circle
	 * @return circumference of the circle
	 */
	public double getCircumference() {

		return 2 * Math.PI * this.radius;

	}
	
	/*
	 *  Returns whether the given point lies inside this circle.
	 *  @return whether the given point lies inside this circle.
	 */

	public boolean contains(Point p) {

		return this.center.distance(p) <= this.radius;

	}
	/*
	 *  Returns a text representation of this circle, such as(non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	// "Circle{center=(40, 100),radius=100}".

	public String toString() {
		return "Circle{center=" + this.center + ",radius=" + this.radius + "}";
	}
	
	/*
	 *  Draws this Circle onto a DrawingPanel.
	 *  @param g the graphics of the panel
	 */
    public void draw(Graphics g) {
    	Graphics2D g2 =(Graphics2D)g;
    	g2.setRenderingHint(
    		    RenderingHints.KEY_ANTIALIASING,
    		    RenderingHints.VALUE_ANTIALIAS_ON);
    		g2.setRenderingHint(
    		    RenderingHints.KEY_TEXT_ANTIALIASING,
    		    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    		

    	float thickness = 2;
    	Stroke oldStroke = g2.getStroke();
    	g2.setStroke(new BasicStroke(10));
    	g2.setColor(Color.BLACK);
    	g2.drawOval((int)center.getX() - radius, (int)center.getY() - radius, 2 * radius, 2 * radius);
    	g2.setStroke(oldStroke);
    	g2.setColor(Color.GRAY);
    	g2.fillOval((int)center.getX() - radius, (int)center.getY() - radius, 2 * radius, 2 * radius);
		g2.setColor(Color.WHITE);
		Font font =new Font(Font.SANS_SERIF,Font.BOLD,15);
		g2.setFont(font);
		g2.drawString(letter,(int)center.getX()-2 ,(int)center.getY()+5);
		
		for(RectangleMessage rec:rectangles){
			rec.drawRectange(g2);
			
		}
	}
    
    /*
     * this method returns the number of lines 
     * @return number of lines 
     */
    public ArrayList<Line> getLines() {
		return lines;
	}

	/*
     * returns true if the circle contains the letter passed to it, false otherwise
     * @param l a string that represents a letter
     * @return true if the circle contains the letter passed to it, false otherwise
     */
    public boolean containsLetter(String l){
    	return letter.equals(l);
    }
    /*
     * adds a line to the circle
     * @param line to add to the circle
     */
	public void addLine(Line line) {
		lines.add(line);
		
	}
	/*
	 * removes the lines of the circle
	 */
	public void removeLines() {
		lines.clear();
		
	}
	/*
	 * returns true if the circle contains the line that is passed to it, false otherwise
	 * @param a line to be checked if it is in the circle
	 * @return true if the circle contains the line that is passed to it, false otherwise
	 */
	public boolean containsLine(Line l) {
		
		return lines.contains(l);
	}
	/*
	 * removes the line passed to it from the circle
	 * @param line to be removed from this circle
	 */
	public void removeLine(Line l){
		lines.remove(l);
	}

	/*
	 * this method gets the radius of the circle
	 * @return the radius of this circle
	 */
	public int getRadius() {
		
		return this.radius;
	}
	
	/*
	 * this method sets the centre of the circle 
	 * @param x is the x coordinate of the circle 
	 * @param y is the y coordinate of the circle 
	 */
	public void setCentre(int x,int y){
		this.center=new Point(x,y);
	}
	/*
	 * adds a rectangle above the circle, which represents the message in the circle
	 * @param message to be despalyed in the rectangle to be shown 
	 */
	public void addRect(RectangleMessage rect) {
		
		rectangles.add(rect);
		
	}
	/*
	 * returns an xml representation of the circle in form of a string
	 * @return an xml representation of the circle in form of a string
	 */
	public String toXML(int i) {
		String tab="";
		String tab1="";
		for(int j=0;j<i;j++){
			tab += "\t";
			if(j<i-1){
				tab1+="\t";
			}
		}
		String s="<circle>\n"+tab+"<x>"+center.getX()+"</x>\n"+tab+"<y>"+center.getY()+"</y>\n"+tab1+"</circle>";
		return s;
	}
}