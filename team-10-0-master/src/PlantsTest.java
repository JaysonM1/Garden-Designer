import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PlantsTest {

	@Test
	public void testMake_list_of_mods() {
		Plants testPlant = new Plants("ageratum","/FlowersAndStructures/ageratum.jpg","Considerable","Considerable","Spring","Loam");
		
		List<String> testList = new ArrayList<>(Arrays.asList("Considerable","Considerable","Spring","Loam"));
		
		assertEquals(testList, testPlant.make_list_of_mods());
	}

}
