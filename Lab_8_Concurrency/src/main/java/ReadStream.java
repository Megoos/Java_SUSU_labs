import java.util.concurrent.SynchronousQueue;
import java.io.UnsupportedEncodingException;

public class ReadStream implements Runnable {
    
	private SynchronousQueue<String> myQueue;
    private Thread ReadStreamThread;
    private String infaCrypt = "";
    private String keyForInfa = "";
    private String infaEncrypted = "";

    public ReadStream(SynchronousQueue<String> syncQueue, String infa, String keyForInfa) throws InterruptedException, UnsupportedEncodingException {
        
    	this.myQueue = syncQueue;
        this.infaCrypt = infa;
        this.keyForInfa = keyForInfa;
        this.infaEncrypted = encrypt();
        
        ReadStreamThread = new Thread(this, "ReadStream Thread");
        ReadStreamThread.start();
    }

    private String encrypt() throws UnsupportedEncodingException {
        
        byte[] arr = this.infaCrypt.getBytes();
        byte[] keyarr = this.keyForInfa.getBytes();
        byte[] result = new byte[arr.length];
        
        for(int i = 0; i<arr.length; i++)
        {
            result[i] = (byte) (arr[i] ^ keyarr[i % keyarr.length]);
        }
        
        return this.infaEncrypted = new String(result, "UTF-8");
    }

    public void run() {
        
    	String threadName = Thread.currentThread().getName();
        long threadID = Thread.currentThread().getId();
        System.out.println(threadName + " с ID=" + threadID + " шифрование данных");
        
        while (infaEncrypted == null) {}
        try {
        	myQueue.put(this.infaEncrypted);
        	infaEncrypted = null;
        } catch (InterruptedException ie) {}
          catch (IllegalMonitorStateException ex) {}
    }

    public void stop() {
        this.ReadStreamThread = null;
    }
}