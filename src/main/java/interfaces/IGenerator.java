package interfaces;

import people.Worker;

import java.util.Vector;

public interface IGenerator<T> {

    String[] arrayGeneratorWork(Vector<Worker> collection);
}
