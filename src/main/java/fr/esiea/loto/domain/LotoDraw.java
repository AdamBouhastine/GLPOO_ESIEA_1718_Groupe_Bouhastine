package fr.esiea.loto.domain;

import java.util.List;

public class LotoDraw implements Loto {

	private String year;
	private Day day;
	private String date;
	private int numberOfDraw;
	private List<Integer> balls;
	private List<Integer> stars;

	public LotoDraw(String year,Day day,String date,int numberOfDraw,List<Integer> balls,List<Integer> stars) {
		this.setYear(year);
		this.setDay(day);
		this.setDate(date);
		this.setNumberOfDraw(numberOfDraw);
		this.setBalls(balls);
		this.setStars(stars);
	}

	public LotoDraw() {
		super();
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNumberOfDraw() {
		return numberOfDraw;
	}

	public void setNumberOfDraw(int numberOfDraw) {
		this.numberOfDraw = numberOfDraw;
	}

	public List<Integer> getBalls() {
		return balls;
	}

	public void setBalls(List<Integer> balls) {
		this.balls = balls;
	}

	public List<Integer> getStars() {
		return stars;
	}

	public void setStars(List<Integer> stars) {
		this.stars = stars;
	}


}
