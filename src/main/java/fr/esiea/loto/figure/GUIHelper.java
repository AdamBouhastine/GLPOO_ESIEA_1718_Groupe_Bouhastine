package fr.esiea.loto.figure;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class GUIHelper {

	public static void showOnFrame(JComponent component, String frameName, int choseColorBackground) {
		JFrame frame = new JFrame(frameName);
		WindowAdapter wa = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//Do nothing
			}
		};
		frame.addWindowListener(wa);
		frame.getContentPane().add(component);
		switch (choseColorBackground) {
		case 0:
			frame.setBackground(Color.BLACK);
			break;
		case 1:
			frame.setBackground(Color.BLUE);
			break;
		case 2:
			frame.setBackground(Color.CYAN);
			break;
		case 3:
			frame.setBackground(Color.DARK_GRAY);
			break;
		case 4:
			frame.setBackground(Color.GRAY);
			break;
		case 5:
			frame.setBackground(Color.GREEN);
			break;
		case 6:
			frame.setBackground(Color.LIGHT_GRAY);
			break;
		case 7:
			frame.setBackground(Color.MAGENTA);
			break;
		case 8:
			frame.setBackground(Color.ORANGE);
			break;
		case 9:
			frame.setBackground(Color.PINK);
			break;
		case 10:
			frame.setBackground(Color.RED);
			break;
		case 11:
			frame.setBackground(Color.WHITE);
			break;
		case 12:
			frame.setBackground(Color.YELLOW);
			break;

		}
		
		frame.pack();
		frame.setVisible(true);
	}

}
