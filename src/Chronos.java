import java.util.concurrent.atomic.AtomicLong;

public class Chronos implements Runnable {
    private final AtomicLong lockCounter;
    private int period;

    public Chronos(AtomicLong lockCounter, int period) {
        this.lockCounter = lockCounter;
        this.period = period;
    }

    @Override
    public void run() {
        long cm = System.currentTimeMillis();
        while (true){
            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

            synchronized (lockCounter) {
                long l = lockCounter.incrementAndGet();
                lockCounter.notifyAll();
                System.out.println("sec 1: " + l + " : " + (System.currentTimeMillis() - cm));
            }
        }
    }
}
