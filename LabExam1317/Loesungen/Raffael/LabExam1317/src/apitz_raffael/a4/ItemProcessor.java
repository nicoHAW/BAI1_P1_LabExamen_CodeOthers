package apitz_raffael.a4;

import java.util.HashMap;
import java.util.Map;

import _untouchable_.thingy.Item;
import _untouchable_.thingy.Weight;

public class ItemProcessor implements ItemProcessor_I {

    private Map<Weight, Triple> processedItems;
    private int processedItemCount;
    private int triplesFoundCount;

    public ItemProcessor() {
        processedItems = new HashMap<Weight, Triple>();
    }

    @Override
    public Triple_I process(Item item) {
        assert item != null : "the item cant be null";

        processedItemCount++;

        final Weight itemWeight = item.getWeight();
        Triple currentTriple = processedItems.get(itemWeight);

        if (currentTriple == null) {
            currentTriple = new Triple();
            processedItems.put(itemWeight, currentTriple);
        }
        currentTriple.addItem(item);

        if (currentTriple.getSize() >= 3) {
            processedItems.remove(itemWeight);
            triplesFoundCount++;
            return currentTriple;
        }

        return null;
    }

    @Override
    public int itemsProcessed() {
        return processedItemCount;
    }

    @Override
    public int triplesFound() {
        return triplesFoundCount;
    }

    @Override
    public void reset() {
        processedItems.clear();
        processedItemCount = 0;
        triplesFoundCount = 0;
    }

}
