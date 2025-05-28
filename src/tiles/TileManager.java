package tiles;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum; // 2D array to store the tile numbers

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10]; // Assuming you have 10 tiles, adjust as necessary
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow]; // Initialize the map array
        getTileImage();
        loadMap("/maps/map1.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/2.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/3.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor2.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor5.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor6.png"));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void loadMap(String mapPath) {
        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while (col < gp.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num; // Store the tile number in the array
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close(); // Close the BufferedReader
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
    }

    public void changeMap(String mapName) {
        String mapPath = "/maps/" + mapName + ".txt"; // Construct the map file path
        loadMap(mapPath); // Load the new map
        gp.player.resetPosition(); // Reset the player's position
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row]; // Get the tile number from the array
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
