package main;

import background.TileManager;
import entity.Entity;
import entity.Player;
import object.superObject;
import screens.Screens;

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
    KeyHandler keyHandler = new KeyHandler(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    Player player = new Player(this,keyHandler);
    public AssetSetter assetSetter = new AssetSetter(this, tileManager,player);
    Thread gameThread;
    public superObject[] obj = new superObject[330];
    public superObject[] heart = new superObject[4];
    public Screens screens = new Screens(this);
    public Entity npc[] = new Entity[4];

   public int gameState;
   public final int playState = 1;
   public final int pauseState = 2;
   public final int endState = 3;
   public final int loseState = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    public void setupGame(){
        assetSetter.setObject();
        assetSetter.setNPC();
        gameState = playState;
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
            if(gameState == 4){
                break;
            }

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
        if(gameState == playState) {
            player.update();
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
        }
        if(gameState == pauseState){

        }
        if(gameState == endState){

        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileManager.draw(g2d);
        for(int i =0; i < obj.length; i++){
            if(obj[i]!= null){
                obj[i].draw(g2d,this);
            }
        }
        for(int i =0; i < npc.length; i++){
            if(npc[i]!= null){
                npc[i].draw(g2d);
            }
        }
        for(int i =0; i < heart.length; i++){
            if(heart[i]!= null){
                heart[i].draw(g2d,this);
            }
        }
        player.draw(g2d);
        screens.draw(g2d);
        g2d.dispose();

    }
}



// https://arcarc.xmission.com/Web%20Archives/Pac%20Man/Pacman%20Graphics/PACANAC.htm