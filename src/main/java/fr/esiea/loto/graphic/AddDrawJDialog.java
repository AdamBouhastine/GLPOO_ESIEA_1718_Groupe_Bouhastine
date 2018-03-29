package fr.esiea.loto.graphic;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.SwingConstants.RIGHT;
import static javax.swing.SwingConstants.LEFT;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import fr.esiea.loto.domain.Loto;
import fr.esiea.loto.domain.LotoDraw;
import fr.esiea.loto.handler.Action;
import fr.esiea.loto.handler.ActionHandler;
import fr.esiea.loto.domain.Day;

public class AddDrawJDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddDrawJDialog.class);
	private ActionHandler handler;
	private Loto draw;

	private JButton addButton = new JButton(new AddAction("Add"));
	private JButton cancelButton = new JButton(new CancelAction("Cancel"));

	private JPanel buttonPane = new JPanel();
	private JPanel formPane = new JPanel();

	private JTextField year = new JTextField(15);
	private JComboBox day = new JComboBox();
	private JTextField date = new JTextField(15);
	private JTextField nbOfDraw = new JTextField(15);
	private JTextField balls = new JTextField(15);
	private JTextField stars = new JTextField(15);

	private int espace = 5;
	private int widthChamp = 500;
	private int widthLabel = 133;
	private int y = 0;
	private int heightLigne = 10;

	public AddDrawJDialog(ActionHandler handler) {
		this(handler, null);
	}

	public AddDrawJDialog(ActionHandler handler, Loto draw) {
		super();
		this.handler = handler;
		this.draw = draw;

		setTitle("Add a draw");

		setModal(true);

		addForms();

		addButtons();

		pack();

	}

	private void addButtons() {
		buttonPane.add(addButton);
		buttonPane.add(cancelButton);
		add(buttonPane, SOUTH);
	}

	@SuppressWarnings("unchecked")
	private void addForms() {

		ajouter("Year :", year);

		for (Day days : Day.values()) {
			day.addItem(days);
		}
		day.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				final Day days = (Day) value;
				setText(days.getLabel());
				return this;

			}
		});

		ajouter("Day :", day);
		ajouter("Date :", date);
		ajouter("Number Of Draw :", nbOfDraw);
		ajouter("Balls :", balls);
		ajouter("Stars :", stars);

		formPane.setPreferredSize(new Dimension(3 * espace + widthLabel + widthChamp, y + espace));
		add(formPane, CENTER);

	}

	private void ajouter(final String label, final JComponent champ) {
		y += espace;

		final JLabel l = new JLabel(label);
		l.setBounds(espace, y, widthLabel, heightLigne);
		l.setHorizontalAlignment(LEFT);

		champ.setBounds(2 * espace + widthLabel, y, widthChamp, heightLigne);

		y += heightLigne;

		formPane.add(l);
		formPane.add(champ);
	}

	private class AddAction extends AbstractAction {

		private static final long serialVersionUID = 4215544910020283675L;

		public AddAction(String string) {
			super(string);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			log.debug("add");

			final LotoDraw draw1 = new LotoDraw();
			FillDraw(draw1);
			try {
				handler.process(Action.CREATE, draw1);
			} catch (Exception exception) {
				
			}

			// Fermeture de la fenetre
			AddDrawJDialog.this.closePopup();
		}

	}

	private void FillDraw(final LotoDraw draw) {

		draw.setYear(year.getText());
		draw.setDay((Day) day.getSelectedItem());
		draw.setDate(date.getText());

		String temp = nbOfDraw.getText();
		draw.setNumberOfDraw(Integer.valueOf(temp));

		String tempBall = balls.getText();
		final String separator = ",";
		final String[] values = tempBall.split(separator);

		String tempBalls;
		List<Integer> balls = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			tempBalls = values[i];
			balls.add(Integer.parseInt(tempBalls));
		}
		draw.setBalls(balls);
		
		String tempStar = stars.getText();
		final String[] values2 = tempStar.split(separator);
		
		String tempStars;
		List<Integer> stars = new ArrayList<Integer>();
		for(int i=0;i<2;i++) {
			tempStars = values2[i];
			stars.add(Integer.parseInt(tempStars));
		}
		draw.setStars(stars);
	}

	private class CancelAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public CancelAction(String string) {
			super(string);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			log.debug("Cancel");

			// Fermeture de la fenetre
			AddDrawJDialog.this.closePopup();
		}

	}

	private void closePopup() {
		// Fermeture de la fenetre.
		dispose();
	}
}
