INSERT IGNORE INTO `authentication`.`user_credentials` (`id`, `active`, `password`, `role`, `username`) VALUES ('1', b'1', 'bojansifra', '0', 'bojan');
INSERT IGNORE INTO `authentication`.`user_credentials` (`id`, `active`, `password`, `role`, `username`) VALUES ('2', b'1', 'adminsifra', '2', 'admin');
INSERT IGNORE INTO `authentication`.`user_credentials` (`id`, `active`, `password`, `role`, `username`) VALUES ('3', b'1', 'rokisifra', '0', 'roki');
INSERT IGNORE INTO `authentication`.`user_credentials` (`id`, `active`, `password`, `role`, `username`) VALUES ('4', b'1', 'blanusasifra', '0', 'blanusa');

INSERT IGNORE INTO `authentication`.`user_permission` (`id`, `other_perm`, `request_perm`, `vehicle_perm`, `user_id`) VALUES ('1', b'1',b'1',b'1','1');
INSERT IGNORE INTO `authentication`.`user_permission` (`id`, `other_perm`, `request_perm`, `vehicle_perm`, `user_id`) VALUES ('2', b'1',b'1',b'1','3');
INSERT IGNORE INTO `authentication`.`user_permission` (`id`, `other_perm`, `request_perm`, `vehicle_perm`, `user_id`) VALUES ('3', b'1',b'1',b'1','4');
INSERT IGNORE INTO `authentication`.`user_permission` (`id`, `other_perm`, `request_perm`, `vehicle_perm`, `user_id`) VALUES ('4', b'1',b'1',b'1','2');
INSERT IGNORE INTO `authentication`.`user_permission` (`id`, `other_perm`, `request_perm`, `vehicle_perm`, `user_id`) VALUES ('5', b'1',b'1',b'1','5');
