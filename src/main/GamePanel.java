package main;

import javax.swing.JPanel;
import entity.Player;
import tiles.TileManager;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
    final int fps = 60; // Frames per second
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 2; // Scale the tile size by 3
    
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    
    public final int maxScreenCol = 32; // 32 tiles across the screen
    public final int maxScreenRow = 24; // 24 tiles down the screen
    
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels wide
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels tall

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);
    public TileManager tileM = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        this.setBackground(java.awt.Color.black);
        this.setDoubleBuffered(true); // Enable double buffering for smoother graphics
        this.addKeyListener(keyH); // Add the key listener to the panel
        this.setFocusable(true); // Make the panel focusable to receive key events
    }

    public void startGameThread() {
        gameThread = new Thread(this); // Create a new thread for the game loop
        gameThread.start(); // Start the thread, which will call the run() method
    } 

    @Override
    public void run() {
        double drawInterval = 1000000000 / fps; // Nanoseconds per frame (1 second / fps)
        double delta = 0; // Time difference between frames
        long lastTime = System.nanoTime(); // Get the current time in nanoseconds
        long currentTime; // Variable to store the current time

        while(gameThread != null) {
            currentTime = System.nanoTime(); // Get the current time in nanoseconds
            delta += (currentTime - lastTime) / drawInterval; // Calculate the time difference
            lastTime = currentTime; // Update the last time

            if (delta >= 1) { // If enough time has passed for a new frame
                update(); // Update the game state
                repaint(); // Repaint the panel
                delta--; // Decrease delta by 1 to account for the frame
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //update graphics to 2d
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2); 

        g2.dispose();
    }
}
