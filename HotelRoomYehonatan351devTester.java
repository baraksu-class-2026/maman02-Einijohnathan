import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HotelRoomYehonatan351devTester {

@Test
public void getRoomNumTest1() {
    assertEquals(210, new HotelRoom(210, 3).getRoomNum());
}

@Test
public void getRoomNumTest2() {
    assertEquals(999, new HotelRoom(50, 2).getRoomNum());
}

@Test
public void getRoomNumTest3() {
    assertEquals(350, new HotelRoom(350, 4).getRoomNum());
}

@Test
public void getRoomNumTest4() {
    assertEquals(999, new HotelRoom(1000, 2).getRoomNum());
}

@Test
public void getRoomNumTest5() {
    assertEquals(402, new HotelRoom(402, 3).getRoomNum());
}

@Test
public void getNumBedsTest1() {
    assertEquals(3, new HotelRoom(120, 3).getNumBeds());
}

@Test
public void getNumBedsTest2() {
    assertEquals(2, new HotelRoom(122, 5).getNumBeds());
}

@Test
public void getNumBedsTest3() {
    assertEquals(4, new HotelRoom(200, 4).getNumBeds());
}

@Test
public void getNumBedsTest4() {
    assertEquals(2, new HotelRoom(210, 1).getNumBeds());
}

@Test
public void getNumBedsTest5() {
    assertEquals(2, new HotelRoom(220, -3).getNumBeds());
}

@Test
public void isOccupiedTest1() {
    HotelRoom r1 = new HotelRoom(301, 3);
    assertFalse(r1.isOccupied());
}

@Test
public void isOccupiedTest2() {
    HotelRoom r2 = new HotelRoom(302, 2);
    r2.checkIn("Anna");
    assertTrue(r2.isOccupied());
}

@Test
public void isOccupiedTest3() {
    HotelRoom r3 = new HotelRoom(303, 4);
    r3.checkIn("Bob");
    assertTrue(r3.isOccupied());
}

@Test
public void isOccupiedTest4() {
    HotelRoom r4 = new HotelRoom(304, 2);
    r4.checkIn("Clara");
    r4.checkOut();
    assertFalse(r4.isOccupied());
}

@Test
public void isOccupiedTest5() {
    HotelRoom r5 = new HotelRoom(305, 3);
    assertFalse(r5.isOccupied());
}

@Test
public void getGuestTest1() {
    HotelRoom r1 = new HotelRoom(401, 3);
    r1.checkIn("Zara");
    assertEquals("Zara", r1.getGuest());
}

@Test
public void getGuestTest2() {
    HotelRoom r2 = new HotelRoom(402, 4);
    assertEquals("", r2.getGuest());
}

@Test
public void getGuestTest3() {
    HotelRoom r3 = new HotelRoom(403, 2);
    r3.checkIn("Max");
    assertEquals("Max", r3.getGuest());
}

@Test
public void getGuestTest4() {
    HotelRoom r4 = new HotelRoom(404, 3);
    r4.checkIn("");
    assertEquals("", r4.getGuest());
}

@Test
public void getGuestTest5() {
    HotelRoom r5 = new HotelRoom(405, 4);
    r5.checkIn("Lina");
    assertEquals("Lina", r5.getGuest());
}

@Test
public void equalsTest1() {
    HotelRoom r1 = new HotelRoom(501, 3);
    HotelRoom r2 = new HotelRoom(502, 3);
    assertFalse(r1.equals(r2));
}

@Test
public void equalsTest2() {
    HotelRoom r3 = new HotelRoom(503, 2);
    HotelRoom r4 = new HotelRoom(503, 2);
    assertTrue(r3.equals(r4));
}

@Test
public void equalsTest3() {
    HotelRoom r5 = new HotelRoom(504, 4);
    HotelRoom r6 = new HotelRoom(504, 3);
    assertFalse(r5.equals(r6));
}

@Test
public void equalsTest4() {
    HotelRoom r7 = new HotelRoom(505, 3);
    HotelRoom r8 = r7;
    assertTrue(r7.equals(r8));
}

@Test
public void equalsTest5() {
    HotelRoom r9 = new HotelRoom(506, 2);
    HotelRoom r10 = new HotelRoom(507, 4);
    assertFalse(r9.equals(r10));
}

@Test
public void beforeTest1() {
    HotelRoom r1 = new HotelRoom(601, 3);
    HotelRoom r2 = new HotelRoom(602, 2);
    assertTrue(r1.before(r2));
}

@Test
public void beforeTest2() {
    HotelRoom r3 = new HotelRoom(603, 4);
    HotelRoom r4 = new HotelRoom(600, 3);
    assertFalse(r3.before(r4));
}

@Test
public void beforeTest3() {
    HotelRoom r5 = new HotelRoom(604, 2);
    HotelRoom r6 = new HotelRoom(605, 4);
    assertTrue(r5.before(r6));
}

@Test
public void beforeTest4() {
    HotelRoom r7 = new HotelRoom(606, 3);
    HotelRoom r8 = new HotelRoom(606, 2);
    assertFalse(r7.before(r8));
}

@Test
public void beforeTest5() {
    HotelRoom r9 = new HotelRoom(607, 4);
    HotelRoom r10 = new HotelRoom(608, 2);
    assertTrue(r9.before(r10));
}

@Test
public void afterTest1() {
    HotelRoom r1 = new HotelRoom(701, 3);
    HotelRoom r2 = new HotelRoom(700, 4);
    assertTrue(r1.after(r2));
}

@Test
public void afterTest2() {
    HotelRoom r3 = new HotelRoom(702, 2);
    HotelRoom r4 = new HotelRoom(703, 3);
    assertFalse(r3.after(r4));
}

@Test
public void afterTest3() {
    HotelRoom r5 = new HotelRoom(704, 4);
    HotelRoom r6 = new HotelRoom(704, 3);
    assertFalse(r5.after(r6));
}

@Test
public void afterTest4() {
    HotelRoom r7 = new HotelRoom(705, 2);
    HotelRoom r8 = new HotelRoom(704, 4);
    assertTrue(r7.after(r8));
}

@Test
public void afterTest5() {
    HotelRoom r9 = new HotelRoom(706, 3);
    HotelRoom r10 = new HotelRoom(705, 2);
    assertTrue(r9.after(r10));
}

@Test
public void checkInTest1() {
    HotelRoom r1 = new HotelRoom(801, 3);
    assertTrue(r1.checkIn("Nina"));
}

@Test
public void checkInTest2() {
    HotelRoom r2 = new HotelRoom(802, 4);
    assertTrue(r2.checkIn("Omer"));
}

@Test
public void checkInTest3() {
    HotelRoom r3 = new HotelRoom(803, 2);
    assertTrue(r3.checkIn("Tali"));
}

@Test
public void checkInTest4() {
    HotelRoom r4 = new HotelRoom(804, 3);
    r4.checkIn("Gili");
    assertFalse(r4.checkIn("Noa"));
}

@Test
public void checkInTest5() {
    HotelRoom r5 = new HotelRoom(805, 2);
    r5.checkIn("Erez");
    assertFalse(r5.checkIn("Shai"));
}

@Test
public void checkOutTest1() {
    HotelRoom r1 = new HotelRoom(901, 3);
    r1.checkOut();
    assertFalse(r1.isOccupied());
}

@Test
public void checkOutTest2() {
    HotelRoom r2 = new HotelRoom(902, 4);
    r2.checkIn("Lea");
    r2.checkOut();
    assertFalse(r2.isOccupied());
}

@Test
public void checkOutTest3() {
    HotelRoom r3 = new HotelRoom(903, 2);
    r3.checkIn("Noam");
    r3.checkOut();
    assertEquals("", r3.getGuest());
}

@Test
public void checkOutTest4() {
    HotelRoom r4 = new HotelRoom(904, 3);
    r4.checkIn("Ella");
    r4.checkOut();
    r4.checkIn("Avi");
    r4.checkOut();
    assertEquals("", r4.getGuest());
}

@Test
public void checkOutTest5() {
    HotelRoom r5 = new HotelRoom(905, 4);
    r5.checkIn("Maya");
    r5.checkOut();
    assertFalse(r5.isOccupied());
}

}
