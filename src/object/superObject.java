package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The superObject class represents a generic object within the game.
 * This class provides the basic structure and functionality for all game objects.
 */
public class superObject {

    /**
     * The image of the object.
     */
    public BufferedImage image;

    /**
     * The name of the object.
     */
    String name;

    /**
     * Indicates whether the object has collision enabled.
     */
    public boolean collision = false;

    /**
     * The x-coordinate of the object's position in the world.
     */
    public int wordX;

    /**
     * The y-coordinate of the object's position in the world.
     */
    public int wordY;

    /**
     * The collision bounds of the object. By default, it's a 1x1 rectangle at the object's position.
     */
    public Rectangle bounds = new Rectangle(wordX + 26, wordY + 26, 1, 1);

    /**
     * Draws the object on the screen.
     *
     * @param g2 The Graphics2D object used for drawing.
     * @param gp The GamePanel containing the object.
     */
    public void draw(Graphics2D g2, GamePanel gp) {
        g2.drawImage(image, wordX, wordY, gp.tileSize, gp.tileSize, null);
    }
}
