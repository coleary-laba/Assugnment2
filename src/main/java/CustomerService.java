public class CustomerService extends Worker implements Person{
    public String workId;
    private String name;
    private String task;


    public CustomerService(String newName, String newWorkId, String newTask) {
        name = newName;
        workId = newWorkId;
        task = newTask;
    }

    @Override
    public void setMachine(Machine newMachine) {
        machine = newMachine;
        if(machine != null){
            machine.setOwner(this);
        }
    }
}
