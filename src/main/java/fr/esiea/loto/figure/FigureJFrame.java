package fr.esiea.loto.figure;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class FigureJFrame {
	
	public static JFrame frame;
	public static void showOnFrame(JComponent component, String frameName, int choseColorBackground) {
		 frame = new JFrame(frameName);
		WindowAdapter wa = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// Do nothing
			}
		};

		
		frame.addWindowListener(wa);
		frame.getContentPane().add(component);
		frame.isResizable();
		final JPanel buttonBar = new JPanel();
		frame.getContentPane().add(buttonBar, BorderLayout.SOUTH);
		switch (choseColorBackground) {
		case 0:
			buttonBar.setBackground(Color.BLACK);
			break;
		case 1:
			buttonBar.setBackground(Color.BLUE);
			break;
		case 2:
			buttonBar.setBackground(Color.CYAN);
			break;
		case 3:
			buttonBar.setBackground(Color.DARK_GRAY);
			break;
		case 4:
			buttonBar.setBackground(Color.GRAY);
			break;
		case 5:
			buttonBar.setBackground(Color.GREEN);
			break;
		case 6:
			buttonBar.setBackground(Color.LIGHT_GRAY);
			break;
		case 7:
			buttonBar.setBackground(Color.MAGENTA);
			break;
		case 8:
			buttonBar.setBackground(Color.ORANGE);
			break;
		case 9:
			buttonBar.setBackground(Color.PINK);
			break;
		case 10:
			buttonBar.setBackground(Color.RED);
			break;
		case 11:
			buttonBar.setBackground(Color.WHITE);
			break;
		case 12:
			buttonBar.setBackground(Color.YELLOW);
			break;

		}
		final JButton buttonSave = new JButton(new SaveFigure());
		buttonBar.add(buttonSave);
		frame.pack();
		frame.setVisible(true);
		
	

	}
	
	private static class SaveFigure extends AbstractAction {

		private static final long serialVersionUID = 1L;
	

		public SaveFigure() {
			super("Save");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			File file = new File("images/figure.png");
			file.getParentFile().mkdirs();
			BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D graphics = image.createGraphics();
			frame.paint(graphics);
			graphics.dispose();

			try {
				ImageIO.write(image, "PNG", file);
			} catch (Exception e) {
			}
			
		}
		
	}

}
