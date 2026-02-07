
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HotelYehonatan351devTester {

    // ---------------- displaySorted ----------------
    @Test
    public void testDisplaySorted1() {
        HotelRoom a = new HotelRoom(307, 4);
        HotelRoom b = new HotelRoom(205, 3);
        HotelRoom c = new HotelRoom(402, 2);
        Hotel.displaySorted(a, b, c);
        // displaySorted only prints in sorted order, doesn't modify references
        assertEquals(307, a.getRoomNum());
        assertEquals(205, b.getRoomNum());
        assertEquals(402, c.getRoomNum());
    }

    @Test
    public void testDisplaySorted2() {
        HotelRoom a = new HotelRoom(100, 2);
        HotelRoom b = new HotelRoom(200, 3);
        HotelRoom c = new HotelRoom(150, 4);
        Hotel.displaySorted(a, b, c);
        // displaySorted only prints in sorted order, doesn't modify references
        assertEquals(100, a.getRoomNum());
        assertEquals(200, b.getRoomNum());
        assertEquals(150, c.getRoomNum());
    }

    @Test
    public void testDisplaySorted3() {
        HotelRoom a = new HotelRoom(400, 2);
        HotelRoom b = new HotelRoom(300, 2);
        HotelRoom c = new HotelRoom(500, 3);
        Hotel.displaySorted(a, b, c);
        // displaySorted only prints in sorted order, doesn't modify references
        assertEquals(400, a.getRoomNum());
        assertEquals(300, b.getRoomNum());
        assertEquals(500, c.getRoomNum());
    }

    @Test
    public void testDisplaySorted4() {
        HotelRoom a = new HotelRoom(101, 3);
        HotelRoom b = new HotelRoom(102, 2);
        HotelRoom c = new HotelRoom(103, 4);
        Hotel.displaySorted(a, b, c);
        // displaySorted only prints in sorted order, doesn't modify references
        assertEquals(101, a.getRoomNum());
        assertEquals(102, b.getRoomNum());
        assertEquals(103, c.getRoomNum());
    }

    @Test
    public void testDisplaySorted5() {
        HotelRoom a = new HotelRoom(500, 4);
        HotelRoom b = new HotelRoom(400, 3);
        HotelRoom c = new HotelRoom(450, 2);
        Hotel.displaySorted(a, b, c);
        // displaySorted only prints in sorted order, doesn't modify references
        assertEquals(500, a.getRoomNum());
        assertEquals(400, b.getRoomNum());
        assertEquals(450, c.getRoomNum());
    }

    // ---------------- checkIn ----------------
    @Test
    public void testCheckIn1() {
        HotelRoom a = new HotelRoom(301, 2);
        HotelRoom b = new HotelRoom(302, 3);
        HotelRoom c = new HotelRoom(303, 4);
        Hotel.checkIn("Alice", 301, a, b, c);
        assertTrue(a.isOccupied());
        assertEquals("Alice", a.getGuest());
    }

    @Test
    public void testCheckIn2() {
        HotelRoom a = new HotelRoom(301, 2);
        a.checkIn("Bob");
        HotelRoom b = new HotelRoom(302, 3);
        HotelRoom c = new HotelRoom(303, 4);
        Hotel.checkIn("Charlie", 301, a, b, c);
        assertEquals("Bob", a.getGuest());
        assertTrue(a.isOccupied());
    }

    @Test
    public void testCheckIn3() {
        HotelRoom a = new HotelRoom(301, 2);
        HotelRoom b = new HotelRoom(302, 3);
        HotelRoom c = new HotelRoom(303, 4);
        Hotel.checkIn("David", 304, a, b, c); // roomNum לא קיים
        assertFalse(a.getGuest().equals("David") || b.getGuest().equals("David") || c.getGuest().equals("David"));
    }

    @Test
    public void testCheckIn4() {
        HotelRoom a = new HotelRoom(301, 2);
        HotelRoom b = new HotelRoom(302, 3);
        HotelRoom c = new HotelRoom(303, 4);
        Hotel.checkIn("Eva", 303, a, b, c);
        assertTrue(c.isOccupied());
        assertEquals("Eva", c.getGuest());
    }

    @Test
    public void testCheckIn5() {
        HotelRoom a = new HotelRoom(301, 2);
        HotelRoom b = new HotelRoom(302, 3);
        HotelRoom c = new HotelRoom(303, 4);
        b.checkIn("Frank");
        Hotel.checkIn("Grace", 302, a, b, c);
        assertEquals("Frank", b.getGuest());
    }

    // ---------------- checkOut ----------------
    @Test
    public void testCheckOut1() {
        HotelRoom a = new HotelRoom(301, 2);
        a.checkIn("Alice");
        Hotel.checkOut(301, a, a, a);
        assertFalse(a.isOccupied());
        assertEquals("", a.getGuest());
    }

    @Test
    public void testCheckOut2() {
        HotelRoom a = new HotelRoom(301, 2);
        Hotel.checkOut(301, a, a, a);
        assertFalse(a.isOccupied());
    }

    @Test
    public void testCheckOut3() {
        HotelRoom a = new HotelRoom(301, 2);
        HotelRoom b = new HotelRoom(302, 3);
        HotelRoom c = new HotelRoom(303, 4);
        Hotel.checkOut(304, a, b, c); // roomNum לא קיים
        assertFalse(a.isOccupied());
        assertFalse(b.isOccupied());
        assertFalse(c.isOccupied());
    }

    @Test
    public void testCheckOut4() {
        HotelRoom a = new HotelRoom(301, 2);
        a.checkIn("Bob");
        HotelRoom b = new HotelRoom(302, 3);
        Hotel.checkOut(301, a, b, b);
        assertFalse(a.isOccupied());
    }

    @Test
    public void testCheckOut5() {
        HotelRoom c = new HotelRoom(303, 4);
        c.checkIn("Carol");
        Hotel.checkOut(303, c, c, c);
        assertFalse(c.isOccupied());
        assertEquals("", c.getGuest());
    }

    // ---------------- findAvailableByBeds ----------------
    @Test
    public void testFindAvailableByBeds1() {
        HotelRoom a = new HotelRoom(301, 2);
        HotelRoom b = new HotelRoom(302, 3);
        HotelRoom c = new HotelRoom(303, 4);
        Hotel.findAvailableByBeds(2, a, b, c);
        assertFalse(a.isOccupied());
    }

    @Test
    public void testFindAvailableByBeds2() {
        HotelRoom a = new HotelRoom(301, 2);
        a.checkIn("Alice");
        HotelRoom b = new HotelRoom(302, 3);
        HotelRoom c = new HotelRoom(303, 2);
        Hotel.findAvailableByBeds(2, a, b, c);
        assertFalse(c.isOccupied());
    }

    @Test
    public void testFindAvailableByBeds3() {
        HotelRoom a = new HotelRoom(301, 4);
        HotelRoom b = new HotelRoom(302, 4);
        HotelRoom c = new HotelRoom(303, 4);
        a.checkIn("Guest1");
        b.checkIn("Guest2");
        c.checkIn("Guest3");
        Hotel.findAvailableByBeds(4, a, b, c); // אין חדר פנוי
        assertTrue(a.isOccupied() && b.isOccupied() && c.isOccupied());
    }

    @Test
    public void testFindAvailableByBeds4() {
        HotelRoom a = new HotelRoom(301, 3);
        HotelRoom b = new HotelRoom(302, 3);
        HotelRoom c = new HotelRoom(303, 3);
        Hotel.findAvailableByBeds(3, a, b, c);
        assertFalse(a.isOccupied());
    }

    @Test
    public void testFindAvailableByBeds5() {
        HotelRoom a = new HotelRoom(301, 2);
        HotelRoom b = new HotelRoom(302, 3);
        HotelRoom c = new HotelRoom(303, 2);
        a.checkIn("Guest1");
        Hotel.findAvailableByBeds(2, a, b, c);
        assertFalse(c.isOccupied());
    }

    // ---------------- findRoomByNumber ----------------
    @Test
    public void testFindRoomByNumber1() {
        HotelRoom a = new HotelRoom(101, 2);
        HotelRoom b = new HotelRoom(102, 3);
        HotelRoom c = new HotelRoom(103, 4);
        assertEquals(a, Hotel.findRoomByNumber(101, a, b, c));
    }

    @Test
    public void testFindRoomByNumber2() {
        HotelRoom a = new HotelRoom(101, 2);
        HotelRoom b = new HotelRoom(102, 3);
        HotelRoom c = new HotelRoom(103, 4);
        assertEquals(b, Hotel.findRoomByNumber(102, a, b, c));
    }

    @Test
    public void testFindRoomByNumber3() {
        HotelRoom a = new HotelRoom(101, 2);
        HotelRoom b = new HotelRoom(102, 3);
        HotelRoom c = new HotelRoom(103, 4);
        assertEquals(c, Hotel.findRoomByNumber(103, a, b, c));
    }

    @Test
    public void testFindRoomByNumber4() {
        HotelRoom a = new HotelRoom(101, 2);
        HotelRoom b = new HotelRoom(102, 3);
        HotelRoom c = new HotelRoom(103, 4);
        assertNull(Hotel.findRoomByNumber(104, a, b, c));
    }

    @Test
    public void testFindRoomByNumber5() {
        HotelRoom a = new HotelRoom(101, 2);
        HotelRoom b = new HotelRoom(102, 3);
        HotelRoom c = new HotelRoom(103, 4);
        assertNull(Hotel.findRoomByNumber(0, a, b, c));
    }

}
