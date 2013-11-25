package calculateDays;

import calculateDays.ColorEnum;
import calculateDays.DayType;
import calculateDays.SimpleDate;

public class SimpleDate {

	private int day;
	private int month;
	private int year;
	private ColorEnum color;
	private DayType dayType;

	public SimpleDate(int day, int month, int year, DayType dayType) {

		this.day = day;
		this.month = month+1;
		this.year = year;
		this.dayType = dayType;

		switch (dayType) {
		case PERIOD:
			color = ColorEnum.CYAN;
			break;
		case FERTILE:
			color = ColorEnum.YELLOW;
			break;
		case LESSFERTILE:
			color = ColorEnum.GREEN;
			break;
		case MOSTFERTILE:
			// @todo: Agregar color a la enumeración para diferenciarlo
			color = ColorEnum.RED;
			break;
		}

	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		SimpleDate d = (SimpleDate) o;
		if (d.day == this.day && d.month == month && d.year == year) {
			return true;
		}
		return false;
	}

	public ColorEnum getColor() {
		return color;
	}

	public void setColor(ColorEnum color) {
		this.color = color;
	}

	public DayType getDayType() {
		return dayType;
	}

	public void setDayType(DayType dayType) {
		this.dayType = dayType;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return day + "/" + month + "/" + year + "-" + dayType;
	}

}