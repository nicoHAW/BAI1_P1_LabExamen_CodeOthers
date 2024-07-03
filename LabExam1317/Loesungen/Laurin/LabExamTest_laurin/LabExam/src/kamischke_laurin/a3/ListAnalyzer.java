package kamischke_laurin.a3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;

public class ListAnalyzer<T> implements ListAnalyzer_I<T>{

	private List<T> currentList;
	
	public ListAnalyzer(List<T> givenList) {
		assert (givenList != null);
		assert (!givenList.contains(null));
		
		currentList = givenList;
	}
	
	@Override
	public FrequencyReporter_I<T> computeFrequencyReporter() {
		
		Map<T, Integer> frequencyMap = new HashMap<T, Integer>();
		int amountOfItems = currentList.size();
		
		for (int i = 0; i < currentList.size(); i++) {
			frequencyMap.putIfAbsent(currentList.get(i), 0);
			frequencyMap.replace(currentList.get(i), frequencyMap.get(currentList.get(i)), frequencyMap.get(currentList.get(i))+1);
		}
		
		FrequencyReporter<T> fq = new FrequencyReporter<T>(frequencyMap, amountOfItems, frequencyMap.keySet());
		
		return fq;
	}

	@Override
	public List<T> getList() {
		return currentList;
	}

}
