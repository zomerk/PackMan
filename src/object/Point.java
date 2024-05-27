package object;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * The Point class represents a point object in the game, extending the generic superObject class.
 * This class is used to create point objects that can be collected by the player.
 */
public class Point extends superObject {

    /**
     * Constructor for the Point class.
     * Initializes the point object with an image and sets the collision property.
     */
    public Point() {
        name = "point";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/images/characters/NormalPoint.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
