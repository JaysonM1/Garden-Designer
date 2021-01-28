/**
 * @author team 10-0 
 */

import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import java.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Slider;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.layout.VBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;

import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

import java.util.HashMap;

public class View  implements java.io.Serializable{
	private Controller imc;
	private ImageView iv1;
	double canvasWidth;
	double canvasHeight;
	String gardenHeight;
	String gardenWidth;
	
	public boolean swap = true;
	
	BorderPane global_border_pane = new BorderPane();
	
	private int popUpWidth = 300;
	private int popUpLength = 250;

	Scene theScene;
	Group currentPane;
	ScrollPane sidePane;
	ScrollPane structurePane;
	ScrollPane plantsPane; 
	public HashMap<String, Plants> hashPlant = new HashMap<>();
	
	public Set<Plants> set_of_plants_in_garden = new HashSet<Plants>(); 

	public HashMap<String, Placeables> structHash = new HashMap<>();
	
	public ArrayList<Plants> saved_plants = new ArrayList<Plants>();
	public ArrayList<Placeables> saved_structures = new ArrayList<Placeables>();

	public File gardenFile;
	
	Stage homeMenu;
	
	//drawing attributes
	Canvas c = new Canvas(canvasWidth, canvasHeight);
	ColorPicker cp = new ColorPicker(Color.BLACK);
	GraphicsContext gc = c.getGraphicsContext2D();

	int lineWidth = 1;
	int prevWidth;
	boolean draw = true;
	//
	
	StackPane centerPane;
	
	public VBox sunlight_buttons;
	public ToggleGroup sunlight_group;
	
	public VBox rainfall_buttons;
	public ToggleGroup rainfall_group;
	
	public VBox season_buttons;
	public ToggleGroup season_group;
	
	public VBox soil_buttons;
	public ToggleGroup soil_group;
	
	public static String sunlight;
	//public static String bloomtime;
	public static String rainfall;
	public String current_season;
	public String soil;
	
	private ArrayList<String> soils = new ArrayList<>(Arrays.asList("Clay", "Loam", "Sand", "Silt"));
	private ArrayList<String> descriptors = new ArrayList<>(Arrays.asList("Little", "Moderate", "Considerable"));
    private ArrayList<String> seasons_list = new ArrayList<>(Arrays.asList("Winter",
    		"Spring", "Summer", "Autumn"));
	
	public ArrayList<String> modifiers = new ArrayList<>(Arrays.asList(sunlight,  rainfall, current_season, soil));
    
	Stage theStage;
	
	/**
	 * Sets up the stage and it's attributes
	 * 
	 * @param theStage		the stage to be edited
	 */

	public View(Stage theStage, Controller controller, HashMap<String, Plants> p, HashMap<String, Placeables> s) {
		this.theStage = theStage;
		imc = controller;
        theStage.setTitle("Garden");
        this.hashPlant = p;
        this.structHash = s;
        
 
		

		//make setter for this
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		canvasWidth = primaryScreenBounds.getWidth()-20;
		canvasHeight = primaryScreenBounds.getHeight()-20;
		this.setSunlight_buttons();
		this.setRainfall_buttons();
		this.setSeason_buttons();
		this.setSoil_buttons();
		
		
		this.season_group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		    		Toggle old_toggle, Toggle new_toggle) {
		    			RadioButton rb = (RadioButton)season_group.getSelectedToggle();
		    			current_season = rb.getText();
		    			System.out.print(current_season);
		    			
		     } 
		});
		this.sunlight_group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		    		Toggle old_toggle, Toggle new_toggle) {
		    			RadioButton rb = (RadioButton)sunlight_group.getSelectedToggle();
		    			sunlight = rb.getText();
		    			System.out.println(sunlight);
		     } 
		});
		this.rainfall_group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		    		Toggle old_toggle, Toggle new_toggle) {
		    			RadioButton rb = (RadioButton)rainfall_group.getSelectedToggle();
		    			rainfall = rb.getText();
		    			System.out.println(rainfall);
		     } 
		});
		this.soil_group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov,
		    	Toggle old_toggle, Toggle new_toggle) {
		    	RadioButton rb = (RadioButton)soil_group.getSelectedToggle();
		    	soil = rb.getText();
		    	System.out.println(soil);

			}
		});
		

		imc = controller;
		this.theStage = theStage;
        theStage.setTitle("Garden");
       

     
        Group root = new Group();
   
        cp = new ColorPicker(Color.BLACK);
        // create a stack pane 
       // StackPane r = new StackPane();
        //iv1 = makeImg();
        
        global_border_pane.setTop(topBar());
        //border.setCenter(iv1);
        structurePane = addStructures();
        plantsPane = addPlants();
        
        sidePane = plantsPane;
        
        this.setCenterPane();
        global_border_pane.setCenter(centerPane);
        global_border_pane.setRight(sidePane);
        this.theScene = new Scene(global_border_pane, canvasWidth, canvasHeight);

        displayStartMenu(); //startup screen
        

        
        global_border_pane.setStyle("-fx-background-color: white");
        theStage.setScene(theScene);
        
	}
	
	/**
	 * fills the hash plant
	 * @param hashPlant - hashmap<String,plants>	
	 */
	public void fillPlantHash(HashMap<String, Plants> hashPlant) {
		/*
		 * key will be the plant name, the value will be the plant object
		 */
		this.hashPlant = hashPlant;
	}
	/**
	 * fills the hash structures
	 * @param structHash- hashmap<string, placeables>
	 */
	public void fillStructHash(HashMap<String, Placeables> structHash) {
		this.structHash = structHash;
	}
	
	/**
	 * returns a flowpane
	 * 
	 * 
	 * @return FlowPane
	 */
	public FlowPane addFlowPane() {
	    //FlowPane flow = new FlowPane();
	    FlowPane flowpane = new FlowPane();
		
	    return flowpane;
	}
	

	/**
	 * creates the top bar of the garden screen
	 * creates/organizes the buttons
	 * @return anchorpane
	 */
	public AnchorPane topBar() {
		AnchorPane anchorpane = new AnchorPane();
		Button plantsButton = new Button("Plants");
		plantsButton.setOnAction(imc.gethandlerForSwapSide());
		Button structuresButton = new Button("Structure");
		structuresButton.setOnAction(imc.gethandlerForSwapSide());
		Button ratingButton = new Button("Rating");
		Button drawingButton = new Button("Drawing");

		//Button fileButton = new Button("File");
		//Button modifiersButton = new Button("Modifiers");
		ratingButton.setOnAction(e -> this.displayRatingsWindow());
		drawingButton.setOnAction(e -> this.displayDrawingWindow());
		

		
		HBox hb = new HBox();
		hb.getChildren().addAll(drawingButton, ratingButton,structuresButton, plantsButton);
		anchorpane.getChildren().addAll(hb);
		anchorpane.setMinSize(300, 100);
		AnchorPane.setRightAnchor(hb, 10.0);
		
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(makeMenuBar());
		anchorpane.getChildren().addAll(hb2);
		anchorpane.setMinSize(300, 100);
		AnchorPane.setLeftAnchor(hb2, 10.0);
		
	
		
		return anchorpane;
		
	}

	/**
	 * creates a pop out window that lets the 
	 * user manipulate drawing features like 
	 * width, color, and erasing
	 */
	public void displayDrawingWindow() {
		
		if(lineWidth > 10) {
			lineWidth = prevWidth;
		}
		Stage popupwindow=new Stage();      
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Drawing ");
		GridPane layout = new GridPane();
		layout.setAlignment(Pos.CENTER);
		
		Button stop = new Button("Stop drawing");
		stop.setOnMousePressed(e -> stopDrawing());
		
		Label stopL = new Label("Stop drawing completely \n click to turn on again");
		Button Erase = new Button("Erase");
		Erase.setOnMousePressed(e -> eraseLine());
	
		
		Label label1= new Label("width adjuster");
		label1.setTextFill(Color.BLUE);
		

		cp.setOnAction(e -> {
			if(!draw)
				draw = !draw;
			gc.setStroke(cp.getValue());
			gc.setLineWidth(lineWidth);
		});
		Label width = new Label("Line Width");
		Slider slider = new Slider(1, 10, lineWidth);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(1);
		slider.setBlockIncrement(1);
		
		slider.valueProperty().addListener( 
	             new ChangeListener<Number>() { 
	  
	            public void changed(ObservableValue <? extends Number >  
	                      observable, Number oldValue, Number newValue) 
	            { 
	            	gc.setLineWidth(newValue.intValue());
	                lineWidth= newValue.intValue();
	            } 
	        }); 

		layout.addRow(0, Erase);
		layout.addRow(1,width, slider);
		layout.addRow(2,stopL, stop);
		layout.addRow(3, cp);
		Scene scene1= new Scene(layout, popUpWidth, popUpLength);
		      
		popupwindow.setScene(scene1);
		      
		popupwindow.showAndWait();
		      

	}
	

	
	/**
	 * changes boolean draw to its opposite
	 */
	public void stopDrawing() {
		draw = false;
	}
	/**
	 * helper function to set up the erasing feature
	 * on the canvas
	 * 
	 */
	public void eraseLine() {
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(100);
	}
	/**
	 * creates a sliderpane with the string and double values
	 * @param String name, double value
	 * @return StackPane
	 */
	public static VBox addSliderPane(String name, double value) {
		VBox root = new VBox();  
		Label label1= new Label(name);
		label1.setTextFill(Color.web("#ff2e1f"));
		
		Slider slider = new Slider(0, 1, value);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(0.25f);
		slider.setBlockIncrement(0.1f);
		//need to add update modifier for slider
		 root.getChildren().addAll(label1,slider); 
		 return root;
	}
	
	
	/**
	 * returns an imageview with a specified image
	 * @return ImageView
	 */
	public ImageView make_img_from_plant(Plants plant) {
		ImageView imageview = new ImageView();

		Image im1 = new Image(plant.getImageAddress());
    	//Image im1 = plant.getImage().getImage();
		imageview.setImage(im1);
		imageview.setPreserveRatio(true);
		imageview.setFitHeight(100);
		imageview.setPickOnBounds(true);
		//imageview.setOnMouseClicked(imc.getHandlerForClick());
		imageview.setOnMouseDragged(imc.getHandlerForDrag());
		
		imageview.setOnMouseReleased(imc.getHandlerForClick());
		imageview.setOnMousePressed(imc.getHandlerForDeleteImage());
		Tooltip prop = new Tooltip("Sunglight: " + plant.getSunlightNeeded() +"\n" +
									"Rainfaill: " + plant.getRainfallNeeded() + "\n" +
									"Soil type: " + plant.getSoilType());
		
		Tooltip.install(imageview, prop);
		return imageview;
	}
	
	/**
	 * makes an img from a placeable 
	 * @param plant 
	 * @return Imageview
	 */
	public ImageView make_img_from_place(Placeables plant) {
		ImageView imageview = new ImageView();
		System.out.print(plant.getImageAddress());
		Image im1 = new Image(plant.getImageAddress());
    	//Image im1 = plant.getImage().getImage();
		imageview.setImage(im1);
		imageview.setPreserveRatio(true);
		imageview.setFitHeight(100);
		//imageview.setOnMouseClicked(imc.getHandlerForClick());
		imageview.setOnMouseDragged(imc.getHandlerForDrag());
		imageview.setOnMouseReleased(imc.getHandlerForClick());
		imageview.setOnMousePressed(imc.getHandlerForDeleteImage());
		//Tooltip prop = new Tooltip(plant.getSunlightNeeded());
		//imageview.setPickOnBounds(true);
		//Tooltip.install(imageview, prop);
		return imageview;
	}
	
	/**
	 * makes an imageview and sets the mouse actions
	 * @param image
	 * @return imageview
	 */
	public ImageView make_img(ImageView image) {
		ImageView imageview = new ImageView();
		//Image im1 = new Image(getClass().getResourceAsStream("/FlowersAndStructures/daisy.jpg"));
    	
		imageview.setImage(image.getImage());
		imageview.setPreserveRatio(true);
		imageview.setFitHeight(100);
		//imageview.setOnMouseClicked(imc.getHandlerForClick());
		imageview.setOnMouseDragged(imc.getHandlerForDrag());
		imageview.setOnMouseReleased(imc.getHandlerForClick());
		
		imageview.setOnMousePressed(imc.getHandlerForDeleteImage());
		return imageview;
	}
	
	
	/**
	 * returns a VBox with images for the side pane
	 * @return VBox
	 */
	public ScrollPane addPlants() {
		VBox stack = new VBox();
	//	System.out.println("add plants loop1");
//		System.out.println(hashPlant);
		stack.setSpacing(5);
		
		for(Map.Entry mapElement : hashPlant.entrySet()) {
			StackPane sp = new StackPane();
			Plants value = (Plants)mapElement.getValue();
			Label label1 = new Label();
			label1.setText(value.getName());
			label1.setTextFill(Color.valueOf("black"));
			
	
			ImageView newImage = make_img_from_plant(value);
			//System.out.println(value.getName());
			//System.out.println(value.getImage());
			sp.getChildren().add(newImage);
			//System.out.println("here");
			//sp.setOnMouseReleased(imc.getHandlerForClick());
			stack.getChildren().addAll(label1, sp);
		}
		ScrollPane sideGroup = new ScrollPane(stack);
		return sideGroup;
	}
	
	
	/**
	 * add structures to scrollpane
	 * @return scrollpane
	 */
	public ScrollPane addStructures() {
		VBox stack = new VBox();
		stack.setSpacing(5);
		
		for(Map.Entry mapElement : imc.model.hash_of_placeables.entrySet()) {
			StackPane sp = new StackPane();
			Placeables value = (Placeables)mapElement.getValue();
			Label label1 = new Label();
			label1.setText(value.getName());
			label1.setTextFill(Color.valueOf("black"));
			
			ImageView newImage = make_img_from_place(value);
			sp.getChildren().add(newImage);
			stack.getChildren().addAll(label1, sp);
		}
		ScrollPane sideGroup = new ScrollPane(stack);
		return sideGroup;
	}
	
	/**
	 * creates the season menu for the user
	 * organizes the 4 seasons buttons
	 * @param null
	 * @return vbox
	 */
	public static VBox makeSeasonMenuItem() {
		VBox vbox = new VBox();
		HBox hbox = new HBox();
		Label vboxLabel = new Label("Set season");
		Button winter = new Button("Winter");
		Button spring = new Button("Spring");
		Button autumn = new Button("Autumn");
		Button summer = new Button("Summer");
		winter.setOnAction(new EventHandler<ActionEvent>() {  
            
            @Override  
            public void handle(ActionEvent arg0) {    
                System.out.println("Button clicked");  
                  
            }  
        } ); 
		hbox.getChildren().addAll(winter,spring,autumn, summer);
		vbox.getChildren().addAll(vboxLabel, hbox);
		
		return vbox;
	}
	
	
	/**
	 * creates the menu bar for the file button
	 * @return menubar
	 */
	public MenuBar makeMenuBar() {
		MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        
        MenuItem loadFile = new MenuItem("Load");
        MenuItem saveFile = new MenuItem("Save");
        MenuItem saveImage = new MenuItem("Save Image");
        MenuItem exitApp = new MenuItem("Exit");
        
        fileMenu.getItems().add(loadFile);
        fileMenu.getItems().add(saveFile);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(saveImage);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(exitApp);
        
        loadFile.setOnAction(e->imc.loadInApp(this));
        saveFile.setOnAction(e->imc.saveGarden(set_of_plants_in_garden, saved_plants, saved_structures));
        
        saveImage.setOnAction(e->imc.saveGardenImage(centerPane));
        exitApp.setOnAction(e->theStage.close());
        
        //.setOnAction(e->theScene.close());
        
        Menu modifiersMenu = new Menu("Modifiers");
        //add sunlight radioButton box

        CustomMenuItem customMenuItem1 = new CustomMenuItem();
        customMenuItem1.setContent(this.sunlight_buttons);
        customMenuItem1.setHideOnClick(false);
        modifiersMenu.getItems().add(customMenuItem1);
        //add rainfall slider
        
        CustomMenuItem customMenuItem2 = new CustomMenuItem();
        customMenuItem2.setContent(this.rainfall_buttons);
        customMenuItem2.setHideOnClick(false);
        modifiersMenu.getItems().add(customMenuItem2);
        
        //add bloomtime slider
        CustomMenuItem customMenuItem3 = new CustomMenuItem();
        customMenuItem3.setContent(this.season_buttons);
        customMenuItem3.setHideOnClick(false);
        modifiersMenu.getItems().add(customMenuItem3);
       
        //soil options
        CustomMenuItem customMenuItem4 = new CustomMenuItem();
        customMenuItem4.setContent(this.soil_buttons);
        customMenuItem4.setHideOnClick(false);
        modifiersMenu.getItems().add(customMenuItem4);
        
        menuBar.getMenus().addAll(fileMenu, modifiersMenu);

        
        return menuBar;
	}
	
	
	/**
	 * makes a radio button based on a list of strings and a string
	 * @param names list of strings
	 * @param title string
	 * @return List
	 */
	public List make_radio_button(List<String> names, String title) {
		VBox vbox = new VBox(3);
		ToggleGroup group = new ToggleGroup();
		HBox hbox = new HBox(5);
		for (int i =0; i < names.size(); i++) {
			RadioButton button = new RadioButton();
			button.setText(names.get(i));
			button.setTextFill(Color.valueOf("black"));
			button.setToggleGroup(group);
			hbox.getChildren().add(button);
			//button.setOnMousePressed(imc.getHanderForToggle());
		}
		Label label1 = new Label();
		label1.setText(title);
		label1.setTextFill(Color.valueOf("black"));
		vbox.getChildren().addAll(label1, hbox);
		List<Object> objects = new ArrayList<>();
		objects.add(vbox);
		objects.add(group);
		
		
		return objects;
	}
	
	/**
	 * creates the start menu 
	 * for the beginning of the program
	 * @throws IOException 
	 * 
	 */
	public void displayStartMenu() {
		homeMenu = new Stage();
		homeMenu.initModality(Modality.APPLICATION_MODAL);
		homeMenu.setTitle("Home Menu");
		
		GridPane layout = new GridPane();
		layout.setAlignment(Pos.CENTER);
		Scene scene1= new Scene(layout, 400, 400);
		
		Label titleLabel = new Label("Garden Grower: Setup Screen");
		
		Button toDescribePage = new Button("Continue >");
		FileChooser file = new FileChooser();
		Button loadFile = new Button("Load other garden");
		
		
		Button next = new Button("Next");
		
		layout.addRow(0, titleLabel);
		layout.addRow(3, this.sunlight_buttons);
		layout.addRow(4, this.rainfall_buttons);
		layout.addRow(5, this.season_buttons);
		layout.addRow(6, this.soil_buttons);
		layout.addRow(7, toDescribePage);
		homeMenu.setScene(scene1);
		
	
		toDescribePage.setOnAction(e->{
			homeMenu.close();
		describeWindow();
		});
		
		homeMenu.showAndWait();
		
	}
	
	private static final String WORDS = 
	"Click and drag on the center to draw \n" +
	"click and drag the pictures on the left to place images\n" +
	"use the buttons on the top bar to change modifiers,\n" + 
	"save, check the quality of your garden, and changes the images between\n" +
	"plants and structures\n" + 
	"Have fun!";
	/**
	 * creates a description window
	 */
	public void describeWindow() {
		Stage dWindow= new Stage();
		//Scene scene1= new Scene(layout, 800, 800);
		Label label = new Label(WORDS);
	    label.setWrapText(true);
	   
	    StackPane layout = new StackPane();
	    Scene scene1= new Scene(layout, 400, 400);
	    layout.getChildren().setAll(label);
	    dWindow.setScene(scene1);
	    dWindow.showAndWait();
	}
	
	/**
	 * creates a popup window 
	 * for the ratings button
	 * 
	 */
	public void displayRatingsWindow()
	{
		Stage popupwindow=new Stage();      
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("rating ");
		
		VBox layout= get_rating();
		     
		//layout.getChildren().add(new Label(imc.calculateRating(modifiers)));   
		
		//layout.getChildren().addAll(label1, button1);
		      
		layout.setAlignment(Pos.CENTER);
		      
		Scene scene1= new Scene(layout,popUpWidth, popUpLength);
		      
		popupwindow.setScene(scene1);
		      
		popupwindow.showAndWait();
		      

	}
	/**
	 * sets the x location of an imageview
	 * @param x double
	 * @param iv imageview
	 */
	public void setX(double x, ImageView iv) {
    	iv.setTranslateX(iv.getTranslateX() + x);

    }
	/**
	 * sets the y location of an imageview
	 * @param y double
	 * @param iv imageview
	 */
    public void setY(double y, ImageView iv) {
    	iv.setTranslateY(iv.getTranslateY() + y);

    }
    
    /**
     * returns an imageview
     * @return imageview
     */
	public ImageView getIv1() {
		return iv1;
	}
	
	/**
	 * sets group 
	 * @param object
	 */
	public void setGroup(Group object) {
		this.currentPane = object;
	}
	/**
	 * returns the scrollpane, of the side
	 * @return scrollpane
	 */
	public ScrollPane getSidePane() {
		return sidePane;
	}

	
	/**
	 * sets the scrollpane for the side
	 * @param sidePane - scrollpane
	 */
	public void setSidePane(ScrollPane sidePane) {
		this.sidePane = sidePane;
	}
	
	
	/**
	 * makes the garden border with a rectangle shape
	 * @return Rectangle
	 */
	public Rectangle makeGardenBorder(){
		Rectangle rect=new Rectangle(); 
		rect.setWidth(100);  
		rect.setHeight(100);  
		return rect;
	}
	
	/**
	 * takes in a canvas and adds it to the pane 
	 * which is returned.
	 * @param c
	 * @return Pane
	 */
	public Pane setCanvas(Canvas c) {
		
		Pane pane = new Pane();
		pane.getChildren().addAll(c);
		pane.setPrefSize(canvasWidth,canvasHeight);
		pane.setBorder(new Border(new BorderStroke(Color.BLACK, 
	           BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		gc = c.getGraphicsContext2D();
		pane.setOnMousePressed(imc.getHandlerForPressLine());
		pane.setOnMouseDragged(imc.getHandlerForDragLines());
		
		return pane;
	}
	
	


	/**
	 * removes a specified imageview from the parent
	 * @param target - imageview
	 */
	public void remove_from_pane(ImageView target) {
		StackPane parent = (StackPane)target.getParent();
		parent.getChildren().remove(target);
		
		
	}
	
	/**
	 * removes a plant object from the pane 
	 * @param target - imageview
	 */
	public void removePlants(ImageView target) {
		StackPane parent = (StackPane)target.getParent();
		System.out.println(target.getLayoutX());
		System.out.println(target.getLayoutY());
		parent.getChildren().remove(target);
		if(sidePane.equals(plantsPane)) {
			Plants p = this.find_saved_plant(target.getLayoutX(), target.getLayoutY());
			
			this.saved_plants.remove(p);
			System.out.println("removed");
		}
		else if(sidePane.equals(structurePane)) {
			 Placeables pla = this.find_saved_structure(target.getLayoutX(), target.getLayoutY());
				
			 this.saved_structures.remove(pla);
		 }
		else {
			
		}
		
		//saved_plants.remove(p);
		System.out.println(saved_plants);
	}
	
	/**
	 * sets the center pane with a canvas
	 * 
	 */
	public void setCenterPane() {
		this.centerPane = new StackPane();
		this.centerPane.setAlignment(Pos.CENTER_RIGHT);
		Canvas canvas = new Canvas(canvasWidth, canvasHeight);
		this.centerPane.getChildren().add(setCanvas(canvas));
	}
	/**
	 * adds an imageview to the centerpane
	 * @param newImage - imageView
	 */
	public void add_image_to_center(ImageView newImage) {
        //Bounds boundsInScene = newImage.localToScene(newImage.getBoundsInLocal());
		double radius;

        if(newImage.getFitHeight() > newImage.getFitWidth()) {
        	radius = newImage.getFitHeight()/2;
        }
        else
        	radius = newImage.getFitWidth()/2;
        Circle clip = new Circle(50, 50, radius);
        
        newImage.setClip(clip);
        
  
        centerPane.getChildren().add(newImage);
        
     //   System.out.println(newImage.getLayoutX());
        if(sidePane.equals(plantsPane)) {
        	Plants p = find_plant(newImage);
            Plants newPlant = new Plants(p, 1008.0, 230.0);
    		this.saved_plants.add(newPlant);
    		System.out.println(saved_plants);
        }
        else if(sidePane.equals(structurePane)) {
        	Placeables pl = find_structure(newImage);
        	Placeables newStruc = new Placeables(pl, 1008.0, 230.0);
    		this.saved_structures.add(newStruc);
    		System.out.println(saved_structures);
        }
	}
	
	/**
	 * adds an image to a list of plants to save
	 * @param newImage
	 */
	public void add_image_to_saved(ImageView newImage) {
        Plants p = find_plant(newImage);
        Plants newPlant = new Plants(p, 1008.0, 230.0);
		System.out.println(newPlant);
		this.saved_plants.add(newPlant);
		//System.out.println(this.saved_plants);
	}
	/**
	 * returns a stackpane as the centerpane
	 * @return
	 */
	public StackPane getCenterPane() {
		return this.centerPane;
	}
	

	/**
	 * returns a vbox for the sunlight buttons
	 * @return vbox
	 */
	public VBox getSunlight_buttons() {
		return sunlight_buttons;
	}

	/**
	 * sets the sunlight buttons
	 */
	public void setSunlight_buttons() {
		List<Object> currentList = this.make_radio_button(this.descriptors, "Set Sunlight: ");
		this.sunlight_buttons = (VBox)currentList.get(0);
		this.sunlight_group = (ToggleGroup)currentList.get(1);
	}
	/**
	 * gets a vbox for the rainfall buttons
	 * @return
	 */
	public VBox getRainfall_buttons() {
		return rainfall_buttons;
	}
	/**
	 * sets the rainfall buttons
	 */
	public void setRainfall_buttons() {
		List<Object> currentList = this.make_radio_button(this.descriptors, "Set Rainfall: ");
		this.rainfall_buttons = (VBox)currentList.get(0);
		this.rainfall_group = (ToggleGroup)currentList.get(1);
	}
	/**
	 * gets the vbox for the season buttons
	 * @return
	 */
	public VBox getSeason_buttons() {
		return season_buttons;
	}
 
	/**
	 * sets the season buttons
	 */
	public void setSeason_buttons() {
		List<Object> currentList = this.make_radio_button(this.seasons_list, "Set Season: ");
		this.season_buttons = (VBox)currentList.get(0);
		this.season_group= (ToggleGroup)currentList.get(1);
	}
	
	/**
	 * gets the vbox for the soil buttons
	 * @return
	 */
	public VBox getSoil_buttons() {
		return soil_buttons;
	}
	
	/**
	 * sets the soil buttons
	 */
	public void setSoil_buttons() {
		List<Object> currentList = this.make_radio_button(this.soils, "Set Soil Type: ");
		this.soil_buttons = (VBox)currentList.get(0);
		this.soil_group = (ToggleGroup)currentList.get(1);
	}
	
	
	/**
	 * gets the toggle group for the seasons
	 * @return
	 */
	public ToggleGroup getSeasonGroup() {
		return this.season_group;
	}
	
	
	/**
	 * sets the current season
	 * @param set - string
	 */
	public void setSeason(String set) {
		this.current_season = set;
	}
	
	/**
	 * gets the current season
	 * @return string
	 */
	public String getSeason() {
		return this.current_season;
	}
		
	/**
	 * swaps the current side pane depending on the current boolean
	 * @param source - button
	 */
	public void swapSide(Button source) {
		if(sidePane.equals(plantsPane) && source.getText().equalsIgnoreCase("Structure")) {
			this.sidePane = structurePane;
			this.global_border_pane.setRight(sidePane);
			this.swap = false;
		}
		else if(sidePane.equals(this.structurePane) && source.getText().equals("Plants")) {
			System.out.println("got here");
			this.sidePane = plantsPane;
			this.global_border_pane.setRight(sidePane);
		}
		else {
			
		}
	}
	/**
	 * looks for the plant object with the same image address
	 * @param given_image - imageview
	 * @return Plants
	 */
	@SuppressWarnings("deprecation")
	public Plants find_plant(ImageView given_image) {
		for(Map.Entry mapElement : hashPlant.entrySet()) {
			Plants value = (Plants)mapElement.getValue();
			//value.getImage().getImage()
			//System.out.println(given_image.getImage().getUrl());
			
			if((given_image.getImage()).impl_getUrl().contains(value.getImageAddress())) {
				System.out.println("Found " + value.getName());
				return value;
			}
		}
		Plants dead = new Plants("deadplant");
		return dead;
	}
	
	/**
	 * finds a structure based on an imageview
	 * @param given_image - Imageview
	 * @return Placeables
	 */
	@SuppressWarnings("deprecation")
	public Placeables find_structure(ImageView given_image) {
		for(Map.Entry mapElement : structHash.entrySet()) {
			Placeables value = (Placeables)mapElement.getValue();
			//value.getImage().getImage()
			System.out.println(structHash);
			System.out.println(given_image.getImage().impl_getUrl());
			/*if(given_image.getImage().getUrl().contains(value.getImageAddress())) {
				System.out.println("Found " + value.getName());
				return value;
			}*/
		}
		Placeables dead = new Placeables("dead");
		return dead;
	}
	
	
	/**
	 * adds plant to set 
	 * @param given - Imageview
	 */
	public void add_to_plant_set(ImageView given) {
		Plants p;
		if(this.sidePane.equals(plantsPane)) {
			p = find_plant(given);
			//set_of_plants_in_garden.add(p);
			System.out.println(set_of_plants_in_garden);
			
		}
	}
	
	/**
	 * removes a plant from the set
	 * @param given - imageview 
	 */
	public void remove_from_plant_set(ImageView given) {
		if(this.sidePane.equals(plantsPane)) {
			set_of_plants_in_garden.add(find_plant(given));
		//	System.out.println(set_of_plants_in_garden);
		}
		set_of_plants_in_garden.remove(find_plant(given));
	}
	
	/**
	 * returns vbox for rating feature
	 * @return vbox
	 */
	public VBox get_rating() {
		VBox rb = new VBox();
		
		ArrayList<Plants> outliers = new ArrayList<>();
		for(Plants p : set_of_plants_in_garden) {
			if(is_plant_not_compatible(p)) {
				outliers.add(p);
			}
		}
		
		if (outliers.size() == 0) {
			Label label1= new Label("Great work you garden is compatible");
			label1.setTextFill(Color.web("#ff2e1f"));
			rb.getChildren().add(label1);
		}
		else {
			for(Plants p : outliers) {
				Label label1= new Label(p.getName() + " is not compatible");
				label1.setTextFill(Color.web("#ff2e1f"));
				rb.getChildren().add(label1);
			}
		}
		return rb;
	}
	

	/**
	 * checks if the current plants are com
	 * @param plant
	 * @return boolean
	 */
	
	public boolean is_plant_not_compatible(Plants plant) {
		modifiers.clear();
		modifiers.addAll(Arrays.asList(sunlight, rainfall, current_season, soil));
		List<String> plant_mods = plant.make_list_of_mods();
		System.out.println(plant_mods);
		System.out.println(modifiers);
		for (int i = 0; i < 4; i++) {
			String s1 = plant_mods.get(i);
			String s2 = this.modifiers.get(i);
			if (!s1.equals(s2)) {
				return true;
			}
		}

		return false;
	}
	
	
	/** 
	 * finds a saved plant based on x and y locations
	 * @param x - double
	 * @param y - double
	 * @return Plants
	 */
	public Plants find_saved_plant(double x, double y) {
		System.out.println("saved");
		System.out.println(saved_plants);
	
		for(Plants plant: this.saved_plants) {
			if(plant.getLocationX() == x && plant.getLocationY() == y) {
				System.out.println("foudn saved");
				return plant;
			}
		}
		System.out.println("asdljfladsj");
		return null;
	}
	
	/**
	 * finds a saved structure based on x and y locations
	 * @param x - double
	 * @param y - double
	 * @return Placeables
	 */
	public Placeables find_saved_structure(double x, double y) {
		System.out.println("saved");
	
		for(Placeables struc: this.saved_structures) {
			if(struc.getLocationX() == x && struc.getLocationY() == y) {
				System.out.println("foudn saved");
				return struc;
			}
		}
		System.out.println("asdljfladsj");
		return null;
	}
	
	/**
	 * gets the plant or structures based on an imageview
	 * @param image - image view
	 * @return - placeable
	 */
	public Placeables get_place(ImageView image) {
    	Placeables p = new Placeables();
		if(sidePane.equals(plantsPane)) {
			p = find_saved_plant(image.getLayoutX(), image.getLayoutY());
		}
		if(sidePane.equals(structurePane)) {
			p =find_saved_structure(image.getLayoutX(), image.getLayoutY());
		}
		
		
		
		return p;
	}



}



