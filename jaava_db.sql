-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 18 mai 2024 à 01:02
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `jaava_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `booking`
--

CREATE TABLE `booking` (
  `id` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `booking_date` date DEFAULT NULL,
  `description` text DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `statut` enum('washed','pending') DEFAULT 'pending',
  `service_id` int(11) NOT NULL,
  `Matricule_vehicule` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `booking`
--

INSERT INTO `booking` (`id`, `client_id`, `employee_id`, `booking_date`, `description`, `price`, `statut`, `service_id`, `Matricule_vehicule`) VALUES
(55, 7, 2, '2024-05-17', 'KLZ?D LAZ/D?AZMLD?AZJ', 1, 'washed', 6, '354R3'),
(56, 7, 1, '2024-05-17', 'POZMEJF?JZELNZEKOLMf,ojlfnezKL', 6000, 'pending', 6, '43534'),
(57, 6, 4, '2024-05-17', 'EZADAZM?Fdpùmle fkùze kln', 3000, 'pending', 8, 'FZE32'),
(58, 8, 2, '2024-05-17', 'welcome zekdmlze', 1333, 'pending', 3, '52353D'),
(60, 7, 10, '2024-05-17', 'AZERTYUI123456789ZSDFGHJK', 19993, 'pending', 7, '34ZESD');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `deleted_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `prenom`, `nom`, `email`, `telephone`, `description`, `deleted_at`) VALUES
(6, 'yass ', 'test_test', 'test@gmail.com', '0654334564', 'AZLKD?AZKL', NULL),
(7, 'hamzaa ', 'test', 'test@gmail.com', '0654332342', 'ZEJFKNZEOKLFJ?APOZML', NULL),
(8, 'Mohamed', 'med123', 'med@gmail.com', '0543232123', 'POEZJF?ZEKJFN?Z.AEMLFK?L', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `fname` varchar(100) DEFAULT NULL,
  `username` varchar(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salary` varchar(159) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `employee`
--

INSERT INTO `employee` (`id`, `fname`, `username`, `password`, `salary`) VALUES
(1, 'Alice ', '111-222-333', '789 Oak St, Village, Country', '55000.0'),
(2, 'Jane Smith', '987-654-321', '456 Elm St, Town, Country', '60000.00'),
(4, 'Bob Williams', '444-555-666', '321 Pine St, Hamlet, Country', '52000.00'),
(7, 'yassine', 'yass12', '1234', '2000.0'),
(8, 'test', 'test1', '12345', '100000.0'),
(10, 'mounir mounir', 'mon21', '123456', '1277.0');

-- --------------------------------------------------------

--
-- Structure de la table `services`
--

CREATE TABLE `services` (
  `id` int(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `price` decimal(65,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `services`
--

INSERT INTO `services` (`id`, `name`, `description`, `price`) VALUES
(3, 'water mark ', 'hdahjdhaj', '300'),
(6, 'full wash service', 'hajhdjahdjkhajkda', '350'),
(7, 'headlight cleaning ', 'aaaaaaaaaaaa', '12000'),
(8, 'seat dry washing', 'aaaaaae', '6000'),
(10, 'Door panel cleaning	', 'doooooooooor', '70000');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(15) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `full_name`, `email`, `password`) VALUES
(1, 'ha', 'ha', '123');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `fk_service_id` (`service_id`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `booking`
--
ALTER TABLE `booking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `services`
--
ALTER TABLE `services`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `fk_service_id` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
