## AI Code Review

❌ Duplicate Code (-10 points)
Methods with duplication: HotelRoom(int, int) and setRoomNum; HotelRoom(int, int) and setNumBeds.
Extracted helper method to add:
```java
private boolean numBedsOk(int numBeds) {
    return (numBeds >= MIN_NUM_BEDS && numBeds <= MAX_NUM_BEDS);
}
```
Also reuse the existing roomNumOk(...) in the constructor and in setRoomNum.
Recommended adjusted grade: 90%
