INSERT INTO users (id,confirmed,email,first_name,last_name,password,role,username) VALUES ('1', b'1','petros@hotmail', 'petros', 'soursos', '$2y$04$W.8aIS3MrjiMlDWh31uNEesAa1Ak.t5Bh7NJfKtWGzg5A/AoEf01y', 'USER', 'psoursos');
INSERT INTO `mymdb`.`users` (`id`, `confirmed`, `email`, `first_name`, `last_name`, `password`, `role`, `username`) VALUES ('2', b'1','admin@hotmail', 'admin', 'admin', '$2y$04$W.8aIS3MrjiMlDWh31uNEesAa1Ak.t5Bh7NJfKtWGzg5A/AoEf01y', 'ADMIN', 'admin');
INSERT INTO `mymdb`.`users` (`id`, `confirmed`, `email`, `first_name`, `last_name`, `password`, `role`, `username`) VALUES ('3', b'0','petros@hotmail', 'petros1', 'soursos1', '$2y$04$W.8aIS3MrjiMlDWh31uNEesAa1Ak.t5Bh7NJfKtWGzg5A/AoEf01y', 'USER', 'psoursos1');
INSERT INTO `mymdb`.`users` (`id`, `confirmed`, `email`, `first_name`, `last_name`, `password`, `role`, `username`) VALUES ('4', b'0','petros@hotmail', 'petros2', 'soursos2', '$2y$04$W.8aIS3MrjiMlDWh31uNEesAa1Ak.t5Bh7NJfKtWGzg5A/AoEf01y', 'USER', 'psoursos2');
INSERT INTO `mymdb`.`users` (`id`, `confirmed`, `email`, `first_name`, `last_name`, `password`, `role`, `username`) VALUES ('5', b'1','petros@hotmail', 'petros3', 'soursos3', '$2y$04$W.8aIS3MrjiMlDWh31uNEesAa1Ak.t5Bh7NJfKtWGzg5A/AoEf01y', 'CINEMAOWNER', 'psoursos3');



INSERT INTO `mymdb`.`cinemas` (`id`, `name`,`owner`) VALUES ('1','cinema_1','name1');
INSERT INTO `mymdb`.`cinemas` (`id`, `name`,`owner`) VALUES ('2','cinema_2','name2');

INSERT INTO `mymdb`.`owns` (`id`, `cinema_id`, `user_id`) VALUES ('1', '1','5');
INSERT INTO `mymdb`.`owns` (`id`, `cinema_id`, `user_id`) VALUES ('2', '2','5');

INSERT INTO `mymdb`.`movies` (`movie_id`, `category`, `cinema_name`, `cinema_id`,`end_date`,`start_date`,`title`) VALUES ('1', 'cat1','cin1','1','2020-01-01','2020-01-01','title1');
INSERT INTO `mymdb`.`movies` (`movie_id`, `category`, `cinema_name`, `cinema_id`,`end_date`,`start_date`,`title`) VALUES ('2', 'cat1','cin1','1','2020-01-01','2020-01-01','title2');
INSERT INTO `mymdb`.`movies` (`movie_id`, `category`, `cinema_name`, `cinema_id`,`end_date`,`start_date`,`title`) VALUES ('3', 'cat2','cin1','1','2020-01-01','2020-01-01','title3');
INSERT INTO `mymdb`.`movies` (`movie_id`, `category`, `cinema_name`, `cinema_id`,`end_date`,`start_date`,`title`) VALUES ('4', 'cat1','cin2','2','2020-01-01','2020-01-01','title4');
INSERT INTO `mymdb`.`movies` (`movie_id`, `category`, `cinema_name`, `cinema_id`,`end_date`,`start_date`,`title`) VALUES ('5', 'cat2','cin2','2','2020-01-01','2020-01-01','title5');