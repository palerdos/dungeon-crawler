package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {

    private Cell cell;
    private String name;


    public Item (Cell cell, String name) {
        this.cell = cell;
        this.cell.setItem(this);
        this.name = name;
    }

    public Cell getCell() {
        return cell;
    }

    public String getItemName() {return this.name;}

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
