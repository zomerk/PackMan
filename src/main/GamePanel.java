package main;

import background.TileManager;
import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 28; // 16x16 packman size
    final int scale = 1;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 27;
    public int maxScreenRow = 29;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;

    double FPS = 60;

    TileManager tileManager = new TileManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyHandler);




    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    //game loop
    @Override
    public void run() {

        double drawInterval = 1_000_000_000/FPS;
        double nextDrawTIme = System.nanoTime() + drawInterval;
        while(gameThread.isAlive()){


            update();

            repaint();

            try{
                double remainngTime = nextDrawTIme - System.nanoTime();
                remainngTime = remainngTime / 1_000_000;
                if(remainngTime < 0){
                    remainngTime = 0;
                }
                Thread.sleep((long)remainngTime);

                nextDrawTIme += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileManager.draw(g2d);
        player.draw(g2d);
        g2d.dispose();

    }
}



// https://arcarc.xmission.com/Web%20Archives/Pac%20Man/Pacman%20Graphics/PACANAC.htm