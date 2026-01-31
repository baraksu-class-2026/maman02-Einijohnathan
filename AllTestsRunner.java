public class AllTestsRunner {

    public static void main(String[] args) {

        System.out.println("===== RUNNING ALL TESTS =====\n");

        /* -------- HotelRoom tests -------- */
        System.out.println(">>> Running HotelRoom tests\n");
        HotelRoomTester.main(null);

        int roomTotal = HotelRoomTester.getTotalTests();
        int roomPassed = HotelRoomTester.getPassedTests();

        /* -------- Hotel tests -------- */
        System.out.println("\n>>> Running Hotel tests\n");
        HotelTester.main(null);

        int hotelTotal = HotelTester.getTotalTests();
        int hotelPassed = HotelTester.getPassedTests();

        /* -------- Final summary -------- */
        int totalTests = roomTotal + hotelTotal;
        int passedTests = roomPassed + hotelPassed;

        System.out.println("\n=================================");
        System.out.println("FINAL TEST SUMMARY");
        System.out.println("---------------------------------");
        System.out.println("HotelRoom tests: " + roomPassed + " / " + roomTotal);
        System.out.println("Hotel tests:     " + hotelPassed + " / " + hotelTotal);
        System.out.println("---------------------------------");
        System.out.println("TOTAL:           " + passedTests + " / " + totalTests);
        System.out.println("=================================");
    }
}
