
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("HotelRoom Teacher Tester")
public class HotelRoomTeacherTester {

	/* ---------- Constructor Tests ---------- */

	@Test
	@DisplayName("Constructor with valid values")
	public void testConstructorValid() {
		HotelRoom r = new HotelRoom(203, 3);
		
		assertEquals(203, r.getRoomNum(), "Room number should be 203");
		assertEquals(3, r.getNumBeds(), "Number of beds should be 3");
		assertFalse(r.isOccupied(), "Room should not be occupied");
		assertEquals("", r.getGuest(), "Guest should be empty string");
	}

	@Test
	@DisplayName("Constructor with invalid room number")
	public void testConstructorInvalidRoomNum() {
		HotelRoom r = new HotelRoom(50, 3);
		
		assertEquals(999, r.getRoomNum(), "Invalid room number should default to 999");
	}

	@Test
	@DisplayName("Constructor with invalid number of beds")
	public void testConstructorInvalidNumBeds() {
		HotelRoom r = new HotelRoom(203, 10);
		
		assertEquals(2, r.getNumBeds(), "Invalid number of beds should default to 2");
	}

	/* ---------- Getters Tests ---------- */

	@Test
	@DisplayName("Getters after construction")
	public void testGettersAfterConstruction() {
		HotelRoom r = new HotelRoom(301, 4);
		
		assertEquals(301, r.getRoomNum(), "Room number should be 301");
		assertEquals(4, r.getNumBeds(), "Number of beds should be 4");
		assertFalse(r.isOccupied(), "Room should not be occupied");
		assertEquals("", r.getGuest(), "Guest should be empty string");
	}

	/* ---------- setRoomNum Tests ---------- */

	@Test
	@DisplayName("Set valid room number")
	public void testSetRoomNumValid() {
		HotelRoom r = new HotelRoom(200, 2);
		r.setRoomNum(345);
		
		assertEquals(345, r.getRoomNum(), "Room number should be updated to 345");
	}

	@Test
	@DisplayName("Set invalid room number")
	public void testSetRoomNumInvalid() {
		HotelRoom r = new HotelRoom(200, 2);
		r.setRoomNum(10);
		
		assertEquals(200, r.getRoomNum(), "Room number should remain unchanged at 200");
	}

	/* ---------- setNumBeds Tests ---------- */

	@Test
	@DisplayName("Set valid number of beds")
	public void testSetNumBedsValid() {
		HotelRoom r = new HotelRoom(210, 2);
		r.setNumBeds(4);
		
		assertEquals(4, r.getNumBeds(), "Number of beds should be updated to 4");
	}

	@Test
	@DisplayName("Set invalid number of beds")
	public void testSetNumBedsInvalid() {
		HotelRoom r = new HotelRoom(210, 2);
		r.setNumBeds(1);
		
		assertEquals(2, r.getNumBeds(), "Number of beds should remain unchanged at 2");
	}

	/* ---------- toString Tests ---------- */

	@Test
	@DisplayName("toString for available room")
	public void testToStringAvailable() {
		HotelRoom r = new HotelRoom(102, 2);
		String expected = "Room 102, 2 Beds: Available";
		
		assertEquals(expected, r.toString(), "toString should match expected format for available room");
	}

	@Test
	@DisplayName("toString for occupied room")
	public void testToStringOccupied() {
		HotelRoom r = new HotelRoom(205, 3);
		r.checkIn("Dana Levi");
		String expected = "Room 205, 3 Beds: Occupied by Dana Levi";
		
		assertEquals(expected, r.toString(), "toString should match expected format for occupied room");
	}

	/* ---------- equals Tests ---------- */

	@Test
	@DisplayName("Equal rooms")
	public void testEquals() {
		HotelRoom a = new HotelRoom(300, 2);
		HotelRoom b = new HotelRoom(300, 2);
		
		assertTrue(a.equals(b), "Rooms with same room number and beds should be equal");
	}

	/* ---------- before / after Tests ---------- */

	@Test
	@DisplayName("before method")
	public void testBefore() {
		HotelRoom a = new HotelRoom(101, 2);
		HotelRoom b = new HotelRoom(202, 2);
		
		assertTrue(a.before(b), "Room 101 should be before Room 202");
	}

	@Test
	@DisplayName("after method")
	public void testAfter() {
		HotelRoom a = new HotelRoom(303, 2);
		HotelRoom b = new HotelRoom(202, 2);
		
		assertTrue(a.after(b), "Room 303 should be after Room 202");
	}

	/* ---------- checkIn Tests ---------- */

	@Test
	@DisplayName("Check-in to available room")
	public void testCheckInAvailable() {
		HotelRoom r = new HotelRoom(400, 2);
		
		assertTrue(r.checkIn("Noa"), "Check-in to available room should return true");
		assertTrue(r.isOccupied(), "Room should be occupied after check-in");
		assertEquals("Noa", r.getGuest(), "Guest name should be set correctly");
	}

	/* ---------- checkOut Tests ---------- */

	@Test
	@DisplayName("Check-out from occupied room")
	public void testCheckOutOccupied() {
		HotelRoom r = new HotelRoom(500, 3);
		r.checkIn("Guest");
		r.checkOut();
		
		assertFalse(r.isOccupied(), "Room should not be occupied after check-out");
		assertEquals("", r.getGuest(), "Guest should be empty string after check-out");
	}
}
