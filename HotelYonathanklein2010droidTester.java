
import static org.junit.Assert.*;
import org.junit.Test;

public class HotelRoomTest {

    /* ========= Constructor & getters ========= */

    @Test
    public void testConstructorValidValues() {
        HotelRoom r = new HotelRoom(305, 3);
        assertEquals(305, r.getRoomNum());
        assertEquals(3, r.getNumBeds());
        assertFalse(r.isOccupied());
    }

    @Test
    public void testConstructorInvalidRoomNumLow() {
        HotelRoom r = new HotelRoom(50, 3);
        assertEquals(HotelRoom.DEFAULT_ROOM_NUM, r.getRoomNum());
    }

    @Test
    public void testConstructorInvalidRoomNumHigh() {
        HotelRoom r = new HotelRoom(2000, 3);
        assertEquals(HotelRoom.DEFAULT_ROOM_NUM, r.getRoomNum());
    }

    @Test
    public void testConstructorInvalidBedsLow() {
        HotelRoom r = new HotelRoom(300, 1);
        assertEquals(HotelRoom.DEFAULT_NUM_BEDS, r.getNumBeds());
    }

    @Test
    public void testConstructorInvalidBedsHigh() {
        HotelRoom r = new HotelRoom(300, 6);
        assertEquals(HotelRoom.DEFAULT_NUM_BEDS, r.getNumBeds());
    }

    /* ========= checkIn ========= */

    @Test
    public void testCheckInSuccess() {
        HotelRoom r = new HotelRoom(300, 2);
        assertTrue(r.checkIn("Dan"));
        assertTrue(r.isOccupied());
    }

    @Test
    public void testCheckInGuestName() {
        HotelRoom r = new HotelRoom(300, 2);
        r.checkIn("Noam");
        assertEquals("Noam", r.getGuest());
    }

    @Test
    public void testCheckInTwiceFails() {
        HotelRoom r = new HotelRoom(300, 2);
        r.checkIn("Avi");
        assertFalse(r.checkIn("Moshe"));
    }

    @Test
    public void testCheckInOccupiedStillSameGuest() {
        HotelRoom r = new HotelRoom(300, 2);
        r.checkIn("Dana");
        r.checkIn("Lior");
        assertEquals("Dana", r.getGuest());
    }

    @Test
    public void testCheckInSetsOccupiedTrue() {
        HotelRoom r = new HotelRoom(300, 2);
        r.checkIn("Yael");
        assertTrue(r.isOccupied());
    }

    /* ========= checkOut ========= */

    @Test
    public void testCheckOutAfterCheckIn() {
        HotelRoom r = new HotelRoom(300, 2);
        r.checkIn("Tom");
        r.checkOut();
        assertFalse(r.isOccupied());
    }

    @Test
    public void testCheckOutClearsGuest() {
        HotelRoom r = new HotelRoom(300, 2);
        r.checkIn("Eli");
        r.checkOut();
        assertEquals("", r.getGuest());
    }

    @Test
    public void testCheckOutOnEmptyRoom() {
        HotelRoom r = new HotelRoom(300, 2);
        r.checkOut();
        assertFalse(r.isOccupied());
    }

    @Test
    public void testCheckOutTwice() {
        HotelRoom r = new HotelRoom(300, 2);
        r.checkIn("Shir");
        r.checkOut();
        r.checkOut();
        assertFalse(r.isOccupied());
    }

    @Test
    public void testCheckOutDoesNotChangeRoomNum() {
        HotelRoom r = new HotelRoom(321, 2);
        r.checkOut();
        assertEquals(321, r.getRoomNum());
    }

    /* ========= before / after ========= */

    @Test
    public void testBeforeTrue() {
        HotelRoom r1 = new HotelRoom(200, 2);
        HotelRoom r2 = new HotelRoom(300, 2);
        assertTrue(r1.before(r2));
    }

    @Test
    public void testBeforeFalse() {
        HotelRoom r1 = new HotelRoom(400, 2);
        HotelRoom r2 = new HotelRoom(300, 2);
        assertFalse(r1.before(r2));
    }

    @Test
    public void testAfterTrue() {
        HotelRoom r1 = new HotelRoom(400, 2);
        HotelRoom r2 = new HotelRoom(300, 2);
        assertTrue(r1.after(r2));
    }

    @Test
    public void testAfterFalse() {
        HotelRoom r1 = new HotelRoom(200, 2);
        HotelRoom r2 = new HotelRoom(300, 2);
        assertFalse(r1.after(r2));
    }

    @Test
    public void testBeforeEqualRoomNums() {
        HotelRoom r1 = new HotelRoom(300, 2);
        HotelRoom r2 = new HotelRoom(300, 3);
        assertFalse(r1.before(r2));
    }

    /* ========= equals ========= */

    @Test
    public void testEqualsTrue() {
        HotelRoom r1 = new HotelRoom(300, 2);
        HotelRoom r2 = new HotelRoom(300, 2);
        assertTrue(r1.equals(r2));
    }

    @Test
    public void testEqualsDifferentRoomNum() {
        HotelRoom r1 = new HotelRoom(300, 2);
        HotelRoom r2 = new HotelRoom(301, 2);
        assertFalse(r1.equals(r2));
    }

    @Test
    public void testEqualsDifferentBeds() {
        HotelRoom r1 = new HotelRoom(300, 2);
        HotelRoom r2 = new HotelRoom(300, 3);
        assertFalse(r1.equals(r2));
    }

    @Test
    public void testEqualsNull() {
        HotelRoom r = new HotelRoom(300, 2);
        assertFalse(r.equals(null));
    }

    @Test
    public void testEqualsDifferentObject() {
        HotelRoom r = new HotelRoom(300, 2);
        assertFalse(r.equals("Room"));
    }
}




