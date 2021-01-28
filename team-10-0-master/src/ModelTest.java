import static org.junit.Assert.fail;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModelTest {
	Model model = new Model();
	@Test
	public void testRead_csv_plants() throws IOException {
		ArrayList<String> testList = new ArrayList<>(Arrays.asList("ageratum","/FlowersAndStructures/ageratum.jpg","Considerable","Considerable","Spring","Loam"));
		List<List<String>> outputList = model.read_csv_plants();
		assertEquals(testList, outputList.get(0));
	}

	@Test
	public void testRead_csv_structures() throws IOException {
		ArrayList<String> testList = new ArrayList<>(Arrays.asList("gazebo","/FlowersAndStructures/gazebo.jpg"));
		List<List<String>> outputList = model.read_csv_structures();
		assertEquals(testList, outputList.get(0));
	}

	/*
	 * We realized we cannot test Plants and Placeables 
	@Test
	public void testCreate_list_of_plants() throws IOException {
		
		ArrayList<Plants> testList = new ArrayList<>();
		testList.add(new Plants("ageratum","/FlowersAndStructures/ageratum.jpg","Considerable","Considerable","Spring","Loam"));
		model.read_csv_plants();
		List<Plants> outputList = model.create_list_of_plants();
		
		assertEquals(testList, outputList.get(0));
	}

	@Test
	public void testMake_hashSet_of_plants() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreate_list_of_placeables() {
		fail("Not yet implemented");
	}

	@Test
	public void testMake_hashSet_of_placeables() {
		fail("Not yet implemented");
	}
	*/
}
