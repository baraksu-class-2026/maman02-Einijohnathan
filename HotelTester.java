import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class HotelTester {

	private static int totalTests = 0;
	private static int passedTests = 0;

	public static void main(String[] args) {

		System.out.println("===== Hotel Tester =====\n");

		testCheckIn();
		testCheckOut();
		testFindRoomByNumber();
		testFindAvailableByBeds();
		testDisplaySorted();

		System.out.println("=================================");
		System.out.println("FINAL SUMMARY");
		System.out.println("Passed " + passedTests + " out of " + totalTests + " tests");
		System.out.println("=================================");
	}

	public static int getTotalTests() {
		return totalTests;
	}

	public static int getPassedTests() {
		return passedTests;
	}

	/* ---------- checkIn ---------- */

	private static void testCheckIn() {
		System.out.println("---------------------------------");
		System.out.println("Testing Hotel.checkIn");
		System.out.println("---------------------------------");

		// Existing and available room
		try {
			HotelRoom r1 = new HotelRoom(307, 4);
			HotelRoom r2 = new HotelRoom(205, 3);
			r2.checkIn("Guest Test");
			HotelRoom r3 = new HotelRoom(402, 2);

			Hotel.checkIn("Dana", 307, r1, r2, r3);

			totalTests++;
			System.out.println("Test: Check-in to available room");

			if (r1.isOccupied() && r1.getGuest().equals("Dana")) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: checkIn(\"Dana\", 307)");
				System.out.println("Expected: occupied=true, guest=\"Dana\"");
				System.out.println("Actual:   occupied=" + r1.isOccupied() + ", guest=\"" + r1.getGuest() + "\"");
			}
			System.out.println();

		} catch (Exception e) {
			reportException("Check-in to available room", "checkIn(\"Dana\", 307)", e);
		}

		// Non-existing room
		try {
			HotelRoom r1 = new HotelRoom(307, 4);
			HotelRoom r2 = new HotelRoom(205, 3);
			r2.checkIn("Guest Test");
			HotelRoom r3 = new HotelRoom(402, 2);

			Hotel.checkIn("Avi", 999, r1, r2, r3);

			totalTests++;
			System.out.println("Test: Check-in to non-existing room");

			if (!r1.isOccupied()) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: checkIn(\"Avi\", 999)");
				System.out.println("Expected: no change to room 307");
				System.out.println("Actual:   occupied=" + r1.isOccupied() + ", guest=\"" + r1.getGuest() + "\"");
			}
			System.out.println();

		} catch (Exception e) {
			reportException("Check-in to non-existing room", "checkIn(\"Avi\", 999)", e);
		}
	}

	/* ---------- checkOut ---------- */

	private static void testCheckOut() {
		System.out.println("---------------------------------");
		System.out.println("Testing Hotel.checkOut");
		System.out.println("---------------------------------");

		// Occupied room
		try {
			HotelRoom r1 = new HotelRoom(307, 4);
			HotelRoom r2 = new HotelRoom(205, 3);
			r2.checkIn("Guest Test");
			HotelRoom r3 = new HotelRoom(402, 2);

			Hotel.checkOut(205, r1, r2, r3);

			totalTests++;
			System.out.println("Test: Check-out occupied room");

			if (!r2.isOccupied() && r2.getGuest().equals("")) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: checkOut(205)");
				System.out.println("Expected: occupied=false, guest=\"\"");
				System.out.println("Actual:   occupied=" + r2.isOccupied() + ", guest=\"" + r2.getGuest() + "\"");
			}
			System.out.println();

		} catch (Exception e) {
			reportException("Check-out occupied room", "checkOut(205)", e);
		}

		// Available room
		try {
			HotelRoom r1 = new HotelRoom(307, 4);
			HotelRoom r2 = new HotelRoom(205, 3);
			HotelRoom r3 = new HotelRoom(402, 2);

			Hotel.checkOut(205, r1, r2, r3);

			totalTests++;
			System.out.println("Test: Check-out available room");

			if (!r2.isOccupied()) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: checkOut(205)");
				System.out.println("Expected: room remains available");
			}
			System.out.println();

		} catch (Exception e) {
			reportException("Check-out available room", "checkOut(205)", e);
		}

		// Non-existing room
		try {
			HotelRoom r1 = new HotelRoom(307, 4);
			HotelRoom r2 = new HotelRoom(205, 3);
			r2.checkIn("Guest Test");
			HotelRoom r3 = new HotelRoom(402, 2);

			Hotel.checkOut(999, r1, r2, r3);

			totalTests++;
			System.out.println("Test: Check-out non-existing room");

			if (r2.isOccupied()) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: checkOut(999)");
				System.out.println("Expected: no room state changes");
			}
			System.out.println();

		} catch (Exception e) {
			reportException("Check-out non-existing room", "checkOut(999)", e);
		}
	}

	/* ---------- findRoomByNumber ---------- */

	private static void testFindRoomByNumber() {
		System.out.println("---------------------------------");
		System.out.println("Testing Hotel.findRoomByNumber");
		System.out.println("---------------------------------");

		// Existing room
		try {
			HotelRoom r1 = new HotelRoom(307, 4);
			HotelRoom r2 = new HotelRoom(205, 3);
			HotelRoom r3 = new HotelRoom(402, 2);

			HotelRoom result = Hotel.findRoomByNumber(205, r1, r2, r3);

			totalTests++;
			System.out.println("Test: Find existing room");

			if (result != null && result.getRoomNum() == 205) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: findRoomByNumber(205)");
				System.out.println("Expected: room 205");
				System.out.println("Actual: " + result);
			}
			System.out.println();

		} catch (Exception e) {
			reportException("Find existing room", "findRoomByNumber(205)", e);
		}

		// Non-existing room
		try {
			HotelRoom r1 = new HotelRoom(307, 4);
			HotelRoom r2 = new HotelRoom(205, 3);
			HotelRoom r3 = new HotelRoom(402, 2);

			HotelRoom result = Hotel.findRoomByNumber(999, r1, r2, r3);

			totalTests++;
			System.out.println("Test: Find non-existing room");

			if (result == null) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: findRoomByNumber(999)");
				System.out.println("Expected: null");
				System.out.println("Actual: room " + result.getRoomNum());
			}
			System.out.println();

		} catch (Exception e) {
			reportException("Find non-existing room", "findRoomByNumber(999)", e);
		}
	}

	/* ---------- findAvailableByBeds (void) ---------- */

	private static void testFindAvailableByBeds() {
		System.out.println("---------------------------------");
		System.out.println("Testing Hotel.findAvailableByBeds");
		System.out.println("---------------------------------");

		runFindAvailableByBedsTest("Find available room by beds (exists)", 2, new int[] { 307, 205, 402 },
				new boolean[] { false, true, false }, new int[] { 402 });

		runFindAvailableByBedsTest("Find available room by beds (none)", 3, new int[] { 307, 205, 402 },
				new boolean[] { false, true, false }, new int[] {});

		runFindAvailableByBedsTest("Find available room by beds (illegal value)", 1, new int[] { 307, 205, 402 },
				new boolean[] { false, false, false }, new int[] {});
	}

	private static void runFindAvailableByBedsTest(String testName, int beds, int[] roomNums, boolean[] occupied,
			int[] expectedPrintedRooms) {

		try {
			HotelRoom r1 = new HotelRoom(roomNums[0], 4);
			HotelRoom r2 = new HotelRoom(roomNums[1], 3);
			HotelRoom r3 = new HotelRoom(roomNums[2], 2);

			if (occupied[1])
				r2.checkIn("Guest Test");

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			PrintStream originalOut = System.out;
			System.setOut(new PrintStream(buffer));

			Hotel.findAvailableByBeds(beds, r1, r2, r3);

			System.setOut(originalOut);

			List<Integer> rooms = extractRoomNumbers(buffer.toString());

			totalTests++;
			System.out.println("Test: " + testName);

			boolean ok = rooms.size() == expectedPrintedRooms.length;
			for (int i = 0; i < rooms.size() && ok; i++) {
				ok = rooms.get(i) == expectedPrintedRooms[i];
			}

			if (ok) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: findAvailableByBeds(" + beds + ")");
				System.out.println("Expected printed rooms: " + arrayToString(expectedPrintedRooms));
				System.out.println("Actual printed rooms:   " + rooms);
			}
			System.out.println();

		} catch (Exception e) {
			reportException(testName, "findAvailableByBeds(" + beds + ")", e);
		}
	}

	/* ---------- displaySorted ---------- */

	private static void testDisplaySorted() {
		System.out.println("---------------------------------");
		System.out.println("Testing Hotel.displaySorted");
		System.out.println("---------------------------------");

		runDisplaySortedTest("displaySorted order [307,205,402]", new int[] { 307, 205, 402 },
				new int[] { 205, 307, 402 });

		runDisplaySortedTest("displaySorted order [402,307,205]", new int[] { 402, 307, 205 },
				new int[] { 205, 307, 402 });
	}

	private static void runDisplaySortedTest(String testName, int[] inputOrder, int[] expectedOrder) {

		try {
			HotelRoom r1 = new HotelRoom(inputOrder[0], 4);
			HotelRoom r2 = new HotelRoom(inputOrder[1], 3);
			HotelRoom r3 = new HotelRoom(inputOrder[2], 2);

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			PrintStream originalOut = System.out;
			System.setOut(new PrintStream(buffer));

			Hotel.displaySorted(r1, r2, r3);

			System.setOut(originalOut);

			List<Integer> rooms = extractRoomNumbers(buffer.toString());

			totalTests++;
			System.out.println("Test: " + testName);

			boolean ok = rooms.size() == expectedOrder.length;
			for (int i = 0; i < rooms.size() && ok; i++) {
				ok = rooms.get(i) == expectedOrder[i];
			}

			if (ok) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Expected order: " + arrayToString(expectedOrder));
				System.out.println("Actual order:   " + rooms);
				System.out.println("Full output:");
				System.out.println(buffer.toString());
			}
			System.out.println();

		} catch (Exception e) {
			reportException(testName, "displaySorted", e);
		}
	}

	/* ---------- helpers ---------- */

	private static List<Integer> extractRoomNumbers(String text) {
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

	private static String arrayToString(int[] arr) {
		String s = "[";
		for (int i = 0; i < arr.length; i++) {
			s += arr[i];
			if (i < arr.length - 1)
				s += ", ";
		}
		return s + "]";
	}

	private static void reportException(String testName, String parameters, Exception e) {
		totalTests++;
		System.out.println("Test: " + testName);
		System.out.println("FAIL");
		System.out.println("Parameters: " + parameters);
		System.out.println("Exception: " + e.getClass().getSimpleName());
		System.out.println();
	}
}
