/**
 * @author team 10-0
 */

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Plants extends Placeables implements java.io.Serializable{
	private int age = 0;
	private String sunlightNeeded;
	private String rainfallNeeded; 
	private String bloomtime;
	private String soilType;


	/**
	 * creates a plant using 5 strings
	 * @param name - string
	 * @param image_address - string
	 * @param sunLightNeeded - string
	 * @param rainfallNeeded - string
	 * @param bloomtime - string
	 * @param soilType - string
	 */
	public Plants(String name, String image_address, String sunLightNeeded,
			String rainfallNeeded, String bloomtime, String soilType) {
		super(name, image_address);
		this.sunlightNeeded= sunLightNeeded;
		this.rainfallNeeded=rainfallNeeded;
		this.bloomtime=bloomtime;
		this.soilType=soilType;
	}	
	
	/**
	 * adds an x and a y location to an exisiting plant
	 * @param given_p - plant
	 * @param x - double
	 * @param y - double
	 */
	public Plants(Plants given_p, double x, double y) {
		super(given_p.getName(), given_p.getImageAddress());
		this.sunlightNeeded= given_p.getSunlightNeeded();
		this.rainfallNeeded= given_p.getRainfallNeeded();
		this.bloomtime = given_p.getBloomtime();
		this.soilType=  given_p.getSoilType();
		this.locationX = x;
		this.locationY = y;
	}
	
	
	/**
	 * returns the bloomtime
	 * @return string
	 */
	private String getBloomtime() {
		return this.bloomtime;
	}
	
	
	/**
	 * default constructor
	 * @param name - string
	 */
	public Plants(String name) {
		super(name);
	}

	/**
	 * makes a list of modifiers and returns them
	 * @return list of strings
	 */
	public List<String> make_list_of_mods() {
		List<String> list = new ArrayList<>();
		list.add(sunlightNeeded);
		list.add(rainfallNeeded);
		list.add(bloomtime);
		list.add(soilType);
		
		return list;
	}
	
	//getters and setters *******************************
	
	/**
	 * returns the sunlight needed as a string
	 * @return string
	 */
	public String getSunlightNeeded() {
		return sunlightNeeded;
	}
	/**
	 * sets the sunlightneeded for the plant
	 * @param sunlightNeeded -string
	 */
	public void setSunlightNeeded(String sunlightNeeded) {
		this.sunlightNeeded = sunlightNeeded;
	}
	/**
	 * gets the rainfall needed for a plant
	 * @return string
	 */
	public String getRainfallNeeded() {
		return rainfallNeeded;
	}
	/**
	 * sets the rainfall needed for a plant
	 * @param rainfallNeeded - string
	 */
	public void setRainfallNeeded(String rainfallNeeded) {
		this.rainfallNeeded = rainfallNeeded;
	}
	
	/**
	 * gets the soiltype for a plant
	 * @return string
	 */
	public String getSoilType() {
		return soilType;
	}
	/**
	 * sets the soil type needed for a plant
	 * @param soilType - string
	 */
	public void setSoilType(String soilType) {
		this.soilType = soilType;
	}
	//******************************************************
	
}