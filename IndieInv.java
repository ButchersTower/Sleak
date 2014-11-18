package Sleak;

import java.awt.Color;
import java.awt.Graphics;

public class IndieInv {
	int x, y, width, height, margin, topMargin, exitXundent, exitYindent,
			exitRadius;
	boolean closed = false;
	boolean clickTop = false;

	// 12 inventory slots
	int[][] inv = new int[12][2];

	String[] items = { "Axe", "Plank" };

	public IndieInv(int x, int y, int width, int height, int margin,
			int topMargin, int exitXundent, int exitYindent, int exitRadius) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.margin = margin;
		this.topMargin = topMargin;
		this.exitXundent = exitXundent;
		this.exitYindent = exitYindent;
		this.exitRadius = exitRadius;
		invColumns = (width - 2 * margin) / (invMargin + itemWidth);
		// the -invMargin is to make sure there is proper margin on the right
		// side.
		// invColumns = (width - 2 * margin - invMargin) / (invMargin +
		// itemWidth);
	}

	void draw(Graphics g) {
		if (!closed) {
			// draws out line
			g.setColor(Color.DARK_GRAY);
			g.fillRoundRect(x, y, width, height, 12, 12);
			// draws inner panel
			g.setColor(Color.LIGHT_GRAY);
			g.fillRoundRect(x + margin, y + margin + topMargin, width - 2
					* margin, height - 2 * margin - topMargin, 12, 12);
			// draw an exit button. top right.
			g.setColor(Color.RED);
			g.fillOval(x + width - exitXundent, y + exitYindent,
					exitRadius * 2, exitRadius * 2);
			drawInv(g);
		}
	}

	void clickHandle(int mouseX, int mouseY) {
		// first check to see if user has clicked on the exit button.
		float hype = (float) Math.sqrt(Math.pow(
				(x + width - exitXundent + exitRadius) - mouseX, 2)
				+ Math.pow((y + exitYindent + exitRadius) - mouseY, 2));
		if (hype <= exitRadius) {
			// System.out.println("EXIT");
			closed = true;
		}

		// sees if the button press is on the top bar of the Sleak
		if (mouseY - y >= 0 && mouseY - (y + topMargin) <= 2 && mouseX - x >= 0
				&& mouseX - (x + width) <= 0) {
			clickTop = true;
		} else {
			clickTop = false;
		}
	}

	public boolean lapCheck(int mouseX, int mouseY) {
		if (!closed) {
			if (mouseX - x >= 0 && mouseX - x - width <= 0 && mouseY - y >= 0
					&& mouseY - y - height <= 0) {
				// overlap
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	void addItem(int item) {
		// use the next available slot.
		loopa: for (int i = 0; i < items.length; i++) {
			if (inv[i][0] == 0) {
				inv[i][0] = item;
				inv[i][1] = 1;
				break loopa;
			}
		}
	}

	void addItem(int item, int quantity) {
		// use the next available slot.
		loopa: for (int i = 0; i < items.length; i++) {
			if (inv[i][0] == 0) {
				inv[i][0] = item;
				inv[i][1] = quantity;
				break loopa;
			}
		}
	}

	void addItem(int item, int quantity, int slot) {
		// if slot is full then use the next available slot.
		if (inv[slot][0] == 0) {
			inv[slot][0] = item;
			inv[slot][1] = quantity;
		} else {
			loopa: for (int i = 0; i < items.length; i++) {
				if (inv[i][0] == 0) {
					inv[i][0] = item;
					inv[i][1] = quantity;
					break loopa;
				}
			}
		}
	}

	int invMargin = 6;
	int itemWidth = 16;
	int invColumns;

	void drawInv(Graphics g) {
		// I should even out the margins on both sides of the inv slots
		// draw x and y
		int dx = 0;
		int dy = -1;
		for (int i = 0; i < inv.length; i++) {
			if (i % invColumns == 0) {
				dy++;
				dx = 0;
			}
			// g.setColor(Color.)
			g.fillRect(x + margin + invMargin + dx * (invMargin + itemWidth), y
					+ margin + topMargin + invMargin + dy
					* (invMargin + itemWidth), itemWidth, itemWidth);

			dx++;
		}
	}
}
