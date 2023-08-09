-- возраст не меньше 16
ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK (age >= 16);

-- имя студента уникально и не нулл
ALTER TABLE student
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE student
    ADD CONSTRAINT name_unique UNIQUE (name);

-- имя студента уникально и не нулл (не дает создать второй ключ)
-- ALTER TABLE student
--     ADD PRIMARY KEY (name);

-- пара цвет-название уникальна
ALTER TABLE faculty
    ADD CONSTRAINT color_name_unique UNIQUE (color, name);

-- дефолтный возраст 20
ALTER  TABLE student
    ALTER COLUMN age SET DEFAULT 20;
