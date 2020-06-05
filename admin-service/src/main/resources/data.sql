/*Cars*/
INSERT IGNORE INTO `admindb`.`brand` (`id`, `name`) VALUES ('1', 'Mercedes');
INSERT IGNORE INTO `admindb`.`brand` (`id`, `name`) VALUES ('2', 'Zastava');
INSERT IGNORE INTO `admindb`.`brand` (`id`, `name`) VALUES ('3', 'Ford');
INSERT IGNORE INTO `admindb`.`brand` (`id`, `name`) VALUES ('4', 'Toyota');

/*Brand models*/
    /*Mercedes*/
INSERT IGNORE INTO `admindb`.`model` (`id`, `name`) VALUES ('1', 'G-Wagen');
INSERT IGNORE INTO `admindb`.`brand_models` (`brand_id`, `models_id`) VALUES ('1', '1');
INSERT IGNORE INTO `admindb`.`model` (`id`, `name`) VALUES ('2', '500 E');
INSERT IGNORE INTO `admindb`.`brand_models` (`brand_id`, `models_id`) VALUES ('1', '2');
INSERT IGNORE INTO `admindb`.`model` (`id`, `name`) VALUES ('3', 'SLR');
INSERT IGNORE INTO `admindb`.`brand_models` (`brand_id`, `models_id`) VALUES ('1', '3');
INSERT IGNORE INTO `admindb`.`model` (`id`, `name`) VALUES ('4', 'C63 AMG');
INSERT IGNORE INTO `admindb`.`brand_models` (`brand_id`, `models_id`) VALUES ('1', '4');

    /*Zastava*/
INSERT IGNORE INTO `admindb`.`model` (`id`, `name`) VALUES ('5', '750 (Fica)');
INSERT IGNORE INTO `admindb`.`brand_models` (`brand_id`, `models_id`) VALUES ('2', '5');
INSERT IGNORE INTO `admindb`.`model` (`id`, `name`) VALUES ('6', 'Skala');
INSERT IGNORE INTO `admindb`.`brand_models` (`brand_id`, `models_id`) VALUES ('2', '6');
INSERT IGNORE INTO `admindb`.`model` (`id`, `name`) VALUES ('7', 'Koral (Yugo 45)');
INSERT IGNORE INTO `admindb`.`brand_models` (`brand_id`, `models_id`) VALUES ('2', '7');

    /*Ford*/
INSERT IGNORE INTO `admindb`.`model` (`id`, `name`) VALUES ('8', 'Fiesta');
INSERT IGNORE INTO `admindb`.`brand_models` (`brand_id`, `models_id`) VALUES ('3', '8');
INSERT IGNORE INTO `admindb`.`model` (`id`, `name`) VALUES ('9', 'Focus');
INSERT IGNORE INTO `admindb`.`brand_models` (`brand_id`, `models_id`) VALUES ('3', '9');
INSERT IGNORE INTO `admindb`.`model` (`id`, `name`) VALUES ('10', 'GT');
INSERT IGNORE INTO `admindb`.`brand_models` (`brand_id`, `models_id`) VALUES ('3', '10');
INSERT IGNORE INTO `admindb`.`model` (`id`, `name`) VALUES ('11', 'Escape');
INSERT IGNORE INTO `admindb`.`brand_models` (`brand_id`, `models_id`) VALUES ('3', '11');

    /*Toyota*/
INSERT IGNORE INTO `admindb`.`model` (`id`, `name`) VALUES ('12', 'Camry');
INSERT IGNORE INTO `admindb`.`brand_models` (`brand_id`, `models_id`) VALUES ('4', '12');
INSERT IGNORE INTO `admindb`.`model` (`id`, `name`) VALUES ('13', 'Land Cruiser');
INSERT IGNORE INTO `admindb`.`brand_models` (`brand_id`, `models_id`) VALUES ('4', '13');
INSERT IGNORE INTO `admindb`.`model` (`id`, `name`) VALUES ('14', 'Corolla');
INSERT IGNORE INTO `admindb`.`brand_models` (`brand_id`, `models_id`) VALUES ('4', '14');
INSERT IGNORE INTO `admindb`.`model` (`id`, `name`) VALUES ('15', 'Prius');
INSERT IGNORE INTO `admindb`.`brand_models` (`brand_id`, `models_id`) VALUES ('4', '15');


/*Fuel types*/
INSERT IGNORE INTO `admindb`.`fuel_type` (`id`, `name`) VALUES ('1', 'Diesel');
INSERT IGNORE INTO `admindb`.`fuel_type` (`id`, `name`) VALUES ('2', 'Gasoline');
INSERT IGNORE INTO `admindb`.`fuel_type` (`id`, `name`) VALUES ('3', 'Electricity');
INSERT IGNORE INTO `admindb`.`fuel_type` (`id`, `name`) VALUES ('4', 'Natural Gas');


/*Transmission*/
INSERT IGNORE INTO `admindb`.`transmission` (`id`, `name`) VALUES ('1', 'Manual');
INSERT IGNORE INTO `admindb`.`transmission` (`id`, `name`) VALUES ('2', 'Automatic');


/*Car types*/
INSERT IGNORE INTO `admindb`.`vehicle_class` (`id`, `name`) VALUES ('1', 'Mini-Van');
INSERT IGNORE INTO `admindb`.`vehicle_class` (`id`, `name`) VALUES ('2', 'SUV');
INSERT IGNORE INTO `admindb`.`vehicle_class` (`id`, `name`) VALUES ('3', 'Pickup Truck');
INSERT IGNORE INTO `admindb`.`vehicle_class` (`id`, `name`) VALUES ('4', 'Convertible');
INSERT IGNORE INTO `admindb`.`vehicle_class` (`id`, `name`) VALUES ('5', 'Family car');
INSERT IGNORE INTO `admindb`.`vehicle_class` (`id`, `name`) VALUES ('6', 'Sports Car');






