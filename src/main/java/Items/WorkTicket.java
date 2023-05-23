package Items;

import Interfaces.IItem;
import Issues.Task;

public class WorkTicket implements IItem {
    public Task task;
    private String status;
    private int timeSpent;

    public WorkTicket(Task newTaskType, String newStatus, int newTimeSpent) {
        task = newTaskType;
        status = newStatus;
        timeSpent = newTimeSpent;
    }

    public void resolve() {
        status = "solved";
    }

    public void assign() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String newStatus) {
        status = newStatus;
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
}
