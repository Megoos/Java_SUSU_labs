
import java.util.concurrent.SynchronousQueue;
import java.io.UnsupportedEncodingException;

public class Main {
	public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        
		SynchronousQueue<String> SyncQueue = new SynchronousQueue<String>();
		
		String [] infa = {"123456789", "chainik13", "samovar62"};
        String [] key = {"ajhfga564", "qwerty123abc", "sdkgh1215kjsd"};
        int numberOfDataToCrypt;

        if (infa.length == key.length) {
        	numberOfDataToCrypt = infa.length;
        } else {
        	throw new IllegalArgumentException("Несоответствие количества ключей и данных");
        }
        
        for (int i = 0; i < numberOfDataToCrypt; i++) {
            ReadStream ReadStream = new ReadStream(SyncQueue, infa[i], key[i]);
            WriteStream WriteStream = new WriteStream(SyncQueue);
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
            	
            }
            
            ReadStream.stop();
            WriteStream.stop();
        }
 
    }
}