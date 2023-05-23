package main;

import items.WorkTicket;

public class LinkedList {

    Node head;

    public void add(WorkTicket t) {
        Node newNode = new Node(t);
        if (head == null) {
            head = newNode;
        } else {
            Node finalNode = head;
            while (finalNode.next != null) {
                finalNode = finalNode.next;
            }
            finalNode.next = newNode;
        }
    }

    public void addFirst(WorkTicket t) {
        Node newNode = new Node(t);
        newNode.setNext(head);
        head = newNode;
    }

    public void addLast(WorkTicket t) {
        Node newNode = new Node(t);
        Node oldNode = head;
        while (oldNode.getNext() != null) {
            oldNode = oldNode.getNext();
        }
        oldNode.setNext(newNode);
    }

    public void insertAt(int index, WorkTicket ticket) {
        Node newNode = new Node(ticket);
        Node oldNode = head;
        for (int i = 0; i < index - 1; i++) {
            oldNode = oldNode.getNext();
        }
        newNode.setNext(oldNode.getNext());
        oldNode.setNext(newNode);
    }

    public WorkTicket get(int index) {
        Node newNode = head;
        for (int i = 0; i < index - 1; i++) {
            newNode = newNode.getNext();
        }
        return newNode.getData();
    }

    public void remove(int index) {
        Node newNode = head;
        for (int i = 0; i < index - 1; i++) {
            newNode = newNode.getNext();
        }
        newNode.setNext(newNode.getNext().getNext());
    }

    public int size() {
        int counter = 0;
        if (head != null) {
            Node curNode = head;
            while (curNode.getNext() != null) {
                counter++;
                curNode = curNode.getNext();
            }
            counter++;
        }
        return counter;
    }

    public void showAll() {
        if (head != null) {
            Node curNode = head;
            while (curNode.getNext() != null) {
                Main.logger.info(curNode.getData().getTask().toString());
                curNode = curNode.getNext();
            }
            Main.logger.info(curNode.getData().getTask().toString());
        }
    }
}
