package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Point extends superObject{
    public Point() {
        name = "point";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/resources/images/characters/NormalPoint.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
