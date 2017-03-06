-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 23, 2017 at 04:58 PM
-- Server version: 5.7.16-0ubuntu0.16.04.1
-- PHP Version: 7.0.8-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecf_regate`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `intervention_commissaire_challenge` (IN `p_id_challenge` INT, IN `p_date_debut` DATE, IN `p_date_fin` DATE)  BEGIN
SELECT nom_personne, prenom_personne, comite, nom_challenge, date_regate FROM challenge cha
INNER JOIN regate r ON cha.id_challenge=r.id_challenge
INNER JOIN commissaire com ON r.id_commissaire=com.id_commissaire
INNER JOIN personne p ON com.id_personne=p.id_personne
WHERE r.id_challenge=p_id_challenge AND p_date_debut < date_regate AND p_date_fin > date_regate;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `liste_equipage_regate` (IN `p_id_regate` INT, IN `p_id_voilier` INT)  BEGIN
SELECT nom_personne, prenom_personne, nom_regate , nom_voilier, num_skipper FROM personne p
INNER JOIN concurrent c ON p.id_personne=c.id_personne
INNER JOIN voilier v ON c.id_voilier=v.id_voilier
INNER JOIN PARTICIPER par ON v.id_voilier=par.id_voilier
INNER JOIN regate r ON par.id_regate=r.id_regate
INNER JOIN skipper s ON v.id_voilier=s.id_voilier
WHERE par.id_regate=p_id_regate AND par.id_voilier=p_id_voilier;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `moyenne_distance_challenge` (IN `p_id_challenge` INT)  BEGIN
SELECT nom_challenge, AVG(distance) FROM regate r
INNER JOIN challenge c ON r.id_challenge=c.id_challenge
WHERE c.id_challenge=p_id_challenge;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `challenge`
--

CREATE TABLE `challenge` (
  `id_challenge` int(11) NOT NULL,
  `nom_challenge` varchar(60) DEFAULT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `challenge`
--

INSERT INTO `challenge` (`id_challenge`, `nom_challenge`, `date_debut`, `date_fin`) VALUES
(1, 'Challenge Hiver', '2016-11-15', '2017-03-31'),
(2, 'Challenge Ete', '2017-05-15', '2017-09-30'),
(3, 'test3', '2016-06-23', '2016-10-18');

-- --------------------------------------------------------

--
-- Table structure for table `classe`
--

CREATE TABLE `classe` (
  `id_classe` int(11) NOT NULL,
  `nom_classe` varchar(25) DEFAULT NULL,
  `coeff` int(11) DEFAULT NULL,
  `id_serie` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classe`
--

INSERT INTO `classe` (`id_classe`, `nom_classe`, `coeff`, `id_serie`) VALUES
(1, 'Corsaire', 2, 1),
(2, 'Surprise', 2, 1),
(3, '8 Metres', 2, 1),
(4, 'Maraudeur', 2, 1),
(5, 'Figaro', 2, 1),
(6, 'Flying Fifteen', 2, 2),
(7, 'Soling', 2, 2),
(8, 'Star', 2, 2),
(9, 'Tempest', 2, 2),
(10, 'Yngling', 2, 2),
(11, '55', 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `commissaire`
--

CREATE TABLE `commissaire` (
  `id_commissaire` int(11) NOT NULL,
  `id_personne` int(11) DEFAULT NULL,
  `comite` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commissaire`
--

INSERT INTO `commissaire` (`id_commissaire`, `id_personne`, `comite`) VALUES
(1, 3, 'comite de bretagne'),
(2, 10, 'comite de moselle'),
(3, 11, 'comite de gironde');

-- --------------------------------------------------------

--
-- Table structure for table `concurrent`
--

CREATE TABLE `concurrent` (
  `id_concurrent` int(11) NOT NULL,
  `id_personne` int(11) DEFAULT NULL,
  `id_voilier` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `concurrent`
--

INSERT INTO `concurrent` (`id_concurrent`, `id_personne`, `id_voilier`) VALUES
(3, 1, 1),
(4, 2, 1),
(5, 5, 1),
(6, 6, 1),
(7, 8, 2),
(8, 9, 2);

-- --------------------------------------------------------

--
-- Table structure for table `PARTICIPER`
--

CREATE TABLE `PARTICIPER` (
  `point` int(11) NOT NULL,
  `temps_reel` time DEFAULT NULL,
  `id_voilier` int(11) NOT NULL,
  `id_regate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PARTICIPER`
--

INSERT INTO `PARTICIPER` (`point`, `temps_reel`, `id_voilier`, `id_regate`) VALUES
(1, '06:00:00', 1, 1),
(2, '07:00:00', 2, 1);

--
-- Triggers `PARTICIPER`
--
DELIMITER $$
CREATE TRIGGER `PARTICIPER_BEFORE_UPDATE` BEFORE UPDATE ON `PARTICIPER` FOR EACH ROW BEGIN
DECLARE nb_voilier INT;
SELECT COUNT(id_voilier) INTO nb_voilier FROM PARTICIPER WHERE id_regate=NEW.id_regate;
IF(NEW.point > nb_voilier) THEN
signal sqlstate '45000' set message_text = 'nbr pts > nbre joueurs';
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `personne`
--

CREATE TABLE `personne` (
  `id_personne` int(11) NOT NULL,
  `nom_personne` varchar(40) DEFAULT NULL,
  `prenom_personne` varchar(40) DEFAULT NULL,
  `age_personne` int(11) DEFAULT NULL,
  `num_licence` int(11) DEFAULT NULL,
  `nom_club` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `personne`
--

INSERT INTO `personne` (`id_personne`, `nom_personne`, `prenom_personne`, `age_personne`, `num_licence`, `nom_club`) VALUES
(1, 'Lopez', 'Richard', 30, 54, 'YC St Pol'),
(2, 'Balcon', 'Yoann', 31, 78, 'YC Brest'),
(3, 'Fourteau', 'Lucas', 28, 92, 'YC Lorient'),
(4, 'Callec', 'Yoann', 25, 24, 'YC Morlaix'),
(5, 'Tallegas', 'Erwan', 26, 84, 'YC ASSM'),
(6, 'Glaziou', 'Antoine', 22, 56, 'YC Tours'),
(7, 'Cocher', 'Mathieu', 27, 84, 'YC Carantec'),
(8, 'Oreal', 'Alexandre', 29, 41, 'YC St Seve'),
(9, 'Dilasser', 'Manu', 26, 81, 'YC Plouigneau'),
(10, 'Helary', 'Romain', 22, 91, 'YC St Eloi'),
(11, 'Horellou', 'Ghislain', 27, 48, 'YC Penze'),
(12, 'Briand', 'Marie', 22, 71, 'YC Lesigny');

-- --------------------------------------------------------

--
-- Table structure for table `proprietaire`
--

CREATE TABLE `proprietaire` (
  `id_proprietaire` int(11) NOT NULL,
  `id_personne` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proprietaire`
--

INSERT INTO `proprietaire` (`id_proprietaire`, `id_personne`) VALUES
(1, 4),
(2, 7),
(3, 12);

-- --------------------------------------------------------

--
-- Table structure for table `regate`
--

CREATE TABLE `regate` (
  `id_regate` int(11) NOT NULL,
  `nom_regate` varchar(50) DEFAULT NULL,
  `num_regate` int(11) DEFAULT NULL,
  `date_regate` date DEFAULT NULL,
  `id_challenge` int(11) DEFAULT NULL,
  `id_commissaire` int(11) DEFAULT NULL,
  `distance` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `regate`
--

INSERT INTO `regate` (`id_regate`, `nom_regate`, `num_regate`, `date_regate`, `id_challenge`, `id_commissaire`, `distance`) VALUES
(1, 'La traversee', 1, '2017-01-15', 1, 1, 1200),
(2, 'Voyage en eaux profondes', 2, '2017-05-31', 2, 2, 450),
(3, 'La ruee vers l\'eau', 3, '2017-07-30', 2, 3, 700),
(4, 'transat', 4, '2017-03-14', 1, 3, 700),
(5, 'tour du monde', 5, '2016-03-22', 1, 1, 1100),
(11, 'test_supp', 45, '2017-02-21', 1, 2, 180);

--
-- Triggers `regate`
--
DELIMITER $$
CREATE TRIGGER `regate_BEFORE_DELETE` BEFORE DELETE ON `regate` FOR EACH ROW BEGIN
DECLARE v_date_fin DATE;

SELECT date_fin INTO v_date_fin FROM challenge WHERE id_challenge=OLD.id_challenge;
	IF(CURDATE() < v_date_fin)
    THEN signal sqlstate '45000' set message_text = 'la regate est toujours en cours';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `regate_BEFORE_INSERT` BEFORE INSERT ON `regate` FOR EACH ROW BEGIN
DECLARE v_date_debut DATE;
DECLARE v_date_fin DATE;
SELECT date_debut INTO v_date_debut FROM challenge WHERE id_challenge=NEW.id_challenge;
SELECT date_fin INTO v_date_fin FROM challenge WHERE id_challenge=NEW.id_challenge;
   IF (NEW.date_regate < v_date_debut OR NEW.date_regate > v_date_fin) THEN 
		signal sqlstate '45000' set message_text = 'la date est incorrecte', MYSQL_ERRNO = 45000;
	END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `serie`
--

CREATE TABLE `serie` (
  `id_serie` int(11) NOT NULL,
  `nom_serie` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `serie`
--

INSERT INTO `serie` (`id_serie`, `nom_serie`) VALUES
(1, 'Habitables'),
(2, 'Quillards de sport');

-- --------------------------------------------------------

--
-- Table structure for table `skipper`
--

CREATE TABLE `skipper` (
  `id_skipper` int(11) NOT NULL,
  `num_skipper` int(11) DEFAULT NULL,
  `id_personne` int(11) DEFAULT NULL,
  `id_voilier` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `skipper`
--

INSERT INTO `skipper` (`id_skipper`, `num_skipper`, `id_personne`, `id_voilier`) VALUES
(1, 18, 1, 1),
(2, 83, 9, 2);

-- --------------------------------------------------------

--
-- Table structure for table `voilier`
--

CREATE TABLE `voilier` (
  `id_voilier` int(11) NOT NULL,
  `nom_voilier` varchar(50) DEFAULT NULL,
  `num_voile` int(11) DEFAULT NULL,
  `id_proprietaire` int(11) DEFAULT NULL,
  `id_classe` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `voilier`
--

INSERT INTO `voilier` (`id_voilier`, `nom_voilier`, `num_voile`, `id_proprietaire`, `id_classe`) VALUES
(1, 'La barque', 45, 1, 4),
(2, 'Le godzilla', 13, 2, 7),
(3, 'le titanic', 8, 3, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `challenge`
--
ALTER TABLE `challenge`
  ADD PRIMARY KEY (`id_challenge`);

--
-- Indexes for table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`id_classe`),
  ADD KEY `FK_classe_id_serie` (`id_serie`);

--
-- Indexes for table `commissaire`
--
ALTER TABLE `commissaire`
  ADD PRIMARY KEY (`id_commissaire`),
  ADD KEY `FK_commissaire_id_personne` (`id_personne`);

--
-- Indexes for table `concurrent`
--
ALTER TABLE `concurrent`
  ADD PRIMARY KEY (`id_concurrent`),
  ADD KEY `FK_concurrent_id_personne` (`id_personne`),
  ADD KEY `FK_concurrent_id_voilier` (`id_voilier`);

--
-- Indexes for table `PARTICIPER`
--
ALTER TABLE `PARTICIPER`
  ADD PRIMARY KEY (`id_voilier`,`id_regate`),
  ADD KEY `FK_PARTICIPER_id_regate` (`id_regate`);

--
-- Indexes for table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id_personne`);

--
-- Indexes for table `proprietaire`
--
ALTER TABLE `proprietaire`
  ADD PRIMARY KEY (`id_proprietaire`),
  ADD KEY `FK_proprietaire_id_personne` (`id_personne`);

--
-- Indexes for table `regate`
--
ALTER TABLE `regate`
  ADD PRIMARY KEY (`id_regate`),
  ADD KEY `FK_regate_id_challenge` (`id_challenge`),
  ADD KEY `FK_regate_id_commissaire` (`id_commissaire`);

--
-- Indexes for table `serie`
--
ALTER TABLE `serie`
  ADD PRIMARY KEY (`id_serie`);

--
-- Indexes for table `skipper`
--
ALTER TABLE `skipper`
  ADD PRIMARY KEY (`id_skipper`),
  ADD KEY `FK_skipper_id_personne` (`id_personne`),
  ADD KEY `FK_skipper_id_voilier` (`id_voilier`);

--
-- Indexes for table `voilier`
--
ALTER TABLE `voilier`
  ADD PRIMARY KEY (`id_voilier`),
  ADD KEY `FK_voilier_id_proprietaire` (`id_proprietaire`),
  ADD KEY `FK_voilier_id_classe` (`id_classe`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `challenge`
--
ALTER TABLE `challenge`
  MODIFY `id_challenge` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `classe`
--
ALTER TABLE `classe`
  MODIFY `id_classe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `commissaire`
--
ALTER TABLE `commissaire`
  MODIFY `id_commissaire` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `concurrent`
--
ALTER TABLE `concurrent`
  MODIFY `id_concurrent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `personne`
--
ALTER TABLE `personne`
  MODIFY `id_personne` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `proprietaire`
--
ALTER TABLE `proprietaire`
  MODIFY `id_proprietaire` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `regate`
--
ALTER TABLE `regate`
  MODIFY `id_regate` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `serie`
--
ALTER TABLE `serie`
  MODIFY `id_serie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `skipper`
--
ALTER TABLE `skipper`
  MODIFY `id_skipper` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `voilier`
--
ALTER TABLE `voilier`
  MODIFY `id_voilier` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `classe`
--
ALTER TABLE `classe`
  ADD CONSTRAINT `FK_classe_id_serie` FOREIGN KEY (`id_serie`) REFERENCES `serie` (`id_serie`);

--
-- Constraints for table `commissaire`
--
ALTER TABLE `commissaire`
  ADD CONSTRAINT `FK_commissaire_id_personne` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id_personne`);

--
-- Constraints for table `concurrent`
--
ALTER TABLE `concurrent`
  ADD CONSTRAINT `FK_concurrent_id_personne` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id_personne`),
  ADD CONSTRAINT `FK_concurrent_id_voilier` FOREIGN KEY (`id_voilier`) REFERENCES `voilier` (`id_voilier`);

--
-- Constraints for table `PARTICIPER`
--
ALTER TABLE `PARTICIPER`
  ADD CONSTRAINT `FK_PARTICIPER_id_regate` FOREIGN KEY (`id_regate`) REFERENCES `regate` (`id_regate`),
  ADD CONSTRAINT `FK_PARTICIPER_id_voilier` FOREIGN KEY (`id_voilier`) REFERENCES `voilier` (`id_voilier`);

--
-- Constraints for table `proprietaire`
--
ALTER TABLE `proprietaire`
  ADD CONSTRAINT `FK_proprietaire_id_personne` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id_personne`);

--
-- Constraints for table `regate`
--
ALTER TABLE `regate`
  ADD CONSTRAINT `FK_regate_id_challenge` FOREIGN KEY (`id_challenge`) REFERENCES `challenge` (`id_challenge`),
  ADD CONSTRAINT `FK_regate_id_commissaire` FOREIGN KEY (`id_commissaire`) REFERENCES `commissaire` (`id_commissaire`);

--
-- Constraints for table `skipper`
--
ALTER TABLE `skipper`
  ADD CONSTRAINT `FK_skipper_id_personne` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id_personne`),
  ADD CONSTRAINT `FK_skipper_id_voilier` FOREIGN KEY (`id_voilier`) REFERENCES `voilier` (`id_voilier`);

--
-- Constraints for table `voilier`
--
ALTER TABLE `voilier`
  ADD CONSTRAINT `FK_voilier_id_classe` FOREIGN KEY (`id_classe`) REFERENCES `classe` (`id_classe`),
  ADD CONSTRAINT `FK_voilier_id_proprietaire` FOREIGN KEY (`id_proprietaire`) REFERENCES `proprietaire` (`id_proprietaire`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
