package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;

public class Player extends Actor {

    ArrayList<Item> inventory = new ArrayList<>();

    public Player(Cell cell) {
        super(cell);
        this.setHealth(100);
        this.setAttack(10);
        this.setDefense(2);
    }

    public String getTileName() {
        return "player";
    }

    public void lootItem(Item item) {
        inventory.add(item);
        useItem(item);
    }

    public void useItem(Item item) {
        switch (item.getTileName()) {
            case "health_potion":
                setHealth(Math.min(getHealth() + item.getValue(), 100));
                inventory.remove(item);
                break;
            case "weapon":
                setAttack(getAttack() + item.getValue());
                break;
            case "shield":
                setDefense(getDefense() + item.getValue());
                break;
        }
    }

    public String displayInventory() {
        ArrayList<String> displayableInventory = new ArrayList<>();
        inventory.forEach(item -> displayableInventory.add(item.getItemName()));
        if (displayableInventory.size() > 0) {
            return displayableInventory.toString();
        } else {
            return "No items in inventory";
        }
    }
}
