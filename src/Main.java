public class Main {

    public static void main(String[] args) {
        Object lock = new Object();
        new Thread(new Chronos(lock, 1000)).start();
        new Thread(new ChronosStrob(lock, 5)).start();
        new Thread(new ChronosStrob(lock, 7)).start();
    }
}
