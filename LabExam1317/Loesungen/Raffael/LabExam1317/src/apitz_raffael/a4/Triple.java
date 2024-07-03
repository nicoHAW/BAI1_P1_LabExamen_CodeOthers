package apitz_raffael.a4;

import java.util.ArrayList;
import java.util.List;

import _untouchable_.thingy.Item;

public class Triple implements Triple_I {

    private List<Item> items = new ArrayList<Item>();

    @Override
    public Item getItem(int appearenceRelatedIndex) {
        assert appearenceRelatedIndex < items.size() : "appearenceRelatedIndex out of bounds";
        return items.get(appearenceRelatedIndex);
    }

    public void addItem(Item item) {
        assert item != null : "the item cant be null";
        assert getSize() < 3 : "there are already 3 items in this triple";
        items.add(item);
    }

    public int getSize() {
        return items.size();
    }

}
