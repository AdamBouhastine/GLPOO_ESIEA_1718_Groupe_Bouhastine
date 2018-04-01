package fr.esiea.loto;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import fr.esiea.loto.graphic.LotoJFrame;



public class Launcher {

	private static final Logger log = Logger.getLogger(Launcher.class);
	
	public static void main(String[] args) {
		
	log.debug("Welcome to the Real World");
	
	
	final JFrame lotoJFrame = new LotoJFrame();
	lotoJFrame.setVisible(true);
	
	
	}

}
