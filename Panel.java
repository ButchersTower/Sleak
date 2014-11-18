package Sleak;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Panel extends JPanel implements MouseListener, KeyListener,
		MouseMotionListener {
	// Clicking mouse draws the screen.

	int width = 420;
	int height = 400;

	Image[] imageAr;

	Thread thread;
	Image image;
	Graphics g;

	// Vars for gLoop Above

	// x, y, width height
	int[] panelInfo = { 10, 20, 120, 180 };

	// px margin in all sides
	int margin = 4;

	// top margin
	int topMarg = 12;

	public Panel() {
		super();

		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		this.setSize(new Dimension(width, height));

		pStart();
	}

	/**
	 * Methods go below here.
	 * 
	 */

	public void pStart() {
		imageInit();

	}

	void drawSleak() {
		// draw margin. Can be either 4 rectangles around the edge of or 1 large
		// rectangle which gets overlaped in the middle.
		g.setColor(Color.DARK_GRAY);
		g.fillRect(panelInfo[0], panelInfo[1], panelInfo[2], panelInfo[3]);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(panelInfo[0] + margin, panelInfo[1] + margin + topMarg,
				panelInfo[2] - 2 * margin, panelInfo[3] - 2 * margin - topMarg);
	}

	// x indent, y indent. radius
	int[] exitInfo = { 18, 2, 6 };

	void roundSleak() {
		g.setColor(Color.DARK_GRAY);
		g.fillRoundRect(panelInfo[0], panelInfo[1], panelInfo[2], panelInfo[3],
				12, 12);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(panelInfo[0] + margin, panelInfo[1] + margin + topMarg,
				panelInfo[2] - 2 * margin, panelInfo[3] - 2 * margin - topMarg,
				12, 12);
		// draw an exit button. top right.
		g.setColor(Color.RED);
		g.fillOval(panelInfo[0] + panelInfo[2] - exitInfo[0], panelInfo[1]
				+ exitInfo[1], exitInfo[2] * 2, exitInfo[2] * 2);
	}

	void draw() {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		roundSleak();
	}

	boolean clickTop = false;

	void seachForTop(int mouseX, int mouseY) {
		// first check to see if user has clicked on the exit button.
		float hype = (float) Math.sqrt(Math.pow((panelInfo[0] + panelInfo[2]
				- exitInfo[0] + exitInfo[2])
				- mouseX, 2)
				+ Math.pow((panelInfo[1] + exitInfo[1] + exitInfo[2]) - mouseY,
						2));
		if (hype <= exitInfo[2]) {
			System.out.println("EXIT");
		}

		// sees if the button press is on the top bar of the Sleak
		if (mouseY - panelInfo[1] >= 0
				&& mouseY - (panelInfo[1] + topMarg) <= 2
				&& mouseX - panelInfo[0] >= 0
				&& mouseX - (panelInfo[0] + panelInfo[2]) <= 0) {
			// System.out.println("topBar");
			clickTop = true;
		} else {
			clickTop = false;
		}
	}

	/**
	 * Methods go above here.
	 * 
	 */

	public void drwGm() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

	public void imageInit() {
		/**
		 * imageAr = new Image[1]; ImageIcon ie = new
		 * ImageIcon(this.getClass().getResource( "res/image.png")); imageAr[0]
		 * = ie.getImage();
		 */

	}

	@Override
	public void mousePressed(MouseEvent me) {
		draw();
		drwGm();
		seachForTop(me.getX(), me.getY());
		mouseLast[0] = me.getX();
		mouseLast[1] = me.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	int[] mouseLast = new int[2];

	@Override
	public void mouseDragged(MouseEvent me) {
		if (clickTop) {
			int[] delta = { me.getX() - mouseLast[0], me.getY() - mouseLast[1] };
			panelInfo[0] += delta[0];
			panelInfo[1] += delta[1];
			mouseLast[0] = me.getX();
			mouseLast[1] = me.getY();
			draw();
			drwGm();
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
