public class ChronosStrob implements Runnable {
    private final Object lock;
    private final int strob;
    private int counter = 0;

    public ChronosStrob(Object lock, int strob) {
        this.lock = lock;
        this.strob = strob;
    }

    @Override
    public void run() {
        long cm = System.currentTimeMillis();

        while (true) {
            synchronized (lock) {
                try {
                    lock.wait();
                    ++counter;
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }

            if ((counter % strob) == 0) {
                System.out.println("sec " + strob + ": " + (System.currentTimeMillis() - cm));
            }
        }
    }
}
