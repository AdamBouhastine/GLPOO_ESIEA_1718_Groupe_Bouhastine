package fr.esiea.loto.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.loto.domain.Day;
import fr.esiea.loto.domain.Loto;

public class CsvLotoDaoTest {

	private LotoDao dao;

	@Before
	public void doBefore() {
		dao = new CsvLotoDao("src/test/resources/euromillion.csv");
	}

	@Test
	public void eclipseWorking() {

		Assert.assertEquals(1, 1);
	}

	@Test
	public void testFindNumberOfDraws() {
		// Arrange
		final int expectedNbOfDraws = 145;
		// Act
		final List<Loto> draws = dao.findAllDraws();
		final int nb = draws.size();
		// Assert
		Assert.assertEquals(expectedNbOfDraws, nb);
	}

	@Test
	public void testFindFirstYear() {
		// Arrange
		final String expectedYear = "2016078";
		// Act
		final String nb = getYearOfElement(0);
		// Assert
		Assert.assertEquals(expectedYear, nb);
	}

	@Test
	public void testFindLastYear() {
		// Arrange
		final String expectedYear = "2018013";
		// Act
		final String nb = getYearOfElement(144);
		// Assert
		Assert.assertEquals(expectedYear, nb);
	}

	@Test
	public void testFindFirstBalls() {
		// Arrange
		final List<Integer> expectedBalls = new ArrayList<Integer>();
		expectedBalls.add(41);
		expectedBalls.add(6);
		expectedBalls.add(13);
		expectedBalls.add(39);
		expectedBalls.add(9);
		// Act
		final List<Integer> balls = getBallsOfElement(0);
		// Assert
		Assert.assertEquals(expectedBalls, balls);
	}

	@Test
	public void testFindLastBalls() {
		// Arrange
		final List<Integer> expectedBalls = new ArrayList<Integer>();
		expectedBalls.add(13);
		expectedBalls.add(44);
		expectedBalls.add(10);
		expectedBalls.add(21);
		expectedBalls.add(17);
		// Act
		final List<Integer> balls = getBallsOfElement(144);
		// Assert
		Assert.assertEquals(expectedBalls, balls);
	}

	@Test
	public void testFindFirstStars() {
		// Arrange
		final List<Integer> expectedStars = new ArrayList<Integer>();
		expectedStars.add(2);
		expectedStars.add(12);
		// Act
		final List<Integer> stars = getStarsOfElement(0);
		// Assert
		Assert.assertEquals(expectedStars, stars);
	}

	@Test
	public void testFindLastStars() {
		// Arrange
		final List<Integer> expectedStars = new ArrayList<Integer>();
		expectedStars.add(10);
		expectedStars.add(1);
		// Act
		final List<Integer> stars = getStarsOfElement(144);
		// Assert
		Assert.assertEquals(expectedStars, stars);
	}

	@Test
	public void testFindFirstNumberOfDraws() {
		// Arrange
		final int expectedNbOfDraws = 1;
		// Act
		final int nbOfDraws = getNbOfDrawsOfElement(0);
		//Assert
		Assert.assertEquals(expectedNbOfDraws, nbOfDraws);
	}
	
	@Test
	public void testFindSecondNumberOfDraws() {
		// Arrange
		final int expectedNbOfDraws = 0;
		// Act
		final int nbOfDraws = getNbOfDrawsOfElement(1);
		//Assert
		Assert.assertEquals(expectedNbOfDraws, nbOfDraws);
	}

	@Test
	public void testFindLastNumberOfDraws() {
		// Arrange
		final int expectedNbOfDraws = 14;
		// Act
		final int nbOfDraws = getNbOfDrawsOfElement(144);
		//Assert
		Assert.assertEquals(expectedNbOfDraws, nbOfDraws);
	}
	
	@Test
	public void testFindFirstDate() {
		//Arrange
		final String expectedDate = "27/09/2016";
		// Act
		final String Date = getDateOfElement(0);
		//Assert
		Assert.assertEquals(expectedDate, Date);
	}
	
	@Test
	public void testFindLastDate() {
		//Arrange
		final String expectedDate = "13/02/2018";
		// Act
		final String Date = getDateOfElement(144);
		//Assert
		Assert.assertEquals(expectedDate, Date);
	}
	
	@Test
	public void testFindFirstDay() {
		//Arrange
		final String expectedDay = "Mardi";
		//Act
		final String day = getDayOfElement(0);
		//Assert
		Assert.assertEquals(expectedDay, day);
	}
	
	@Test
	public void testFindSecondDay() {
		//Arrange
		final String expectedDay = "Vendredi";
		//Act
		final String day = getDayOfElement(1);
		//Assert
		Assert.assertEquals(expectedDay, day);
	}
	
	@Test
	public void testFindLastDay() {
		//Arrange
		final String expectedDay = "Mardi";
		//Act
		final String day = getDayOfElement(144);
		//Assert
		Assert.assertEquals(expectedDay, day);
	}
	
	
	// Fonctions utiles pour les tests
	public String getYearOfElement(int n) {
		final List<Loto> draws = dao.findAllDraws();
		final Loto draw = draws.get(n);
		return draw.getYear();
	}

	public List<Integer> getBallsOfElement(int n) {
		final List<Loto> draws = dao.findAllDraws();
		final Loto draw = draws.get(n);
		return draw.getBalls();
	}

	public List<Integer> getStarsOfElement(int n) {
		final List<Loto> draws = dao.findAllDraws();
		final Loto draw = draws.get(n);
		return draw.getStars();
	}
	
	public int getNbOfDrawsOfElement(int n) {
		final List<Loto> draws = dao.findAllDraws();
		final Loto draw = draws.get(n);
		return draw.getNumberOfDraw();
	}
	
	public String getDateOfElement(int n) {
		final List<Loto> draws = dao.findAllDraws();
		final Loto draw = draws.get(n);
		return draw.getDate();
	}
	
	public String getDayOfElement(int n) {
		final List<Loto> draws = dao.findAllDraws();
		final Loto draw = draws.get(n);
		final Day day = draw.getDay();
		return day.getLabel();
	}
}
