package apitz_raffael.a3;

import java.util.List;

public class ListAnalyzer<T> implements ListAnalyzer_I<T> {

    private List<T> list;
    
    public ListAnalyzer( List<T> list ) {
        assert list != null : "list cant be null";
        for (T item : list) {
            assert item != null : "items cant be null";
        }
        this.list = list;
    }
    
    @Override
    public FrequencyReporter_I<T> computeFrequencyReporter() {
        final FrequencyReporter<T> freqReporter = new FrequencyReporter<T>();
        freqReporter.setList(list);
        return freqReporter;
    }

    @Override
    public List<T> getList() {
        return list;
    }


}
