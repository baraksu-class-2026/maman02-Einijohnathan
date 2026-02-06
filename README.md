## Grade

[![Grade](https://img.shields.io/badge/Grade-90%25-brightgreen)](https://github.com/baraksu-class-2026/maman02-Einijohnathan/actions/runs/21748319028) *(Original: 100%, Penalties: -10)*

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
