package main;

import javax.swing.*;

import entity.Player;
import entity.Obstacle;
import object.SuperObject;
import tiles.TileManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class  GamePanel extends JPanel implements Runnable {
    final int fps = 60; // Frames per second
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 2; // Scale the tile size by 3
    
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    
    public final int maxScreenCol = 32; // 32 tiles across the screen
    public final int maxScreenRow = 24; // 24 tiles down the screen
    
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels wide
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels tall

    public CollisionChecker cChecker = new CollisionChecker(this);
    public KeyHandler keyH;
    public Player player = new Player(this, keyH);

    public AssetSetter aSetter = new AssetSetter(this);
    public TileManager tileM = new TileManager(this);

    public UI ui = new UI(this);
    public Map<String, ArrayList<SuperObject>> mapObjects;
    public ArrayList<SuperObject> currentMapObjects;
    public Map<String, ArrayList<Obstacle>> mapObstacles = new HashMap<>();
    public ArrayList<Obstacle> currentMapObstacles;

    public HashMap<String, Integer> mapTotalChestCounts = new HashMap<>();
    public int totalChestsOnCurrentMap = 0;
    public int currentMapCollectedChests = 0;


    public String currentMap = "map1";
    public int elapsedSeconds = 0;
    private int timerFrameCounter = 0;

    public long elapsedMillis = 0;
    private long lastUpdateTime = System.nanoTime();

    Thread gameThread;

    public GamePanel() {
        keyH = new KeyHandler();
        this.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        this.setBackground(java.awt.Color.black);
        this.setDoubleBuffered(true); // Enable double buffering for smoother graphics
        this.addKeyListener(keyH); // Add the key listener to the panel
        this.setFocusable(true); // Make the panel focusable to receive key events

        mapObjects = new HashMap<>();
        mapObjects.put("map1", new ArrayList<>());
        mapObjects.put("map2", new ArrayList<>());
        mapObjects.put("map3", new ArrayList<>());

        mapObstacles.put("map1", new ArrayList<>());
        mapObstacles.put("map2", new ArrayList<>());
        mapObstacles.put("map3", new ArrayList<>());


        mapTotalChestCounts.put("map1", 3);
        mapTotalChestCounts.put("map2", 4);
        mapTotalChestCounts.put("map3", 4);

        currentMapObjects = mapObjects.get(currentMap);
        currentMapObstacles = mapObstacles.get(currentMap);
        tileM = new TileManager(this);
        player = new Player(this,keyH);
        aSetter = new AssetSetter(this);
    }

    public void startGameThread() {
        gameThread = new Thread(this); // Create a new thread for the game loop
        gameThread.start();

        setupGame();// Start the thread, which will call the run() method
    }

    public void setupGame() {
        // This should be called only ONCE at the beginning of the game.
        aSetter.setObjectsForAllMaps(); // Call the AssetSetter
        aSetter.setObstaclesForAllMaps(); // Add this line
        // (Ensure your player.getPlayerImage() and tileM.getTileImage() are also called)
        totalChestsOnCurrentMap = mapTotalChestCounts.get(currentMap);
        player.getPlayerImage(); // Make sure player images are loaded
        tileM.getTileImage(); // Make sure tile images are loaded
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / fps; // Nanoseconds per frame (1 second / fps)
        double delta = 0; // Time difference between frames
        long lastTime = System.nanoTime(); // Get the current time in nanoseconds
        long currentTime; // Variable to sto re the current time
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime(); // Get the current time in nanoseconds
            delta += (currentTime - lastTime) / drawInterval; // Calculate the time difference
            timer += (currentTime  - lastTime);
            lastTime = currentTime; // Update the last time

            if (delta >= 1) { // If enough time has passed for a new frame
                update(); // Update the game state
                repaint(); // Repaint the panel
                delta--; // Decrease delta by 1 to account for the frame
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
        if (currentMapObstacles != null) {
            for (Obstacle obs : currentMapObstacles) {
                obs.update();
            }
        }
        // --- TIMER LOGIC (real time, with ms) ---
        long now = System.nanoTime();
        elapsedMillis += (now - lastUpdateTime) / 1_000_000;
        lastUpdateTime = now;
    }

    // In GamePanel.java
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g); // Call parent paintComponent

        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);

        if (currentMapObjects != null) {
            for(SuperObject obj : currentMapObjects) {
                obj.draw(g2, this);
            }
        } else {
        }

        if (currentMapObstacles != null) {
            for (Obstacle obs : currentMapObstacles) {
                obs.draw(g2);
            }
        }

        player.draw(g2);

        ui.draw(g2);

        g2.dispose();
    }
}
