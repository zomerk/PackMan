package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x;
    public int y;
    public int speed;
    public BufferedImage up, down, left, right;
    public String direction;
    public String LastDirection;
    public Rectangle bounds;
    public boolean collisionOn = false;
}
