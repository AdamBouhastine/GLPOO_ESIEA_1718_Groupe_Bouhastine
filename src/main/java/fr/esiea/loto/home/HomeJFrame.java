package fr.esiea.loto.home;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import fr.esiea.loto.graphic.LotoJFrame;

public class HomeJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public HomeJFrame() {
		HomeJPanel panel = new HomeJPanel();
		setTitle("Home");
		setPreferredSize(new Dimension(900, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setContentPane(panel);
		// ajoute un écouteur d'événements personnalisé à la fenêtre
        addKeyListener(new AddKeyListener(this));
		pack();
	}
	
	
	public class AddKeyListener implements KeyListener{

		JFrame frame;
		public AddKeyListener(JFrame frame) {
			this.frame=frame;
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				final JFrame lotoJFrame = new LotoJFrame();
				lotoJFrame.setVisible(true);
				frame.dispose();
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			frame.dispose();
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// Do nothing
			
		}
		
	}

	
}
