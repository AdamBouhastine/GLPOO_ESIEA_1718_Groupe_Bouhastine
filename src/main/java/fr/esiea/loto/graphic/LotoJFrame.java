package fr.esiea.loto.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;

import fr.esiea.loto.domain.Day;

import fr.esiea.loto.domain.Loto;
import fr.esiea.loto.domain.LotoDraw;


import java.util.Comparator;

public class LotoJFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LotoJFrame.class);
	private LotoModel model;
	private JTable table;
	
	
	public static long dateAsComparingLong(Object date) {
	        String[] tab = date.toString().split("/");
	        int day = Integer.parseInt(tab[0]);
	        int month = Integer.parseInt(tab[1]);
	        int year = Integer.parseInt(tab[2]);
	        return year * 400L + month * 32L + day;
	    }
	    
	public LotoJFrame() {
		setTitle("Loto Easy bae");
		setPreferredSize(new Dimension(900, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		model = new LotoModel();

		table = new JTable(model);
		
	    
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		sorter.setComparator(0, Comparator.comparing(String::valueOf));
        sorter.setComparator(2, Comparator.comparingLong(LotoJFrame::dateAsComparingLong));
		table.setDefaultRenderer(Day.class, new DayRenderer());
		table.setRowSorter(sorter);
		
		
		table.setBackground(Color.lightGray);
		table.setGridColor(Color.red);
		table.setSelectionBackground(Color.orange);
		
		final JScrollPane scroll = new JScrollPane(table);
		getContentPane().add(scroll, BorderLayout.CENTER);
		
		final JPanel boutons = new JPanel();
        boutons.add(new JButton(new AjouterLigneAction()));
        getContentPane().add(boutons, BorderLayout.SOUTH);
        
        
        boutons.add(new JButton(new SupprimerLigneAction()));
        getContentPane().add(boutons, BorderLayout.SOUTH);
		
		pack();
	}
	
	private class AjouterLigneAction extends AbstractAction {
		

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private AjouterLigneAction() {
            super("Ajouter");
        }

		@Override
		public void actionPerformed(ActionEvent arg0) {

			log.debug("Click sur le bouton ajouter");
			final Loto toto = new LotoDraw("2018030", Day.JEUDI, "03/08/2018", 15, null, null);
            model.ajouterloto(toto);

			log.debug("Clique sur le bouton ajouter");

			
		}
		
		
	}
	
	 private class SupprimerLigneAction extends AbstractAction {

	        private static final long serialVersionUID = -5556554884674073716L;

	        private SupprimerLigneAction() {
	            super("Supprimer");
	        }

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            final int[] selection = table.getSelectedRows();
	            for (int i = selection.length - 1; i >= 0; i--) {
	                model.supprimerloto(selection[i]);
	            }
	        }
	    }
}
	


