package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Blinky extends Entity {
    private Player player;
    private String mode = "scatter"; // Modes: "scatter", "chase"
    private long modeSwitchTimer;
    private long modeSwitchInterval = 23000; // Example interval in milliseconds
    private Point scatterTarget = new Point(0, 0); // Top-right corner of the maze

    public Blinky(GamePanel gp, Player p) {
        super(gp);
        this.player = p;
        direction = "left";
        speed = 2;
        bounds = new Rectangle(2, 2, 26, 26);
        boundX = bounds.x;
        boundsY = bounds.y;
        getBlinkyImage();
        modeSwitchTimer = System.currentTimeMillis();
    }

    public void getBlinkyImage() {
        try {
            character = ImageIO.read(getClass().getResource("/resources/images/characters/RedLeft.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAction() {
        long currentTime = System.currentTimeMillis();
        Point target = new Point(player.x, player.y);

        // Toggle between scatter and chase mode
        if ((currentTime - modeSwitchTimer) < modeSwitchInterval) {
            target = scatterTarget;
        } else {
            if ((currentTime - modeSwitchTimer) >= 2 * modeSwitchInterval) {
                modeSwitchTimer = currentTime;
            }
        }

        moveToGivenSquare(target);
    }
}
