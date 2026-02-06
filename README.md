## Grade

[![Grade](https://img.shields.io/badge/Grade-90%25-brightgreen)](https://github.com/baraksu-class-2026/maman02-Einijohnathan/actions/runs/21748884484) *(Original: 100%, Penalties: -10)*

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
