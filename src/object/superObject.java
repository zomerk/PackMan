package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class superObject {
    public BufferedImage image;
    String name;
    public boolean collision = false;
    public int wordX, wordY;
    public Rectangle bounds = new Rectangle(wordX+14,wordY+14,2,2);
    public void draw(Graphics2D g2, GamePanel gp) {
        g2.drawImage(image, wordX, wordY, gp.tileSize, gp.tileSize, null);
    }
}
