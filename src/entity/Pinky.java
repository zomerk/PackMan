package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
/**
 * The Pinky class represents the Pinky enemy character in the game.
 */
public class Pinky extends Entity{
    private Player player;
    private Point scatterTarget = new Point(27*gp.tileSize, 0); // Top-right corner of the maze

    /**
     * The Pinky class represents the Pinky enemy character in the game.
     */
    public Pinky(GamePanel gp, Player p) {
        super(gp);
        this.player = p;
        direction = "left";
        speed = 2;
        bounds = new Rectangle(2, 2, 26, 26);
        boundX = bounds.x;
        boundsY = bounds.y;
        getPinkyImage();
    }
    /**
     * The Pinky class represents the Pinky enemy character in the game.
     */
    public void getPinkyImage() {
        try {
            character = ImageIO.read(getClass().getResource("/resources/images/characters/PinkRight.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDefoultValues(){
        x = gp.tileSize*14;
        y = gp.tileSize*13;
        speed = 2;
        direction = "down";
        LastDirection = "";
    }
    /**
     * The Pinky class represents the Pinky enemy character in the game.
     */
    @Override
    public void setAction() {
//        long currentTime = System.currentTimeMillis();
        Point target = new Point();
        if(dead){
            speed = 3;
            bounds = new Rectangle(3, 3, 25, 25);
            target = new Point( gp.tileSize*15, gp.tileSize*13);
        } else if (panicMode) {
            speed = 1;
            bounds = new Rectangle(1, 1, 27, 27);
            target = scatterTarget;
        }
        else {
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
        moveToGivenSquare(target);
    }
}
