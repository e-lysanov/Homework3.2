-- создание таблицы машин
CREATE TABLE auto (
    id SERIAL PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price INTEGER
);

-- создание таблицы людей
CREATE TABLE man (
    id SERIAL PRIMARY KEY,
    name TEXT,
    age INTEGER,
    drive_license BOOLEAN,
    auto_id INTEGER REFERENCES auto(id)
);
