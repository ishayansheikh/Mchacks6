CREATE TABLE `hint` (
  `hintid` int(11) NOT NULL AUTO_INCREMENT,
  `xcoord` int(11) DEFAULT NULL,
  `ycoord` int(11) DEFAULT NULL,
  `distance` double DEFAULT NULL,
  `imageurl` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`hintid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
