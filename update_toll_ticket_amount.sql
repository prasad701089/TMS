-- SQL script to update the amount column in toll_ticket table based on vehicle_type and plan

UPDATE toll_ticket
SET amount = CASE 
    WHEN vehicle_type = 'Car' AND plan = 'single' THEN 50
    WHEN vehicle_type = 'Car' AND plan = 'return' THEN 100
    WHEN vehicle_type = 'Car' AND plan = 'monthly' THEN 1500
    WHEN vehicle_type = 'Truck' AND plan = 'single' THEN 100
    WHEN vehicle_type = 'Truck' AND plan = 'return' THEN 200
    WHEN vehicle_type = 'Truck' AND plan = 'monthly' THEN 3000
    WHEN vehicle_type = 'Bus' AND plan = 'single' THEN 150
    WHEN vehicle_type = 'Bus' AND plan = 'return' THEN 300
    WHEN vehicle_type = 'Bus' AND plan = 'monthly' THEN 4500
    WHEN vehicle_type = 'Jeep' AND plan = 'single' THEN 130
    WHEN vehicle_type = 'Jeep' AND plan = 'return' THEN 260
    WHEN vehicle_type = 'Jeep' AND plan = 'monthly' THEN 3900
    WHEN vehicle_type = 'Three wheeler' AND plan = 'single' THEN 90
    WHEN vehicle_type = 'Three wheeler' AND plan = 'return' THEN 180
    WHEN vehicle_type = 'Three wheeler' AND plan = 'monthly' THEN 2700
    ELSE amount
END;
