-- создание таблицы машин
CREATE TABLE auto (
    id REAL PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price INTEGER
);

-- создание таблицы людей
CREATE TABLE man (
    id REAL PRIMARY KEY,
    name TEXT,
    age INTEGER,
    drive_license BOOLEAN,
    auto_id REAL REFERENCES auto(id)
);