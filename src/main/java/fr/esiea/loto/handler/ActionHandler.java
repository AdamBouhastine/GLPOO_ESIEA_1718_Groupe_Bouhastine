package fr.esiea.loto.handler;

import fr.esiea.loto.domain.Loto;

public interface ActionHandler {

	void process(final Action action);
	void process(final Action action,final Loto draw);
}
