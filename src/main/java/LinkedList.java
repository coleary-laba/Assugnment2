public class LinkedList {

    Node head;

    public static LinkedList intert(LinkedList list, WorkTicket t){
        Node newNode = new Node(t);
        if(list.head == null){
            list.head = newNode;
        }
        else{
            Node finalNode = list.head;
            while(finalNode.next != null){
                finalNode = finalNode.next;
            }
            finalNode.next = newNode;
        }
        return list;
    }
}
