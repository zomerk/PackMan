package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;

public class Pinky extends Entity{
    private Player player;
    private String mode = "scatter"; // Modes: "scatter", "chase"
    private long modeSwitchTimer;
    private long modeSwitchInterval = 23000; // Example interval in milliseconds
    private Point scatterTarget = new Point(27*gp.tileSize, 0); // Top-right corner of the maze

    public Pinky(GamePanel gp, Player p) {
        super(gp);
        this.player = p;
        direction = "left";
        speed = 2;
        bounds = new Rectangle(2, 2, 26, 26);
        boundX = bounds.x;
        boundsY = bounds.y;
        getPinkyImage();
        modeSwitchTimer = System.currentTimeMillis();
    }

    public void getPinkyImage() {
        try {
            character = ImageIO.read(getClass().getResource("/resources/images/characters/PinkRight.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAction() {
//        long currentTime = System.currentTimeMillis();
        Point target = new Point();
        if(panicMode){
            target = scatterTarget;
        }
        else {
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
