import java.util.Map;
import java.util.HashMap;

public class main {

	public static void main(String[] args) {
        Map<Integer, String> userMap = new HashMap<Integer, String>();
        userMap.put(123, "OneTwoThree \n");
        userMap.put(456, "FourFiveSix \n");
        userMap.put(789, "SevenEightNine \n");
        userMap.put(159, "OneFiveNine \n");
        userMap.put(753, "SevenFiveThree \n");
        userMap.put(321, "ThreeTwoOne \n");
        MapPS userPS = new MapPS(System.out);
        userPS.print(userMap);
        userPS.close();
	}

}
