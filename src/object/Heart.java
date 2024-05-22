package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Heart extends superObject {
    public Heart() {
        name = "point";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/resources/images/characters/Heart.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
