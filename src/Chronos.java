public class Chronos implements Runnable {
    private final Object lock;
    private int period;

    public Chronos(Object lock, int period) {
        this.lock = lock;
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

            synchronized (lock) {
                lock.notifyAll();
                System.out.println("sec 1: " + (System.currentTimeMillis() - cm));
            }
        }
    }
}
