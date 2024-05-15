package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.Key;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        this.setDefoultValues();
        this.getPlayerImage();
    }
    public void setDefoultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            up = ImageIO.read(getClass().getResourceAsStream("src/res/PackmanUp.png"));
            down = ImageIO.read(getClass().getResourceAsStream("src/res/PackmanDown.png"));
            left = ImageIO.read(getClass().getResourceAsStream("src/res/PackmanLeft.png"));
            right = ImageIO.read(getClass().getResourceAsStream("src/res/PackmanRight.png"));

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyHandler.upPressed){
            direction = "up";
            y -= speed;

        }
        else if(keyHandler.downPressed){
            direction = "down";
            y += speed;
        }
        else if(keyHandler.leftPressed){
            direction = "left";
            x -= speed;
        }
        else if(keyHandler.rightPressed){
            direction = "right";
            x += speed;
        }
    }
    public void draw(Graphics2D g2d){
        BufferedImage image = null;
        switch(direction){
            case "up":
                image = up;
                break;
                case "down":
                    image = down;
                break;
                case "left":
                    image = left;
                    break;
                        case "right":
                            image = right;
                        break;
        }
        g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
