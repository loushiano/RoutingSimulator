package network2;

import java.awt.*;   // for Graphics
import java.util.ArrayList;



//Each Circle object represents a circle in the 2D plane

//with a given center and radius.

public class Circle {

	private Point center;   // fields
	private int radius;
	private String letter;
	private ArrayList<Line> lines;

	/*
	 *  constructor
	 *  @param centre the center of the circle
	 *  @param radius the radius of the center
	 *  @param letter the letter that the circle holds
	 */

	public Circle(Point center, int radius,String letter) {

		this.center = center;

		this.radius = radius;
		this.letter=letter;
		lines=new ArrayList<Line>();

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
    	g.setColor(Color.green);
    	g.drawOval((int)center.getX() - radius, (int)center.getY() - radius, 2 * radius, 2 * radius);
    	g.setColor(Color.blue);
    	g.fillOval((int)center.getX() - radius, (int)center.getY() - radius, 2 * radius, 2 * radius);
		g.setColor(Color.WHITE);
		g.drawString(letter,(int)center.getX() ,(int)center.getY());
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
}