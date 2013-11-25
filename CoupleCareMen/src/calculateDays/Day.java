package calculateDays;

import java.util.Calendar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Day implements Serializable {

	private Calendar fertileDay; // Inicio del periodo.
	private boolean fertile;
	private String comment = "";
	private ColorEnum color = ColorEnum.BLUE; // Suponiendo valor por defecto.
	//private Months month = Months.JANUARY;
	private int freqDayNumber = 0;
	private int periodays = 0;
	private ArrayList<SimpleDate> simpleDatesList = null;

	private final String[] freqPeriod = { "21", "22", "23", "24", "25", "26",
			"27", "28", "29", "30", "31", "32", "33", "34", "35" };

	private int _perioddays = -1;

	public void setPeriod(int x) {
		_perioddays = x;
	}

	private int _freqDayNumber;

	public void setCyclePeriod(int y) {
		_freqDayNumber = y;
	}

	public void setFreqDayNumber(int freqDayNumber) {
		this.freqDayNumber = freqDayNumber;
	}

	public ArrayList<SimpleDate> getSimpleDatesList() {
		return simpleDatesList;
	}

	public long getPeriodays() {
		return periodays;
	}

	public void setPeriodays(int periodays) {
		this.periodays = periodays;
	}

	public void setCalendar(Calendar cal_aumentado) { // Regresa la fecha aumentada, se modifica con el mes del scroll se asigna

		fertileDay.set(Calendar.DAY_OF_MONTH,
				cal_aumentado.get(Calendar.DAY_OF_MONTH));
		fertileDay.set(Calendar.MONTH, cal_aumentado.get(Calendar.MONTH));
		fertileDay.set(Calendar.YEAR, cal_aumentado.get(Calendar.YEAR));

	}

	public void calculateFertilesDays() {

		setCyclePeriod(_freqDayNumber);
		setPeriod(_perioddays);

		Calendar tempCal = GregorianCalendar.getInstance();

		// Settear el tempCal a la configuración de fertileDay inicial:
		tempCal.set(Calendar.DAY_OF_MONTH,
				fertileDay.get(Calendar.DAY_OF_MONTH));
		tempCal.set(Calendar.MONTH, fertileDay.get(Calendar.MONTH));
		tempCal.set(Calendar.YEAR, fertileDay.get(Calendar.YEAR));

		// Agrego el primer día a la lista (period day)
		simpleDatesList.add(new SimpleDate(tempCal.get(Calendar.DAY_OF_MONTH),
				tempCal.get(Calendar.MONTH), tempCal.get(Calendar.YEAR),
				DayType.PERIOD));
		// Haciendo el ciclo dependiendo de la variable utilizada
		for (int i = 1; i < _perioddays; i++) {
			
			// Le sumo un día al period start:
						tempCal.add(Calendar.DAY_OF_MONTH, 1);
			
			// Agrego el día a la lista:
			simpleDatesList.add(new SimpleDate(tempCal
					.get(Calendar.DAY_OF_MONTH), tempCal.get(Calendar.MONTH),
					tempCal.get(Calendar.YEAR), DayType.PERIOD));
			

		
			// Le sumo 1 al segundo día para llegar al tercer día:
			//tempCal.add(Calendar.DAY_OF_MONTH, 1);

		}

		/*
		 * // Agrego a la lista el tercer día: simpleDatesList.add(new
		 * SimpleDate(tempCal.get(Calendar.DAY_OF_MONTH),
		 * tempCal.get(Calendar.MONTH), tempCal.get(Calendar.YEAR),
		 * DayType.PERIOD));
		 */
		// Sumarle el freqPeriod/2
		// tempCal.add(Calendar.DAY_OF_MONTH, freqDayNumber / 2);
		int i = 0;
		for (; i <= ((_freqDayNumber / 2) - 1) - 3; i++) {
			tempCal.add(Calendar.DAY_OF_MONTH, 1);
			simpleDatesList.add(new SimpleDate(tempCal
					.get(Calendar.DAY_OF_MONTH), tempCal.get(Calendar.MONTH),
					tempCal.get(Calendar.YEAR), DayType.FERTILE));
		}

		tempCal.add(Calendar.DAY_OF_MONTH, 1);
		simpleDatesList.add(new SimpleDate(tempCal.get(Calendar.DAY_OF_MONTH),
				tempCal.get(Calendar.MONTH), tempCal.get(Calendar.YEAR),
				DayType.MOSTFERTILE));

		tempCal.add(Calendar.DAY_OF_MONTH, 1);
		simpleDatesList.add(new SimpleDate(tempCal.get(Calendar.DAY_OF_MONTH),
				tempCal.get(Calendar.MONTH), tempCal.get(Calendar.YEAR),
				DayType.FERTILE));

		tempCal.add(Calendar.DAY_OF_MONTH, 1);
		simpleDatesList.add(new SimpleDate(tempCal.get(Calendar.DAY_OF_MONTH),
				tempCal.get(Calendar.MONTH), tempCal.get(Calendar.YEAR),
				DayType.FERTILE));

		for (int j = 0; j < (_freqDayNumber / 2) - 2; j++) {
			tempCal.add(Calendar.DAY_OF_MONTH, 1);
			simpleDatesList.add(new SimpleDate(tempCal
					.get(Calendar.DAY_OF_MONTH), tempCal.get(Calendar.MONTH),
					tempCal.get(Calendar.YEAR), DayType.LESSFERTILE));
		}

		tempCal.add(Calendar.DAY_OF_MONTH, 1);
		fertileDay.set(Calendar.DAY_OF_MONTH,
				tempCal.get(Calendar.DAY_OF_MONTH));
		fertileDay.set(Calendar.MONTH, tempCal.get(Calendar.MONTH));
		fertileDay.set(Calendar.YEAR, tempCal.get(Calendar.YEAR));

	}

	public ColorEnum getColor() {
		return color;
	}

	public void setColor(ColorEnum color) {
		this.color = color;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isFertile() {
		return fertile;
	}

	public void setFertile(boolean fertile) {
		this.fertile = fertile;
	}

	public Calendar getFertileDay() {
		return fertileDay;
	}

	public void setFertileDay(Calendar fertileDay) {
		this.fertileDay = fertileDay;
	}

	public Day(Calendar fertileDay, boolean fertile, String comment,
			ColorEnum color) {
		this.fertileDay = fertileDay;
		this.fertile = fertile;
		this.comment = comment;
		this.color = color;
		this.simpleDatesList = new ArrayList<SimpleDate>(); // jdk7
		// this.simpleDatesList = new ArrayList<SimpleDate>();// jdk6
	}

	public Day(Calendar fertileDay, boolean fertile) {
		this.fertileDay = fertileDay;
		this.fertile = fertile;
	}

	@Override
	public String toString() {
		return "Day{" + "fertileDay=" + fertileDay.getTime() + ", fertile="
				+ fertile + ", comment=" + comment + ", color=" + color + '}';
	}

	// Borrar la lista
	public void resetCalendar() {
		simpleDatesList.clear();
	}

}