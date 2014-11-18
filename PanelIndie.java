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


public class PanelIndie extends JPanel implements MouseListener, KeyListener,
		MouseMotionListener {
	// Clicking mouse draws the screen.

	int width = 420;
	int height = 400;

	Image[] imageAr;

	Thread thread;
	Image image;
	Graphics g;

	// Vars for gLoop Above

	int[] mouseLast = new int[2];
	IndieInv[] panel;

	public PanelIndie() {
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
		panel = new IndieInv[2];
		for (int p = 0; p < panel.length; p++) {
			panel[p] = new IndieInv(10 + p * 14, 20 + p * 24, 120, 180, 4, 12, 18,
					2, 6);
		}
	}

	void draw() {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		for (int p = 0; p < panel.length; p++) {
			panel[p].draw(g);
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
		for (int p = 0; p < panel.length; p++) {
			panel[p].clickHandle(me.getX(), me.getY());
		}
		mouseLast[0] = me.getX();
		mouseLast[1] = me.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		draw();
		drwGm();
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

	@Override
	public void mouseDragged(MouseEvent me) {
		int[] delta = { me.getX() - mouseLast[0], me.getY() - mouseLast[1] };
		mouseLast[0] = me.getX();
		mouseLast[1] = me.getY();
		for (int p = 0; p < panel.length; p++) {
			if (panel[p].clickTop) {
				panel[p].x += delta[0];
				panel[p].y += delta[1];
				draw();
				drwGm();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
