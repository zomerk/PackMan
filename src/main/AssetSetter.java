package main;
import background.TileManager;
import entity.Blinky;
import entity.Clyde;
import entity.Pinky;
import entity.Player;
import object.Heart;
import object.Point;

/**
 * The AssetSetter class is responsible for setting up game assets such as points, hearts, and NPCs.
 */
public class AssetSetter {
    GamePanel gp;
    TileManager tm;
    Player player;

    /**
     * Constructor for the AssetSetter class.
     * @param gp The GamePanel instance.
     * @param tm The TileManager instance.
     * @param player The Player instance.
     */
    public AssetSetter(GamePanel gp, TileManager tm, Player player) {
        this.gp = gp;
        this.tm = tm;
        this.player = player;

    }

    /**
     * Sets up points and hearts within the game world.
     */
    public void setObject() {
        int k = 0;
        for(int i = 0; i < gp.maxScreenCol; i++) {
            for (int j = 0; j < gp.maxScreenRow; j++) {
                if (tm.mapTileNum[i][j] == 0) {
                    gp.obj[k] = new Point();
                    gp.obj[k].wordX = i * gp.tileSize;
                    gp.obj[k].wordY = j * gp.tileSize;
                    gp.obj[k].bounds.x = gp.obj[k].wordX;
                    gp.obj[k].bounds.y = gp.obj[k].wordY;
                    k++;
                    //System.out.println(k);
                }
            }
        }
        gp.heart[0] = new Heart();
        gp.heart[0].wordX = 1*gp.tileSize;
        gp.heart[0].wordY = 1*gp.tileSize;
        gp.heart[0].bounds.x = gp.heart[0].wordX;
        gp.heart[0].bounds.y = gp.heart[0].wordY;
        gp.heart[1] = new Heart();
        gp.heart[1].wordX = 1*gp.tileSize;
        gp.heart[1].wordY = 27*gp.tileSize;
        gp.heart[1].bounds.x = gp.heart[1].wordX;
        gp.heart[1].bounds.y = gp.heart[1].wordY;
        gp.heart[2] = new Heart();
        gp.heart[2].wordX = 25*gp.tileSize;
        gp.heart[2].wordY = 1*gp.tileSize;
        gp.heart[2].bounds.x = gp.heart[2].wordX;
        gp.heart[2].bounds.y = gp.heart[2].wordY;
        gp.heart[3] = new Heart();
        gp.heart[3].wordX = 25*gp.tileSize;
        gp.heart[3].wordY = 27*gp.tileSize;
        gp.heart[3].bounds.x = gp.heart[3].wordX;
        gp.heart[3].bounds.y = gp.heart[3].wordY;

    }

    /**
     * Sets up points and hearts within the game world.
     */
    public void setNPC(){
         gp.npc[0] = new Blinky(gp,player);
         gp.npc[0].x = gp.tileSize*13;
         gp.npc[0].y = gp.tileSize*13;

         gp.npc[1] = new Pinky(gp,player);
         gp.npc[1].x = gp.tileSize*14;
         gp.npc[1].y = gp.tileSize*13;

         gp.npc[2] = new Clyde(gp,player);
         gp.npc[2].x = gp.tileSize*15;
         gp.npc[2].y = gp.tileSize*13;
    }
}
