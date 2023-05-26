package interfaces;

import people.Customer;

import java.util.Queue;

public interface IQueueGen<T> {

    String[] arrayGeneratorCust(Queue<Customer> collection);
}