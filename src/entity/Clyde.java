package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * The Clyde class represents the Clyde ghost entity in the game.
 * Clyde's behavior alternates between scatter mode and chase mode.
 * In scatter mode, Clyde moves towards a fixed target position.
 * In chase mode, Clyde moves towards the player's current position.
 */
public class Clyde extends Entity {
    private Player player; // Reference to the player entity
    private String mode = "scatter"; // Current mode: "scatter", "chase"
    private long modeSwitchTimer; // Timer to switch between modes
    private long modeSwitchInterval = 23000; // Interval between mode switches in milliseconds
    private Point scatterTarget = new Point(0, 0); // Target position in scatter mode

    /**
     * Constructs a new instance of Clyde.
     *
     * @param gp The GamePanel instance for accessing game settings and utilities.
     * @param p  The Player instance representing the player entity in the game.
     */
    public Clyde(GamePanel gp, Player p) {
        super(gp);
        this.player = p;
        direction = "left";
        speed = 2;
        bounds = new Rectangle(2, 2, 26, 26);
        boundX = bounds.x;
        boundsY = bounds.y;
        getClydeImage(); // Load Clyde's image
        modeSwitchTimer = System.currentTimeMillis(); // Initialize mode switch timer
    }

    /**
     * Loads Clyde's image from the resources directory.
     */
    public void getClydeImage() {
        try {
            character = ImageIO.read(getClass().getResourceAsStream("/resources/images/characters/OrangeRight.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets Clyde's action based on the current game state.
     * In scatter mode, Clyde moves towards a fixed target position.
     * In chase mode, Clyde moves towards the player's current position.
     */
    public void setDefoultValues(){
        x = gp.tileSize*13;
        y = gp.tileSize*13;
        speed = 2;
        direction = "down";
        LastDirection = "";
    }
    @Override
    public void setAction() {
        Point target = new Point();
        if (dead) {
            // Set action when Clyde is dead
            speed = 3;
            bounds = new Rectangle(3, 3, 25, 25);
            target = new Point(gp.tileSize * 15, gp.tileSize * 13);
        } else if (panicMode) {
            // Set action when Clyde is in panic mode
            speed = 1;
            bounds = new Rectangle(1, 1, 27, 27);
            target = scatterTarget;
        } else {
            // Set action when Clyde is not dead and not in panic mode
            speed = 2;
            bounds = new Rectangle(2, 2, 26, 26);
            switch (player.direction) {
                case "left":
                    target = new Point(player.x + 4 * gp.tileSize, player.y);
                    break;
                case "right":
                    target = new Point(player.x - 4 * gp.tileSize, player.y);
                    break;
                case "up":
                    target = new Point(player.x, player.y - 4 * gp.tileSize);
                    break;
                case "down":
                    target = new Point(player.x, player.y + 4 * gp.tileSize);
                    break;
            }
        }

        moveToGivenSquare(target); // Move Clyde towards the target position
    }
}
