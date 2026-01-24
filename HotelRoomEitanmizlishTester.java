
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class HotelRoomEitanmizlishTester {

    private static final int DEFAULT_ROOM_NUM = 999;
    private static final int DEFAULT_NUM_BEDS = 2;

    @Test
    public void getRoomNum1() {
        HotelRoom room = new HotelRoom(324, 3);
        assertEquals(324, room.getRoomNum());
    }

    @Test
    public void getRoomNum2() {
        HotelRoom room = new HotelRoom(454, 4);
        assertEquals(454, room.getRoomNum());
    }

    @Test
    public void getRoomNum3() {
        HotelRoom room = new HotelRoom(-34, 3);
        assertEquals(DEFAULT_ROOM_NUM, room.getRoomNum());
    }

    @Test
    public void getRoomNum4() {
        HotelRoom room = new HotelRoom(4355, 2);
        assertEquals(DEFAULT_ROOM_NUM, room.getRoomNum());
    }

    @Test
    public void getRoomNum5() {
        HotelRoom room = new HotelRoom(99, 2);
        assertEquals(DEFAULT_ROOM_NUM, room.getRoomNum());
    }

    @Test
    public void getNumBeds1() {
        HotelRoom room = new HotelRoom(112, 3);
        assertEquals(3, room.getNumBeds());
    }

    @Test
    public void getNumBeds2() {
        HotelRoom room = new HotelRoom(143, 5);
        assertEquals(DEFAULT_NUM_BEDS, room.getNumBeds());
    }

    @Test
    public void getNumBeds3() {
        HotelRoom room = new HotelRoom(232, 1);
        assertEquals(DEFAULT_NUM_BEDS, room.getNumBeds());
    }

    @Test
    public void getNumBeds4() {
        HotelRoom room = new HotelRoom(564, -3);
        assertEquals(DEFAULT_NUM_BEDS, room.getNumBeds());
    }

    @Test
    public void getNumBeds5() {
        HotelRoom room = new HotelRoom(143, 38);
        assertEquals(DEFAULT_NUM_BEDS, room.getNumBeds());
    }

    @Test
    public void isOccupied1() {
        HotelRoom room = new HotelRoom(424, 4);
        assertFalse(room.isOccupied());
    }

    @Test
    public void isOccupied2() {
        HotelRoom room = new HotelRoom(543, 3);
        room.checkIn("Eitan");
        assertTrue(room.isOccupied());
    }

    @Test
    public void isOccupied3() {
        HotelRoom room = new HotelRoom(132, 2);
        room.checkIn("Test");
        assertTrue(room.isOccupied());
    }

    @Test
    public void isOccupied4() {
        HotelRoom room = new HotelRoom(132, 2);
        room.checkIn("Eitan");
        room.checkOut();
        assertFalse(room.isOccupied());
    }

    @Test
    public void isOccupied5() {
        HotelRoom room = new HotelRoom(132, 2);
        room.checkIn("Test");
        room.checkOut();
        assertFalse(room.isOccupied());
    }

    @Test
    public void getGuest1() {
        HotelRoom room = new HotelRoom(234, 4);
        room.checkIn("Eitan");
        assertEquals("Eitan", room.getGuest());
    }

    @Test
    public void getGuest2() {
        HotelRoom room = new HotelRoom(234, 2);
        assertEquals("", room.getGuest());
    }

    @Test
    public void getGuest3() {
        HotelRoom room = new HotelRoom(234, 4);
        room.checkIn("");
        assertEquals("", room.getGuest());
    }

    @Test
    public void getGuest4() {
        HotelRoom room = new HotelRoom(234, 4);
        room.checkIn(null);
        assertEquals(null, room.getGuest());
    }

    @Test
    public void getGuest5() {
        HotelRoom room = new HotelRoom(234, 4);
        room.checkIn("Moshe");
        assertEquals("Moshe", room.getGuest());
    }

    @Test
    public void equals1() {
        HotelRoom room1 = new HotelRoom(233, 4);
        HotelRoom room2 = new HotelRoom(788, 3);
        assertFalse(room1.equals(room2));
    }

    @Test
    public void equals2() {
        HotelRoom room1 = new HotelRoom(233, 4);
        HotelRoom room2 = new HotelRoom(233, 4);
        assertTrue(room1.equals(room2));
    }

    @Test
    public void equals3() {
        HotelRoom room1 = new HotelRoom(233, 2);
        HotelRoom room2 = new HotelRoom(738, 2);
        assertFalse(room1.equals(room2));
    }

    @Test
    public void equals4() {
        HotelRoom room1 = new HotelRoom(456, 2);
        HotelRoom room2 = new HotelRoom(456, 3);
        assertFalse(room1.equals(room2));
    }

    @Test
    public void equals5() {
        HotelRoom room1 = new HotelRoom(456, 2);
        HotelRoom room2 = room1;
        assertTrue(room1.equals(room2));
    }

    @Test
    public void before1() {
        HotelRoom room1 = new HotelRoom(535, 3);
        HotelRoom room2 = new HotelRoom(243, 3);
        assertFalse(room1.before(room2));
    }

    @Test
    public void before2() {
        HotelRoom room1 = new HotelRoom(234, 3);
        HotelRoom room2 = new HotelRoom(343, 2);
        assertTrue(room1.before(room2));
    }

    @Test
    public void before3() {
        HotelRoom room1 = new HotelRoom(453, 3);
        HotelRoom room2 = new HotelRoom(456, 4);
        assertTrue(room1.before(room2));
    }

    @Test
    public void before4() {
        HotelRoom room1 = new HotelRoom(344, 3);
        HotelRoom room2 = new HotelRoom(243, 2);
        assertFalse(room1.before(room2));
    }

    @Test
    public void before5() {
        HotelRoom room1 = new HotelRoom(344, 3);
        HotelRoom room2 = new HotelRoom(243, 4);
        assertFalse(room1.before(room2));
    }

    @Test
    public void after1() {
        HotelRoom room1 = new HotelRoom(535, 3);
        HotelRoom room2 = new HotelRoom(243, 3);
        assertTrue(room1.after(room2));
    }

    @Test
    public void after2() {
        HotelRoom room1 = new HotelRoom(234, 3);
        HotelRoom room2 = new HotelRoom(343, 2);
        assertFalse(room1.after(room2));
    }

    @Test
    public void after3() {
        HotelRoom room1 = new HotelRoom(453, 3);
        HotelRoom room2 = new HotelRoom(456, 4);
        assertFalse(room1.after(room2));
    }

    @Test
    public void after4() {
        HotelRoom room1 = new HotelRoom(344, 3);
        HotelRoom room2 = new HotelRoom(243, 2);
        assertTrue(room1.after(room2));
    }

    @Test
    public void after5() {
        HotelRoom room1 = new HotelRoom(344, 3);
        HotelRoom room2 = new HotelRoom(243, 4);
        assertTrue(room1.after(room2));
    }

    @Test
    public void checkIn1() {
        HotelRoom room = new HotelRoom(235, 4);
        assertTrue(room.checkIn("Lavi"));
    }

    @Test
    public void checkIn2() {
        HotelRoom room = new HotelRoom(812, 3);
        assertTrue(room.checkIn("Gilad"));
    }

    @Test
    public void checkIn3() {
        HotelRoom room = new HotelRoom(8415, 2);
        assertTrue(room.checkIn("Eitan"));
    }

    @Test
    public void checkIn4() {
        HotelRoom room = new HotelRoom(345, 2);
        room.checkIn("Eitan");
        assertFalse(room.checkIn("Beni"));
    }

    @Test
    public void checkIn5() {
        HotelRoom room = new HotelRoom(213, 4);
        room.checkIn("Yoav");
        assertFalse(room.checkIn("Eitan"));
    }

    @Test
    public void checkOut1() {
        HotelRoom room = new HotelRoom(236, 3);
        room.checkOut();
        assertFalse(room.isOccupied());
    }

    @Test
    public void checkOut2() {
        HotelRoom room = new HotelRoom(276, 4);
        room.checkIn("Eitan");
        room.checkOut();
        assertFalse(room.isOccupied());
    }

    @Test
    public void checkOut3() {
        HotelRoom room = new HotelRoom(456, 2);
        room.checkIn("Eitan");
        room.checkOut();
        assertEquals("", room.getGuest());
    }

    @Test
    public void checkOut4() {
        HotelRoom room = new HotelRoom(254, 3);
        room.checkIn("Eitan");
        room.checkOut();
        room.checkIn("Lavi");
        room.checkOut();
        assertEquals("", room.getGuest());
    }

    @Test
    public void checkOut5() {
        HotelRoom room = new HotelRoom(154, 3);
        room.checkIn("Eitan");
        room.checkOut();
        room.checkIn("Lavi");
        room.checkOut();
        assertFalse(room.isOccupied());
    }
}
