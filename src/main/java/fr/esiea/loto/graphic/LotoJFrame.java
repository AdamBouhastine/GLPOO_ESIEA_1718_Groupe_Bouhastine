package fr.esiea.loto.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;

import fr.esiea.loto.domain.Day;

public class LotoJFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LotoJFrame.class);
	private LotoModel model;
	private JTable table;
	
	public LotoJFrame() {
		setTitle("Loto Easy bae");
		setPreferredSize(new Dimension(900, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		model = new LotoModel();

		table = new JTable(model);
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		
		table.setDefaultRenderer(Day.class, new DayRenderer());
		
		table.setRowSorter(sorter);
		
		
		table.setBackground(Color.lightGray);
		table.setGridColor(Color.red);
		table.setSelectionBackground(Color.orange);
		
		final JScrollPane scroll = new JScrollPane(table);
		getContentPane().add(scroll, BorderLayout.CENTER);
		
		
		pack();
	}
	
	

}
