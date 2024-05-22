package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.KeyStore;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Entity {
    public static String playerDirection;
    public static boolean panicMode = true;
    public GamePanel gp;
    public int x;
    public int y;
    public int speed;
    public BufferedImage up, down, left, right,character;
    public String direction;
    public String LastDirection = "up";
    public Rectangle bounds = new Rectangle(2,2,26,26);
    public int boundX,boundsY;
    public boolean collisionOn = false;
    public LinkedList<String> possibleDirections;
    public Entity(GamePanel gp) {
        this.gp = gp;
    }



    public void moveToGivenSquare(Point target) {
        possibleDirections = new LinkedList<>();
        Point currentPos = new Point(x, y);

        // Determine the preferred directions based on the target position
        if (currentPos.y < target.y) {
            possibleDirections.add("down");
        } else{
            possibleDirections.add("up");
        }
        if (currentPos.x < target.x) {
            possibleDirections.add("right");
        } else{
            possibleDirections.add("left");
        }



        // Ensure all directions are considered to avoid being stuck
        if (possibleDirections.size() < 4) {
            if (!possibleDirections.contains("right")) possibleDirections.add("right");
            if (!possibleDirections.contains("left")) possibleDirections.add("left");
            if (!possibleDirections.contains("up")) possibleDirections.add("up");
            if (!possibleDirections.contains("down")) possibleDirections.add("down");
        }
    }
    public void setAction(){}
    public void update() {
        setAction();
        LastDirection = direction;
        collisionOn = false;
        //System.out.println(possibleDirections);
        if(Entity.panicMode) {
            if (Entity.playerDirection.equals("left") || Entity.playerDirection.equals("right")) {
                Collections.swap(possibleDirections, 0, 1);
                Collections.swap(possibleDirections, 2, 3);
            }
        }
        for (int i = 0; i < possibleDirections.size(); i++) {
            direction = possibleDirections.get(i);
            gp.collisionChecker.checkTile(this);
            if (LastDirection.equals("left") && direction.equals("right")) {
                continue;
            } else if (LastDirection.equals("up") && direction.equals("down")) {
                continue;
            } else if (LastDirection.equals("down") && direction.equals("up")) {
                continue;
            } else if (LastDirection.equals("right") && direction.equals("left")) {
                continue;
            }
            if(!collisionOn){
                    switch (direction) {
                        case "up":
                            y -= speed;
                            break;
                        case "down":
                            y += speed;
                            break;
                        case "left":
                            x -= speed;
                            break;
                        case "right":
                            x += speed;
                            break;
                    }
                }
                if(!collisionOn) {
                    break;
                }
        }
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(character, x, y, gp.tileSize, gp.tileSize, null);
    }

    private double getManhattanDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

}
