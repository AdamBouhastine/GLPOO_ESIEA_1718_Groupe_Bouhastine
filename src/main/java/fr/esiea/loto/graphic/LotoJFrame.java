package fr.esiea.loto.graphic;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.data.general.DefaultPieDataset;

import fr.esiea.loto.domain.Day;

import fr.esiea.loto.domain.Loto;

import fr.esiea.loto.domain.LotoDraw;

import java.util.Comparator;
import java.util.HashMap;

public class LotoJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LotoJFrame.class);
	private LotoModel model;
	private JTable table;
	Map<String, Integer> map = new HashMap<String, Integer>();
	private JDialog balljdialog;

	private JMenuItem menuSupprimer;

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

		AddMenu();
		AddTable();
		pack();

	}

	public void AddMenu() {
		final JMenuBar menuBar = new JMenuBar();

		// Menu Edition
		final JMenu menuEdition = new JMenu("Edition");
		menuBar.add(menuEdition);
		final JMenuItem menuAjouter = new JMenuItem(new AjouterLigneAction());
		menuEdition.add(menuAjouter);
		menuSupprimer = new JMenuItem(new SupprimerLigneAction());
		menuEdition.add(menuSupprimer);
		activerOuDesactiverMenuEdition();

		// Menu Graphe
		final JMenu menuGraphe = new JMenu("Grahes");
		menuBar.add(menuGraphe);
		final JMenuItem menuBallsNumber = new JMenuItem(new Graphe());
		menuGraphe.add(menuBallsNumber);

		// Ajout a la fenetre
		setJMenuBar(menuBar);

	}

	public void AddTable() {

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

		// Ajout d'un ecouteur
		final ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(new TableauListSelectionListener());


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

			/*
			 * final AddLotoActionHandler handler = new AddLotoActionHandler(model);
			 * AddLotoJDialog popup = new AddLotoJDialog(handler);
			 * 
			 * popup.setvisible(true);
			 */

			/*
			 * final Loto toto = new LotoDraw("2018030", Day.JEUDI, "03/08/2018", 15, null,
			 * null); model.ajouterloto(toto);
			 * 
			 * log.debug("Clique sur le bouton ajouter");
			 */

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

	private class Graphe extends AbstractAction {

		private static final long serialVersionUID = 1L;

		private Graphe() {

			super("NumberOfBalls");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			log.debug("clic clic");
			String[] trancheNames = { "0-10", "11-20", "21-30", "31-40", "41-50" };
			Map<String, Integer> map = new HashMap<String, Integer>();
			for (String trancheName : trancheNames) {
				map.put(trancheName, 0);
			}
			List<Integer> balls;
			final List<Loto> loto = model.getLoto();
			for (Loto tirage : loto) {
				balls = tirage.getBalls();
				for (int i = 0; i < 5; i++) {

					String tranche = null;
					if (balls.get(i) <= 10) {
						tranche = "0-10";
					}
					if (balls.get(i) <= 20 && balls.get(i) > 10) {
						tranche = "11-20";
					}
					if (balls.get(i) <= 30 && balls.get(i) > 20) {
						tranche = "21-30";
					}
					if (balls.get(i) <= 40 && balls.get(i) > 30) {
						tranche = "31-40";
					}
					if (balls.get(i) > 40) {
						tranche = "41-50";
					}

					Integer value = map.get(tranche);
					value++;
					map.put(tranche, value);
				}
			}
			balljdialog = new JDialog();
			balljdialog.setTitle("Balls gagnantes");

			final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			for (String tranche : trancheNames) {
				final Integer nb = map.get(tranche);
				dataset.addValue(nb, "Balls", tranche);
			}

			final JFreeChart barChart = ChartFactory.createBarChart("Balls", "Tranches", "Nombre", /**/
					dataset, PlotOrientation.VERTICAL, true, true, false);

			final ChartPanel cPanel = new ChartPanel(barChart);

			balljdialog.getContentPane().add(cPanel, CENTER);

			balljdialog.pack();
			balljdialog.setVisible(true);
		}
	}

	private class TableauListSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(final ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				return;
			}

			activerOuDesactiverMenuEdition();
		}

	}

	private void activerOuDesactiverMenuEdition() {
		final int[] selection = table.getSelectedRows();

		final boolean isSelection = selection != null && selection.length != 0;
		menuSupprimer.setEnabled(isSelection);
	}

}
