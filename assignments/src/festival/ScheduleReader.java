package festival;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides a method to read a shuttle timetable from a file.
 */
public class ScheduleReader {

	private static Set<String> setOfVenues;
	// a set of venue names, used to check for
	// source venue repetition.
	private static int numberOfSessions; // the number of sessions in the
											// festival.

	/**
	 * <p>
	 * Reads a text file called fileName that describes the shuttle services
	 * available for a festival, and returns the shuttle timetable containing
	 * each of the services in the file.
	 * </p>
	 * 
	 * <p>
	 * The first line of the file contains a single positive integer, denoting
	 * the number of sessions in the festival. The rest of the file contains
	 * zero or more descriptions of venues and their services for the available
	 * sessions.
	 * </p>
	 * 
	 * <p>
	 * There may be leading or trailing whitespace on the first line of the file
	 * that contains the single positive integer denoting the number of sessions
	 * in the festival.
	 * </p>
	 * 
	 * <p>
	 * A description of a venue and its services consists of (1) a venue name on
	 * a line of its own, followed by (2) one line for each session in the
	 * festival that describes the services that depart the venue at the end of
	 * that session, followed by (3) an empty line. <br>
	 * <br>
	 * (For the purpose of this method) a venue name is simply an unformatted
	 * non-empty string that doesn't contain any whitespace characters. There
	 * may be leading and trailing whitespace on the line containing the venue
	 * name but no other information. <br>
	 * <br>
	 * For (2) the lines for each session in the festival should be ordered by
	 * session number: starting at 1 and ending at the number of sessions in the
	 * festival. Each such line should consist of the session number, followed
	 * by zero or more venue names separated by white spaces. There may be
	 * leading or trailing whitespace on each such line.
	 * 
	 * A venue may not have a shuttle service to itself, and there can be no
	 * duplicate services.
	 * </p>
	 * 
	 * <p>
	 * A venue shouldn't have more than one description of itself and its
	 * services, but a venue doesn't have to have a description of itself and
	 * its services if it doesn't have any.
	 * </p>
	 * 
	 * @param fileName
	 *            the file to read from.
	 * @return the shuttle timetable that was read from the file.
	 * @throws IOException
	 *             if there is an error reading from the input file.
	 * @throws FormatException
	 *             if there is an error with the input format (e.g. a venue has
	 *             more than one description of itself and its services, or the
	 *             file format is not as specified above in any other way.) The
	 *             FormatExceptions thrown should have a meaningful message that
	 *             accurately describes the problem with the input file format.
	 */
	public static ShuttleTimetable read(String fileName) throws IOException,
			FormatException {

		setOfVenues = new HashSet<String>(); // initialize the set
		Scanner s = new Scanner(new File(fileName)); // open the file, start
														// scanning it.
		String line = s.nextLine();
		// System.out.println(line);
		getNumberOfSessions(line);// parse first line for numebr of sessions

		ShuttleTimetable myTimeTable = new ShuttleTimetable(); // initialize the
																// schedule
		while (s.hasNextLine()) { // each cycle of this loop reads in all the
									// data for a single source venue, checking
									// the format, and for repeats etc.

			Venue sourceVenue = getSourceVenue(s.nextLine());// parse and check
																// source venue
																// line,
																// generate
																// source venue.

			Set<Service> services = getServicesFromVenue(s, sourceVenue);
			// get the set of services for this venue

			// loop over services, add into the timetable. services already
			// gauranteed not to have duplicates
			for (Service newService : services) {
				myTimeTable.addService(newService);
			}

			checkNextLine(s); // next line should be blank

		}

		s.close();

		return myTimeTable;
	}

	/**
	 * Checks if the line after the data for a venue is empty.
	 * 
	 * @param s
	 * @throws FormatException
	 */
	
	private static void checkNextLine(Scanner s) throws FormatException {
		if (s.hasNextLine()){
			String line = s.nextLine().trim();
			//System.out.println(line);
	
			if (!line.isEmpty()) {
				s.close();
				throw new FormatException("must have blank line between venues");
			}
		}else {
			s.close();
			throw new FormatException("must have blank line after final venue");
		}
	}

	/**
	 * Parses the first line of the schedule data file to extract the number of
	 * sessions in the festival.
	 * 
	 * @Param myLine: The string that system is to parse
	 * 
	 * @Return numberOfSessions: a positive integer that tells the system how
	 *         many sessions there are int he festival.
	 * 
	 * @Throws FormatException: If the line does not correctly resolve to a
	 *         valid number of sessions
	 */

	private static void getNumberOfSessions(String myLine)
			throws FormatException {

		try {
			numberOfSessions = Integer.parseInt(myLine.trim());
		} catch (NumberFormatException c) {
			throw new FormatException("first line of file must be an integer");
		}
		if (numberOfSessions <= 0) {
			throw new FormatException(
					"number of sessions in event must be positive");
		}

	}

	private static Venue getSourceVenue(String myLine) throws FormatException {
		String sourceVenueName = myLine.trim();
		//System.out.println(sourceVenueName);
		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(sourceVenueName);
		boolean found = matcher.find();
		Venue sourceVenue;
		if (!found) {
			sourceVenue = new Venue(sourceVenueName);
			if (setOfVenues.contains(sourceVenueName)) {

				throw new FormatException(
						"venues cannot appear twice in schedule");
			}
			setOfVenues.add(sourceVenueName);
		} else {

			//System.out.println(sourceVenueName + " " + numberOfSessions);
			throw new FormatException("venue names cannot contain spaces");
		}
		return sourceVenue;
	}

	private static Set<Service> getServicesFromVenue(Scanner s,
			Venue sourceVenue) throws FormatException {
		// loop over the sessions
		Set<Service> services = new HashSet<Service>();
		for (int j = 0; j < numberOfSessions; j++) {
			if(s.hasNextLine()){
				String line = s.nextLine().trim();
				//System.out.println(line);
				Scanner sc = new Scanner(line);
				if (Integer.parseInt(sc.next()) == (j + 1)) {
					while (sc.hasNext()) {
						Service newService;
						try {
							newService = new Service(sourceVenue, new Venue(
									sc.next()), j + 1);
							if (!services.contains(newService)) {
								services.add(newService);
							} else {
								throw new FormatException("Duplicate service found");
							}
	
						} catch (InvalidServiceException e) {
							sc.close();
							throw new FormatException("Service not valid");
						}
					}
				} else {
					sc.close();
					//System.out.println(line + " " + j);
					throw new FormatException("timetable must have every session "
							+ "listed for each venue");
				}
				sc.close();
			}else{
				throw new FormatException("File appears to terminate mid venue description");
			}
		}

		return services;
	}
}
