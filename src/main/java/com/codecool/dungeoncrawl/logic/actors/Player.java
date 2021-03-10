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
    }
}
