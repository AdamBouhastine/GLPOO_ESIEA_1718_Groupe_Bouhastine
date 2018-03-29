package fr.esiea.loto.graphic;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.esiea.loto.dao.CsvLotoDao;
import fr.esiea.loto.domain.Day;
import fr.esiea.loto.domain.Loto;
import org.apache.log4j.Logger;

public class LotoModel extends AbstractTableModel {
	private static final long serialVersionUID = -139320534128196933L;;
	private final String[] headers;
	private static List<Loto> draws;
	private static final Logger log = Logger.getLogger(LotoModel.class);

	public LotoModel() {
		super();
		headers = new String[] { "Year", "Day", "Date", "Number of Draw", "Balls", "Stars" };
		draws = CreateDraws();
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
	public String getColumnName(int column) {
		return headers[column];
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

	private List<Loto> CreateDraws() {
		final CsvLotoDao csvLotoDao = new CsvLotoDao("src/main/resources/euromillion.csv");
		final List<Loto> csvDraw = csvLotoDao.findAllDraws();
		return csvDraw;
	}

	public void ajouterLoto(final Loto tirage) {
		log.debug("ajouterTirage");
		draws.add(tirage);

		final int position = draws.size() - 1;
		fireTableRowsInserted(position, position);

	}

	public void supprimerloto(final int rowIndex) {
		log.debug("supprimerTirage");

		draws.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public List<Loto> getLoto() {
		return draws;
	}
}
