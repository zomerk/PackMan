package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler  implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    @Override
    public void keyTyped(KeyEvent e) {
    }
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = true;
            downPressed = false;
            leftPressed = false;
            rightPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
            upPressed = false;
            leftPressed = false;
            rightPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
            downPressed = false;
            upPressed = false;
            rightPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
            downPressed = false;
            upPressed = false;
            leftPressed = false;
        }
        if(code == KeyEvent.VK_ESCAPE) {
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
            else if(gp.gameState == gp.endState){
                gp.gameState = 4;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void lastButtonPressed(String direction){
        if(direction.equals("up")){
            upPressed = true;
            downPressed = false;
            leftPressed = false;
            rightPressed = false;
        }
        if(direction.equals("down")){
            downPressed = true;
            upPressed = false;
            leftPressed = false;
            rightPressed = false;
        }
        if(direction.equals("left")){
            leftPressed = true;
            upPressed = false;
            downPressed = false;
            rightPressed = false;
        }
        if(direction.equals("right")){
            rightPressed = true;
            upPressed = false;
            downPressed = false;
            leftPressed = false;
        }
    }
}
