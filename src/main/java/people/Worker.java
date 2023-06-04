package people;

import interfaces.IPerson;
import issues.Task;
import items.Machine;

public class Worker implements IPerson {

    private String workId;
    protected String name;
    private Task task;
    protected Machine machine;

    public Worker(String newName, String newWorkId, Task newTask) {
        name = newName;
        workId = newWorkId;
        task = newTask;
    }

    public Worker() {
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task newTask) {
        task = newTask;
    }

    @Override
    public void setMachine(Machine newMachine) {
        machine = newMachine;
    }
}
