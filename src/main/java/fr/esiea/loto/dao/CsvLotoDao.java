package fr.esiea.loto.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.esiea.loto.domain.Day;
import fr.esiea.loto.domain.Loto;
import fr.esiea.loto.domain.LotoDraw;

public class CsvLotoDao implements LotoDao {

	final String filename;

	public CsvLotoDao(final String filename) {
		this.filename = filename;
	}

	private List<String> readFile() throws IOException {
		final List<String> lines = new ArrayList<>();
		FileReader File = null;
		BufferedReader tampon = null;

		try {
			File = new FileReader(filename);
			tampon = new BufferedReader(File);
			while (true) {

				String ligne = tampon.readLine();
				if (ligne == null)
					break;
				lines.add(ligne);
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			try {
				tampon.close();
				File.close();
			} catch (IOException exception1) {
				exception1.printStackTrace();
			}
		}
		return lines;
	}

	private Loto lineToLotoDraw(final String line) {
		final Loto loto = new LotoDraw();
		final String separator = ";";
		final String[] values = line.split(separator);

		loto.setYear(values[0]);

		final String tempDay = values[1];
		final Day day = Day.valueOfByCode(tempDay);
		loto.setDay(day);

		loto.setDate(values[2]);

		final String tempNumberOfDraw = values[3];
		if (tempNumberOfDraw.equals("Super Jackpot et tirages suivants")) {
			loto.setNumberOfDraw(0);
		}
		else {
			int numberOfDraw = Integer.parseInt(tempNumberOfDraw);
			loto.setNumberOfDraw(numberOfDraw);
		}

		String tempBalls;
		List<Integer> balls = new ArrayList<Integer>();
		for (int i = 5; i < 10; i++) {
			tempBalls = values[i];
			balls.add(Integer.parseInt(tempBalls));
		}
		loto.setBalls(balls);
		
		String tempStars;
		List<Integer> stars = new ArrayList<Integer>();
		for(int i = 10;i <12 ;i++) {
			tempStars = values[i];
			stars.add(Integer.parseInt(tempStars));
		}
		loto.setStars(stars);

		return loto;
	}

	@Override
	public List<Loto> findAllDraws() {
		final List<Loto> draws = new ArrayList<>();

		// Fic > tab de ligne
		List<String> lines = null;
		try {
			lines = readFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean isFirst = true;
		for (final String line : lines) {
			// Lignes vides ou commentaires
			if (line.trim().isEmpty() || line.startsWith("#")) {
				continue;
			}

			// Titres
			if (isFirst) {
				isFirst = false;
				continue;
			}
			final Loto draw = lineToLotoDraw(line);
			draws.add(draw);
		}
		// Retour
		return draws;
	}

}
