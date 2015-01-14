import java.util.concurrent.SynchronousQueue;

public class WriteStream implements Runnable {

    private SynchronousQueue<String> myQueue;
    private Thread OutStreamThread;

    public WriteStream(SynchronousQueue<String> syncQueue) throws InterruptedException {
        this.myQueue = syncQueue;
        OutStreamThread = new Thread(this, "WriteStream Thread");
        OutStreamThread.start();
    }

    public void run() {

        Thread currentThread = Thread.currentThread();
        String threadName = Thread.currentThread().getName();
        long threadID = Thread.currentThread().getId();
        
        try {
            while (OutStreamThread == currentThread) {
            	System.out.println(threadName + " с ID=" + threadID + " получение данных: " + myQueue.take() + "\n");
            }
        } catch (InterruptedException ie) {}
    }

    public void stop() {
        this.OutStreamThread = null;
    }
}