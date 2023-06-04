package threading;

import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {

    private ArrayBlockingQueue<Connection> pool;
    private ArrayBlockingQueue<Connection> inUse;
    private int connections = 5;

    public synchronized Connection getConnect() throws InterruptedException {
        if (pool == null) {
            pool = new ArrayBlockingQueue<>(connections);
            for (int i = 0; i < connections; i++) {
                Connection connect = new Connection(i + "connection");
                pool.add(connect);
            }
        }
        Connection current = pool.take();
        if (inUse == null) {
            inUse = new ArrayBlockingQueue<>(connections);
        }
        inUse.add(current);
        return current;
    }

    public void release(Connection connect) {
        inUse.remove(connect);
        pool.add(connect);
    }
}
