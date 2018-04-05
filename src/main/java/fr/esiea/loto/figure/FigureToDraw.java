package fr.esiea.loto.figure;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

public class FigureToDraw extends JPanel {

	private static final long serialVersionUID = 1L;
	private List<Integer> stars;
	private List<Integer> balls;
	private static final Logger log = Logger.getLogger(FigureToDraw.class);

	public FigureToDraw(List<Integer> balls, List<Integer> stars) {
		this.stars = stars;
		this.balls = balls;
	}

	private void drawTree(Graphics g, int x1, int y1, double angle, int iteration) {
		if (iteration == 0)
			return;
		int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * iteration * 10.0);
		int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * iteration * 10.0);
		g.drawLine(x1, y1, x2, y2);
		drawTree(g, x2, y2, angle - 20, iteration - 1);
		drawTree(g, x2, y2, angle + 20, iteration - 1);
	}
	


	public void paint(Graphics g) {

		int choseTreeColor = balls.get(1) % 13;
		switch (choseTreeColor) {
		case 0:
			g.setColor(Color.BLACK);
			break;
		case 1:
			g.setColor(Color.blue);
			break;
		case 2:
			g.setColor(Color.CYAN);
			break;
		case 3:
			g.setColor(Color.DARK_GRAY);
			break;
		case 4:
			g.setColor(Color.GRAY);
			break;
		case 5:
			g.setColor(Color.GREEN);
			break;
		case 6:
			g.setColor(Color.LIGHT_GRAY);
			break;
		case 7:
			g.setColor(Color.MAGENTA);
			break;
		case 8:
			g.setColor(Color.ORANGE);
			break;
		case 9:
			g.setColor(Color.PINK);
			break;
		case 10:
			g.setColor(Color.RED);
			break;
		case 11:
			g.setColor(Color.WHITE);
			break;
		case 12:
			g.setColor(Color.YELLOW);
			break;

		}
		log.debug(choseTreeColor);
		drawTree(g, 400, 500, stars.get(0) * (-10), stars.get(1));

	}

}
