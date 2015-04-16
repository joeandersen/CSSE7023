package festival_old.test;

import org.junit.Assert;
import org.junit.Test;

import festival_old.*;

/**
 * Basic tests for the {@link Event} implementation class.
 * 
 * Write your own tests for the class here.
 */
public class EventTest {

	/** Test construction of a typical event. */
	@Test
	public void testTypicalEvent() {
		Venue venue = new Venue("v1");
		int session = 2;
		String act = "abc";
		
		Event event = new Event(venue, session, act);
		Assert.assertEquals(venue, event.getVenue());
		Assert.assertEquals(session, event.getSession());
		Assert.assertEquals(act, event.getAct());
		Assert.assertTrue("Invariant incorrect", event.checkInvariant());
		Assert.assertEquals("abc: session 2 at v1",event.toString());
		
		//and test comparison routines
		
		Venue venue1 = new Venue("v1");
		Venue venue2 = new Venue("v2");
		int session2 = 2;
		int session1 = 1;
		String act1 = "a1";
		String act2 = "a2";
		
		Event event111 = new Event(venue1, session1, act1);

		Event event111a = new Event(venue1, session1, act1);
		//Different object with same properties
		
		
		Event event112 = new Event(venue1, session1, act2);
		Event event121 = new Event(venue1, session2, act1);
		Event event122 = new Event(venue1, session2, act2);
		Event event211 = new Event(venue2, session1, act1);
		Event event212 = new Event(venue2, session1, act2);
		Event event221 = new Event(venue2, session2, act1);
		Event event222 = new Event(venue2, session2, act2);
		
		//checking equal events are equal
		
		Assert.assertTrue("compare 111 w 111", event111.equals(event111));
		Assert.assertTrue("compare 111 w 111a", event111.equals(event111a));
		Assert.assertTrue("compare 111a w 111", event111a.equals(event111));
		
		//checking different events are different
		Assert.assertFalse("compare 111 w 112", event111.equals(event112));
		Assert.assertFalse("compare 111 w 121", event111.equals(event121));
		Assert.assertFalse("compare 111 w 122", event111.equals(event122));
		Assert.assertFalse("compare 111 w 211", event111.equals(event211));
		Assert.assertFalse("compare 111 w 212", event111.equals(event212));
		Assert.assertFalse("compare 111 w 221", event111.equals(event221));
		Assert.assertFalse("compare 111 w 222", event111.equals(event222));
		
		
		//checking ordering works as expected
		Assert.assertEquals(0,event111.compareTo(event111));
		Assert.assertEquals(-1,event111.compareTo(event112));
		Assert.assertEquals(1,event112.compareTo(event111));
		
		Assert.assertEquals(-1,event111.compareTo(event121));
		Assert.assertEquals(1,event121.compareTo(event111));
		
		Assert.assertEquals(-1,event111.compareTo(event211));
		Assert.assertEquals(1,event211.compareTo(event111));
	}
	
	/**
	 * Check that the appropriate exception is thrown if an event is created
	 * with an invalid session number.
	 */
	@Test(expected = InvalidSessionException.class)
	public void testInvalidSessionException() {
		Venue venue = new Venue("v1");
		int session = -2;
		String act = "abc";
		Event event = new Event(venue, session, act);
	}
	
	
	/**
	 * Check that the appropriate exception is thrown if an event is created
	 * with a null venue.
	 */
	@Test(expected = NullPointerException.class)
	public void testNullVenueException() {
		
		int session = 2;
		String act = "abc";
		Event event = new Event(null, session, act);
	}
	/**
	 * Check that the appropriate exception is thrown if an event is created
	 * with a null act.
	 */
	@Test(expected = NullPointerException.class)
	public void testNullActException() {
		Venue venue = new Venue("v1");
		int session = 2;
	
		Event event = new Event(venue, session, null);
	}
	
	
	
	
}
