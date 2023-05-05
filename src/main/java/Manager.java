public class Manager extends Worker implements Person{

    public String workId;
    public  String manTask;
    public static Worker[] employees;

    private Machine machine;

    public Manager(String newName, String newWorkId, String newTask, Worker[] newEmployees) {
        name = newName;
        workId = newWorkId;
        manTask = newTask;
        employees = newEmployees;
    }
    public void setMachine(Machine newMachine) {
        machine = newMachine;
        if(machine != null){
            machine.setOwner(this);
        }
    }
}
