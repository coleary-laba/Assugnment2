package interfaces;

import items.Recipt;

import java.util.HashSet;

public interface IBillTotal<T> {

    int generateTotal(HashSet<Recipt> collection);
}
