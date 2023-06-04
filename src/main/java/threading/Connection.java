package threading;

public class Connection {

    private final String name;

    public Connection(String newName) {
        name = newName;
    }

    public String begin() {
        return ("Thread " + name + " begun");
    }

    public String finish() {
        return ("Thread " + name + " finished");
    }

    public String getName() {
        return name;
    }
}
