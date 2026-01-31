
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


@DisplayName("Hotel Teacher Tester")
public class HotelTeacherTester {

	private final PrintStream originalOut = System.out;
	private ByteArrayOutputStream outputBuffer;

	@BeforeEach
	public void setUp() {
		outputBuffer = new ByteArrayOutputStream();
	}

	@AfterEach
	public void tearDown() {
		System.setOut(originalOut);
	}

	/* ---------- checkIn Tests ---------- */

	@Test
	@DisplayName("Check-in to available room")
	public void testCheckInAvailable() {
		HotelRoom r1 = new HotelRoom(307, 4);
		HotelRoom r2 = new HotelRoom(205, 3);
		r2.checkIn("Guest Test");
		HotelRoom r3 = new HotelRoom(402, 2);

		Hotel.checkIn("Dana", 307, r1, r2, r3);

		assertTrue(r1.isOccupied(), "Room should be occupied after check-in");
		assertEquals("Dana", r1.getGuest(), "Guest name should be Dana");
	}

	@Test
	@DisplayName("Check-in to non-existing room")
	public void testCheckInNonExisting() {
		HotelRoom r1 = new HotelRoom(307, 4);
		HotelRoom r2 = new HotelRoom(205, 3);
		r2.checkIn("Guest Test");
		HotelRoom r3 = new HotelRoom(402, 2);

		Hotel.checkIn("Avi", 999, r1, r2, r3);

		assertFalse(r1.isOccupied(), "Room 307 should remain unoccupied when checking in to non-existing room");
	}

	/* ---------- checkOut Tests ---------- */

	@Test
	@DisplayName("Check-out occupied room")
	public void testCheckOutOccupied() {
		HotelRoom r1 = new HotelRoom(307, 4);
		HotelRoom r2 = new HotelRoom(205, 3);
		r2.checkIn("Guest Test");
		HotelRoom r3 = new HotelRoom(402, 2);

		Hotel.checkOut(205, r1, r2, r3);

		assertFalse(r2.isOccupied(), "Room should not be occupied after check-out");
		assertEquals("", r2.getGuest(), "Guest should be empty string after check-out");
	}

	@Test
	@DisplayName("Check-out available room")
	public void testCheckOutAvailable() {
		HotelRoom r1 = new HotelRoom(307, 4);
		HotelRoom r2 = new HotelRoom(205, 3);
		HotelRoom r3 = new HotelRoom(402, 2);

		Hotel.checkOut(205, r1, r2, r3);

		assertFalse(r2.isOccupied(), "Room should remain available");
	}

	@Test
	@DisplayName("Check-out non-existing room")
	public void testCheckOutNonExisting() {
		HotelRoom r1 = new HotelRoom(307, 4);
		HotelRoom r2 = new HotelRoom(205, 3);
		r2.checkIn("Guest Test");
		HotelRoom r3 = new HotelRoom(402, 2);

		Hotel.checkOut(999, r1, r2, r3);

		assertTrue(r2.isOccupied(), "Room 205 should remain occupied when checking out non-existing room");
	}

	/* ---------- findRoomByNumber Tests ---------- */

	@Test
	@DisplayName("Find existing room by number")
	public void testFindRoomByNumberExists() {
		HotelRoom r1 = new HotelRoom(307, 4);
		HotelRoom r2 = new HotelRoom(205, 3);
		HotelRoom r3 = new HotelRoom(402, 2);

		HotelRoom result = Hotel.findRoomByNumber(205, r1, r2, r3);

		assertNotNull(result, "Should find existing room");
		assertEquals(205, result.getRoomNum(), "Found room should be room 205");
	}

	@Test
	@DisplayName("Find non-existing room by number")
	public void testFindRoomByNumberNotExists() {
		HotelRoom r1 = new HotelRoom(307, 4);
		HotelRoom r2 = new HotelRoom(205, 3);
		HotelRoom r3 = new HotelRoom(402, 2);

		HotelRoom result = Hotel.findRoomByNumber(999, r1, r2, r3);

		assertNull(result, "Should return null for non-existing room");
	}

	/* ---------- findAvailableByBeds Tests ---------- */

	@Test
	@DisplayName("Find available room by beds (exists)")
	public void testFindAvailableByBedsExists() {
		HotelRoom r1 = new HotelRoom(307, 4);
		HotelRoom r2 = new HotelRoom(205, 3);
		r2.checkIn("Guest Test");
		HotelRoom r3 = new HotelRoom(402, 2);

		System.setOut(new PrintStream(outputBuffer));
		Hotel.findAvailableByBeds(2, r1, r2, r3);
		System.setOut(originalOut);

		List<Integer> rooms = extractRoomNumbers(outputBuffer.toString());
		int[] expectedRooms = {402};

		assertEquals(expectedRooms.length, rooms.size(), "Should find 1 available room with 2 beds");
		assertEquals(expectedRooms[0], rooms.get(0), "Should find room 402");
	}

	@Test
	@DisplayName("Find available room by beds (none)")
	public void testFindAvailableByBedsNone() {
		HotelRoom r1 = new HotelRoom(307, 4);
		HotelRoom r2 = new HotelRoom(205, 3);
		r2.checkIn("Guest Test");
		HotelRoom r3 = new HotelRoom(402, 2);

		System.setOut(new PrintStream(outputBuffer));
		Hotel.findAvailableByBeds(3, r1, r2, r3);
		System.setOut(originalOut);

		List<Integer> rooms = extractRoomNumbers(outputBuffer.toString());

		assertEquals(0, rooms.size(), "Should find no available rooms with 3 beds");
	}

	@Test
	@DisplayName("Find available room by beds (illegal value)")
	public void testFindAvailableByBedsIllegal() {
		HotelRoom r1 = new HotelRoom(307, 4);
		HotelRoom r2 = new HotelRoom(205, 3);
		HotelRoom r3 = new HotelRoom(402, 2);

		System.setOut(new PrintStream(outputBuffer));
		Hotel.findAvailableByBeds(1, r1, r2, r3);
		System.setOut(originalOut);

		List<Integer> rooms = extractRoomNumbers(outputBuffer.toString());

		assertEquals(0, rooms.size(), "Should find no rooms for illegal bed count");
	}

	/* ---------- displaySorted Tests ---------- */

	@Test
	@DisplayName("displaySorted order [307,205,402]")
	public void testDisplaySortedOrder1() {
		HotelRoom r1 = new HotelRoom(307, 4);
		HotelRoom r2 = new HotelRoom(205, 3);
		HotelRoom r3 = new HotelRoom(402, 2);

		System.setOut(new PrintStream(outputBuffer));
		Hotel.displaySorted(r1, r2, r3);
		System.setOut(originalOut);

		List<Integer> rooms = extractRoomNumbers(outputBuffer.toString());
		int[] expectedOrder = {205, 307, 402};

		assertEquals(expectedOrder.length, rooms.size(), "Should display all 3 rooms");
		for (int i = 0; i < expectedOrder.length; i++) {
			assertEquals(expectedOrder[i], rooms.get(i), 
				"Room at position " + i + " should be " + expectedOrder[i]);
		}
	}

	@Test
	@DisplayName("displaySorted order [402,307,205]")
	public void testDisplaySortedOrder2() {
		HotelRoom r1 = new HotelRoom(402, 2);
		HotelRoom r2 = new HotelRoom(307, 4);
		HotelRoom r3 = new HotelRoom(205, 3);

		System.setOut(new PrintStream(outputBuffer));
		Hotel.displaySorted(r1, r2, r3);
		System.setOut(originalOut);

		List<Integer> rooms = extractRoomNumbers(outputBuffer.toString());
		int[] expectedOrder = {205, 307, 402};

		assertEquals(expectedOrder.length, rooms.size(), "Should display all 3 rooms");
		for (int i = 0; i < expectedOrder.length; i++) {
			assertEquals(expectedOrder[i], rooms.get(i), 
				"Room at position " + i + " should be " + expectedOrder[i]);
		}
	}

	/* ---------- Helper Methods ---------- */

	private List<Integer> extractRoomNumbers(String text) {
		List<Integer> result = new ArrayList<>();
		String[] parts = text.split("Room ");
		for (int i = 1; i < parts.length; i++) {
			try {
				result.add(Integer.parseInt(parts[i].substring(0, 3)));
			} catch (Exception ignored) {
			}
		}
		return result;
	}
}
