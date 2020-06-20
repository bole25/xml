/*Adding Vehicles*/
INSERT IGNORE INTO `vehicles`.`vehicle` (`id`, `brand`, `company_username`, `start_date`, `end_date`, `transmission`,
                                         `price`, `model`, `fuel_type`, `mileage`, `vehicle_class`) VALUES
                ('1', 'Mercedes', 'bojan', '2020-05-05', '2020-08-12','Automatic','156','G-Wagen', 'Diesel', '65000', 'SUV');
    /*Occupations for that vehicle*/
    INSERT IGNORE INTO `vehicles`.`occupation` (`id`, `start_date`, `end_date`) VALUES ('1', '2020-05-13', '2020-05-18');
    INSERT IGNORE INTO `vehicles`.`occupation` (`id`, `start_date`, `end_date`) VALUES ('2', '2020-05-27', '2020-06-08');
    INSERT IGNORE INTO `vehicles`.`occupation` (`id`, `start_date`, `end_date`) VALUES ('3', '2020-06-19', '2020-06-29');
    INSERT IGNORE INTO `vehicles`.`occupation` (`id`, `start_date`, `end_date`) VALUES ('4', '2020-07-07', '2020-07-15');

    /*Connecting vehicle with occupations*/
    INSERT IGNORE INTO `vehicles`.`vehicle_occupations` (`vehicle_id`, `occupations_id`) VALUES ('1', '1');
    INSERT IGNORE INTO `vehicles`.`vehicle_occupations` (`vehicle_id`, `occupations_id`) VALUES ('1', '2');
    INSERT IGNORE INTO `vehicles`.`vehicle_occupations` (`vehicle_id`, `occupations_id`) VALUES ('1', '3');
    INSERT IGNORE INTO `vehicles`.`vehicle_occupations` (`vehicle_id`, `occupations_id`) VALUES ('1', '4');

/*Adding Vehicles*/
INSERT IGNORE INTO `vehicles`.`vehicle` (`id`, `brand`, `company_username`, `start_date`, `end_date`, `transmission`,
                                         `price`, `model`, `fuel_type`, `mileage`, `vehicle_class`) VALUES
('2', 'Zastavan', 'bojan', '2020-07-01', '2020-07-30','Manual','15','Koral (Yugo 45)', 'Gasoline', '300000', 'Family car');

    /*Occupations for that vehicle*/
    INSERT IGNORE INTO `vehicles`.`occupation` (`id`, `start_date`, `end_date`) VALUES ('5', '2020-07-03', '2020-07-08');
    INSERT IGNORE INTO `vehicles`.`occupation` (`id`, `start_date`, `end_date`) VALUES ('6', '2020-07-21', '2020-07-26');

    /*Connecting vehicle with occupations*/
    INSERT IGNORE INTO `vehicles`.`vehicle_occupations` (`vehicle_id`, `occupations_id`) VALUES ('2', '5');
    INSERT IGNORE INTO `vehicles`.`vehicle_occupations` (`vehicle_id`, `occupations_id`) VALUES ('2', '6');

/*Adding Vehicles*/
INSERT IGNORE INTO `vehicles`.`vehicle` (`id`, `brand`, `company_username`, `start_date`, `end_date`, `transmission`,
                                         `price`, `model`, `fuel_type`, `mileage`, `vehicle_class`) VALUES
('3', 'Toyota', 'roki', '2020-07-10', '2020-08-30','Manual','62','Prius', 'Electricity', '125000', 'Family car');

/*Occupations for that vehicle*/
INSERT IGNORE INTO `vehicles`.`occupation` (`id`, `start_date`, `end_date`) VALUES ('7', '2020-07-19', '2020-07-26');
INSERT IGNORE INTO `vehicles`.`occupation` (`id`, `start_date`, `end_date`) VALUES ('8', '2020-08-09', '2020-08-19');

/*Connecting vehicle with occupations*/
INSERT IGNORE INTO `vehicles`.`vehicle_occupations` (`vehicle_id`, `occupations_id`) VALUES ('3', '7');
INSERT IGNORE INTO `vehicles`.`vehicle_occupations` (`vehicle_id`, `occupations_id`) VALUES ('3', '8');


/*Adding Vehicles*/
INSERT IGNORE INTO `vehicles`.`vehicle` (`id`, `brand`, `company_username`, `start_date`, `end_date`, `transmission`,
                                         `price`, `model`, `fuel_type`, `mileage`, `vehicle_class`) VALUES
('4', 'Toyota', 'blanusa', '2020-08-10', '2020-08-22','Manual','62','Prius', 'Electricity', '125000', 'Family car');

/*Occupations for that vehicle*/
INSERT IGNORE INTO `vehicles`.`occupation` (`id`, `start_date`, `end_date`) VALUES ('9', '2020-08-19', '2020-08-22');

/*Connecting vehicle with occupations*/
INSERT IGNORE INTO `vehicles`.`vehicle_occupations` (`vehicle_id`, `occupations_id`) VALUES ('4', '9');


/*Adding Vehicles*/
INSERT IGNORE INTO `vehicles`.`vehicle` (`id`, `brand`, `company_username`, `start_date`, `end_date`, `transmission`,
                                         `price`, `model`, `fuel_type`, `mileage`, `vehicle_class`) VALUES
('5', 'Mercedes', 'blanusa', '2020-07-23', '2020-08-02','Manual','52','SLR', 'Gasoline', '225000', 'Family car');

/*Occupations for that vehicle*/
INSERT IGNORE INTO `vehicles`.`occupation` (`id`, `start_date`, `end_date`) VALUES ('10', '2020-07-23', '2020-07-26');

/*Connecting vehicle with occupations*/
INSERT IGNORE INTO `vehicles`.`vehicle_occupations` (`vehicle_id`, `occupations_id`) VALUES ('5', '10');
