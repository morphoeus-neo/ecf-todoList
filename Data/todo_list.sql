-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 27 Octobre 2017 à 12:19
-- Version du serveur :  10.1.10-MariaDB
-- Version de PHP :  7.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `todo`
--

-- --------------------------------------------------------

--
-- Structure de la table `todo_list`
--

CREATE TABLE `todo_list` (
  `todo_id` int(10) UNSIGNED NOT NULL,
  `todo_task` varchar(200) NOT NULL,
  `todo_executed` tinyint(1) DEFAULT NULL,
  `todo_finish` tinyint(1) DEFAULT NULL,
  `todo_id_categorie` int(11) NOT NULL,
  `todo_urgent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `todo_list`
--

INSERT INTO `todo_list` (`todo_id`, `todo_task`, `todo_executed`, `todo_finish`, `todo_id_categorie`, `todo_urgent`) VALUES
(1, 'sdfgbsgfb', 0, NULL, 6, 0);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `todo_list`
--
ALTER TABLE `todo_list`
  ADD PRIMARY KEY (`todo_id`),
  ADD UNIQUE KEY `id` (`todo_id`),
  ADD KEY `todo_id_categorie` (`todo_id_categorie`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `todo_list`
--
ALTER TABLE `todo_list`
  MODIFY `todo_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `todo_list`
--
ALTER TABLE `todo_list`
  ADD CONSTRAINT `delCategorie` FOREIGN KEY (`todo_id`) REFERENCES `categorie` (`categorie_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
