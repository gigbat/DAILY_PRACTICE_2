import java.util.concurrent.atomic.AtomicInteger;

public class Ship extends Thread {
    public AtomicInteger container;

    public Ship(int containers) {
        this.container = new AtomicInteger(containers);
    }

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("Корабль " + Thread.currentThread().getName());
        }
    }
}
