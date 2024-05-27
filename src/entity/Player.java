package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Player class represents the player-controlled character in the game.
 */
public class Player extends Entity {
    KeyHandler keyHandler;
    int points = 0 ;

    /**
     * Constructor for the Player class.
     * @param gp The GamePanel instance.
     * @param keyHandler The KeyHandler instance.
     */
    public Player(GamePanel gp, KeyHandler keyHandler) {
        super(gp);
        this.keyHandler = keyHandler;
        this.setDefoultValues();
        this.getPlayerImage();
        bounds = new Rectangle(2,2,26,26);
        boundX = bounds.x;
        boundsY = bounds.y;
    }
    /**
     * Sets default values for the player.
     */
    public void setDefoultValues(){
        x = 300;
        y = 300;
        speed = 2;
        direction = "down";
        LastDirection = "";
    }
    /**
     * Sets default values for the player.
     */
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

    /**
     * Updates the player's position and state based on user input and collisions.
     */
    public void update() {
        System.out.println("THRED PLAYER" + Thread.currentThread().getName());
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
        if(points == 311){
            gp.gameState = gp.endState;
        }
        gp.addPosition(new Point(x, y));


    }

    /**
     * Handles interactions with non-playable characters (NPCs).
     * @param npcIndex The index of the NPC collided with.
     */
    public void interactNPC(int npcIndex) {
        if(npcIndex!= 999 && !panicMode){
            //System.out.println("Kolicaj z npc");
            gp.gameState = gp.loseState;
        }
    }
    /**
     * Picks up objects within the game world.
     * @param i The index of the object picked up.
     */
    public void pickUpObject(int i){
        if(i != 999){
            gp.obj[i] = null;
            points += 1;
        }
    }
    /**
     * Picks up heart objects within the game world.
     * @param i The index of the heart object picked up.
     */
    public void pickUpHeart(int i){
        //System.out.println(i);
        if(i != 999){
            gp.heart[i] = null;
            Entity.triggerPanicMode();
        }
    }
    /**
     * Draws the player on the screen.
     * @param g2d The Graphics2D object to draw with.
     */
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
