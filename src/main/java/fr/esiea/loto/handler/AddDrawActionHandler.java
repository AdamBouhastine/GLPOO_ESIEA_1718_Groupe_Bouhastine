package fr.esiea.loto.handler;


import org.apache.log4j.Logger;

import fr.esiea.loto.domain.Loto;
import fr.esiea.loto.graphic.LotoModel;

public class AddDrawActionHandler implements ActionHandler{

	private static final Logger log = Logger.getLogger(AddDrawActionHandler.class);
	LotoModel model;
	
	public AddDrawActionHandler(LotoModel model) {
		this.model=model;
	}
	@Override
	public void process(Action action) {
		log.debug("process");
		process(action,null);
	}

	@Override
	public void process(final Action action, final Loto draw){
		log.debug("process");
		switch(action) {
		case CANCEL :
			cancel();
			break;
		case CREATE :
			create(draw);
			break;
		default : 
			throw new IllegalArgumentException("Action here is not possible");
		}
		
	}
	
	private void create(final Loto draw) {
		model.ajouterLoto(draw);
	}
	
	private void cancel() {
		//Do nothing
		log.debug("Cancel");
	}
	

}
