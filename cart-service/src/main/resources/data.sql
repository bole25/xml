/*Users in cart table*/
INSERT IGNORE INTO `cartdb`.`cart` (`id`, `username`) VALUES ('1', 'roki');
INSERT IGNORE INTO `cartdb`.`cart` (`id`, `username`) VALUES ('2', 'bojan');
INSERT IGNORE INTO `cartdb`.`cart` (`id`, `username`) VALUES ('3', 'blanusa');

/*Cart items*/
    /*Occupation*/
    INSERT IGNORE INTO `cartdb`.`occupation` (`id`, `start_date`, `end_date`) VALUES ('1', '2020-07-01', '2020-07-04');

INSERT IGNORE INTO `cartdb`.`cart_item` (`id`, `owner_username`, `vehicle_name`, `vehicle_id`, `price`, `time_span_id`)
    VALUES ('1', 'bojan', 'Koral (Yugo 45)', '2', '15', '1');

    /*Occupation*/
    INSERT IGNORE INTO `cartdb`.`occupation` (`id`, `start_date`, `end_date`) VALUES ('2', '2020-07-02', '2020-07-12');

INSERT IGNORE INTO `cartdb`.`cart_item` (`id`, `owner_username`, `vehicle_name`, `vehicle_id`, `price`, `time_span_id`)
    VALUES ('2', 'bojan', 'G-Wagen', '1', '156', '2');

    /*Occupation*/
    INSERT IGNORE INTO `cartdb`.`occupation` (`id`, `start_date`, `end_date`) VALUES ('3', '2020-08-10', '2020-08-12');

INSERT IGNORE INTO `cartdb`.`cart_item` (`id`, `owner_username`, `vehicle_name`, `vehicle_id`, `price`, `time_span_id`)
    VALUES ('3', 'blanusa', 'Prius', '4', '62', '3');

/*Occupation*/
INSERT IGNORE INTO `cartdb`.`occupation` (`id`, `start_date`, `end_date`) VALUES ('4', '2020-07-27', '2020-07-30');

INSERT IGNORE INTO `cartdb`.`cart_item` (`id`, `owner_username`, `vehicle_name`, `vehicle_id`, `price`, `time_span_id`)
VALUES ('4', 'blanusa', 'SLR', '5', '52', '4');

/*Connectiong cart with its items*/
INSERT IGNORE INTO `cartdb`.`cart_items` (`cart_id`, `items_id`) VALUES ('1', '1');
INSERT IGNORE INTO `cartdb`.`cart_items` (`cart_id`, `items_id`) VALUES ('1', '2');
INSERT IGNORE INTO `cartdb`.`cart_items` (`cart_id`, `items_id`) VALUES ('1', '3');
INSERT IGNORE INTO `cartdb`.`cart_items` (`cart_id`, `items_id`) VALUES ('1', '4');


