public class HotelStudTester {
	public static void main(String[] args) {

		HotelRoom a = new HotelRoom(307, 4);
		HotelRoom b = new HotelRoom(205, 3);
		HotelRoom c = new HotelRoom(402, 2);

		// === display ===
		System.out.println("== display ==");
		System.out.println("Expected:");
		System.out.println("Room 307, 4 Beds: Available");
		System.out.println("Room 205, 3 Beds: Available");
		System.out.println("Room 402, 2 Beds: Available");
		System.out.println("Actual  :");
		Hotel.display(a, b, c);

		// === displaySorted ===
		System.out.println("\n== displaySorted ==");
		System.out.println("Expected:");
		System.out.println("Room 205, 3 Beds: Available");
		System.out.println("Room 307, 4 Beds: Available");
		System.out.println("Room 402, 2 Beds: Available");
		System.out.println("Actual  :");
		Hotel.displaySorted(a, b, c);
		
      // === findRoomByNumber (קיים) ===
      System.out.println("\n== findRoomByNumber(205) ==");
      System.out.println("Expected: Room 205, 3 Beds: Available");
      System.out.println("Actual  : " + Hotel.findRoomByNumber(205, a, b, c));

      // === checkIn ===
      System.out.println("\n== after checkIn(\"Dana\", 205) ==");
      System.out.println("Expected: Room 205, 3 Beds: Occupied by Dana");
      System.out.print("Actual  : ");
      Hotel.checkIn("Dana", 205, a, b, c);

      // === checkOut ===
      System.out.println("\n== after checkOut(205) ==");
      System.out.println("Expected: Room 205, 3 Beds: Available");
      System.out.print("Actual  : ");
      Hotel.checkOut(205, a, b, c);

      // === findAvailableByBeds ===
      System.out.println("\n== findAvailableByBeds(2) ==");
      System.out.println("Expected: Room 402, 2 Beds: Available");
      System.out.print("Actual  : "); 
      Hotel.findAvailableByBeds(2, a, b, c);

	}
}