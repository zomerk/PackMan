package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The KeyHandler class handles keyboard input for the game.
 * It implements the KeyListener interface and provides methods
 * to handle key presses and updates the game state accordingly.
 */
public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    /**
     * Constructor for the KeyHandler class.
     * @param gp The GamePanel object associated with this KeyHandler.
     */
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Handles key press events.
     * @param e The KeyEvent triggered when a key is pressed.
     */
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
        if (code == KeyEvent.VK_ESCAPE) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            } else if (gp.gameState == gp.endState) {
                gp.gameState = 4;
            }
        }
    }

    /**
     * Not used but required to implement KeyListener.
     * @param e The KeyEvent triggered when a key is typed.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    /**
     * Not used but required to implement KeyListener.
     * @param e The KeyEvent triggered when a key is released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }

    /**
     * Updates the direction keys based on the last button pressed.
     * @param direction The direction in which the last button was pressed ("up", "down", "left", "right").
     */
    public void lastButtonPressed(String direction) {
        if (direction.equals("up")) {
            upPressed = true;
            downPressed = false;
            leftPressed = false;
            rightPressed = false;
        }
        if (direction.equals("down")) {
            downPressed = true;
            upPressed = false;
            leftPressed = false;
            rightPressed = false;
        }
        if (direction.equals("left")) {
            leftPressed = true;
            upPressed = false;
            downPressed = false;
            rightPressed = false;
        }
        if (direction.equals("right")) {
            rightPressed = true;
            upPressed = false;
            downPressed = false;
            leftPressed = false;
        }
    }
}
