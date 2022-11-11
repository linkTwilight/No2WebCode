CREATE DATABASE fruitdb charset utf8mb4;
USE fruitdb;
CREATE TABLE `t_fruit` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `fname` varchar(20) NOT NULL,
                           `price` int(11) DEFAULT NULL,
                           `fcount` int(11) DEFAULT NULL,
                           `remark` varchar(50) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `fname` (`fname`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

insert  into `t_fruit`(`id`,`fname`,`price`,`fcount`,`remark`) values (3,'葡萄',3,55,'葡萄很好吃'),(5,'榴莲',9,99,'榴莲是一种神奇的水果'),(6,'菠萝',5,166,'OK'),(7,'哈密瓜',9,500,'哈密瓜真好吃！'),(8,'苹果1',1,20,'苹果1真好吃'),(9,'苹果2',1,10,'苹果2真好吃'),(12,'苹果5',1,10,'苹果5真好吃'),(13,'苹果6',1,10,'苹果6真好吃'),(14,'苹果7',1,10,'苹果7真好吃'),(15,'苹果8',1,10,'苹果8真好吃'),(18,'西瓜',5,55,'OK'),(19,'火龙果',9,99,'火龙果真好吃'),(20,'西红柿',3,88,'西红柿真好吃'),(21,'西红柿2022',3,99,'西红柿是一种神奇的水果'),(22,'菠萝蜜2022',9,99,'菠萝蜜是一种神奇的水果'),(23,'西红柿2099',9,789,'西红柿真好吃'),(24,'西红柿2199',8,888,'真好吃'),(25,'西红柿2299',33,33,'西红柿'),(26,'西红柿2399',3,88,'西红柿真好吃'),(29,'西红柿2号',9,98,'OK'),(30,'hmg2099',9,99,'OKOK');


INSERT INTO t_fruit (fname, price, fcount, remark) VALUES (?,?,?,?);
SELECT * FROM t_fruit WHERE id=?;

UPDATE t_fruit SET fname=? ,price = ? ,fcount = ? , remark = ?WHERE id=?;