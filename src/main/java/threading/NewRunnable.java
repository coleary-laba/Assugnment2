package threading;

import main.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewRunnable implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private final String name;

    public NewRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        LOGGER.info("Now running " + name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
    }
}
