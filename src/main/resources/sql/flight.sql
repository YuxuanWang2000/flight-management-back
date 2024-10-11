CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      email VARCHAR(100) NOT NULL UNIQUE,
                      tel VARCHAR(15),
                      name VARCHAR(50),
                      nickname varchar(50),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE airport (
                         airport_id INT AUTO_INCREMENT PRIMARY KEY,
                         airport_code VARCHAR(10) NOT NULL,
                         airport_name VARCHAR(100) NOT NULL,
                         city VARCHAR(100) NOT NULL,
                         region VARCHAR(100) NOT NULL,
                         timezone VARCHAR(50) NOT NULL
);

INSERT INTO airport (airport_code, airport_name, city, region, timezone) VALUES
                                     ('NZNE', 'North Shore Aerodrome', 'North Shore', 'Auckland', 'UTC+12'),
                                     ('NZGB', 'Claris Aerodrome', 'Great Barrier Island', 'Auckland', 'UTC+12'),
                                     ('NZRO', 'Rotorua Airport', 'Rotorua', 'Bay of Plenty', 'UTC+12'),
                                     ('NZCI', 'Tuuta Airport', 'Chatham Islands', 'Chatham Islands', 'UTC+12:45'),
                                     ('YSSY', 'Sydney Kingsford Smith Airport', 'Sydney', 'New South Wales', 'UTC+10'),
                                     ('NZCH', 'Christchurch Airport', 'Christchurch', 'Canterbury', 'UTC+12'),
                                     ('NZTL', 'Lake Tekapo', 'Lake Tekapo', 'Mackenzie District', 'UTC+12'),
                                     ('NZFJ', 'Franz Josef Aerodrome', 'Franz Josef', 'West Coast', 'UTC+12'),
                                     ('NZMF', 'Milford Sound Airport', 'Milford Sound', 'Fjordland', 'UTC+12'),
                                     ('NZQN', 'Queenstown Airport', 'Queenstown', 'Lake Wakatipu and Central Otago', 'UTC+12'),
                                     ('NZRC', 'Ryan’s Creek Aerodrome', 'Stewart Island', 'Stewart Island', 'UTC+12');


CREATE TABLE airplane (
                          airplane_id INT AUTO_INCREMENT PRIMARY KEY,
                          model VARCHAR(50) NOT NULL,
                          capacity INT NOT NULL,
                          manufacturer VARCHAR(100),
                          range_km INT,
                          registration_number  VARCHAR(20)
);

INSERT INTO airplane (model, capacity, manufacturer, range_km, registration_number) VALUES
                                    ('Boeing 737', 160, 'Boeing', 6030, 'N737AB'),
                                    ('Boeing 737', 160, 'Boeing', 6200, 'B-1937'),
                                    ('Boeing 737', 162, 'Boeing', 6100, 'VH-B737'),
                                    ('Airbus A320', 180, 'Airbus', 6100, 'B-1234'),
                                    ('Airbus A320', 182, 'Airbus', 6150, 'D-A3201'),
                                    ('Airbus A320', 178, 'Airbus', 6000, 'G-A320X'),
                                    ('Boeing 747', 416, 'Boeing', 13800, 'G-BYGC'),
                                    ('Boeing 747', 400, 'Boeing', 13700, 'B-4747'),
                                    ('Airbus A380', 555, 'Airbus', 15700, 'D-AIMN'),
                                    ('Airbus A380', 550, 'Airbus', 15500, 'F-A380X'),
                                    ('Boeing 777', 396, 'Boeing', 15600, 'VH-OEI'),
                                    ('Boeing 777', 400, 'Boeing', 15550, 'N777XY'),
                                    ('Airbus A350', 440, 'Airbus', 15000, 'F-WWOW'),
                                    ('Airbus A350', 442, 'Airbus', 14900, 'B-A350Z'),
                                    ('Boeing 787', 242, 'Boeing', 13620, 'JA873A'),
                                    ('Boeing 787', 245, 'Boeing', 13750, 'N787XA'),
                                    ('Embraer E190', 114, 'Embraer', 4260, 'PR-AYX'),
                                    ('Embraer E190', 110, 'Embraer', 4300, 'B-ER190'),
                                    ('Airbus A330', 277, 'Airbus', 13450, 'B-5678'),
                                    ('Airbus A330', 280, 'Airbus', 13300, 'G-A330Y');


CREATE TABLE flight (
                        flight_id INT AUTO_INCREMENT PRIMARY KEY,
                        flight_number VARCHAR(10) NOT NULL,
                        airplane_id INT NOT NULL,
                        route_id INT NOT NULL,
                        departure_date DATE NOT NULL,
                        departure_time DATETIME NOT NULL,
                        arrive_time DATETIME NOT NULL,
                        price DECIMAL(10, 2) NOT NULL,
                        status VARCHAR(20) DEFAULT 'scheduled',
                        FOREIGN KEY (airplane_id) REFERENCES airplane(airplane_id),
                        FOREIGN KEY (route_id) REFERENCES routes(route_id)
);

INSERT INTO flight (flight_number, airplane_id, route_id, departure_date, departure_time, arrive_time, price)
VALUES
    ('FL001', 1, 7, CURDATE(), CONCAT(CURDATE(), ' 08:00:00'), DATE_ADD(CONCAT(CURDATE(), ' 08:00:00'),
                                                                        INTERVAL FLOOR((SELECT duration_hours FROM routes WHERE route_id = 7)) HOUR)
        + INTERVAL (SELECT (duration_hours - FLOOR(duration_hours)) * 60 FROM routes WHERE route_id = 7) MINUTE, 150.00)




CREATE TABLE routes (
                        route_id INT AUTO_INCREMENT PRIMARY KEY,
                        departure_airport_id INT NOT NULL,
                        arrival_airport_id INT NOT NULL,
                        stopover_airport_id INT,
                        distance_km INT,
                        duration_hours DECIMAL(4, 2),
                        route_type VARCHAR(20) DEFAULT 'direct',
                        FOREIGN KEY (departure_airport_id) REFERENCES airport(airport_id),
                        FOREIGN KEY (arrival_airport_id) REFERENCES airport(airport_id),
                        FOREIGN KEY (stopover_airport_id) REFERENCES airport(airport_id)
);

INSERT INTO routes (departure_airport_id, arrival_airport_id, stopover_airport_id, distance_km, duration_hours, route_type) VALUES
                                                                                                                                (1, 2, NULL, 2250, 3.50, 'direct'),   -- 从悉尼国际机场到基督城国际机场的直飞航线
                                                                                                                                (1, 3, NULL, 2300, 3.75, 'direct'),   -- 从悉尼国际机场到格雷茅斯机场的直飞航线
                                                                                                                                (2, 4, NULL, 600, 1.25, 'direct'),    -- 从基督城国际机场到皇后镇机场的直飞航线
                                                                                                                                (3, 5, 4, 700, 1.50, 'stopover'),     -- 从格雷茅斯机场到陶波机场，经过皇后镇机场的中转航线
                                                                                                                                (4, 1, NULL, 2250, 3.50, 'direct'),   -- 从皇后镇机场返回悉尼国际机场的直飞航线
                                                                                                                                (5, 1, NULL, 2500, 4.00, 'direct');   -- 从陶波机场返回悉尼国际机场的直飞航线


-- NZNE to YSSY (direct)
INSERT INTO routes (departure_airport_id, arrival_airport_id, stopover_airport_id, distance_km, duration_hours, route_type)
VALUES (1, 5, NULL, 2160, 3.5, 'direct');

-- NZGB to NZNE to YSSY (stopover at NZNE)
INSERT INTO routes (departure_airport_id, arrival_airport_id, stopover_airport_id, distance_km, duration_hours, route_type)
VALUES (2, 5, 1, 2430, 4.0, 'stopover');

-- NZRO to NZNE (direct)
INSERT INTO routes (departure_airport_id, arrival_airport_id, stopover_airport_id, distance_km, duration_hours, route_type)
VALUES (3, 1, NULL, 300, 0.5, 'direct');

-- NZCI to YSSY (direct)
INSERT INTO routes (departure_airport_id, arrival_airport_id, stopover_airport_id, distance_km, duration_hours, route_type)
VALUES (4, 5, NULL, 4030, 5.5, 'direct');

-- NZCH to YSSY (direct)
INSERT INTO routes (departure_airport_id, arrival_airport_id, stopover_airport_id, distance_km, duration_hours, route_type)
VALUES (6, 5, NULL, 2130, 3.0, 'direct');

-- NZTL to NZRO to YSSY (stopover at NZRO)
INSERT INTO routes (departure_airport_id, arrival_airport_id, stopover_airport_id, distance_km, duration_hours, route_type)
VALUES (7, 5, 3, 2590, 4.2, 'stopover');

-- NZFJ to NZTL to YSSY (stopover at NZTL)
INSERT INTO routes (departure_airport_id, arrival_airport_id, stopover_airport_id, distance_km, duration_hours, route_type)
VALUES (8, 5, 7, 2700, 4.5, 'stopover');

-- NZMF to NZCH (direct)
INSERT INTO routes (departure_airport_id, arrival_airport_id, stopover_airport_id, distance_km, duration_hours, route_type)
VALUES (9, 6, NULL, 470, 1.0, 'direct');

-- NZQN to NZCH (direct)
INSERT INTO routes (departure_airport_id, arrival_airport_id, stopover_airport_id, distance_km, duration_hours, route_type)
VALUES (10, 6, NULL, 350, 0.6, 'direct');

-- NZRC to NZCH (direct)
INSERT INTO routes (departure_airport_id, arrival_airport_id, stopover_airport_id, distance_km, duration_hours, route_type)
VALUES (11, 6, NULL, 500, 1.2, 'direct');


CREATE TABLE seats (
                       seat_id INT AUTO_INCREMENT PRIMARY KEY,   -- Unique ID for each seat
                       seat_type VARCHAR(20) NOT NULL,           -- Type of seat (e.g., 'economy', 'first-class')
                       seat_number VARCHAR(5) NOT NULL           -- Seat number (e.g., '12A', '1B')
);
INSERT INTO seats (seat_number, seat_type)
VALUES
    ('1A', 'B'),
    ('1B', 'B'),
    ('2A', 'B'),
    ('2B', 'B'),
    ('3A', 'B'),
    ('3B', 'B'),
    ('4A', 'B'),
    ('4B', 'B'),
    ('5A', 'B'),
    ('5B', 'B'),
    ('6A', 'B'),
    ('6B', 'B'),
    ('7A', 'B'),
    ('7B', 'B'),
    ('8A', 'B'),
    ('8B', 'B');

INSERT INTO seats (seat_number, seat_type)
VALUES
    ('1A', 'E'), ('1B', 'E'), ('1D', 'E'), ('1E', 'E'),
    ('2A', 'E'), ('2B', 'E'), ('2D', 'E'), ('2E', 'E'),
    ('3A', 'E'), ('3B', 'E'), ('3D', 'E'), ('3E', 'E'),
    ('4A', 'E'), ('4B', 'E'), ('4D', 'E'), ('4E', 'E'),
    ('5A', 'E'), ('5B', 'E'), ('5D', 'E'), ('5E', 'E'),
    ('6A', 'E'), ('6B', 'E'), ('6D', 'E'), ('6E', 'E'),
    ('7A', 'E'), ('7B', 'E'), ('7D', 'E'), ('7E', 'E'),
    ('8A', 'E'), ('8B', 'E'), ('8D', 'E'), ('8E', 'E'),
    ('9A', 'E'), ('9B', 'E'), ('9D', 'E'), ('9E', 'E'),
    ('10A', 'E'), ('10B', 'E'), ('10D', 'E'), ('10E', 'E'),
    ('11A', 'E'), ('11B', 'E'), ('11D', 'E'), ('11E', 'E'),
    ('12A', 'E'), ('12B', 'E'), ('12D', 'E'), ('12E', 'E'),
    ('13A', 'E'), ('13B', 'E'), ('13D', 'E'), ('13E', 'E'),
    ('14A', 'E'), ('14B', 'E'), ('14D', 'E'), ('14E', 'E'),
    ('15A', 'E'), ('15B', 'E'), ('15D', 'E'), ('15E', 'E'),
    ('16A', 'E'), ('16B', 'E'), ('16D', 'E'), ('16E', 'E'),
    ('17A', 'E'), ('17B', 'E'), ('17D', 'E'), ('17E', 'E'),
    ('18A', 'E'), ('18B', 'E'), ('18D', 'E'), ('18E', 'E'),
    ('19A', 'E'), ('19B', 'E'), ('19D', 'E'), ('19E', 'E'),
    ('20A', 'E'), ('20B', 'E'), ('20D', 'E'), ('20E', 'E');

CREATE TABLE flight_seats (
                              flight_seat_id INT AUTO_INCREMENT PRIMARY KEY, -- Unique ID for each flight seat record
                              seat_number VARCHAR(20) NOT NULL,                          -- Reference to the seat in the 'seats' table
                              flight_id INT NOT NULL,                        -- Reference to the flight in the 'flight' table
                              seat_type VARCHAR(20) NOT NULL,                -- Type of seat (redundant but useful for quick access)
                              is_sold BOOLEAN not null default false,
                              FOREIGN KEY (flight_id) REFERENCES flight(flight_id)
);

INSERT INTO flight_seats (seat_number, flight_id, seat_type)
SELECT seat_number, 11 AS flight_id, seat_type
FROM seats;


CREATE TABLE orders (
                        order_id INT AUTO_INCREMENT PRIMARY KEY,     -- Unique order ID
                        seat_number VARCHAR(20) NOT NULL,                          -- Reference to the seat in the 'seats' table
                        flight_id INT NOT NULL,                        -- Reference to the flight in the 'flight' table
                        seat_type VARCHAR(20) NOT NULL,                -- Type of seat (redundant but useful for quick access)
                        total_price DECIMAL(10, 2) NOT NULL,         -- Total price for the booking
                        passenger_name VARCHAR(50) NOT NULL,              -- Name of the user
                        email VARCHAR(100) NOT NULL,                 -- Email of the user
                        phone VARCHAR(20) NOT NULL,                  -- Phone number of the user
                        baggage_service BOOLEAN DEFAULT FALSE,               -- Whether baggage handling is requested
                        food_service BOOLEAN DEFAULT FALSE,                  -- Whether food service is requested
                        order_time DATETIME NOT NULL default current_timestamp,                -- Time when the order was placed
                        status VARCHAR(20) DEFAULT 'ready',-- Payment status (e.g., 'ready', 'completed')
                        username VARCHAR(255) NOT NULL,
                        FOREIGN KEY (flight_id) REFERENCES flight(flight_id) -- Foreign key to flight table
);
