package oopokemon.inventory;

import java.util.*;

public class Bag<T> {
    public ArrayList<T> listItem;
    public int neff;

    public Bag() {
        this.listItem = new ArrayList<>(Inventory.MAX_CAPACITY);
        this.neff = 0;
    }
    public void Add(T other) {
        if (neff <= Inventory.MAX_CAPACITY) {
            this.listItem.add(neff, other);
            neff++;
        }
    }
    public void printAllInfo() {
        for (int i = 0; i < neff; i++) {
            System.out.println((i+1) + ". " + this.listItem.get(i));
        }
    }
}
