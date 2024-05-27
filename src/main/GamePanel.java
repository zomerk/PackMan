package main;

import background.TileManager;
import entity.Entity;
import entity.Player;
import object.superObject;
import screens.Screens;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * GamePanel class is responsible for the main game screen and game loop.
 * It initializes game components, handles game state, and manages rendering.
 */
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
    Player player = new Player(this, keyHandler);
    public AssetSetter assetSetter = new AssetSetter(this, tileManager, player);
    Thread gameThread;
    public superObject[] obj = new superObject[330];
    public superObject[] heart = new superObject[4];
    public Screens screens = new Screens(this);
    public Entity[] npc = new Entity[4];
    public LinkedList<Point> positionQueue = new LinkedList<>();
    private boolean allMoved;
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int endState = 3;
    public final int loseState = 4;

    /**
     * Constructor for GamePanel. Initializes the game panel's size, background color,
     * and key listener, and sets it to be focusable.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    /**
     * Sets up the game by placing objects and NPCs on the game panel.
     * Initializes the game state to playState.
     */
    public void setupGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        gameState = playState;
    }

    /**
     * Starts the game thread.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * The main game loop. Manages game updates and rendering at a consistent frame rate.
     */
    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread.isAlive()) {
            if (gameState == loseState) {
                break;
            }

            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1_000_000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Adds a position to the position queue. When the queue is full (size 3),
     * it sets the flag to notify that all entities have moved.
     * @param position The position to add to the queue.
     */
    public synchronized void addPosition(Point position) {
        if (positionQueue.size() < 4) {
            positionQueue.add(position);
        }

        if (positionQueue.size() == 4) {
            allMoved = true;
            notify();
        }
    }

    /**
     * Waits until all entities have moved before continuing.
     */
    public synchronized void waitForAllMoves() {
        while (!allMoved) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        allMoved = false;
        positionQueue.clear();
    }

    /**
     * Updates the state of the game entities based on the current game state.
     */
    public void update() {
        if (gameState == playState) {
            new Thread(player::update).start();
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    new Thread(npc[i]::update).start();
                }
            }
        }
    }

    /**
     * Renders the game objects and entities on the screen.
     * @param g The Graphics object used for drawing.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        tileManager.draw(g2d);

        for (superObject obj : obj) {
            if (obj != null) {
                obj.draw(g2d, this);
            }
        }

        for (Entity entity : npc) {
            if (entity != null) {
                entity.draw(g2d);
            }
        }

        for (superObject heart : heart) {
            if (heart != null) {
                heart.draw(g2d, this);
            }
        }

        player.draw(g2d);
        screens.draw(g2d);
        g2d.dispose();
    }
}
