package fr.esiea.loto;

import java.util.List;

import org.apache.log4j.Logger;

import fr.esiea.loto.dao.CsvLotoDao;
import fr.esiea.loto.domain.Loto;



public class Launcher {

	private static final Logger log = Logger.getLogger(Launcher.class);
	
	public static void main(String[] args) {
		
	log.debug("Welcome to PSG will rekt Real");
	final CsvLotoDao csvLotoDao = new CsvLotoDao("src/main/resources/euromillion.csv");
	final List<Loto> csvDraw = csvLotoDao.findAllDraws();
	
	for(Loto draw : csvDraw) {
		log.debug(draw.getYear());
		log.debug(draw.getBalls());
		log.debug(draw.getNumberOfDraw());
	}
	
	
	
	
	}

}
