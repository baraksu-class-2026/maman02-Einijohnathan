public class HotelRoomTester {

	private static int totalTests = 0;
	private static int passedTests = 0;

	public static void main(String[] args) {

		System.out.println("===== HotelRoom Tester =====\n");

		testConstructor();
		testGetters();
		testSetRoomNum();
		testSetNumBeds();
		testToString();
		testEquals();
		testBeforeAfter();
		testCheckIn();
		testCheckOut();

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

	/* ---------- Constructor ---------- */

	private static void testConstructor() {
		System.out.println("---------------------------------");
		System.out.println("Testing constructor");
		System.out.println("---------------------------------");

		// Valid values
		try {
			HotelRoom r = new HotelRoom(203, 3);
			totalTests++;
			System.out.println("Test: Valid constructor values");

			if (r.getRoomNum() == 203 && r.getNumBeds() == 3 && !r.isOccupied() && r.getGuest().equals("")) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: HotelRoom(203, 3)");
				System.out.println("Expected: roomNum=203, numBeds=3, occupied=false, guest=\"\"");
				System.out.println("Actual:   roomNum=" + r.getRoomNum() + ", numBeds=" + r.getNumBeds() + ", occupied="
						+ r.isOccupied() + ", guest=\"" + r.getGuest() + "\"");
			}
			System.out.println();
		} catch (Exception e) {
			reportException("Valid constructor values", "HotelRoom(203, 3)", e);
		}

		// Invalid room number
		try {
			HotelRoom r = new HotelRoom(50, 3);
			totalTests++;
			System.out.println("Test: Invalid room number");

			if (r.getRoomNum() == 999) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: HotelRoom(50, 3)");
				System.out.println("Expected roomNum: 999");
				System.out.println("Actual roomNum:   " + r.getRoomNum());
			}
			System.out.println();
		} catch (Exception e) {
			reportException("Invalid room number", "HotelRoom(50, 3)", e);
		}

		// Invalid number of beds
		try {
			HotelRoom r = new HotelRoom(203, 10);
			totalTests++;
			System.out.println("Test: Invalid number of beds");

			if (r.getNumBeds() == 2) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: HotelRoom(203, 10)");
				System.out.println("Expected numBeds: 2");
				System.out.println("Actual numBeds:   " + r.getNumBeds());
			}
			System.out.println();
		} catch (Exception e) {
			reportException("Invalid number of beds", "HotelRoom(203, 10)", e);
		}
	}

	/* ---------- Getters ---------- */

	private static void testGetters() {
		System.out.println("---------------------------------");
		System.out.println("Testing getters");
		System.out.println("---------------------------------");

		try {
			HotelRoom r = new HotelRoom(301, 4);
			totalTests++;
			System.out.println("Test: Getters after construction");

			boolean ok = r.getRoomNum() == 301 && r.getNumBeds() == 4 && !r.isOccupied() && r.getGuest().equals("");

			if (ok) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: HotelRoom(301, 4)");
				System.out.println("Expected: roomNum=301, numBeds=4, occupied=false, guest=\"\"");
				System.out.println("Actual:   roomNum=" + r.getRoomNum() + ", numBeds=" + r.getNumBeds() + ", occupied="
						+ r.isOccupied() + ", guest=\"" + r.getGuest() + "\"");
			}
			System.out.println();
		} catch (Exception e) {
			reportException("Getters after construction", "HotelRoom(301, 4)", e);
		}
	}

	/* ---------- setRoomNum ---------- */

	private static void testSetRoomNum() {
		System.out.println("---------------------------------");
		System.out.println("Testing setRoomNum");
		System.out.println("---------------------------------");

		// Valid
		try {
			HotelRoom r = new HotelRoom(200, 2);
			r.setRoomNum(345);

			totalTests++;
			System.out.println("Test: Set valid room number");

			if (r.getRoomNum() == 345) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: setRoomNum(345)");
				System.out.println("Expected roomNum: 345");
				System.out.println("Actual roomNum:   " + r.getRoomNum());
			}
			System.out.println();
		} catch (Exception e) {
			reportException("Set valid room number", "setRoomNum(345)", e);
		}

		// Invalid
		try {
			HotelRoom r = new HotelRoom(200, 2);
			r.setRoomNum(10);

			totalTests++;
			System.out.println("Test: Set invalid room number");

			if (r.getRoomNum() == 200) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: setRoomNum(10)");
				System.out.println("Expected roomNum: 200 (unchanged)");
				System.out.println("Actual roomNum:   " + r.getRoomNum());
			}
			System.out.println();
		} catch (Exception e) {
			reportException("Set invalid room number", "setRoomNum(10)", e);
		}
	}

	/* ---------- setNumBeds ---------- */

	private static void testSetNumBeds() {
		System.out.println("---------------------------------");
		System.out.println("Testing setNumBeds");
		System.out.println("---------------------------------");

		// Valid
		try {
			HotelRoom r = new HotelRoom(210, 2);
			r.setNumBeds(4);

			totalTests++;
			System.out.println("Test: Set valid number of beds");

			if (r.getNumBeds() == 4) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: setNumBeds(4)");
				System.out.println("Expected numBeds: 4");
				System.out.println("Actual numBeds:   " + r.getNumBeds());
			}
			System.out.println();
		} catch (Exception e) {
			reportException("Set valid number of beds", "setNumBeds(4)", e);
		}

		// Invalid
		try {
			HotelRoom r = new HotelRoom(210, 2);
			r.setNumBeds(1);

			totalTests++;
			System.out.println("Test: Set invalid number of beds");

			if (r.getNumBeds() == 2) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: setNumBeds(1)");
				System.out.println("Expected numBeds: 2 (unchanged)");
				System.out.println("Actual numBeds:   " + r.getNumBeds());
			}
			System.out.println();
		} catch (Exception e) {
			reportException("Set invalid number of beds", "setNumBeds(1)", e);
		}
	}

	/* ---------- toString ---------- */

	private static void testToString() {
		System.out.println("---------------------------------");
		System.out.println("Testing toString");
		System.out.println("---------------------------------");

		try {
			HotelRoom r = new HotelRoom(102, 2);
			String expected = "Room 102, 2 Beds: Available";

			totalTests++;
			System.out.println("Test: toString available room");

			if (r.toString().equals(expected)) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: HotelRoom(102, 2)");
				System.out.println("Expected: \"" + expected + "\"");
				System.out.println("Actual:   \"" + r.toString() + "\"");
			}
			System.out.println();
		} catch (Exception e) {
			reportException("toString available room", "HotelRoom(102, 2)", e);
		}

		try {
			HotelRoom r = new HotelRoom(205, 3);
			r.checkIn("Dana Levi");
			String expected = "Room 205, 3 Beds: Occupied by Dana Levi";

			totalTests++;
			System.out.println("Test: toString occupied room");

			if (r.toString().equals(expected)) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: HotelRoom(205, 3) + checkIn(\"Dana Levi\")");
				System.out.println("Expected: \"" + expected + "\"");
				System.out.println("Actual:   \"" + r.toString() + "\"");
			}
			System.out.println();
		} catch (Exception e) {
			reportException("toString occupied room", "HotelRoom(205, 3), guest=Dana Levi", e);
		}
	}

	/* ---------- equals ---------- */

	private static void testEquals() {
		System.out.println("---------------------------------");
		System.out.println("Testing equals");
		System.out.println("---------------------------------");

		try {
			HotelRoom a = new HotelRoom(300, 2);
			HotelRoom b = new HotelRoom(300, 2);

			totalTests++;
			System.out.println("Test: Equal rooms");

			if (a.equals(b)) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: (300,2) vs (300,2)");
				System.out.println("Expected: true");
				System.out.println("Actual:   false");
			}
			System.out.println();
		} catch (Exception e) {
			reportException("Equal rooms", "(300,2) vs (300,2)", e);
		}
	}

	/* ---------- before / after ---------- */

	private static void testBeforeAfter() {
		System.out.println("---------------------------------");
		System.out.println("Testing before / after");
		System.out.println("---------------------------------");

		try {
			HotelRoom a = new HotelRoom(101, 2);
			HotelRoom b = new HotelRoom(202, 2);

			totalTests++;
			System.out.println("Test: before method");

			if (a.before(b)) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: 101 before 202");
				System.out.println("Expected: true");
				System.out.println("Actual:   false");
			}
			System.out.println();
		} catch (Exception e) {
			reportException("before method", "101 before 202", e);
		}

		try {
			HotelRoom a = new HotelRoom(303, 2);
			HotelRoom b = new HotelRoom(202, 2);

			totalTests++;
			System.out.println("Test: after method");

			if (a.after(b)) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: 303 after 202");
				System.out.println("Expected: true");
				System.out.println("Actual:   false");
			}
			System.out.println();
		} catch (Exception e) {
			reportException("after method", "303 after 202", e);
		}
	}

	/* ---------- checkIn ---------- */

	private static void testCheckIn() {
		System.out.println("---------------------------------");
		System.out.println("Testing checkIn");
		System.out.println("---------------------------------");

		try {
			HotelRoom r = new HotelRoom(400, 2);

			totalTests++;
			System.out.println("Test: Check-in available room");

			if (r.checkIn("Noa")) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: checkIn(\"Noa\")");
				System.out.println("Expected return: true");
				System.out.println("Actual return:   false");
			}
			System.out.println();
		} catch (Exception e) {
			reportException("Check-in available room", "checkIn(\"Noa\")", e);
		}
	}

	/* ---------- checkOut ---------- */

	private static void testCheckOut() {
		System.out.println("---------------------------------");
		System.out.println("Testing checkOut");
		System.out.println("---------------------------------");

		try {
			HotelRoom r = new HotelRoom(500, 3);
			r.checkIn("Guest");
			r.checkOut();

			totalTests++;
			System.out.println("Test: Check-out occupied room");

			if (!r.isOccupied() && r.getGuest().equals("")) {
				System.out.println("PASS");
				passedTests++;
			} else {
				System.out.println("FAIL");
				System.out.println("Parameters: checkOut() on occupied room");
				System.out.println("Expected: occupied=false, guest=\"\"");
				System.out.println("Actual:   occupied=" + r.isOccupied() + ", guest=\"" + r.getGuest() + "\"");
			}
			System.out.println();
		} catch (Exception e) {
			reportException("Check-out occupied room", "checkOut() on occupied room", e);
		}
	}

	/* ---------- Exception helper ---------- */

	private static void reportException(String testName, String parameters, Exception e) {
		totalTests++;
		System.out.println("Test: " + testName);
		System.out.println("FAIL");
		System.out.println("Parameters: " + parameters);
		System.out.println("Exception: " + e.getClass().getSimpleName());
		System.out.println();
	}
}
