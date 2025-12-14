// ==========================
// File: HotelRoomTester.java
// ==========================
public class HotelRoomStudTester {

	public static void main(String[] args) {
		
		HotelRoom r1 = new HotelRoom(205, 3);
		System.out.println("create an object r1=HotelRoom(205, 3)");

		// === toString ===
		System.out.println("== toString ==");
		System.out.println("Expected: Room 205, 3 Beds: Available");
		System.out.println("Actual  : " + r1.toString());
		System.out.println();

		// === Getters ===
		System.out.println("== getRoomNum ==");
		System.out.println("Expected: 205");
		System.out.println("Actual  : " + r1.getRoomNum());
		System.out.println();

		System.out.println("== getNumBeds ==");
		System.out.println("Expected: 3");
		System.out.println("Actual  : " + r1.getNumBeds());
		System.out.println();

		System.out.println("== isOccupied ==");
		System.out.println("Expected: false");
		System.out.println("Actual  : " + r1.isOccupied());
		System.out.println();

		// === Setters ===
		r1.setRoomNum(450); 
		System.out.println("== setRoomNum(450) ==");
		System.out.println("Expected: 450");
		System.out.println("Actual  : " + r1.getRoomNum());
		System.out.println();

		r1.setNumBeds(4); 
		System.out.println("== setNumBeds(4) ==");
		System.out.println("Expected: 4");
		System.out.println("Actual  : " + r1.getNumBeds());
		System.out.println();

		// === checkIn / isOccupied / getGuest ===
		boolean checkedIn = r1.checkIn("Dana");
		System.out.println("== checkIn(\"Dana\") ==");
		System.out.println("Expected: true");
		System.out.println("Actual  : " + checkedIn);
		System.out.println();

		System.out.println("== getGuest () ==");
		System.out.println("Expected: Dana");
		System.out.println("Actual  : " + r1.getGuest());
		System.out.println();

		// === checkOut ===
		r1.checkOut();
		System.out.println("== checkOut(); isOccupied ==");
		System.out.println("Expected: false");
		System.out.println("Actual  : " + r1.isOccupied());
		System.out.println();

		// === equals ===
		HotelRoom r2 = new HotelRoom(317, 2); 
		HotelRoom r3 = new HotelRoom(521, 3); 
		System.out.println("create 2 objects r2=HotelRoom(317, 2) & r3=HotelRoom(521, 3)");

		System.out.println("== r2.equals(r3) ==");
		System.out.println("Expected: false");
		System.out.println("Actual  : " + r1.equals(r3));
		System.out.println();

		System.out.println("== r2.before(r3) ==");
		System.out.println("Expected: true");
		System.out.println("Actual  : " + r2.before(r3));
		System.out.println();

		System.out.println("== r2.after(r3) ==");
		System.out.println("Expected: false");
		System.out.println("Actual  : " + r2.after(r3));
		System.out.println();
	}
}
