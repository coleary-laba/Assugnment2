public class CustomerService extends Worker{
    public String workId;
    private String name;
    private String task;

    public CustomerService(String newName, String newWorkId, String newTask) {
        name = newName;
        workId = newWorkId;
        task = newTask;
    }

}
