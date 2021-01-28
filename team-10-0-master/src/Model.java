
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import java.util.Arrays;
import java.util.List;


/**
 * 
 * @author team 10-0
 *
 */

public class Model {
	private double canvasSizeX = 100;
	private double canvasSizeY = 200;
	private double sidePaneSizeX;
	private double sidePaneSizeY;
	private double stackPaneSizeX;
	private double stackPaneSizeY;
	private double imageX;
	private double imageY;
	
	private  HashMap<String, Plants> hash_of_plants;
	public  HashMap<String, Placeables> hash_of_placeables;
	
	private List<List<String>> plant_records;
	private List<List<String>> structure_records;
	//hashset of object key: plant ; value: list of [x,y]

	
	/**
	 * sets the x value of an image
	 * @param x - double
	 */
	public void setX(double x) {
		this.imageX = x;
	}
	
	/**
	 * sets the y value of an image
	 * @param y - double
	 */
	public void setY(double y) {
		this.imageY = y;
	}
	
	/**
	 * returns the x value of an image
	 * @return double
	 */
	public double getImageX() {
		return imageX;
	}
	/**
	 * returns the y value of an image
	 * @return double 
	 */
	public double getImageY() {
		return imageY;
	}
	
	
	
	/**
	 * reads in a csv file of plants and attributes
	 * save the information in an arraylist
	 * @return list of list of strings
	 * @throws IOException
	 */
	public List<List<String>> read_csv_plants() throws IOException {
		List<List<String>> records = new ArrayList();
		try {
			File placeables = new File("src/csv_files/plant_parameters.csv");
			Scanner csvScan = new Scanner(placeables);
			
			for(int i = 0; i < 25; i++) {
				String temp = csvScan.nextLine();
				records.add(Arrays.asList(temp.split(",")));
			}
			csvScan.close();
			
		} catch(Exception e) { System.out.println("Something went wrong\n" + e); }
		
		//System.out.println(records.toString());
		this.plant_records = records;
		return plant_records;
	}
	
	/**
	 * reads in a csv file for structures saves it to a list of list of strings
	 * and returns it
	 * @return list of list of strings
	 * @throws IOException
	 */
	public List<List<String>> read_csv_structures() throws IOException {
		List<List<String>> records = new ArrayList();
		try {
			File placeables = new File("src/csv_files/struct_parameters.csv");
			Scanner csvScan = new Scanner(placeables);
			
			for(int i = 0; i < 5; i++) {
				String temp = csvScan.nextLine();
				records.add(Arrays.asList(temp.split(",")));
			}
			
			csvScan.close();
			
		} catch(Exception e) { System.out.println("Something went wrong\n" + e); }
		
		//System.out.println(records.toString());
		this.structure_records = records;
		return records;
	}
	
	
	/**
	 * creates a list of plants
	 * @return list of plants
	 */
	public List<Plants> create_list_of_plants() {
		List<Plants> plants_list = new ArrayList<>();
	//	System.out.println(plant_records.get(0).get(0));
		for (List<String> strList : plant_records) {
			plants_list.add(new Plants(strList.get(0), strList.get(1), strList.get(2),strList.get(3) ,strList.get(4), strList.get(5)));
		}
		System.out.println(plants_list.get(0));
		System.out.println(plants_list);
		return plants_list;
	}
	
	/**
	 * creates a hashmap of plants
	 * 
	 * @return hashmap<string, plants>
	 */
	public HashMap<String, Plants> make_hashSet_of_plants(){
		HashMap<String, Plants> hashPlant = new HashMap<>();
		for(Plants x : create_list_of_plants()) {
			hashPlant.put(x.getName(), x);
		}
		this.hash_of_plants = hashPlant;
		return hashPlant;
	}
	
	/**
	 * creates an array list of placeables
	 * 
	 * @return list<placeables>
	 */
	public List<Placeables> create_list_of_placeables() {
		List<Placeables> place_list = new ArrayList<>();
//		System.out.println(structure_records.get(0).get(0));
		for (List<String> strList : structure_records) {
			place_list.add(new Placeables(strList.get(0), strList.get(1)));
		}
		System.out.println(place_list);
		
		return place_list;
	}
	
	/**
	 * makes a hashmap of placeables
	 * based on a the list of placeables
	 * @return hashmap<string, placeables>
	 */
	public HashMap<String, Placeables> make_hashSet_of_placeables(){
		HashMap<String, Placeables> hashPlaceables = new HashMap<>();
		for(Placeables x : create_list_of_placeables()) {
			hashPlaceables.put(x.getName(), x);
		}
		this.hash_of_placeables = hashPlaceables;
		
		return hashPlaceables;
	}
	
	
	
	/**
	 * returns a hashmap of plants
	 * @return HashMap<String,Plants>
	 */
	public  HashMap<String, Plants> getHashPlant(){
		return this.hash_of_plants;
	}
	
	/**
	 * returns a hashmap of structures
	 * @return HashMap<String, Placeables>
	 */
	public  HashMap<String, Placeables> getHashStructures(){
		return this.hash_of_placeables;
	}
	
	/**
	 * checks if the current plants are com
	 * @param plant
	 * @return boolean
	 */
//	public boolean is_plant_not_compatible(Plants plant) {
//		modifiers.clear();
//		modifiers.addAll(Arrays.asList(sunlight,  rainfall, current_season,soil));
//		List<String> plant_mods = plant.make_list_of_mods();
//		System.out.println(plant_mods);
//		System.out.println(modifiers);
//		for(int i = 0; i < 4; i++) {
//			String s1 = plant_mods.get(i);
//			String s2 = this.modifiers.get(i);
//			if(!s1.equals(s2)) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
	


	//getters and setters ********************************
	/**
	 * returns the canvas size in the x axis
	 * @return double
	 */
	public double getCanvasSizeX() {
		return canvasSizeX;
	}

	/**
	 * set the x value of the canvas size
	 * @param canvasSizeX double
	 */
	public void setCanvasSizeX(double canvasSizeX) {
		this.canvasSizeX = canvasSizeX;
	}

	/**
	 * returns the y values of the canvas size
	 * @return double
	 */
	public double getCanvasSizeY() {
		return canvasSizeY;
	}

	/**
	 * sets the y value of the canvas size
	 * @param canvasSizeY
	 */
	public void setCanvasSizeY(double canvasSizeY) {
		this.canvasSizeY = canvasSizeY;
	}

	/**
	 * gets the x value of the side pane
	 * @return double
	 */
	public double getSidePaneSizeX() {
		return sidePaneSizeX;
	}

	/**
	 * sets the x value of the side pane size
	 * @param sidePaneSizeX
	 */
	public void setSidePaneSizeX(double sidePaneSizeX) {
		this.sidePaneSizeX = sidePaneSizeX;
	}

	/**
	 * gets the side pane y value size
	 * @return double
	 */
	public double getSidePaneSizeY() {
		return sidePaneSizeY;
	}

	/**
	 * sets the y value size side pane
	 * @param sidePaneSizeY
	 */
	public void setSidePaneSizeY(double sidePaneSizeY) {
		this.sidePaneSizeY = sidePaneSizeY;
	}

	/**
	 * gets the x value size of the stack pane
	 * @return double
	 */
	public double getStackPaneSizeX() {
		return stackPaneSizeX;
	}

	/**
	 * sets the stack pane size x value
	 * @param stackPaneSizeX
	 */
	public void setStackPaneSizeX(double stackPaneSizeX) {
		this.stackPaneSizeX = stackPaneSizeX;
	}

	/**
	 * gets the stack pane y value 
	 * @return
	 */
	public double getStackPaneSizeY() {
		return stackPaneSizeY;
	}

	/**
	 * gets the stack pane size y value
	 * @param stackPaneSizeY
	 */
	public void setStackPaneSizeY(double stackPaneSizeY) {
		this.stackPaneSizeY = stackPaneSizeY;
	}
	//*******************************************************
}

