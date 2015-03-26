package festival;

/**
 * <p>
 * An immutable class representing a shuttle service for a festival.
 * </p>
 * 
 * <p>
 * A shuttle service departs a venue at the end of a session, and arrives at a
 * destination venue before the start of the next session. (The source and
 * destination venues in a service are distinct.)
 * </p>
 */
public class Service {

	// REMOVE THIS LINE AND INSERT YOUR INSTANCE VARIABLES AND IMPLEMENTATION
	// INVARIANT HERE

	/**
	 * Creates a new service that departs the source venue at the end of the
	 * given session, and arrives at the destination venue before the start of
	 * the next session.
	 * 
	 * @param source
	 *            the venue that the service departs from
	 * @param destination
	 *            the venue that the service arrives at
	 * @param session
	 *            the session when the service departs
	 * @throws NullPointerException
	 *             if either the source or destination is null
	 * @throws InvalidServiceException
	 *             if the source venue equals the destination venue
	 * @throws InvalidSessionException
	 *             if session <= 0
	 */
	public Service(Venue source, Venue destination, int session) {
		// REMOVE THIS LINE AND WRITE THIS METHOD
	}

	/**
	 * Returns the venue that the service departs from.
	 * 
	 * @return the source venue of this service.
	 */
	public Venue getSource() {
		return null; // REMOVE THIS LINE AND WRITE THIS METHOD
	}

	/**
	 * Returns the venue that the service arrives at.
	 * 
	 * @return the destination venue of this service.
	 */
	public Venue getDestination() {
		return null; // REMOVE THIS LINE AND WRITE THIS METHOD
	}

	/**
	 * Returns the number of the session when the service departs.
	 * 
	 * (To be precise, the service departs at the end of this session, arriving
	 * before the start of the next session.)
	 * 
	 * @return the session when this service departs
	 */
	public int getSession() {
		return -1; // REMOVE THIS LINE AND WRITE THIS METHOD
	}

	/**
	 * Two services are considered to be equal if they have the same source,
	 * destination and session.
	 * 
	 * (Two venues are considered to be the same if they are equal according to
	 * their equals method.)
	 */
	@Override
	public boolean equals(Object object) {
		return super.equals(object); // REMOVE THIS LINE AND WRITE THIS METHOD
	}

	@Override
	public int hashCode() {
		return super.hashCode(); // REMOVE THIS LINE AND WRITE THIS METHOD
	}

	/**
	 * Returns a string of the form:
	 * 
	 * "Departs SOURCE after session SESSION for DESTINATION"
	 * 
	 * where SOURCE is the name of the source venue, SESSION is the session
	 * number when this service departs and and DESTINATION is the name of the
	 * destination venue.
	 */
	@Override
	public String toString() {
		return null; // REMOVE THIS LINE AND WRITE THIS METHOD
	}

	/**
	 * Determines whether this Service is internally consistent (i.e. it
	 * satisfies its class invariant).
	 * 
	 * @return true if this Service is internally consistent, and false
	 *         otherwise.
	 */
	public boolean checkInvariant() {
		return true; // REMOVE THIS LINE AND WRITE THIS METHOD
	}
}
