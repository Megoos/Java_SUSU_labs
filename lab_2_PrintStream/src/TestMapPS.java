import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TestMapPS {

	Map<Integer, String> testMap;
	OutputStream sup;
		
	@Before
	public void setUpClass() throws Exception {
		sup = new ByteArrayOutputStream();
		testMap = new HashMap<Integer, String>();
	}

	@After
	public void takeDownClass() throws Exception {
		sup.close();
		testMap.clear();
	}

	@Test
	public void testPrint() {
        testMap.put(123, "OneTwoThree \n");
        testMap.put(678, "SixSevenEight \n");
        MapPS myPS = new MapPS(sup);
        myPS.print(testMap);
        assertEquals(testMap.toString(), sup.toString());
	}
	
	@Test
	public void testPrintln() {
		testMap.put(863, "Eight \n" +
				"Six \n" +
				"Three \n");
        testMap.put(489, "Four \n" +
        		"Eight \n" +
        		"Nine \n");
        MapPS myPS = new MapPS(sup);
        myPS.println(testMap);
        assertEquals(testMap.toString() + "\r\n", sup.toString());
	}
	
	@Test
	public void testAppend() {
        MapPS myPS = new MapPS(sup);
        char sign = 'c';
        myPS.append(sign);
        assertEquals(sign + "", sup.toString());
	}

}
