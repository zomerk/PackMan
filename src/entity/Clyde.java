package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Clyde extends Entity {
    private Player player;
    private String mode = "scatter"; // Modes: "scatter", "chase"
    private long modeSwitchTimer;
    private long modeSwitchInterval = 23000; // Example interval in milliseconds
    private Point scatterTarget = new Point(0, 0); // Top-right corner of the maze

    public Clyde(GamePanel gp, Player p) {
        super(gp);
        this.player = p;
        direction = "left";
        speed = 2;
        bounds = new Rectangle(2, 2, 26, 26);
        boundX = bounds.x;
        boundsY = bounds.y;
        getClydeImage();
        modeSwitchTimer = System.currentTimeMillis();
    }

    public void getClydeImage() {
        try {
            character = ImageIO.read(getClass().getResourceAsStream("/resources/images/characters/OrangeRight.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAction() {
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