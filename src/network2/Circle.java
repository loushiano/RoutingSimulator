package network2;

import java.awt.*;   // for Graphics



//Each Circle object represents a circle in the 2D plane

//with a given center and radius.

public class Circle {

	private Point center;   // fields

	private int radius;
	private String letter;
	

	// constructor(s)

	public Circle(Point center, int radius,String letter) {

		this.center = center;

		this.radius = radius;
		this.letter=letter;

	}

	

	// Returns the area of this circle.

	public double getArea() {

		return Math.PI * Math.pow(this.radius, 2);

	}

	

	// Returns the circumference of this circle (distance around the circle).

	public Point getCenter() {
		return center;
	}



	public void setCenter(Point center) {
		this.center = center;
	}



	public double getCircumference() {

		return 2 * Math.PI * this.radius;

	}

	

	// Returns whether the given point lies inside this circle.

	public boolean contains(Point p) {

		return this.center.distance(p) <= this.radius;

	}

	

	// Returns a text representation of this circle, such as

	// "Circle{center=(40, 100),radius=100}".

	public String toString() {

		return "Circle{center=" + this.center + ",radius=" + this.radius + "}";

	}

	

	// Draws this Circle onto a DrawingPanel.

 public void draw(Graphics g) {

		g.fillOval((int)center.getX() - radius, (int)center.getY() - radius,

		           2 * radius, 2 * radius);
		g.setColor(Color.WHITE);
		g.drawString(letter,(int)center.getX() ,(int)center.getY());

             

	}

}