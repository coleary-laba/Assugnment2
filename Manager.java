public class Manager extends Worker {

    public String workId;
    private String name;
    private String task;
    public Worker[] employees;

    public Manager(String newName, String newWorkId, String newTask, Worker[] newEmployees) {
        name = newName;
        workId = newWorkId;
        task = newTask;
        employees = newEmployees;
    }

}
