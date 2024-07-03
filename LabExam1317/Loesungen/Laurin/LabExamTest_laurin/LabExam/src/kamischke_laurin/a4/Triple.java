package kamischke_laurin.a4;

import java.util.ArrayList;
import java.util.List;

import _untouchable_.thingy.Item;

public class Triple implements Triple_I {

	List<Item> collectedItems = new ArrayList<Item>();
	
	@Override
	public Item getItem(int appearenceRelatedIndex) {
		return collectedItems.get(appearenceRelatedIndex);
	}

	public int getSize() {
		return collectedItems.size();
	}
	
	public void addItem(Item item) {
		collectedItems.add(item);
	}
}
