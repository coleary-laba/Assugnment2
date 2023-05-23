package interfaces;

import java.util.ArrayList;

public interface IBillTotal<T> {

    int generateTotal(ArrayList<T> collection);
}
