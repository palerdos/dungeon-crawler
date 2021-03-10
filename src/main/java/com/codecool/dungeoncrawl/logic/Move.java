package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.Directions;

import java.util.ArrayList;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


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

    private Actor checkIfNeighbourIsActor(Directions direction){
        return map.getPlayer().getCell().getNeighbor(direction.getCordX(), direction.getCordY()).getActor();
    }


    public void initCombat(Actor attacker, Actor defender){
        while (attacker.getHealth() > 0 && defender.getHealth() > 0){
            defender.setHealth(defender.getHealth() - (attacker.getAttack() - defender.getDefense()));
            System.out.println(defender.getTileName() + defender.getHealth());
            attacker.setHealth(attacker.getHealth() - (defender.getAttack() - attacker.getDefense()));
            System.out.println(attacker.getTileName() + attacker.getHealth());
        }
        if (map.getPlayer().getHealth() > 0) {
            defender.getCell().setActor(null);
        }
    }

    public void moveNPCs () {
        ArrayList<Actor> monsterList = monsterCounter();
        System.out.println("List size: " + monsterList.size());
        monsterList.forEach(monster -> {
            Directions randomDirection = getRandomDirection();
            monster.move(randomDirection.getCordX(), randomDirection.getCordY());
        });
    }

    public Directions getRandomDirection() {
        Random random = new Random();
        return Directions.values()[random.nextInt(Directions.values().length)];
    }

    public ArrayList<Actor> monsterCounter() {
        int counter = 0;
        ArrayList <Actor> monsterList = new ArrayList<>();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null){
                    Actor actor = cell.getActor();
                    if(actor instanceof Player){
                        assert false;
                    }else{
                        counter ++;
                        monsterList.add(actor);
                    }
                }
            }
        }
        System.out.println("COUNTER: " + counter);
        return monsterList;
    }

}
