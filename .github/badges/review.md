## AI Code Review

❌ Duplicate Code (-10 points)
Methods with duplication: checkIn and checkOut
```
private static HotelRoom getRoomOrPrintNotAvailable(int roomNum, HotelRoom a, HotelRoom b, HotelRoom c) {
    HotelRoom chosenRoom = findRoomByNumber(roomNum, a, b, c);
    if (chosenRoom == null) printRoomNotAvailable();
    return chosenRoom;
}
```
Recommended adjusted grade: 90%
