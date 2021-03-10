package com.codecool.dungeoncrawl.logic;

public class Move {
    private GameMap map;


    public Move (GameMap map){
        this.map = map;

    }

    public void initRound(Directions direction){
        if (checkIfNeighbourIsActor(direction) != null){
            initCombat(map.getPlayer(), checkIfNeighbourIsActor(direction));
        }else{
            map.getPlayer().move(direction.getCordX(), direction.getCordY());
            moveNPCs();
        }
    }

}
