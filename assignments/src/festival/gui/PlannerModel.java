package festival.gui;

import java.io.IOException;

import festival.DayPlanner;
import festival.FormatException;
import festival.LineUp;
import festival.LineUpReader;
import festival.ScheduleReader;
import festival.ShuttleTimetable;

/**
 * The model for the Festival Planner.
 */
public class PlannerModel {

	private ShuttleTimetable timeTable;
	private DayPlanner planner;
	private LineUp lineUp;

	/**
	 * Initializes the model for the Festival Planner.
	 */
	public PlannerModel() {

	}

	/**
	 * loads the lineup from the lineup text file. This is where the name of the
	 * file is coded in I'm going to handle the fileio and format errors in the
	 * controller where this is called from
	 * 
	 * @throws IOException
	 * @throws FormatException
	 */

	public void loadLineUp() throws IOException, FormatException {
		setLineUp(LineUpReader.read("lineup.txt"));
	}
	
	
	public void loadTimeTable() throws IOException, FormatException {
		setTimeTable(ScheduleReader.read("timetable.txt"));
		setPlanner(new DayPlanner(getTimeTable()));
	}

	public LineUp getLineUp() {
		return lineUp;
	}

	public void setLineUp(LineUp lineUp) {
		this.lineUp = lineUp;
	}

	public DayPlanner getPlanner() {
		return planner;
	}

	public void setPlanner(DayPlanner planner) {
		this.planner = planner;
	}

	public ShuttleTimetable getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(ShuttleTimetable timeTable) {
		this.timeTable = timeTable;
	}
	
	public void addEventToPlan(Event event){
		
	}

}
