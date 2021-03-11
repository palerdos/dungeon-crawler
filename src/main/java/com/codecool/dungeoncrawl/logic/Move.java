package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
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
    public boolean moveValidator(Actor monster, Directions direction){
        Cell nextCell = monster.getCell().getNeighbor(direction.getCordX(), direction.getCordY());
        if (monster instanceof Ghost){
            if (direction == Directions.North && monster.getY() == 1) {
                return false;
            }else if (direction == Directions.South && monster.getY() == map.getHeight() -2) {
                return false;
            }else if (direction == Directions.East && monster.getX() == map.getWidth() -2) {
                return false;
            }else if (direction == Directions.West && monster.getX() == 1) {
                return false;
            }else {
                if (nextCell.getActor() != null){
                    return false;
                }else {
                    return true;
                }
            }
        }else if (monster instanceof Skeleton) {
            if ((!nextCell.getType().getTileName().equals("wall")) && nextCell.getActor() != null){
                return false;
            }else{
                return true;
            }
        }

        return true;

    }

    public void moveNPCs () {
        ArrayList<Actor> monsterList = monsterCounter();
        monsterList.forEach(monster -> {
            Directions randomDirection = null;
            int counter = 0;
            boolean moveIsValid = false;
            while (!moveIsValid) {
                counter++;
                randomDirection = getRandomDirection();
                moveIsValid = moveValidator(monster, randomDirection);
                if (counter > 4){
                    randomDirection = null;
                    break;
                }
            }
            if (randomDirection != null) {
                if (checkIfNeighbourIsActor(randomDirection) instanceof Player) {
                    initCombat(monster, map.getPlayer());
                }
                monster.move(randomDirection.getCordX(), randomDirection.getCordY());
            }
        });
    }

    public Directions getRandomDirection() {
        Random random = new Random();
        return Directions.values()[random.nextInt(Directions.values().length)];
    }

    public ArrayList<Actor> monsterCounter() {
        ArrayList <Actor> monsterList = new ArrayList<>();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null){
                    Actor actor = cell.getActor();
                    if(actor instanceof Player){
                        assert false;
                    }else{
                        monsterList.add(actor);
                    }
                }
            }
        }
        return monsterList;
    }

}
