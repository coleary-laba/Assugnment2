package items;

import interfaces.IItem;
import issues.Task;

public class WorkTicket implements IItem {


    private Task task;

    public enum Status {
        SOLVED, UNSOLVED;
    }

    private int timeSpent;
    private Status stat;

    public WorkTicket(Task newTaskType, int newTimeSpent) {
        task = newTaskType;
        stat = Status.UNSOLVED;
        timeSpent = newTimeSpent;
    }

    public void resolve() {
        stat = Status.SOLVED;
    }

    public void assign() {

    }

    public Status getStatus() {
        return stat;
    }

    public void setStatus(Status newStatus) {
        stat = newStatus;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int newTimeSpent) {
        timeSpent = newTimeSpent;
    }

    @Override
    public void printItem() {
        System.out.println("brr brr *Printing noises*");
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}