package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        boolean end = false;
        while(true) {
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("Packmanc");
            GamePanel gamePanel = new GamePanel();
            window.add(gamePanel);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            gamePanel.setupGame();
            gamePanel.startGameThread();
            while(gamePanel.gameThread.isAlive()) {
                if(gamePanel.gameState == gamePanel.loseState){
                    while(true){
                        if(gamePanel.keyHandler.enterPressed){
                            System.out.println(gamePanel.gameThread.isAlive());
                            break;
                        }
                        if(gamePanel.keyHandler.escapePressed){
                            end = true;
                            break;
                        }
                    }
                }
            }
            window.dispose();
            if(end){
                break;
            }
        }
    }
}
