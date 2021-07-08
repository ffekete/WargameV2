package com.mygdx.mechwargame.core.ship.component.cargo;

import com.mygdx.mechwargame.core.item.Item;
import com.mygdx.mechwargame.core.ship.component.Component;

import java.util.ArrayList;
import java.util.List;

public abstract class CargoBay extends Component {

    protected int baseCapacity;
    public int capacity;
    public int maxCapacity;

    protected List<Item> items = new ArrayList<>(1000);

    public List<Item> getItems() {
        return items;
    }

    public void removeItem(Item item) {
        int i = items.indexOf(item);
        items.remove(i);
        items.add(i, null);

        capacity++;
    }

    public void addItem(Item item) {
        if (capacity > 0) {
            int i = items.indexOf(null);
            items.remove(i);
            items.add(i, item);
            capacity--;
        }
    }

    public CargoBay(int level,
                    String name) {
        super(level, name);
    }

    @Override
    protected void adjustValues(int level) {
        capacity = level + baseCapacity;
        maxCapacity = level + baseCapacity;
        for (int i = items.size(); i < maxCapacity; i++) {
            items.add(i, null);
        }
    }
}
