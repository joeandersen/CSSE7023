/**
 * 
 */
package festival.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import festival.LineUp;
import festival.gui.PlannerModel;
import festival.DayPlanner;
import festival.Event;
import festival.Venue;
import festival.FormatException;
import festival.ShuttleTimetable;
import festival.Service;

/**
 * @author s342261
 *
 */
public class PlannerModelTest {
	
	// Events for use in testing
	private Event[] events = { new Event(new Venue("v1"), 1, "Megadeth"),
			new Event(new Venue("v1"),2, "Def Leppard"),
			new Event(new Venue("v1"), 3, "Slash"),
			new Event(new Venue("v1"), 4, "Toto"),
			new Event(new Venue("v1"), 5, "Five Finger Death Punch")
			 };
	

	@Test
	public void testLineUpLoad() throws IOException, FormatException {

		PlannerModel model = new PlannerModel();
		model.loadLineUp();
		
		LineUp testLineUp = new LineUp();
		testLineUp.addEvent(events[0]);
		testLineUp.addEvent(events[1]);
		testLineUp.addEvent(events[2]);
		testLineUp.addEvent(events[3]);
		testLineUp.addEvent(events[4]);
		
		System.out.println(testLineUp);
		System.out.println(model.getLineUp());
		

		Assert.assertTrue(testLineUp.toString().equals(model.getLineUp().toString()));
	}
	
	
	public void testLineUpLoadb() throws IOException, FormatException {

		PlannerModel model = new PlannerModel();
		model.loadLineUp();
		
		LineUp testLineUp = new LineUp();
		testLineUp.addEvent(events[0]);
		testLineUp.addEvent(events[1]);
		testLineUp.addEvent(events[2]);
		testLineUp.addEvent(events[3]);
		
		System.out.println(testLineUp);
		System.out.println(model.getLineUp());
		

		Assert.assertFalse(testLineUp.toString().equals(model.getLineUp().toString()));
	}
	@Test
	public void testTimeTableLoad() throws IOException, FormatException {
		
		PlannerModel model = new PlannerModel();
		model.loadTimeTable();

		ShuttleTimetable testTimeTable = new ShuttleTimetable();
		Service[] services =
			{ new Service(new Venue("v1"), new Venue("v3"), 2),
					new Service(new Venue("v1"), new Venue("v3"), 5),
					new Service(new Venue("v2"), new Venue("v1"), 1),
					new Service(new Venue("v2"), new Venue("v1"), 3),
					new Service(new Venue("v3"), new Venue("v2"), 5) };
		for (Service service : services) {
			testTimeTable.addService(service);
		}
		
		
		
		
		System.out.println(testTimeTable);
		System.out.println(model.getTimeTable());
		

		Assert.assertTrue(testTimeTable.toString().equals(model.getTimeTable().toString()));
	}
	@Test
	public void testTimeTableLoadb() throws IOException, FormatException {
		
		PlannerModel model = new PlannerModel();
		model.loadTimeTable();

		ShuttleTimetable testTimeTable = new ShuttleTimetable();
		Service[] services =
			{ new Service(new Venue("v1"), new Venue("v3"), 2),
					new Service(new Venue("v1"), new Venue("v3"), 5),
					new Service(new Venue("v2"), new Venue("v1"), 1),
					new Service(new Venue("v2"), new Venue("v1"), 3)};
		for (Service service : services) {
			testTimeTable.addService(service);
		}
		
		
		
		
		System.out.println(testTimeTable);
		System.out.println(model.getTimeTable());
		

		Assert.assertFalse(testTimeTable.toString().equals(model.getTimeTable().toString()));
	}
}
