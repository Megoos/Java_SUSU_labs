import java.io.*;
import java.util.Map;

public class MapPS extends PrintStream{
	
    MapPS(OutputStream out) {
        super(out);
    }
    
    public void print(Map<Integer, String> map) {
        super.print(map);
    }
    
    public void println(Map<Integer, String> map) {
    	super.println(map);
    }
}
