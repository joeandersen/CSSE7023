package festival.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import festival.DayPlanner;
import festival.Event;
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
	private List<Event> plan;

	/**
	 * Initializes the model for the Festival Planner.
	 */
	public PlannerModel() {
		this.plan = new ArrayList<>();

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
	
	
	public List<Event> getPlan() {
		return plan;
	}

	/**
	 * Routine that flags if the plan is empty
	 * 
	 * @return boolean: true if the plan associated with this model is empty
	 */
	public boolean isEmpty() {
		return (this.plan.size() == 0);
	}

	/**
	 * adds an event to the plan
	 * 
	 * @param event
	 * @return integer that flags result of operation 0: success 1: event
	 *         already exists 2: time clash 3: cannot reach from previous event
	 *         4: cannot reach the next event
	 */

	public int addEventToPlan(Event event) {
		if (isEmpty()) {
			this.plan.add(event);
			return 0; // can always add to empty plan
		}
		if (this.plan.contains(event)) {
			return 1; // report event already addded
		}
		for (Event e : this.plan) {
			if (e.getSession() == event.getSession()) {
				return 2; // report time clash
			}

			if (e.getSession() < event.getSession()) {
				if (!this.planner.canReach(e, event)) {
					return 3; // if we can't get from some earlier event to this
								// one, then we definitely won't be able to get
								// from the last event before this one to this one.
					//i.e. report can't get here from before
				}
			}
			if(e.getSession() > event.getSession()) {
				if (!this.planner.canReach(event, e)) {
					return 4; //report cannot reach next event in plan from the added event
				}
			}
		}
		this.plan.add(event);
		return 0;
		

	}

}
