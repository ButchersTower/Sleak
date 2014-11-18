package Sleak;

import javax.swing.JFrame;

public class Sleak extends JFrame {
	public Sleak() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new PanelIndie());
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Sleak");
	}

	public static void main(String[] args) {
		new Sleak();
	}
}
