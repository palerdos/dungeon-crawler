package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class HealthPotion extends Item {
    private int healthBonus;

    public HealthPotion(Cell cell, int healthBonus) {
        super(cell, "Health Potion", healthBonus);
    }

    @Override
    public String getTileName() {
        return "health_potion";
    }
}
