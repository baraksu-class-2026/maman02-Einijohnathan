## AI Code Review

❌ Duplicate Code (-10 points)
Methods with duplication: HotelRoom(int roomNum, int numBeds) and setRoomNum.
Methods with duplication: HotelRoom(int roomNum, int numBeds) and setNumBeds.
```private boolean roomNumOk(int roomNum) {
    return (roomNum >= MIN_NUM_ROOM && roomNum <= MAX_NUM_ROOM);
}```
```private boolean numBedsOk(int numbed) {
    return (numbed >= MIN_NUM_BEDS && numbed <= MAX_NUM_BEDS);
}```
Recommended adjusted grade: 90%
