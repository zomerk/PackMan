package object;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * The Heart class represents a heart object in the game, extending the generic superObject class.
 * This class is used to create heart objects that can be collected by the player.
 */
public class Heart extends superObject {

    /**
     * Constructor for the Heart class.
     * Initializes the heart object with an image and sets the collision property.
     */
    public Heart() {
        name = "point";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/images/characters/Heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
