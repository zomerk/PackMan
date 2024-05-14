import javax.swing.*;
import java.awt.*;
import java.security.Key;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 packman size
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 28;
    final int maxScreenRow = 36;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;

    double FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    //Default Position
    int packmanX = 100;
    int packmanY = 100;
    int playerSpeed = 4;


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
        if(keyHandler.upPressed){
            packmanY -= playerSpeed;

        }
        else if(keyHandler.downPressed){
            packmanY += playerSpeed;
        }
        else if(keyHandler.leftPressed){
            packmanX -= playerSpeed;
        }
        else if(keyHandler.rightPressed){
            packmanX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(packmanX, packmanY, tileSize, tileSize);
        g2d.dispose();

    }
}