package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Actor{
    public Ghost(Cell cell) {
        super(cell);
        this.type = "monster";
        this.setAttack(6);
        this.setDefense(6);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = this.getCell().getNeighbor(dx, dy);
        this.getCell().setActor(null);
        nextCell.setActor(this);
        this.setCell(nextCell);

    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
