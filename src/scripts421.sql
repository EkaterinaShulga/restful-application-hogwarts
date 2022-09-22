ALTER TABLE student
ADD CONSTRAINT age_constraint CHECK ( age>16 );

ALTER TABLE student
ADD CONSTRAINT name_const  UNIQUE(name), ALTER COLUMN name SET not null;

ALTER TABLE faculty
ADD CONSTRAINT name_color_constraint UNIQUE (name,color);

ALTER TABLE student
ALTER COLUMN age SET DEFAULT 20;

