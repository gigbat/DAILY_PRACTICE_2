public class Main {
    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            Ship threadShip = new Ship(10);
            Thread threadPort = new Thread(new Port(threadShip));
            threadPort.setDaemon(true);

            threadShip.start();
            threadPort.start();
        }
    }
}
