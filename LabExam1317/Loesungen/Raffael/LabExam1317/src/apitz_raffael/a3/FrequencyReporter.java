package apitz_raffael.a3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FrequencyReporter<T> implements FrequencyReporter_I<T> {
    
    private List<T> list;
    
    public void setList(List<T> list) {
        this.list = list;
    }
    
    @Override
    public int getFrequency(T element) {
        int freq = 0;
        for (T item : list) {
            if (item.equals(element)) {
                freq++;
            }
        }
        return freq;
    }

    @Override
    public int getAmountOfUniqueItems() {
        return knownElements().size();
    }

    @Override
    public Set<T> knownElements() {
        final Set<T> uniqueElements = new HashSet<T>(list);
        return uniqueElements;
    }

}
