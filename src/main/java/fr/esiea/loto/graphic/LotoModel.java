package fr.esiea.loto.graphic;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.esiea.loto.dao.CsvLotoDao;
import fr.esiea.loto.domain.Day;
import fr.esiea.loto.domain.Loto;
import org.apache.log4j.Logger;

public class LotoModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private final String[] headers;
	private List<Loto> draws;
	private static final Logger log = Logger.getLogger(LotoModel.class);
	
	public void ajouterChien(final Loto tirage) {
		log.debug("ajouterTirage");}

	public LotoModel() {
		headers = new String[] { "Year", "Day", "Date", "Number of Draw", "Balls", "Stars" };
		draws = CreateDraws();
	}

	private List<Loto> CreateDraws() {
		final CsvLotoDao csvLotoDao = new CsvLotoDao("src/main/resources/euromillion.csv");
		final List<Loto> csvDraw = csvLotoDao.findAllDraws();
		return csvDraw;
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public int getRowCount() {
		return draws.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		final Loto draw = draws.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return draw.getYear();
		case 1:
			return draw.getDay();
		case 2:
			return draw.getDate();
		case 3:
			return draw.getNumberOfDraw();
		case 4:
			return draw.getBalls();
		case 5:
			return draw.getStars();
		default:
			throw new IllegalArgumentException("Error");
		}
	}

	@Override
	public String getColumnName(int column) {
		return headers[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return Day.class;
		case 2:
			return String.class;
		case 3:
			return Integer.class;
		default:
			return Object.class;
		}
	}
}
