package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Directions;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Button pickUpBtn = new Button("Loot");
    Label healthLabel = new Label();
    Label attackLabel = new Label();
    Label defenseLabel = new Label();
    Text combatLog = new Text();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(pickUpBtn, 0, 0);

        ui.add(new Label("Health: "), 0, 1);
        ui.add(healthLabel, 1, 1);

        ui.add(new Label("Attack: "), 0, 2);
        ui.add(attackLabel, 1, 2);

        ui.add(new Label("Defense: "), 0, 3);
        ui.add(defenseLabel, 1, 3);
        ui.add(combatLog, 0, 4);
        ui.setStyle("-fx-background-color: #f26252;");

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        pickUpBtn.setOnAction(event -> onBtnPress());

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
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

    public void onBtnPress() {
        System.out.println(map.getPlayer().getCell().getItem());
        if (map.getPlayer().getCell().getItem() != null) {
            map.getPlayer().getCell().setItem(null);
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
            case UP:
                if (checkIfNeighbourIsActor(Directions.North) != null){
                    initCombat(map.getPlayer(), checkIfNeighbourIsActor(Directions.North));
                }else{
                    map.getPlayer().move(Directions.North.getCordX(), Directions.North.getCordY());
                }
                refresh();
                break;
            case S:
            case DOWN:
                if (checkIfNeighbourIsActor(Directions.South) != null){
                    initCombat(map.getPlayer(), checkIfNeighbourIsActor(Directions.South));
                }else{
                    map.getPlayer().move(Directions.South.getCordX(), Directions.South.getCordY());
                }
                refresh();
                break;
            case A:
            case LEFT:
                if (checkIfNeighbourIsActor(Directions.West) != null){
                    initCombat(map.getPlayer(), checkIfNeighbourIsActor(Directions.West));
                }else{
                    map.getPlayer().move(Directions.West.getCordX(), Directions.West.getCordY());
                }
                refresh();
                break;
            case D:
            case RIGHT:
                if (checkIfNeighbourIsActor(Directions.East) != null){
                    initCombat(map.getPlayer(), checkIfNeighbourIsActor(Directions.East));
                }else{
                    map.getPlayer().move(Directions.East.getCordX(), Directions.East.getCordY());
                }
                refresh();
                break;
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        attackLabel.setText("" + map.getPlayer().getAttack());
        defenseLabel.setText("" + map.getPlayer().getDefense());
    }
}
