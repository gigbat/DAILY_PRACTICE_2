import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Port implements Runnable {
    private AtomicLong capacity;
    public Ship ship;
    public AtomicInteger container;
    public volatile long carryingOfShips = 100_000L;

    public Port(Ship ship) {
        int nContainers = (int) (Math.random() * 10);
        System.out.println(nContainers);
        this.capacity = new AtomicLong(10_000L);
        this.ship = ship;
        this.container = new AtomicInteger(nContainers);
    }

    public synchronized boolean checkCapacity() {
        int squareOfOneContainer = 10;

        if ((container.get() + ship.container.get()) * squareOfOneContainer <= capacity.get()) return true;
        else return false;
    }

    public void addContainerToPortFromShip() {
        synchronized (Ship.class) {
            if (checkCapacity()) {
                System.out.println(this.container.addAndGet(ship.container.get()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else System.out.println("Большой вес.");
        }
    }

    @Override
    public void run() {
        System.out.println("Порт " + Thread.currentThread().getName());
        addContainerToPortFromShip();
    }
}
