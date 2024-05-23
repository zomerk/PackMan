package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyHandler;
    int points = 0 ;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        super(gp);
        this.keyHandler = keyHandler;
        this.setDefoultValues();
        this.getPlayerImage();
        bounds = new Rectangle(2,2,26,26);
        boundX = bounds.x;
        boundsY = bounds.y;
    }
    public void setDefoultValues(){
        x = 300;
        y = 300;
        speed = 2;
        direction = "down";
        LastDirection = "";
    }
    public void getPlayerImage(){
        try{
            up = ImageIO.read(getClass().getResource("/resources/images/characters/PacmanUp.png"));
            down = ImageIO.read(getClass().getResource("/resources/images/characters/PacmanDown.png"));
            left = ImageIO.read(getClass().getResource("/resources/images/characters/PacmanLeft.png"));
            right = ImageIO.read(getClass().getResource("/resources/images/characters/PacmanRight.png"));

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void update() {
        if(points == 321){
            gp.gameState = gp.endState;
        }
        String directionBeforePotencialColision = direction;
        if (keyHandler.upPressed) {
            direction = "up";
            gp.collisionChecker.checkTile(this);
            if (collisionOn == false) {
                y -= speed;

            }
         else {
             LastDirection = "up";
             keyHandler.lastButtonPressed(directionBeforePotencialColision);
             direction = directionBeforePotencialColision;
            }
        }
        else if(keyHandler.downPressed){
            direction = "down";
            gp.collisionChecker.checkTile(this);
            if (collisionOn == false) {
                y += speed;

            }
            else {
                LastDirection = "down";
                keyHandler.lastButtonPressed(directionBeforePotencialColision);
                direction = directionBeforePotencialColision;
            }

        }
        else if(keyHandler.leftPressed){
            direction = "left";
            gp.collisionChecker.checkTile(this);
            if (collisionOn == false) {
                x -= speed;

            }
            else {
                LastDirection = "left";
                keyHandler.lastButtonPressed(directionBeforePotencialColision);
                direction = directionBeforePotencialColision;
            }
        }
        else if(keyHandler.rightPressed){
            direction = "right";
            gp.collisionChecker.checkTile(this);
            if (collisionOn == false) {
                x += speed;

            }
            else {
                LastDirection = "right";
                keyHandler.lastButtonPressed(directionBeforePotencialColision);
                direction = directionBeforePotencialColision;
            }
        }
        if(!LastDirection.equals(direction)) {
            switch (LastDirection) {
                case "up":
                    direction = "up";
                    gp.collisionChecker.checkTile(this);
                    if (collisionOn == false) {
                        y -= speed;
                        keyHandler.lastButtonPressed(direction);
                        LastDirection = "";
                    } else {
                        keyHandler.lastButtonPressed(directionBeforePotencialColision);
                        direction = directionBeforePotencialColision;
                    }
                    break;
                case "down":
                    direction = "down";
                    gp.collisionChecker.checkTile(this);
                    if (collisionOn == false) {
                        y += speed;
                        keyHandler.lastButtonPressed(direction);
                        LastDirection = "";
                    } else {
                        keyHandler.lastButtonPressed(directionBeforePotencialColision);
                        direction = directionBeforePotencialColision;
                    }
                    break;
                case "left":
                    direction = "left";
                    gp.collisionChecker.checkTile(this);
                    if (collisionOn == false) {
                        x -= speed;
                        keyHandler.lastButtonPressed(direction);
                        LastDirection = "";
                    } else {
                        keyHandler.lastButtonPressed(directionBeforePotencialColision);
                        direction = directionBeforePotencialColision;
                    }
                    break;
                case "right":
                    direction = "right";
                    gp.collisionChecker.checkTile(this);
                    if (collisionOn == false) {
                        x += speed;
                        keyHandler.lastButtonPressed(direction);
                        LastDirection = "";
                    } else {
                        keyHandler.lastButtonPressed(directionBeforePotencialColision);
                        direction = directionBeforePotencialColision;
                    }
                    break;
            }

       }
        else{
            LastDirection = "";
        }
        Player.playerDirection = direction;
        int objIndex = gp.collisionChecker.checkObject(this,true);
        pickUpObject(objIndex);
        int  npcIndex = gp.collisionChecker.checkEntity(this,gp.npc);
        interactNPC(npcIndex);
        int heartCollision = gp.collisionChecker.checkHeart(this,true);
        pickUpHeart(heartCollision);
        //System.out.println(objIndex);
        //System.out.println(points);


    }

    public void interactNPC(int npcIndex) {
        if(npcIndex!= 999 && !panicMode){
            //System.out.println("Kolicaj z npc");
            gp.gameState = gp.loseState;
        }
    }

    public void pickUpObject(int i){
        if(i != 999){
            gp.obj[i] = null;
            points += 1;
        }
    }
    public void pickUpHeart(int i){
        //System.out.println(i);
        if(i != 999){
            gp.heart[i] = null;
            Entity.triggerPanicMode();
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
