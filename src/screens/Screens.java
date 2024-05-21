package screens;

import main.GamePanel;

import java.awt.*;

public class Screens {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40,arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameOver = false;

    public Screens(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }
    public void showMessage(String message) {
        messageOn = true;
        this.message = message;
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        if(gp.gameState == gp.playState){

        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if(gp.gameState == gp.endState){
            drawEndScreen();
        }

    }

    public void drawEndScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50f));
        String text = "YOU WON";
        g2.drawString(text,280,400);
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50f));
        String text = "PAUSED";
        g2.drawString(text,280,400);
   }

}
