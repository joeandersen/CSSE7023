package festival_ass1_submitted;

import java.util.*;

/**
 * <p>
 * A mutable representation of the shuttle services between venues at a
 * festival.
 * </p>
 * 
 * <p>
 * A shuttle timetable does not contain duplicate services (no two services run
 * from a source venue to a destination venue at the same time).
 * </p>
 */
public class ShuttleTimetable implements Iterable<Service> {

    
    // Joseph Andersen joe@joeandersen.com 33422619
    //
    // 15-4-15
	
	private ArrayList<Service> services;
	
	
	/*
	 * Invariant:
	 * no duplicate services
     * no null services
	 */
	

	/**
	 * Constructs a new shuttle timetable without any services.
	 **/
	public ShuttleTimetable() {
		services = new ArrayList<Service>();
	}

	/**
	 * Unless the shuttle timetable already contains an equivalent service, this
	 * method adds the given service to the shuttle timetable. If the timetable
	 * already contains an equivalent service, then this method should not
	 * change the shuttle timetable.
	 * 
	 * (Equivalence of services is judged using the equals method in the Service
	 * class.)
	 * 
	 * @param service
	 *            the service to be added to the shuttle timetable.
	 * @throws NullPointerException
	 *             if service is null
	 */
	public void addService(Service service) {
		
		if (service==null) {
			throw new NullPointerException("Services cannot be null");
		}
		
		if (!services.contains(service)){
			services.add(service);
		}
		
		
	}

	/**
	 * If the shuttle timetable contains a service that is equivalent to this
	 * one, then it is removed from the timetable. If there is no equivalent
	 * service, then the timetable is unchanged by the operation.
	 * 
	 * @param service
	 *            the service to be removed from the timetable.
	 */
	public void removeService(Service service) {
		if (services.contains(service)) {
			services.remove(service);
		}
	}

	/**
	 * Returns true if the timetable contains a shuttle service equivalent to
	 * the parameter service, and false otherwise.
	 * 
	 * @param service
	 *            the service to be searched for
	 * @return true iff the timetable contains a shuttle service equivalent to
	 *         the given parameter.
	 */
	public boolean hasService(Service service) {
		return services.contains(service); 
	}

	/**
	 * Returns the set of venues that you can get to by catching an available
	 * shuttle service from the source venue at the end of the given session.
	 * 
	 * @param source
	 *            the source venue
	 * @param session
	 *            the session number
	 * @return A set of venues that can be reached by catching a single shuttle
	 *         service from the source venue at the end of the given session.
	 * 
	 * @throws NullPointerException
	 *             if source is null
	 * @throws InvalidSessionException
	 *             if the session number is not positive
	 */
	public Set<Venue> getDestinations(Venue source, int session) {
		
		Set venues = new HashSet();
		
		if (source==null) {
			throw new NullPointerException("source cannot be null");
		}
		if (session <=0) {
			throw new InvalidSessionException("Session number must be positive");
		}
		
		for (int i = 0; i<services.size();i++) {
			if (services.get(i).getSource().equals(source) &&
					services.get(i).getSession()==session) {
				venues.add(services.get(i).getDestination());
			}
		}
		
		return venues; 
	}

	/**
	 * Returns an iterator over the services in the shuttle timetable.
	 */
	@Override
	public Iterator<Service> iterator() {
		return services.iterator(); 
	}

	/**
	 * Returns any meaningful implementation of the toString method for this
	 * class.
	 */
	@Override
	public String toString() {
		return services.toString();
	}

	/**
	 * Determines whether this ShuttleTimetable is internally consistent (i.e.
	 * it satisfies its class invariant).
	 * 
	 * @return true if this ShuttleTimetable is internally consistent, and false
	 *         otherwise.
	 */
	public boolean checkInvariant() {
		//Loop to check validity of services.
		
		for (int i = 0; i<services.size();i++) {
            
            if (services.get(i)==null) { //checking for null services
                return false;
            }
            
			for (int j = i+1; j<services.size();j++) {
				if (services.get(i).equals(services.get(j))) {
					return false; //checking for duplicates
				}
			}
			
		}
		
		return true; 
	}

}
