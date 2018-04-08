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
	public static JPanel panel;
	public static void showOnFrame(JComponent component, String frameName, int choseColorBackground) {
		 frame = new JFrame(frameName);
		WindowAdapter wa = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// Do nothing
			}
		};

		panel = new JPanel();
		frame.addWindowListener(wa);
		panel.add(component);
		frame.getContentPane().add(panel);
		frame.isResizable();
		
		
		final JPanel buttonBar = new JPanel();
		frame.getContentPane().add(buttonBar, BorderLayout.SOUTH);
		
		
		switch (choseColorBackground) {
		case 0:
			panel.setBackground(Color.BLACK);
			break;
		case 1:
			panel.setBackground(Color.BLUE);
			break;
		case 2:
			panel.setBackground(Color.CYAN);
			break;
		case 3:
			panel.setBackground(Color.DARK_GRAY);
			break;
		case 4:
			panel.setBackground(Color.GRAY);
			break;
		case 5:
			panel.setBackground(Color.GREEN);
			break;
		case 6:
			panel.setBackground(Color.LIGHT_GRAY);
			break;
		case 7:
			panel.setBackground(Color.MAGENTA);
			break;
		case 8:
			panel.setBackground(Color.ORANGE);
			break;
		case 9:
			panel.setBackground(Color.PINK);
			break;
		case 10:
			panel.setBackground(Color.RED);
			break;
		case 11:
			panel.setBackground(Color.WHITE);
			break;
		case 12:
			panel.setBackground(Color.YELLOW);
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
			BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D graphics = image.createGraphics();
			panel.paint(graphics);
			graphics.dispose();

			try {
				ImageIO.write(image, "PNG", file);
			} catch (Exception e) {
			}
			
		}
		
	}

}
