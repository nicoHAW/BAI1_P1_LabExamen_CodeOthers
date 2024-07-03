package kamischke_laurin.a4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

import _untouchable_.thingy.Item;

public class ItemProcessor implements ItemProcessor_I {

	private HashMap<Integer, Triple> collectedItems = new HashMap<Integer, Triple>();
	private int counterOfItems = 0;
	private int counterOfTriples = 0;
	
	@Override
	public Triple_I process(Item item) {
		assert (item != null);
		
		collectedItems.putIfAbsent(item.getWeight().ordinal(), new Triple());
		collectedItems.get(item.getWeight().ordinal()).addItem(item);
		
		counterOfItems++;
		if (collectedItems.get(item.getWeight().ordinal()).getSize() >= 3) {
			counterOfTriples++;
			return collectedItems.remove(item.getWeight().ordinal());
		}
		return null;
	}

	@Override
	public int itemsProcessed() {
		return counterOfItems;
	}

	@Override
	public int triplesFound() {
		return counterOfTriples;
	}

	@Override
	public void reset() {
		collectedItems.clear();
		counterOfItems = 0;
		counterOfTriples = 0;
	}

}
