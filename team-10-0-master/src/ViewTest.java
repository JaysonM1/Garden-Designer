import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


/**
 * man idk
 *
 */
public class ViewTest {
	View view = new View(new Stage(), new Controller(), new HashMap<>(), new HashMap<>());
	
	@Test
	public void testAddFlowPane() {
		FlowPane testPane = new FlowPane();
		assertEquals(testPane, view.addFlowPane());
		
		fail("Not yet implemented");
	}

	@Test
	public void testMake_img() {
		ImageView testImage = new ImageView("./FlowersAndStructures/daisy.jpg");
		testImage.setPreserveRatio(true);
		testImage.setFitHeight(100);
		
		assertEquals(testImage, view.make_img(testImage));
		
		fail("Not yet implemented");
	}

}
