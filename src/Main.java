import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) {
        AtomicLong lock = new AtomicLong(0);
        new Thread(new Chronos(lock, 1000)).start();
        new Thread(new ChronosStrob(lock, 5)).start();
        new Thread(new ChronosStrob(lock, 7)).start();
    }
}
