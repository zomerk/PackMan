package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The Blinky class represents the Blinky ghost in the game.
 * Blinky behaves differently based on the game state.
 */
public class Blinky extends Entity {
    private Player player;
    private String mode = "scatter"; // Modes: "scatter", "chase"
    private long modeSwitchTimer;
    private long modeSwitchInterval = 23000; // Example interval in milliseconds
    private Point scatterTarget = new Point(0, 0); // Top-right corner of the maze

    /**
     * Constructs a Blinky object with the specified GamePanel and Player.
     *
     * @param gp The GamePanel instance.
     * @param p  The Player instance.
     */
    public Blinky(GamePanel gp, Player p) {
        super(gp);
        this.player = p;
        direction = "left";
        speed = 2;
        bounds = new Rectangle(2, 2, 26, 26);
        boundX = bounds.x;
        boundsY = bounds.y;
        getBlinkyImage();
        modeSwitchTimer = System.currentTimeMillis();
    }
    /**
     * Loads the image for Blinky.
     */
    public void getBlinkyImage() {
        try {
            character = ImageIO.read(getClass().getResource("/resources/images/characters/RedLeft.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Sets the action for Blinky based on the game state.
     */
    @Override
    public void setAction() {
//        long currentTime = System.currentTimeMillis();
        Point target = new Point();
        if(dead){
            speed =3;
            bounds = new Rectangle(3, 3, 25, 25);
            target = new Point( gp.tileSize*11, gp.tileSize*13);
        } else if (panicMode) {
            speed = 1;
            bounds = new Rectangle(1, 1, 27, 27);
            target = scatterTarget;
        } else {
            speed = 2;
            bounds = new Rectangle(2, 2, 26, 26);
            target = new Point(player.x, player.y);
        }
//
//        // Toggle between scatter and chase mode
//        if ((currentTime - modeSwitchTimer) < modeSwitchInterval) {
//            target = scatterTarget;
//        } else {
//            if ((currentTime - modeSwitchTimer) >= 2 * modeSwitchInterval) {
//                modeSwitchTimer = currentTime;
//            }
//        }

        moveToGivenSquare(target);
    }
}
