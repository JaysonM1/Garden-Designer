/*
 * @author team 10-0
 */

public class Placeables implements java.io.Serializable{
	private String name;
	private double currentHeight;
	private double currentWidth;
	public double locationX;
	public double locationY;
	private String image_address;
	
	
	/**
	 * constructor for placeables, 2 params
	 * @param name a string
	 * @param address a string
	 */
	public Placeables(String name, String address) {
		this.name = name;
		this.image_address = address;
	}
	
	/**
	 * constructor for placeables
	 * @param given_p - existing placeable
	 * @param x - double
	 * @param y - double
	 */
	public Placeables(Placeables given_p, double x, double y) {
		this.name = given_p.getName();
		this.image_address = given_p.getImageAddress();
		locationX = x;
		locationY = y;
	}
	
	public Placeables() {
		
	}
	
	/**
	 * constructor that only assigns a name
	 * @param name - string
	 */
	public Placeables(String name) {
		this.name = name;
	}
	 /**
	  * returns the x location of the placeable
	  * @return double
	  */
	public double getLocationX() {
		return locationX;
	}

	/**
	 * sets the x location of the placeable
	 * @param locationX - double
	 */
	public void setLocationX(double locationX) {
		this.locationX = locationX;
	}

	/**
	 * gets the y location of the placeable 
	 * @return - double
	 */
	public double getLocationY() {
		return locationY;
	}
	
	
	/**
	 * sets the y location of the placeable
	 * @param locationY 
	 */
	public void setLocationY(double locationY) {
		this.locationY = locationY;
	}

	
	//getters and setters *************************************
	/**
	 * gets the current height of the placeable
	 * @return - double
	 */
	public double getCurrentHeight() {
		return currentHeight;
	}
	
	/**
	 * sets the current height of the placeables
	 * @param currentHeight - double
	 */
	public void setCurrentHeight(double currentHeight) {
		this.currentHeight = currentHeight;
	}
	
	/**
	 * gets the current width
	 * @return double
	 */
	public double getCurrentWidth() {
		return currentWidth;
	}
	
	/**
	 * sets the current width 
	 * @param currentWidth - double
	 */
	public void setCurrentWidth(double currentWidth) {
		this.currentWidth = currentWidth;
	}

	
	/**
	 * returns the name of the placeable
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * sets the name of the placeable
	 * @param name - string
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * returns the image address of the placeable
	 * @return string
	 */
	public String getImageAddress() {
		return this.image_address;
	}

	//************************************************************
}