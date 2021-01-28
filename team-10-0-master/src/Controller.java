/*** @author team 10-0
 */
import javafx.application.Application; 
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button; 
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import javafx.stage.FileChooser;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;


public class Controller extends Application implements java.io.Serializable{
	private final boolean DEBUG = true;
	
	public transient Model model;
	private transient View view;
	/**
	 * main method
	 */
    public static void main(String[] args) {
 
        launch(args);
    }
	    

    /**
     * method for launching through javaFX
     * 
     * @param theStage		the created stage for the javaFX
     * @return NULL
     * @throws IOException 
     */
    @Override
	public void start(Stage theStage) throws IOException {
    	model = new Model();
    	model.read_csv_plants();
    	model.read_csv_structures();
		//model.create_list_of_plants();
		model.make_hashSet_of_plants();
		model.make_hashSet_of_placeables();
		//System.out.println(model.getHashStructures());
		
    	view = new View(theStage, this, model.getHashPlant(), model.getHashStructures());
    	
        
		
		
		//System.out.println(view.bloomtime);
		//System.out.println(view.sunlight);
		//model.setX(view.getIv1().getX());
		//model.setY(view.getIv1().getY());
		//System.out.print(view.getIv1().getX());
		//System.out.print(view.getIv1().getY());
		
		
		
        theStage.show();
    }
    
    
    

    
   

	/**
     * eventhandler for dragging pictures
     * @return eventHandler
     */
    public EventHandler getHandlerForDrag() {	
    	
		return event -> drag((MouseEvent) event, view.get_place((ImageView)event.getSource()));
	}
    /**
     * eventhandler for clicking pictures
     * @return event Handler
     */
    public EventHandler getHandlerForClick() {	
		return event -> click((MouseEvent) event);
	}

    /**
     * eventhandler for deleting images
     * @return eventhandler
     */
	public EventHandler getHandlerForDeleteImage() {
		return event -> delete_img((MouseEvent)event);
	}
	
	/**
	 * click function for moving images
	 * @param event
	 */
    public void click(MouseEvent event) {
    	if (event.getButton() == MouseButton.PRIMARY) {
    		ImageView n = (ImageView)event.getSource();
    		StackPane parent = (StackPane)n.getParent();
    		if(!parent.equals(view.getCenterPane())) {
    			view.add_to_plant_set(n);
    			parent.getChildren().add(view.make_img(n));
    			//System.out.println(n.getTranslateX());
    			view.remove_from_pane(n);
    			view.add_image_to_center(n);
    			System.out.println(n.getLayoutX());
    		}
    	}
    }
   
    /**
     * opens the description window
     * @return eventhandler
     */
    public EventHandler nextClickHome1() {
    	return event -> view.describeWindow();
    }
    /**
     * eventhandler for dragging lines
     * @return eventhandler
     */
    public EventHandler getHandlerForDragLines() {
    	return event -> dragLines((MouseEvent) event);
    }
    
    /**
     * eventhandler for initiating drawing
     * @return eventhandler
     */
    public EventHandler getHandlerForPressLine() {
    	return event -> press((MouseEvent) event);
    }
    


    /**
     * eventhandler for swapping side menus
     * @return
     */
	public EventHandler gethandlerForSwapSide() {
    	return event -> swapSide((ActionEvent) event);
    }
    
	
	/**
	 * swaps the side panes from plants to structures or vice versa
	 * @param event from clicking a button
	 */
    public void swapSide(ActionEvent event) {
    	Button b = (Button)event.getSource();
    	System.out.println(b.getText());
    	view.swapSide((Button)event.getSource());
    	//System.out.println("Hello");
    }

    
    
    
   /**
    * dragging function for placeables, does
    * not let it go off the center pane
    * @param event, placeable
    */
    public void drag(MouseEvent event, Placeables p) {
    	Node n = (Node)event.getSource();
		ImageView image = (ImageView)event.getSource();
		
		
		StackPane parent = (StackPane)image.getParent();
		
		System.out.println("X: " + event.getSceneX()+ " Y: " + event.getSceneY());
            if((event.getSceneY() > 100) &&  (event.getSceneY() < view.centerPane.getHeight()) && (event.getSceneX() < view.centerPane.getWidth()-100) && (event.getSceneX() > 0)){
                model.setX(event.getX());
                model.setY(event.getY());
                //System.out.println("X: " + model.getImageX() + " Y: " + model.getImageY());
                view.setX( model.getImageX() , image);
                view.setY( model.getImageY() , image);
            }
            else {
                model.setX(0);
                model.setY(0);
                //System.out.println("X: " + model.getImageX() + " Y: " + model.getImageY());
                view.setX( model.getImageX() , image);
                view.setY( model.getImageY() , image);
            }
            
        }
    
    /**
     * deletes an image by right clicking
     * @param event
     */
    public void delete_img(MouseEvent event) { 
       if (event.getButton() == MouseButton.SECONDARY) {
    	   ImageView image = (ImageView)event.getSource();
    	   StackPane parent = (StackPane)image.getParent();
    	   if(view.getCenterPane() == parent) {
    		   view.removePlants(image);
    		   view.remove_from_plant_set(image);
    	   }
        }
    }
    

    /**
     * swaps the side menu depending on the current boolean
     * @param swap
     */
    public void swapPane(boolean swap) {
    	if (swap) {
    		//view.setSidePane(view.addStructures());
    		swap = false;
    	}
    	if (!swap) {
    		view.setSidePane(view.addPlants());
    		swap = true;
    	}
    }
    
    
    /**
     * saves the current garden to a file
     * 
     * @param Set set_of_plants				a set of all plants that were used in the garden
     * @param ArrayList saved_plants		each individual plant in the garden
     * @param ArrayList saved_structures	each individual structure in the garden
     * @return void
     */
    public void saveGarden(Set<Plants> set_of_plants, ArrayList<Plants> saved_plants, ArrayList<Placeables> saved_structures) {

    	try {
    		
    	FileChooser fileChooser = new FileChooser();
    	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Serializable files (*.ser)", "*.ser");
    	fileChooser.setInitialDirectory(new File("./gardenSaves"));
    	fileChooser.getExtensionFilters().add(extFilter);
    	File newFile = fileChooser.showSaveDialog(new Stage());
    	
    	FileOutputStream file = new FileOutputStream(newFile);
    	ObjectOutputStream out = new ObjectOutputStream(file);
    	
    	out.writeObject(set_of_plants);
    	out.writeObject(saved_plants);
    	out.writeObject(saved_structures);
    	out.close();
    	file.close();
    	System.out.println("Sucessfully saved!");
    	}
    	catch(Exception e) { System.out.println("An error ocurred\n" + e); }
    }
    
    /**
     * Load a previous garden
     * 
     * @param View view		the view to update the values of
     * @return void
     */
    public void loadInApp(View view) {
    	
    	try {
    		FileChooser fileChooser = new FileChooser();
    		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Serializable files (*.ser)", "*.ser");
    		fileChooser.setInitialDirectory(new File("./gardenSaves"));
    		fileChooser.getExtensionFilters().add(extFilter);
    		File gardenFile = fileChooser.showOpenDialog(new Stage());
    		
			FileInputStream file = new FileInputStream(gardenFile);
			ObjectInputStream in = new ObjectInputStream(file);
			

			System.out.print("Loading File...");
			
			view.set_of_plants_in_garden = (Set<Plants>) (in.readObject()); //set of plants
			view.saved_plants = (ArrayList<Plants>) (in.readObject()); //each individual plant
			view.saved_structures = (ArrayList<Placeables>) (in.readObject()); //each individual structure
			
			in.close();
			file.close();
		}
		catch(Exception e) { System.out.println("An error ocurred, could not load file\n" + e); }
    	
    	//System.out.println("\n\n\n" + view.saved_plants + "\n" + view.saved_structures);
    	System.out.println(view.saved_structures.get(0).getImageAddress());
    	
    	addLoadedItems(view);
    	
    }
    
    /**
     * 
     * @param view
     */
    public void addLoadedItems(View view) {
    	view.centerPane.getChildren().clear();
    	
    	for(Plants p : view.saved_plants) {
    		ImageView load = view.make_img_from_plant(p);
    		
    		double radius;

            if(load.getFitHeight() > load.getFitWidth()) {
            	radius = load.getFitHeight()/2;
            }
            else
            	radius = load.getFitWidth()/2;
            Circle clip = new Circle(50, 50, radius);
            
            load.setClip(clip);
            load.relocate(p.locationX, p.locationY);
      
            view.centerPane.getChildren().add(load);
    	}
    	for(Placeables pl : view.saved_structures) {
    		ImageView load = view.make_img_from_place(pl);
    		
    		double radius;

            if(load.getFitHeight() > load.getFitWidth()) {
            	radius = load.getFitHeight()/2;
            }
            else
            	radius = load.getFitWidth()/2;
            Circle clip = new Circle(50, 50, radius);
            
            load.setClip(clip);
            load.relocate(pl.locationX, pl.locationY);
      
            view.centerPane.getChildren().add(load);
    	}
    }
    
    /**
     * saves current garden as an image
     * 
     * @param the current scene
     * @return void
     */
    public void saveGardenImage(StackPane centerPane) {
    	try {
    		FileChooser fileChooser = new FileChooser();
    		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
    		fileChooser.setInitialDirectory(new File("./gardenSaves"));
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(new Stage());
    		
	    	WritableImage snapshot = centerPane.snapshot(null, null);
	    	BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
	    	ImageIO.write(bufferedImage, "png", file);
    	}
    	catch(Exception e) { System.out.println("An error ocurred\n" + e); }
    }
    
	/**
	 * intiates the drawing funtion if draw == true
	 * @param press mouse event
	 */
	 public void press(MouseEvent press) { 
		 if(view.draw) {
			 //gc.setStroke(pickColor(color));
			 //gc.setLineWidth(lineWidth);
			 view.gc.beginPath();
			 view.gc.lineTo(press.getSceneX(),press.getSceneY()-80);
			 view.gc.stroke();
		 }
	 }
	 
	 /**
	  * creates the lines when drawing if draw == true
	  * @param drag mouse event
	  */
	public void dragLines(MouseEvent drag) {
		// TODO Auto-generated method stub
		if(view.draw) {
			view.gc.lineTo(drag.getSceneX(),drag.getSceneY()-80);
			view.gc.stroke();
		}
	}
	
	
}

