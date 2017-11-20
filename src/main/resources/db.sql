INSERT INTO `lucybank`.`role` (`id`, `role`) VALUES ('1', 'ROLE_BANKER');

INSERT INTO `lucybank`.`role` (`id`, `role`) VALUES ('2', 'ROLE_TELLER');

INSERT INTO `lucybank`.`role` (`id`, `role`) VALUES ('3', 'ROLE_CUSTOMER');

INSERT INTO `lucybank`.`address` (`id`, `state`, `street`, `zipcode`) VALUES ('1', 'IA', 'ZY st', '45334');

INSERT INTO `lucybank`.`address` (`id`, `state`, `street`, `zipcode`) VALUES ('2', 'IA', 'YY st', '45424');

INSERT INTO `lucybank`.`address` (`id`, `state`, `street`, `zipcode`) VALUES ('3', 'IA', 'FF st', '45344');


INSERT INTO `lucybank`.`profile` (`id`, `email`, `firstName`, `lastName`, `password`, `userName`, `address_id`, `role_id`) VALUES ('1', 'lucybanker@lucy.com', 'Filmon', 'Semere', 'admin', 'admin', '1', '1');

INSERT INTO `lucybank`.`profile` (`id`, `email`, `firstName`, `lastName`, `password`, `userName`, `address_id`, `role_id`) VALUES ('2', 'lucyteller@lucy.com', 'Merhawi', 'Habte', 'mera', 'mera', '2', '2');

INSERT INTO `lucybank`.`profile` (`id`, `email`, `firstName`, `lastName`, `password`, `userName`, `address_id`, `role_id`) VALUES ('3', 'fyz@gmail.com', 'Aman', 'Wolde', 'aman', 'aman', '3', '3');