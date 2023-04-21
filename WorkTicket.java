public class WorkTicket {
    public Task taskType;
    private String status;
    private int timeSpent;

    public WorkTicket(Task newTaskType, String newStatus, int newTimeSpent) {
        taskType = newTaskType;
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
}
