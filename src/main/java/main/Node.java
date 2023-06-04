package main;

import items.WorkTicket;

public class Node {

    WorkTicket t;
    Node next;

    public Node(WorkTicket n) {
        t = n;
        next = null;
    }

    public WorkTicket getData() {
        return t;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node newT) {
        next = newT;
    }
}
