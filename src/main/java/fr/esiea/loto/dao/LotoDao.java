package fr.esiea.loto.dao;

import java.util.List;

import fr.esiea.loto.domain.Loto;

public interface LotoDao {

	List<Loto> findAllDraws();

}
