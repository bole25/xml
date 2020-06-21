INSERT IGNORE INTO `messaging_db`.`user_messages` (`id`, `username`) VALUES ('1', 'bojan');
INSERT IGNORE INTO `messaging_db`.`user_messages` (`id`, `username`) VALUES ('2', 'blanusa');
INSERT IGNORE INTO `messaging_db`.`user_messages` (`id`, `username`) VALUES ('3', 'roki');
INSERT IGNORE INTO `messaging_db`.`user_messages` (`id`, `username`) VALUES ('4', 'admin');


INSERT IGNORE INTO `messaging_db`.`message` (`id`, `receiver_username`,`sender_username`,`text_message`,`time`) VALUES
            ('1', 'blanusa', 'roki', 'Dobar dan, da li moze popust na kola?? :(', '2020-06-20 10:34:09');
    INSERT IGNORE INTO `messaging_db`.`user_messages_sent` (`user_messages_id`, `sent_id`) VALUES (3,1);
    INSERT IGNORE INTO `messaging_db`.`user_messages_received` (`user_messages_id`, `received_id`) VALUES (2,1);

INSERT IGNORE INTO `messaging_db`.`message` (`id`, `receiver_username`,`sender_username`,`text_message`,`time`) VALUES
        ('2', 'roki', 'blanusa', 'Nop', '2020-06-20 12:34:09');
    INSERT IGNORE INTO `messaging_db`.`user_messages_sent` (`user_messages_id`, `sent_id`) VALUES (2,2);
    INSERT IGNORE INTO `messaging_db`.`user_messages_received` (`user_messages_id`, `received_id`) VALUES (3,2);

INSERT IGNORE INTO `messaging_db`.`message` (`id`, `receiver_username`,`sender_username`,`text_message`,`time`) VALUES
        ('3', 'blanusa', 'roki', 'Ok, uzimam svakako...','2020-06-20 13:34:00');
    INSERT IGNORE INTO `messaging_db`.`user_messages_sent` (`user_messages_id`, `sent_id`) VALUES (3,3);
    INSERT IGNORE INTO `messaging_db`.`user_messages_received` (`user_messages_id`, `received_id`) VALUES (2,3);
