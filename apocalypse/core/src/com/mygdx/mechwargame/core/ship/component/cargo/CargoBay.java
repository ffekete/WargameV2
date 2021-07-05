package com.mygdx.mechwargame.core.ship.component.cargo;

import com.mygdx.mechwargame.core.item.Item;
import com.mygdx.mechwargame.core.ship.component.Component;

import java.util.LinkedList;
import java.util.List;

public abstract class CargoBay extends Component {

    protected int baseCapacity;
    public int capacity;
    public int maxCapacity;

    protected List<Item> items = new LinkedList<>();

    public List<Item> getItems() {
        return items;
    }

    public void removeItem(Item item) {
        if(items.remove(item)) {
            capacity++;
        }
    }

    public void addItem(Item item) {
        if(capacity > 0) {
            items.add(item);
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
    }
}
