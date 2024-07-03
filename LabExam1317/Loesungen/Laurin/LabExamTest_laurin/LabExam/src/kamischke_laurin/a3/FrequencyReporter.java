package kamischke_laurin.a3;

import java.util.Map;
import java.util.Set;

public class FrequencyReporter<T> implements FrequencyReporter_I<T> {

	private Map<T, Integer> frequencyOfAll;
	private int amountOfUniqueItems;
	private Set<T> knownElements;
	
	public FrequencyReporter(Map<T, Integer> freqOA, int amount, Set<T> elems){
		frequencyOfAll = freqOA;
		amountOfUniqueItems = elems.size();
		knownElements = elems;
	}
	
	@Override
	public int getFrequency(Object element) {
		return (frequencyOfAll.get(element) != null) ? frequencyOfAll.get(element) : 0;
	}

	@Override
	public Set<T> knownElements() {
		return knownElements;
	}

	@Override
	public int getAmountOfUniqueItems() {
		return amountOfUniqueItems;
	}

}
