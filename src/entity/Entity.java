package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.KeyStore;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Entity class represents a generic entity in the game.
 * It serves as a base class for other specific entities like Player, Ghost, etc.
 */
public class Entity {
    public static String playerDirection;
    public static boolean panicMode;
    public static boolean startTimerForPanicMode;
    public static Timer panicModeTimer;
    public static final int PANIC_MODE_DURATION = 10000;
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
    public boolean dead;

    /**
     * Constructor for the Entity class.
     *
     * @param gp The GamePanel instance.
     */
    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    /**
     * Starts the panic mode, during which entities behave differently.
     */
    public static void startPanicMode() {
        panicMode = true;
        if (panicModeTimer != null) {
            panicModeTimer.cancel();
        }
        panicModeTimer = new Timer();
        panicModeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                endPanicMode();
            }
        }, PANIC_MODE_DURATION);
    }

    /**
     * Starts the panic mode, during which entities behave differently.
     */
    public static void endPanicMode() {
        panicMode = false;
        if (panicModeTimer != null) {
            panicModeTimer.cancel();
            panicModeTimer = null;
        }
    }
    /**
     * Starts the panic mode, during which entities behave differently.
     */
    public static void triggerPanicMode() {
        startPanicMode();
    }

    /**
     * Moves the entity towards the given target square.
     *
     * @param target The target square to move towards.
     */
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
    /**
     * Sets the action for the entity based on the game state.
     */
    public void setAction(){}
    /**
     * Updates the entity's position and behavior.
     */
    public void update() {
        System.out.println("THREAD GHOST" + Thread.currentThread().getName());
        String wiadomosc = panicMode?"PANIKA":"POLOWANIE";
        System.out.println(wiadomosc);
        setAction();
        LastDirection = direction;
        collisionOn = false;
        if(Entity.panicMode && !dead) {
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
                if (!collisionOn) {
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
                if (!collisionOn) {
                    break;
                }
            }
        }
        else if(dead) {
            setAction();
            LastDirection = direction;
            collisionOn = false;
            //System.out.println(possibleDirections);
            if(Entity.panicMode) {
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
                    if (!collisionOn) {
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
                    if (!collisionOn) {
                        break;
                    }
                }
            if(x>gp.tileSize*11 && x<gp.tileSize*15 && y>gp.tileSize*11 && y<gp.tileSize*15) {
                dead = false;
            }
            }
        }
        else {
            if (Entity.playerDirection.equals("left") || Entity.playerDirection.equals("right")) {
                Collections.swap(possibleDirections, 0, 1);
                Collections.swap(possibleDirections, 2, 3);
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
                if (!collisionOn) {
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
                if (!collisionOn) {
                    break;
                }
            }
        }
        gp.addPosition(new Point(x, y));
    }
    /**
     * Draws the entity on the graphics context.
     *
     * @param g2 The graphics context to draw on.
     */
    public void draw(Graphics2D g2) {
        g2.drawImage(character, x, y, gp.tileSize, gp.tileSize, null);
    }

}
