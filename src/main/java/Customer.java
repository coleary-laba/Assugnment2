public class Customer implements Person{

    public String name;

    public String email;
    public String phone;
    private Machine machine;
    private int funds;
    private int bill;
    public enum Reaction{
        HAPPY("I am very happy with my service", 0.4),
        IRATE("I am absolutely irate at my service, it was horribly", 0),
        PLEASED("My service was satisfactory, I am pleased", 0.3),
        APPRECIATIVE("Thank you so much for my service, I appreciate it", 0.2),
        DISAPPOINTED("I am disappointed in my service.  Oh well", 0.1),
        NOREACTION("None", 0);
        public final String label;
        public final double tip;
        private Reaction(String label, double tip){
            this.label = label;
            this.tip = tip;
        }
        public String getReaction(Reaction reaction){
            String face = "";
            switch (reaction){
                case HAPPY:
                    face = "( ͡❛ ͜ʖ ͡❛) ";
                    break;
                case IRATE:
                   face = "(╯ ͡❛ ͜ʖ ͡❛)╯┻━┻ ";
                   break;
                case PLEASED:
                    face = "(: ";
                    break;
                case APPRECIATIVE:
                    face = "\\( ͡❛ ͜ʖ ͡❛)/";
                    break;
                case DISAPPOINTED:
                    face = "|: ";
                    break;
            }
            return face+reaction.label;
        }
    }
    public Reaction reaction;

    public Customer(String newName, String newEmail, String newPhone, Machine newMachine) {

        name = newName;
        email = newEmail;
        phone = newPhone;
        machine = newMachine;
        funds = 180;
        bill = 0;
        this.reaction = Reaction.NOREACTION;

    }
    public void bill(int newBill){
        bill = newBill;
    }

    public int getBill(){
        return bill;
    }

    public void processBill(){
        int paid = 0;
        if((bill == 0) || (reaction == Reaction.NOREACTION)){
            Main.logger.info("Improper bill time");
        }
        else{
            if(funds < bill){
                Main.logger.info("Uh oh! "+name+" can not pay, and has fled");
            }
            else{
                paid = bill;
                funds -= bill;
                Main.billpaied += paid;
                Main.totalpaid += paid;
                Main.logger.info(name+" has paid their bill in full");
                if(funds > 0){
                    double tipAmount = reaction.tip*bill;
                    if(funds > tipAmount){
                        paid += tipAmount;
                        funds -= tipAmount;
                        Main.totalpaid += tipAmount;
                    }
                    else{
                        paid += funds;
                        Main.totalpaid += funds;
                        funds = 0;
                    }
                }
            }
        }
        Main.logger.info("Customer "+name+" has paid a total of "+paid+" for their service");
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine newMachine) {
        machine = newMachine;
        if(machine != null){
            machine.setOwner(this);
        }
    }

    public void submitRequest() {

    }
}
