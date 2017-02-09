import java.util.concurrent.atomic.AtomicLong;

/**
 * Потребитель хронометрии.
 */
public class ChronosStrob implements Runnable {
    private final AtomicLong lockCounter;
    private final int strob;

    /**
     * Создаёт потребителя хронометрических сигналов.
     *
     * @param lockCounter счетчик/хронометр используемый для тактирования.
     * @param strob       с каким периодом выполнять действие.
     */
    public ChronosStrob(AtomicLong lockCounter, int strob) {
        this.lockCounter = lockCounter;
        this.strob = strob;
    }

    @Override
    public void run() {
        long cm = System.currentTimeMillis();

        while (true) {
            long counter;
            synchronized (lockCounter) {
                try {
                    lockCounter.wait();
                    counter = lockCounter.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }

            if ((counter % strob) == 0) {
                System.out.println("sec " + strob + ": " + counter + " : " +
                        (System.currentTimeMillis() - cm));
            }
        }
    }
}
