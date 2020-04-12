CREATE DATABASE IF NOT EXISTS test;

USE test;

CREATE TABLE style(
    style_id INTEGER AUTO_INCREMENT,
    style_name VARCHAR(100),
    PRIMARY KEY(style_id)
);

INSERT INTO 
    style(style_name) 
VALUES
    ('Heavy metal'),
    ('Trash metal'),
    ('Speed metal'),
    ('Death metal'),
    ('Brutal death metal'),
    ('Metal extremo'),
    ('Djent'),
    ('Groove metal'),
    ('Death metal técnico'),
    ('Death metal progresivo'),
    ('Death metal melódico'),
    ('Power metal');

CREATE TABLE country(
    country_id INTEGER AUTO_INCREMENT,
    country_name VARCHAR(100),
    PRIMARY KEY(country_id)
);

INSERT INTO 
    country(country_name) 
VALUES
    ('Estados Unidos'),
    ('Suecia'),
    ('Chile'),
    ('Finlandia');

CREATE TABLE band(
    band_id INTEGER AUTO_INCREMENT,
    band_name VARCHAR(100),
    country_id INTEGER,
    band_year INTEGER,
    PRIMARY KEY(band_id),
    FOREIGN KEY(country_id) REFERENCES country(country_id)
);

INSERT INTO 
    band(band_name, country_id, band_year) 
VALUES
    ('Metallica', 1, 1981),
    ('Cannibal Corpse', 1, 1988),
    ('Deicide', 1, 1987),
    ('Meshuggah', 2, 1987),
    ('Criminal', 3, 1991),
    ('Death', 1, 1983),
    ('Children of bodom', 4, 1997);

CREATE TABLE band_style(
    band_id INTEGER,
    style_id INTEGER,
    FOREIGN KEY(band_id) REFERENCES band(band_id),
    FOREIGN KEY(style_id) REFERENCES style(style_id)
);

INSERT INTO
    band_style(band_id, style_id)
VALUES
    (1,1), (1,2), (1,3),
    (2,4), (2,5),
    (3,4),
    (4,6), (4,7),
    (5,2), (5,4), (5,8),
    (6,4), (6,9), (6,10),
    (7,11), (7,12);


SELECT * FROM style;
SELECT * FROM country;
SELECT * FROM band;
SELECT * FROM band_style;

-- 1.- Listado de bandas y su información
SELECT 
    band.band_id 'Band ID',
    band.band_name 'Band',
    GROUP_CONCAT(style.style_name) 'Styles',
    country.country_name 'Country',
    band.band_year 'Year'
FROM   
    band 
    LEFT JOIN country ON country.country_id = band.country_id
    LEFT JOIN band_style ON band_style.band_id = band.band_id
    LEFT JOIN style ON style.style_id = band_style.style_id
GROUP BY
    band.band_id
ORDER BY 
	band_name;
