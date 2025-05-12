package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		// window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Robbery Cy");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack(); // Adjusts the window size to fit the preferred size of the game panel

		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.startGameThread(); // Start the game thread after the window is visible

	}

}
