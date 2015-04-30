package festival_ass1_submitted;

import java.util.*;

/**
 * <p>
 * A mutable class representing the line-up of a festival.
 * </p>
 * 
 * <p>
 * The line-up of a festival keeps track of the events that are scheduled to
 * take place. Time during the festival is broken up into a number of
 * consecutive sessions, and each event is scheduled to take place at a given
 * venue in a particular session. (Sessions are denoted simply by positive
 * integers.)
 * 
 * The session times are the same for all venues, so that events taking place in
 * the same session at different venues, are actually taking place at the same
 * time.
 * </p>
 * 
 * <p>
 * At most one event can be scheduled for a venue in a particular session,
 * although there is no requirement that there is an event scheduled at a venue
 * for every session.
 * </p>
 */
public class LineUp implements Iterable<Event> {

    // Joseph Andersen joe@joeandersen.com 33422619
    //
    // 15-4-15

	private ArrayList<Event> events; //Stores the list of events in the line-up
	
	/*
	 * Invariants
	 * 
	 * no clashing events with same venue and session
	 * no null events
	 * no duplicate events
	 */
	
	
	/**
	 * Creates a new line-up with no events scheduled.
	 */
	public LineUp() {
		events = new ArrayList<Event>(); //new empty list
	}

	/**
	 * Adds a new event to the line-up.
	 * 
	 * @param event
	 *            the event to be added to the line-up
	 * @throws NullPointerException
	 *             if event is null
	 * @throws InvalidLineUpException
	 *             if there is already an event scheduled for the same venue and
	 *             session as the given event
	 */
	public void addEvent(Event event) {
		if (event==null) { //check if event is valid, and isn't a clash
			throw new NullPointerException("no null events");
		}
		for (int j=0;j<events.size();j++){
			if (events.get(j).getVenue().equals(event.getVenue())) {
				if (events.get(j).getSession()== event.getSession()) {
					throw new InvalidLineUpException("Event already "
							+ "scheduled at this venue/time");
				}
			}
		}
		if (!events.contains(event)) {
			events.add(event); //if event is ok, add it to the line up
		}
			
	}

	/**
	 * If the line-up contains an event that is equivalent to this one, then it
	 * is removed from the line-up. If there is no equivalent event, then the
	 * line-up is unchanged by the operation.
	 *
	 * @param event
	 *            the event to be removed from the line-up.
	 */
	public void removeEvent(Event event) {
		if (events.contains(event)) {
			events.remove(event);
		}
	}

	/**
	 * Returns a list of the events scheduled for the given venue. The list of
	 * events should be ordered by session number (in ascending order).
	 * 
	 * @param venue
	 *            the venue for which the events will be retrieved
	 * @return a list of the events scheduled for the given venue, ordered by
	 *         session number
	 * @throws NullPointerException
	 *             if the given venue is null
	 */
	public List<Event> getEvents(Venue venue) {
		
		List<Event> venueEvents = new ArrayList();
		
		if (venue==null) { //check we're looking for a valid venue.
			throw new NullPointerException("no null venue permitted");
		}
		
		for (int i = 0; i<events.size(); i++) {
			if (events.get(i).getVenue().equals(venue)) {
				//find all the events at this venue
				venueEvents.add(events.get(i));
			}
		}
		
		Collections.sort(venueEvents);
		//This line sorts the events by the order established in the event 
		//class compareTo() - for events at the same venue, this will ensure 
		//they are ordered by session number as required. 
		
		return venueEvents; 
	}

	/**
	 * Returns a list of the events scheduled for the given session time (across
	 * all venues). The list should be ordered by venue name (in ascending
	 * order).
	 * 
	 * @param session
	 *            the session to retrieve the events for
	 * @return A list of the events scheduled for the given session time.
	 * @throws InvalidSessionException
	 *             if session <= 0
	 */
	public List<Event> getEvents(int session) {
		
		List<Event> sessionEvents = new ArrayList();
		
		if (session<=0) { //check we are looking at a valid session time
			throw new InvalidSessionException("no negative sessions permitted");
		}
		
		for (int i = 0; i<events.size(); i++) {
			if (events.get(i).getSession() == (session)) {
				sessionEvents.add(events.get(i)); 
				//collect all events in the correct session
			}
		}
		
		Collections.sort(sessionEvents);
		//Events will be ordered as established in the compareTo() 
		//function of the Event class. This will order these events 
		//by venue name, as required.
		return sessionEvents; 
	}

	/**
	 * Returns a set of all the venues where at least one event from the line-up
	 * takes place.
	 * 
	 * @return The venues where events from the line-up will take place.
	 */
	public Set<Venue> getVenues() {
		
		Set<Venue> venues = new HashSet();
		
				
		for (int i = 0; i<events.size(); i++) { //loop over all events
			if (!venues.contains(events.get(i).getVenue())) { //check whether 
				//we've seen this venue before in the line up
				venues.add(events.get(i).getVenue()); 
				//store new venues in the set
			}
		}		
		return venues; 
	}

	/**
	 * If there is at least one event scheduled, then this method returns the
	 * number of the first session where there is an event scheduled. Otherwise
	 * it returns 0.
	 * 
	 * @return If there is at least one event scheduled, then the first session
	 *         number that an event is scheduled for, and 0 otherwise.
	 */
	public int getFirstUsedSession() {
		
		
		if (events.size()==0) { //for lineups with no events
			return 0;
		}
		
		int firstUsedSession = events.get(0).getSession(); 
		//set the value to the first event's time
		
		for (int i = 1; i<events.size(); i++) {
			if (events.get(i).getSession() < firstUsedSession) { 
				//compare this event to the current earliest event
				firstUsedSession = events.get(i).getSession();
			}
		}	
		return firstUsedSession; 
	}

	/**
	 * If there is at least one event scheduled, then this method returns the
	 * number of the last session where there is an event scheduled. Otherwise
	 * it returns 0.
	 * 
	 * @return If there is at least one event scheduled, then the last session
	 *         number that an event is scheduled for, and 0 otherwise.
	 */
	public int getLastUsedSession() {
		if (events.size()==0) { //for line ups with no events
			return 0;
		}
		
		int lastUsedSession = events.get(0).getSession(); 
		//set to first event in line ups value
		
		for (int i = 1; i<events.size(); i++) {
			if (events.get(i).getSession() > lastUsedSession) { 
				//compare each event to the current latest event
				lastUsedSession = events.get(i).getSession();
			}
		}
		return lastUsedSession; 
	}

	/**
	 * Returns an iterator over the events in the line-up.
	 */
	@Override
	public Iterator<Event> iterator() {
		return events.iterator(); 
	}

	/**
	 * The string representation of a line-up contains a line-separated
	 * concatenation of the string representations of the events in the line up.
	 * The events in the line-up should be ordered using their natural ordering
	 * (i.e. using the compareTo method defined in the Event class).
	 * 
	 * The line separator string used to separate the events should be retrieved
	 * in a machine-independent way by calling the function
	 * System.getProperty("line.separator").
	 */
	@Override
	public String toString() {
		
		String eventString = "";
		
		Collections.sort(events); //put events in natural order within list 
		for (int j=0; j<events.size(); j++) {
			eventString = eventString + events.get(j).toString();
			if (j<events.size()-1) { 
				//no line separator at end of string representation
				eventString = eventString 
						+ System.getProperty("line.separator");
			}
		}		
		return eventString; 
	}

	/**
	 * Determines whether this LineUp is internally consistent (i.e. it
	 * satisfies its class invariant).
	 * 
	 * @return true if this LineUp is internally consistent, and false
	 *         otherwise.
	 */
	public boolean checkInvariant() {
		for (int i=0; i<events.size();i++) {
			Event event = events.get(i); //checking this event
			if (event==null) {
				return false; //checking if event is null
			}
			for (int j=0;j<events.size();j++){
				
				if (events.get(j).equals(event) && j!=i) {
					return false; //checking if event is duplicated					
				}								
				if (j!=i&&events.get(j).getVenue().equals(event.getVenue())) {
					if (events.get(j).getSession()== event.getSession()) {
						return false; //checking if event is in a clash
					}	
				}
			}			
		}			
		return true; // nothing has failed, so return true.
	}
}
