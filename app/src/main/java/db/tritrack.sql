DROP TABLE athletes;
DROP TABLE workouts;

CREATE TABLE athletes (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(50)
);

CREATE TABLE workouts (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    discipline VARCHAR(50),
    dateString VARCHAR(50),
    distance INTEGER,
    timeString INTEGER,
    athlete_id INTEGER REFERENCES athletes(id)
);