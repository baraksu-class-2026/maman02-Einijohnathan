
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HotelRoomArielMeyer1Tester {

    @Test
    public void getRoomNum1() {
        HotelRoom room = new HotelRoom(325, 3);
        assertEquals(325, room.getRoomNum());
    }
    
    @Test
    public void getRoomNum2() {
        HotelRoom room = new HotelRoom(567, 2);
        assertEquals(567, room.getRoomNum());
    }
    
    @Test
    public void getRoomNum3() {
        HotelRoom room = new HotelRoom(-54 , 3);
        assertEquals(999, room.getRoomNum());
    }
    
    @Test
    public void getRoomNum4() {
        HotelRoom room = new HotelRoom(4953, 4);
        assertEquals(999, room.getRoomNum());
    }
    
    @Test
    public void getRoomNum5() {
        HotelRoom room = new HotelRoom(99, 2);
        assertEquals(999, room.getRoomNum());
    }
    
    @Test
    public void getNumBeds1() {
        HotelRoom room = new HotelRoom(143, 3);
        assertEquals(3, room.getNumBeds());
    }
    
    @Test
    public void getNumBeds2() {
        HotelRoom room = new HotelRoom(765, 2);
        assertEquals(2, room.getNumBeds());
    }
    
    @Test
    public void getNumBeds3() {
        HotelRoom room = new HotelRoom(782, 4);
        assertEquals(4, room.getNumBeds());
    }
    
    @Test
    public void getNumBeds4() {
        HotelRoom room = new HotelRoom(529, -1);
        assertEquals(2, room.getNumBeds());
    }
    
    @Test
    public void getNumBeds5() {
        HotelRoom room = new HotelRoom(934, 14);
        assertEquals(2, room.getNumBeds());
    }
    
    @Test
    public void isOccupied1() {
        HotelRoom room = new HotelRoom(435, 3);
        assertFalse(room.isOccupied());
    }
    
    @Test
    public void isOccupied2() {
        HotelRoom room = new HotelRoom(724, 2);
        room.checkIn("Ariel");
        assertTrue(room.isOccupied());
    }
    
    @Test
    public void isOccupied3() {
        HotelRoom room = new HotelRoom(135, 4);
        room.checkIn("Test");
        assertTrue(room.isOccupied());
    }
    
    @Test
    public void isOccupied4() {
        HotelRoom room = new HotelRoom(862, 2);
        assertFalse(room.isOccupied());
    }
    
    @Test
    public void isOccupied5() {
        HotelRoom room = new HotelRoom(671, 3);
        room.checkIn("Bot");
        room.checkOut();
        assertFalse(room.isOccupied());
    }
    
    @Test
    public void getGuest1() {
        HotelRoom room = new HotelRoom(974, 4);
        room.checkIn("Ariel");
        assertEquals("Ariel", room.getGuest());
    }
    
    @Test
    public void getGuest2() {
        HotelRoom room = new HotelRoom(124, 2);
        assertEquals("", room.getGuest());
    }
    
    @Test
    public void getGuest3() {
        HotelRoom room = new HotelRoom(591, 3);
        room.checkIn("Beni");
        assertEquals("Beni", room.getGuest());
    }
    
    @Test
    public void getGuest4() {
        HotelRoom room = new HotelRoom(348, 3);
        assertEquals("", room.getGuest());
    }
    
    @Test
    public void getGuest5() {
        HotelRoom room = new HotelRoom(974, 4);
        room.checkIn("Dvir");
        room.checkOut();
        assertEquals("", room.getGuest());
    }
    
    @Test
    public void equals1() {
        HotelRoom room1 = new HotelRoom(234, 2);
        HotelRoom room2 = new HotelRoom(683, 3);
        assertFalse(room1.equals(room2));
    }
    
    @Test
    public void equals2() {
        HotelRoom room1 = new HotelRoom(100, 4);
        HotelRoom room2 = new HotelRoom(100, 4);
        assertTrue(room1.equals(room2));
    }
    
    @Test
    public void equals3() {
        HotelRoom room1 = new HotelRoom(341, 3);
        HotelRoom room2 = new HotelRoom(345, 3);
        assertFalse(room1.equals(room2));
    }
    
    @Test
    public void equals4() {
        HotelRoom room1 = new HotelRoom(912, 2);
        HotelRoom room2 = new HotelRoom(912, 4);
        assertFalse(room1.equals(room2));
    }
    
    @Test
    public void equals5() {
        HotelRoom room1 = new HotelRoom(637, 2);
        HotelRoom room2 = new HotelRoom(637, 2);
        assertTrue(room1.equals(room2));
    }
    
    @Test
    public void before1() {
        HotelRoom room1 = new HotelRoom(724, 3);
        HotelRoom room2 = new HotelRoom(243, 4);
        assertFalse(room1.before(room2));
    }
    
    @Test
    public void before2() {
        HotelRoom room1 = new HotelRoom(487, 2);
        HotelRoom room2 = new HotelRoom(456, 4);
        assertFalse(room1.before(room2));
    }
    
    @Test
    public void before3() {
        HotelRoom room1 = new HotelRoom(382, 2);
        HotelRoom room2 = new HotelRoom(841, 2);
        assertTrue(room1.before(room2));
    }
    
    @Test
    public void before4() {
        HotelRoom room1 = new HotelRoom(143, 2);
        HotelRoom room2 = new HotelRoom(257, 3);
        assertTrue(room1.before(room2));
    }
    
    @Test
    public void before5() {
        HotelRoom room1 = new HotelRoom(619, 3);
        HotelRoom room2 = new HotelRoom(620, 4);
        assertTrue(room1.before(room2));
    }
    
    @Test
    public void after1() {
        HotelRoom room1 = new HotelRoom(546, 3);
        HotelRoom room2 = new HotelRoom(965, 2);
        assertFalse(room1.after(room2));
    }
    
    @Test
    public void after2() {
        HotelRoom room1 = new HotelRoom(456, 4);
        HotelRoom room2 = new HotelRoom(487, 4);
        assertFalse(room1.after(room2));
    }
    
    @Test
    public void after3() {
        HotelRoom room1 = new HotelRoom(987, 2);
        HotelRoom room2 = new HotelRoom(543, 3);
        assertTrue(room1.after(room2));
    }
    
    @Test
    public void after4() {
        HotelRoom room1 = new HotelRoom(762, 3);
        HotelRoom room2 = new HotelRoom(513, 3);
        assertTrue(room1.after(room2));
    }
    
    @Test
    public void after5() {
        HotelRoom room1 = new HotelRoom(112, 2);
        HotelRoom room2 = new HotelRoom(111, 2);
        assertTrue(room1.after(room2));
    }
    
    @Test
    public void checkIn1() {
        HotelRoom room = new HotelRoom(845, 2);
        assertTrue(room.checkIn("Shlomo"));
    }
    
    @Test
    public void checkIn2() {
        HotelRoom room = new HotelRoom(267, 3);
        assertTrue(room.checkIn("Beni"));
    }
    
    @Test
    public void checkIn3() {
        HotelRoom room = new HotelRoom(436, 3);
        room.checkIn("Ariel");
        assertFalse(room.checkIn("Dan"));
    }
    
    @Test
    public void checkIn4() {
        HotelRoom room = new HotelRoom(547, 2);
        room.checkIn("Yotam");
        assertFalse(room.checkIn("Eythan"));
    }
    
    @Test
    public void checkIn5() {
        HotelRoom room = new HotelRoom(658, 3);
        assertTrue(room.checkIn("Gilad"));
    }
    
    @Test
    public void checkOut1() {
        HotelRoom room = new HotelRoom(876, 2);
        room.checkOut();
        assertFalse(room.isOccupied());
    }
    
    @Test
    public void checkOut2() {
        HotelRoom room = new HotelRoom(435, 4);
        room.checkIn("Ariel");
        room.checkOut();
        assertFalse(room.isOccupied());
    }
    
    @Test
    public void checkOut3() {
        HotelRoom room = new HotelRoom(976, 4);
        room.checkIn("Dvir");
        room.checkOut();
        assertEquals("", room.getGuest());
    }
    
    @Test
    public void checkOut4() {
        HotelRoom room = new HotelRoom(714, 3);
        room.checkIn("Bot");
        room.checkOut();
        room.checkIn("Bot2");
        room.checkOut();
        assertFalse(room.isOccupied());
    }
    
    @Test
    public void checkOut5() {
        HotelRoom room = new HotelRoom(436, 3);
        room.checkIn("Bot7");
        room.checkOut();
        assertEquals("", room.getGuest());
    }
}

