package background;

import java.awt.image.BufferedImage;

/**
 * Represents a tile in the game background.
 */
public class Tile {
    /** The image associated with the tile. */
    public BufferedImage image;
    /** Indicates whether the tile is collidable or not. */
    public boolean collision = true;
}
