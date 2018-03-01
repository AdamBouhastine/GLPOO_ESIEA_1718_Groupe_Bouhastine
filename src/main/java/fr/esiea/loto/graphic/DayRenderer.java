package fr.esiea.loto.graphic;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import fr.esiea.loto.domain.Day;

public class DayRenderer extends DefaultTableCellRenderer{
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		
		Day day = (Day) value;
		
		setText(day.getLabel());
		setBackground(day == Day.MARDI ? Color.GREEN : Color.RED);
		return this;
	}

}
