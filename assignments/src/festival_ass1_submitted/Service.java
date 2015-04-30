package festival_ass1_submitted;

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

    // Joseph Andersen joe@joeandersen.com 33422619
    //
    // 15-4-15
    
	private Venue source; //where service starts from
	private Venue destination; //where service goes
	private int session; //when service goes (at end of session)
	
	
	/*
	 * Invariants:
	 * 
	 * source <> null
	 * 
	 * destination <> null
	 * 
	 * source <> destination
	 * 
	 * session >0
	 * 
	 */
	
	
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
		
		//checking for valid parameters 
		
		if (source==null||destination==null) {
			throw new NullPointerException("Service must run between "
					+ "non-null venues");
		}
		
		this.source = source;
		
		if (source.equals(destination)) {
			throw new InvalidServiceException("Service must run between "
					+ "distinct venues");
		}
		
		this.destination = destination;
		
		if (session <=0) {
			throw new InvalidSessionException("Session must be positive");
		}
		
		this.session = session;
	}

	/**
	 * Returns the venue that the service departs from.
	 * 
	 * @return the source venue of this service.
	 */
	public Venue getSource() {
		return this.source; 
	}

	/**
	 * Returns the venue that the service arrives at.
	 * 
	 * @return the destination venue of this service.
	 */
	public Venue getDestination() {
		return this.destination; 
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
		return this.session; 
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
		if (!(object instanceof Service)) {
			return false;
		}
		return this.session==((Service) object).session 
				&& this.source.equals(((Service) object).source)
				&& this.destination.equals(((Service) object).destination);
	}

	@Override
	public int hashCode() {
		return this.source.hashCode() + this.destination.hashCode()+ this.session;
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
		return "Departs "+this.source.toString()+" after session "
					+session+" for "+this.destination.toString(); 
	}

	/**
	 * Determines whether this Service is internally consistent (i.e. it
	 * satisfies its class invariant).
	 * 
	 * @return true if this Service is internally consistent, and false
	 *         otherwise.
	 */
	public boolean checkInvariant() {
		return this.source != null && this.destination !=null 
				&&  !this.source.equals(this.destination) 
				&& this.session>0; 
	}
}
