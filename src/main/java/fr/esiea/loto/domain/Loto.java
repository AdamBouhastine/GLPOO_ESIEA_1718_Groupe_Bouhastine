package fr.esiea.loto.domain;

import java.util.List;

public interface Loto {

	public String getYear();
	public void setYear(String year);

	public Day getDay();
	public void setDay(Day day);

	public String getDate();
	public void setDate(String date);

	public int getNumberOfDraw();
	public void setNumberOfDraw(int numberOfDraw);

	public List<Integer> getBalls();
	public void setBalls(List<Integer> balls);

	public List<Integer> getStars();
	public void setStars(List<Integer> stars);

}
