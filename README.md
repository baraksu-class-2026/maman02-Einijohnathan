## Grade

[![Grade](https://img.shields.io/badge/Grade-90%25-brightgreen)](https://github.com/baraksu-class-2026/maman02-Einijohnathan/actions/runs/21748158754) *(Original: 100%, Penalties: -10)*

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
