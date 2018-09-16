-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 15, 2018 at 05:50 AM
-- Server version: 5.7.23-0ubuntu0.16.04.1
-- PHP Version: 7.0.30-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `opensrp`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_anc_clients`
--

CREATE TABLE `tbl_anc_clients` (
  `clients_id` bigint(20) NOT NULL,
  `age_below_20_years` tinyint(1) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `client_type` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_of_birth` datetime DEFAULT NULL,
  `edd` datetime DEFAULT NULL,
  `family_planning_technique_id` int(11) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gestational_age_below_20` tinyint(1) DEFAULT NULL,
  `gravida` int(11) DEFAULT NULL,
  `height_below_average` tinyint(1) DEFAULT NULL,
  `history_of_abortion` tinyint(1) DEFAULT NULL,
  `history_of_postmartum_haemorrhage` tinyint(1) DEFAULT NULL,
  `history_of_retained_placenta` tinyint(1) DEFAULT NULL,
  `history_of_still_births` tinyint(1) DEFAULT NULL,
  `last_child_birth_status` int(11) DEFAULT NULL,
  `last_child_birth_year` int(11) DEFAULT NULL,
  `last_pregnancy_over_10_years` tinyint(1) DEFAULT NULL,
  `level_of_education` int(11) DEFAULT NULL,
  `lmnp_date` datetime DEFAULT NULL,
  `map_cue` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `para` int(11) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `pmtct_status` int(11) DEFAULT NULL,
  `pregnancy_above_35_years` tinyint(1) DEFAULT NULL,
  `spouse_name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `use_of_family_planning_techniques` tinyint(1) DEFAULT NULL,
  `village` varchar(255) DEFAULT NULL,
  `ward` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_anc_clients`
--

INSERT INTO `tbl_anc_clients` (`clients_id`, `age_below_20_years`, `card_number`, `client_type`, `created_at`, `date_of_birth`, `edd`, `family_planning_technique_id`, `first_name`, `gestational_age_below_20`, `gravida`, `height_below_average`, `history_of_abortion`, `history_of_postmartum_haemorrhage`, `history_of_retained_placenta`, `history_of_still_births`, `last_child_birth_status`, `last_child_birth_year`, `last_pregnancy_over_10_years`, `level_of_education`, `lmnp_date`, `map_cue`, `middle_name`, `para`, `phone_number`, `pmtct_status`, `pregnancy_above_35_years`, `spouse_name`, `surname`, `updated_at`, `use_of_family_planning_techniques`, `village`, `ward`) VALUES
(1, 1, '112', 1, '2018-09-15 05:23:50', '2008-09-01 00:00:00', '2018-09-01 00:00:00', 1, 'test', 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 2, '2018-09-01 00:00:00', 'test', 'test', 0, '0714417593', 0, 0, 'John Doe', 'Doe', '2018-09-15 05:23:50', 0, 'Test village', 'Test ward');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_client_appointments`
--

CREATE TABLE `tbl_client_appointments` (
  `appointment_date` datetime NOT NULL,
  `appointment_id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_cancelled` tinyint(1) DEFAULT NULL,
  `row_version` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `visit_umber` int(11) DEFAULT NULL,
  `health_facility_client_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_client_referral`
--

CREATE TABLE `tbl_client_referral` (
  `referral_id` bigint(20) NOT NULL,
  `anc_client_id` bigint(20) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `facility_id` varchar(255) DEFAULT NULL,
  `from_facility_id` varchar(255) DEFAULT NULL,
  `instance_id` varchar(255) DEFAULT NULL,
  `other_notes` varchar(255) DEFAULT NULL,
  `referral_date` datetime DEFAULT NULL,
  `referral_feedback` varchar(255) DEFAULT NULL,
  `referral_reason` varchar(255) DEFAULT NULL,
  `referral_status` int(11) DEFAULT NULL,
  `referral_type` bigint(20) DEFAULT NULL,
  `referral_uuid` varchar(255) DEFAULT NULL,
  `service_provider_uuid` varchar(255) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_health_facilities`
--

CREATE TABLE `tbl_health_facilities` (
  `_id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `facility_name` varchar(255) DEFAULT NULL,
  `HFR_code` varchar(255) DEFAULT NULL,
  `openmrs_UIID` varchar(255) DEFAULT NULL,
  `parent_openmrs_UIID` varchar(255) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_health_facilities`
--

INSERT INTO `tbl_health_facilities` (`_id`, `created_at`, `facility_name`, `HFR_code`, `openmrs_UIID`, `parent_openmrs_UIID`, `updated_at`) VALUES
(1, '2018-09-13 13:52:56', 'Badi Dispensary', NULL, '021b4910-b682-11e8-8c84-f23c91cc1ef2', '021b4910-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:56'),
(2, '2018-09-13 13:52:56', 'Badugu Dispensary', NULL, '021be20f-b682-11e8-8c84-f23c91cc1ef2', '021be20f-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:56'),
(3, '2018-09-13 13:52:56', 'Banemhi Dispensary', NULL, '021c4410-b682-11e8-8c84-f23c91cc1ef2', '021c4410-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:56'),
(4, '2018-09-13 13:52:56', 'Bariadi Regional Referral', NULL, '021c97ac-b682-11e8-8c84-f23c91cc1ef2', '021c97ac-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:56'),
(5, '2018-09-13 13:52:56', 'Bariadi-Magereza Dispensary', NULL, '021d6b07-b682-11e8-8c84-f23c91cc1ef2', '021d6b07-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:56'),
(6, '2018-09-13 13:52:56', 'Budekwa Dispensary', NULL, '021e1054-b682-11e8-8c84-f23c91cc1ef2', '021e1054-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:56'),
(7, '2018-09-13 13:52:56', 'Bugarama Dispensary', NULL, '021ef487-b682-11e8-8c84-f23c91cc1ef2', '021ef487-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:56'),
(8, '2018-09-13 13:52:56', 'Bukundi Health Center', NULL, '021faaea-b682-11e8-8c84-f23c91cc1ef2', '021faaea-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:56'),
(9, '2018-09-13 13:52:57', 'Bulima Dispensary', NULL, '02204f46-b682-11e8-8c84-f23c91cc1ef2', '02204f46-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(10, '2018-09-13 13:52:57', 'Bulyashi Dispensary', NULL, '0221fcf7-b682-11e8-8c84-f23c91cc1ef2', '0221fcf7-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(11, '2018-09-13 13:52:57', 'Bumera Dispensary', NULL, '02233e5c-b682-11e8-8c84-f23c91cc1ef2', '02233e5c-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(12, '2018-09-13 13:52:57', 'Bunamhala Chuoni Dispensary', NULL, '0223c831-b682-11e8-8c84-f23c91cc1ef2', '0223c831-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(13, '2018-09-13 13:52:57', 'Bunamhala Mbugani Dispensary', NULL, '02248314-b682-11e8-8c84-f23c91cc1ef2', '02248314-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(14, '2018-09-13 13:52:57', 'Bupandagila - SDA Dispensary', NULL, '022530ea-b682-11e8-8c84-f23c91cc1ef2', '022530ea-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(15, '2018-09-13 13:52:57', 'Bushashi Dispensary', NULL, '0225bc31-b682-11e8-8c84-f23c91cc1ef2', '0225bc31-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(16, '2018-09-13 13:52:57', 'Bushigwamhala Dispensary', NULL, '02269414-b682-11e8-8c84-f23c91cc1ef2', '02269414-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(17, '2018-09-13 13:52:57', 'Butuli Dispensary', NULL, '02271ad2-b682-11e8-8c84-f23c91cc1ef2', '02271ad2-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(18, '2018-09-13 13:52:57', 'Byuna Health Center', NULL, '0227fbdf-b682-11e8-8c84-f23c91cc1ef2', '0227fbdf-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(19, '2018-09-13 13:52:57', 'Chambala Dispensary', NULL, '0228b0a7-b682-11e8-8c84-f23c91cc1ef2', '0228b0a7-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(20, '2018-09-13 13:52:57', 'Chamugasa Dispensary', NULL, '02295cbc-b682-11e8-8c84-f23c91cc1ef2', '02295cbc-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(21, '2018-09-13 13:52:57', 'Chinamili Dispensary', NULL, '022a0ea1-b682-11e8-8c84-f23c91cc1ef2', '022a0ea1-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(22, '2018-09-13 13:52:57', 'Ditima Dispensary', NULL, '022ab2d6-b682-11e8-8c84-f23c91cc1ef2', '022ab2d6-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(23, '2018-09-13 13:52:57', 'Dodoma Dispensary', NULL, '022b6033-b682-11e8-8c84-f23c91cc1ef2', '022b6033-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(24, '2018-09-13 13:52:57', 'Dr. Maduhu Dispensary', NULL, '022bf37a-b682-11e8-8c84-f23c91cc1ef2', '022bf37a-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(25, '2018-09-13 13:52:57', 'Dr. Mwijage Clinic', NULL, '022c9f7c-b682-11e8-8c84-f23c91cc1ef2', '022c9f7c-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(26, '2018-09-13 13:52:57', 'Dulung\'wa Dispensary', NULL, '022d72c1-b682-11e8-8c84-f23c91cc1ef2', '022d72c1-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(27, '2018-09-13 13:52:57', 'Dutwa Dispensary', NULL, '022e3c21-b682-11e8-8c84-f23c91cc1ef2', '022e3c21-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(28, '2018-09-13 13:52:57', 'Dutwa - AICT Dispensary', NULL, '022eb872-b682-11e8-8c84-f23c91cc1ef2', '022eb872-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(29, '2018-09-13 13:52:57', 'Gambosi Dispensary', NULL, '022fa1fc-b682-11e8-8c84-f23c91cc1ef2', '022fa1fc-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(30, '2018-09-13 13:52:57', 'Gasuma Dispensary', NULL, '0230ace0-b682-11e8-8c84-f23c91cc1ef2', '0230ace0-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(31, '2018-09-13 13:52:57', 'Gaswa Dispensary', NULL, '02317e8f-b682-11e8-8c84-f23c91cc1ef2', '02317e8f-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(32, '2018-09-13 13:52:57', 'Gibeshi Dispensary', NULL, '02323979-b682-11e8-8c84-f23c91cc1ef2', '02323979-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(33, '2018-09-13 13:52:57', 'Gininiga Dispensary', NULL, '0232cbf0-b682-11e8-8c84-f23c91cc1ef2', '0232cbf0-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(34, '2018-09-13 13:52:57', 'Guduwe Dispensary', NULL, '02334668-b682-11e8-8c84-f23c91cc1ef2', '02334668-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(35, '2018-09-13 13:52:57', 'Gula Dispensary', NULL, '02340337-b682-11e8-8c84-f23c91cc1ef2', '02340337-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(36, '2018-09-13 13:52:57', 'Habiya Dispensary', NULL, '0234b2b5-b682-11e8-8c84-f23c91cc1ef2', '0234b2b5-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(37, '2018-09-13 13:52:57', 'Halawa Dispensary', NULL, '023519bc-b682-11e8-8c84-f23c91cc1ef2', '023519bc-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(38, '2018-09-13 13:52:57', 'Igalukilo Health Center', NULL, '0235ea5e-b682-11e8-8c84-f23c91cc1ef2', '0235ea5e-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(39, '2018-09-13 13:52:57', 'Igegu Dispensary', NULL, '0236c289-b682-11e8-8c84-f23c91cc1ef2', '0236c289-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(40, '2018-09-13 13:52:57', 'Igunya Dispensary', NULL, '02376c72-b682-11e8-8c84-f23c91cc1ef2', '02376c72-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(41, '2018-09-13 13:52:57', 'Ihusi Dispensary', NULL, '023820e0-b682-11e8-8c84-f23c91cc1ef2', '023820e0-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(42, '2018-09-13 13:52:57', 'Ijiha Dispensary', NULL, '0238c5a8-b682-11e8-8c84-f23c91cc1ef2', '0238c5a8-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(43, '2018-09-13 13:52:57', 'Ikindilo Health Center', NULL, '0239829d-b682-11e8-8c84-f23c91cc1ef2', '0239829d-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(44, '2018-09-13 13:52:57', 'Ikulilo Dispensary', NULL, '0239ece8-b682-11e8-8c84-f23c91cc1ef2', '0239ece8-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(45, '2018-09-13 13:52:57', 'Ikungulyabashashi Dispensary', NULL, '023a8f9b-b682-11e8-8c84-f23c91cc1ef2', '023a8f9b-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(46, '2018-09-13 13:52:57', 'Ikungulyambeshi Dispensary', NULL, '023b2470-b682-11e8-8c84-f23c91cc1ef2', '023b2470-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(47, '2018-09-13 13:52:57', 'Ilamata Dispensary', NULL, '023bd458-b682-11e8-8c84-f23c91cc1ef2', '023bd458-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(48, '2018-09-13 13:52:57', 'Imalaseko Dispensary', NULL, '023ca020-b682-11e8-8c84-f23c91cc1ef2', '023ca020-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(49, '2018-09-13 13:52:57', 'Ipililo Dispensary', NULL, '023d658d-b682-11e8-8c84-f23c91cc1ef2', '023d658d-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(50, '2018-09-13 13:52:57', 'Isanga Dispensary', NULL, '023e4a21-b682-11e8-8c84-f23c91cc1ef2', '023e4a21-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(51, '2018-09-13 13:52:57', 'Isanga Dispensary', NULL, '023f233a-b682-11e8-8c84-f23c91cc1ef2', '023f233a-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:57'),
(52, '2018-09-13 13:52:58', 'Isengwa Dispensary', NULL, '023fced2-b682-11e8-8c84-f23c91cc1ef2', '023fced2-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(53, '2018-09-13 13:52:58', 'Itinje Dispensary', NULL, '024078fa-b682-11e8-8c84-f23c91cc1ef2', '024078fa-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(54, '2018-09-13 13:52:58', 'Jija Dispensary', NULL, '02411bdc-b682-11e8-8c84-f23c91cc1ef2', '02411bdc-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(55, '2018-09-13 13:52:58', 'JINAMO Dispensary', NULL, '0241b55b-b682-11e8-8c84-f23c91cc1ef2', '0241b55b-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(56, '2018-09-13 13:52:58', 'Kabondo Dispensary', NULL, '0242b45d-b682-11e8-8c84-f23c91cc1ef2', '0242b45d-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(57, '2018-09-13 13:52:58', 'Kadoto Dispensary', NULL, '02433162-b682-11e8-8c84-f23c91cc1ef2', '02433162-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(58, '2018-09-13 13:52:58', 'Kalemela Dispensary', NULL, '0243e9de-b682-11e8-8c84-f23c91cc1ef2', '0243e9de-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(59, '2018-09-13 13:52:58', 'Kashishi Dispensary', NULL, '0244b7e4-b682-11e8-8c84-f23c91cc1ef2', '0244b7e4-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(60, '2018-09-13 13:52:58', 'Kasoli Dispensary', NULL, '024588d3-b682-11e8-8c84-f23c91cc1ef2', '024588d3-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(61, '2018-09-13 13:52:58', 'Kidaganda Dispensary', NULL, '024625d8-b682-11e8-8c84-f23c91cc1ef2', '024625d8-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(62, '2018-09-13 13:52:58', 'Kijereshi Dispensary', NULL, '0246b12a-b682-11e8-8c84-f23c91cc1ef2', '0246b12a-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(63, '2018-09-13 13:52:58', 'kilabela Dispensary', NULL, '02477552-b682-11e8-8c84-f23c91cc1ef2', '02477552-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(64, '2018-09-13 13:52:58', 'Kiloleli Health Center', NULL, '024829a8-b682-11e8-8c84-f23c91cc1ef2', '024829a8-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(65, '2018-09-13 13:52:58', 'Kilulu Dispensary', NULL, '0248e4e9-b682-11e8-8c84-f23c91cc1ef2', '0248e4e9-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(66, '2018-09-13 13:52:58', 'Kinamwigulu Dispensary', NULL, '02498b7f-b682-11e8-8c84-f23c91cc1ef2', '02498b7f-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(67, '2018-09-13 13:52:58', 'Kinang\'weli Dispensary', NULL, '024a3fad-b682-11e8-8c84-f23c91cc1ef2', '024a3fad-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(68, '2018-09-13 13:52:58', 'Kisesa Dispensary', NULL, '024b0b25-b682-11e8-8c84-f23c91cc1ef2', '024b0b25-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(69, '2018-09-13 13:52:58', 'Lagangabilili Dispensary', NULL, '024bd5f2-b682-11e8-8c84-f23c91cc1ef2', '024bd5f2-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(70, '2018-09-13 13:52:58', 'Lalago Health Center', NULL, '024c6e4a-b682-11e8-8c84-f23c91cc1ef2', '024c6e4a-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(71, '2018-09-13 13:52:58', 'Lamadi Dispensary', NULL, '024d2445-b682-11e8-8c84-f23c91cc1ef2', '024d2445-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(72, '2018-09-13 13:52:58', 'Lingeka Dispensary', NULL, '024dd16a-b682-11e8-8c84-f23c91cc1ef2', '024dd16a-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(73, '2018-09-13 13:52:58', 'Longalombogo Dispensary', NULL, '024e869f-b682-11e8-8c84-f23c91cc1ef2', '024e869f-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(74, '2018-09-13 13:52:58', 'Longaloniga Dispensary', NULL, '024f33ff-b682-11e8-8c84-f23c91cc1ef2', '024f33ff-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(75, '2018-09-13 13:52:58', 'Lubiga Dispensary', NULL, '0250775d-b682-11e8-8c84-f23c91cc1ef2', '0250775d-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(76, '2018-09-13 13:52:58', 'Luguru Dispensary', NULL, '02514814-b682-11e8-8c84-f23c91cc1ef2', '02514814-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(77, '2018-09-13 13:52:58', 'Luguru UWT Dispensary', NULL, '0251c707-b682-11e8-8c84-f23c91cc1ef2', '0251c707-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(78, '2018-09-13 13:52:58', 'Luguru Wazazi Dispensary', NULL, '0252981c-b682-11e8-8c84-f23c91cc1ef2', '0252981c-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(79, '2018-09-13 13:52:58', 'Lukungu Health Center', NULL, '02536ed5-b682-11e8-8c84-f23c91cc1ef2', '02536ed5-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(80, '2018-09-13 13:52:58', 'Lulayu Dispensary', NULL, '02543975-b682-11e8-8c84-f23c91cc1ef2', '02543975-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(81, '2018-09-13 13:52:58', 'Lutubiga Dispensary', NULL, '02550397-b682-11e8-8c84-f23c91cc1ef2', '02550397-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(82, '2018-09-13 13:52:58', 'Maganzo Health Center', NULL, '0255aba1-b682-11e8-8c84-f23c91cc1ef2', '0255aba1-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(83, '2018-09-13 13:52:58', 'Mahembe Dispensary', NULL, '02564233-b682-11e8-8c84-f23c91cc1ef2', '02564233-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(84, '2018-09-13 13:52:58', 'Majahida AICT Dispensary', NULL, '025747c3-b682-11e8-8c84-f23c91cc1ef2', '025747c3-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(85, '2018-09-13 13:52:58', 'Makao Dispensary', NULL, '0257da09-b682-11e8-8c84-f23c91cc1ef2', '0257da09-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(86, '2018-09-13 13:52:58', 'Malampaka Health Center', NULL, '0258a0f1-b682-11e8-8c84-f23c91cc1ef2', '0258a0f1-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(87, '2018-09-13 13:52:58', 'Malili Dispensary', NULL, '02594ebb-b682-11e8-8c84-f23c91cc1ef2', '02594ebb-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(88, '2018-09-13 13:52:58', 'Malita Dispensary', NULL, '025a2410-b682-11e8-8c84-f23c91cc1ef2', '025a2410-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(89, '2018-09-13 13:52:58', 'Malya Dispensary', NULL, '025ac5b9-b682-11e8-8c84-f23c91cc1ef2', '025ac5b9-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(90, '2018-09-13 13:52:58', 'Masanwa Dispensary', NULL, '025b68f6-b682-11e8-8c84-f23c91cc1ef2', '025b68f6-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(91, '2018-09-13 13:52:58', 'Masela Dispensary', NULL, '025c19e8-b682-11e8-8c84-f23c91cc1ef2', '025c19e8-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(92, '2018-09-13 13:52:58', 'Masewa Dispensary', NULL, '025ca842-b682-11e8-8c84-f23c91cc1ef2', '025ca842-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(93, '2018-09-13 13:52:58', 'Maswa Hospital - District Hospital', NULL, '025d9fbc-b682-11e8-8c84-f23c91cc1ef2', '025d9fbc-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(94, '2018-09-13 13:52:58', 'Maswa Sec Dispensary', NULL, '025e2633-b682-11e8-8c84-f23c91cc1ef2', '025e2633-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(95, '2018-09-13 13:52:58', 'Matale Dispensary', NULL, '025ea8d9-b682-11e8-8c84-f23c91cc1ef2', '025ea8d9-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(96, '2018-09-13 13:52:58', 'Matongo Gerezani Dispensary', NULL, '025f45aa-b682-11e8-8c84-f23c91cc1ef2', '025f45aa-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(97, '2018-09-13 13:52:58', 'Matongo I Dispensary', NULL, '025ffffd-b682-11e8-8c84-f23c91cc1ef2', '025ffffd-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(98, '2018-09-13 13:52:58', 'Mbalagane Dispensary', NULL, '0260aeba-b682-11e8-8c84-f23c91cc1ef2', '0260aeba-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(99, '2018-09-13 13:52:58', 'Mbugayabahya Dispensary', NULL, '02617850-b682-11e8-8c84-f23c91cc1ef2', '02617850-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(100, '2018-09-13 13:52:58', 'Mbushi Dispensary', NULL, '02620ead-b682-11e8-8c84-f23c91cc1ef2', '02620ead-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(101, '2018-09-13 13:52:58', 'Meatu Hospital - District Hospital', NULL, '0262d32f-b682-11e8-8c84-f23c91cc1ef2', '0262d32f-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(102, '2018-09-13 13:52:58', 'Menonite Lamadi Dispensary', NULL, '026606ca-b682-11e8-8c84-f23c91cc1ef2', '026606ca-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(103, '2018-09-13 13:52:58', 'Mhunze Dispensary', NULL, '02682b4c-b682-11e8-8c84-f23c91cc1ef2', '02682b4c-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(104, '2018-09-13 13:52:58', 'Migato Dispensary', NULL, '026c6328-b682-11e8-8c84-f23c91cc1ef2', '026c6328-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(105, '2018-09-13 13:52:58', 'Minyanda Dispensary', NULL, '0273131d-b682-11e8-8c84-f23c91cc1ef2', '0273131d-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(106, '2018-09-13 13:52:58', 'Miswaki Dispensary', NULL, '0274627e-b682-11e8-8c84-f23c91cc1ef2', '0274627e-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(107, '2018-09-13 13:52:58', 'Mitobo Dispensary', NULL, '0275aa86-b682-11e8-8c84-f23c91cc1ef2', '0275aa86-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(108, '2018-09-13 13:52:58', 'Mkula Hospital', NULL, '02776ec6-b682-11e8-8c84-f23c91cc1ef2', '02776ec6-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(109, '2018-09-13 13:52:58', 'Mpindo Dispensary', NULL, '0277d61f-b682-11e8-8c84-f23c91cc1ef2', '0277d61f-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(110, '2018-09-13 13:52:58', 'Mshikamano bakwata Dispensary', NULL, '02781692-b682-11e8-8c84-f23c91cc1ef2', '02781692-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(111, '2018-09-13 13:52:58', 'Muhoja KMT Dispensary', NULL, '02785a51-b682-11e8-8c84-f23c91cc1ef2', '02785a51-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(112, '2018-09-13 13:52:58', 'Mungukwanza Maternity Home', NULL, '02788cc4-b682-11e8-8c84-f23c91cc1ef2', '02788cc4-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(113, '2018-09-13 13:52:58', 'Muungano Health Center', NULL, '0278f82d-b682-11e8-8c84-f23c91cc1ef2', '0278f82d-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(114, '2018-09-13 13:52:58', 'Mwabagalu Dispensary', NULL, '027a2610-b682-11e8-8c84-f23c91cc1ef2', '027a2610-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(115, '2018-09-13 13:52:58', 'Mwabagalu Dispensary', NULL, '027b52ad-b682-11e8-8c84-f23c91cc1ef2', '027b52ad-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(116, '2018-09-13 13:52:58', 'Mwabagimu Dispensary', NULL, '027c5a77-b682-11e8-8c84-f23c91cc1ef2', '027c5a77-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(117, '2018-09-13 13:52:58', 'Mwabalebi Dispensary', NULL, '027d2e64-b682-11e8-8c84-f23c91cc1ef2', '027d2e64-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(118, '2018-09-13 13:52:58', 'Mwabayanda Dispensary', NULL, '027dd0c2-b682-11e8-8c84-f23c91cc1ef2', '027dd0c2-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(119, '2018-09-13 13:52:58', 'Mwabomba Dispensary', NULL, '027e6fde-b682-11e8-8c84-f23c91cc1ef2', '027e6fde-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(120, '2018-09-13 13:52:58', 'Mwabuki Dispensary', NULL, '027f0c6f-b682-11e8-8c84-f23c91cc1ef2', '027f0c6f-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(121, '2018-09-13 13:52:58', 'Mwabulimbu Dispensary', NULL, '027fa96d-b682-11e8-8c84-f23c91cc1ef2', '027fa96d-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(122, '2018-09-13 13:52:58', 'Mwabulutagu Dispensary', NULL, '028048c4-b682-11e8-8c84-f23c91cc1ef2', '028048c4-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(123, '2018-09-13 13:52:58', 'Mwabuma Dispensary', NULL, '0280d73b-b682-11e8-8c84-f23c91cc1ef2', '0280d73b-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:58'),
(124, '2018-09-13 13:52:59', 'Mwabusalu Dispensary', NULL, '02816af5-b682-11e8-8c84-f23c91cc1ef2', '02816af5-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(125, '2018-09-13 13:52:59', 'Mwabuzo Dispensary', NULL, '028397f8-b682-11e8-8c84-f23c91cc1ef2', '028397f8-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(126, '2018-09-13 13:52:59', 'Mwafuguji Dispensary', NULL, '02846064-b682-11e8-8c84-f23c91cc1ef2', '02846064-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(127, '2018-09-13 13:52:59', 'Mwagala Dispensary', NULL, '02921485-b682-11e8-8c84-f23c91cc1ef2', '02921485-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(128, '2018-09-13 13:52:59', 'Mwageni Dispensary', NULL, '0292b7af-b682-11e8-8c84-f23c91cc1ef2', '0292b7af-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(129, '2018-09-13 13:52:59', 'Mwakaluba Dispensary', NULL, '029323c3-b682-11e8-8c84-f23c91cc1ef2', '029323c3-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(130, '2018-09-13 13:52:59', 'Mwakibuga Dispensary', NULL, '02938900-b682-11e8-8c84-f23c91cc1ef2', '02938900-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(131, '2018-09-13 13:52:59', 'Mwakipopo Dispensary', NULL, '02945107-b682-11e8-8c84-f23c91cc1ef2', '02945107-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(132, '2018-09-13 13:52:59', 'Mwamagigisi Dispensary', NULL, '0297198f-b682-11e8-8c84-f23c91cc1ef2', '0297198f-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(133, '2018-09-13 13:52:59', 'Mwamalole Dispensary', NULL, '029b124c-b682-11e8-8c84-f23c91cc1ef2', '029b124c-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(134, '2018-09-13 13:52:59', 'Mwamanenge Dispensary', NULL, '029bdced-b682-11e8-8c84-f23c91cc1ef2', '029bdced-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(135, '2018-09-13 13:52:59', 'Mwamanimba Dispensary', NULL, '029c5d9c-b682-11e8-8c84-f23c91cc1ef2', '029c5d9c-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(136, '2018-09-13 13:52:59', 'Mwamanongu Dispensary', NULL, '029cc93a-b682-11e8-8c84-f23c91cc1ef2', '029cc93a-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(137, '2018-09-13 13:52:59', 'Mwamanoni Dispensary', NULL, '029fd647-b682-11e8-8c84-f23c91cc1ef2', '029fd647-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(138, '2018-09-13 13:52:59', 'Mwamapalala Rc Dispensary', NULL, '02a1b366-b682-11e8-8c84-f23c91cc1ef2', '02a1b366-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(139, '2018-09-13 13:52:59', 'Mwamatiga Dispensary', NULL, '02a38b59-b682-11e8-8c84-f23c91cc1ef2', '02a38b59-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(140, '2018-09-13 13:52:59', 'Mwambiti Dispensary', NULL, '02a48990-b682-11e8-8c84-f23c91cc1ef2', '02a48990-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(141, '2018-09-13 13:52:59', 'Mwamigongwa Dispensary', NULL, '02a6e56a-b682-11e8-8c84-f23c91cc1ef2', '02a6e56a-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(142, '2018-09-13 13:52:59', 'Mwamishali Dispensary', NULL, '02aaafee-b682-11e8-8c84-f23c91cc1ef2', '02aaafee-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(143, '2018-09-13 13:52:59', 'Mwamitumai Dispensary', NULL, '02aef408-b682-11e8-8c84-f23c91cc1ef2', '02aef408-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(144, '2018-09-13 13:52:59', 'Mwamjulila Dispensary', NULL, '02afbf10-b682-11e8-8c84-f23c91cc1ef2', '02afbf10-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(145, '2018-09-13 13:52:59', 'Mwamlapa Dispensary', NULL, '02b49e72-b682-11e8-8c84-f23c91cc1ef2', '02b49e72-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(146, '2018-09-13 13:52:59', 'Mwanaleguma Dispensary', NULL, '02b7ae0d-b682-11e8-8c84-f23c91cc1ef2', '02b7ae0d-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(147, '2018-09-13 13:52:59', 'Mwanangi Disp Dispensary', NULL, '02b9e74f-b682-11e8-8c84-f23c91cc1ef2', '02b9e74f-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(148, '2018-09-13 13:52:59', 'Mwandoya Health Center', NULL, '02be2750-b682-11e8-8c84-f23c91cc1ef2', '02be2750-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(149, '2018-09-13 13:52:59', 'Mwandu Itinje Dispensary', NULL, '02c31d61-b682-11e8-8c84-f23c91cc1ef2', '02c31d61-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(150, '2018-09-13 13:52:59', 'Mwang\'honoli Dispensary', NULL, '02c5dc13-b682-11e8-8c84-f23c91cc1ef2', '02c5dc13-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(151, '2018-09-13 13:52:59', 'Mwangikulu Dispensary', NULL, '02c83763-b682-11e8-8c84-f23c91cc1ef2', '02c83763-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(152, '2018-09-13 13:52:59', 'Mwangudo Dispensary', NULL, '02cb172c-b682-11e8-8c84-f23c91cc1ef2', '02cb172c-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(153, '2018-09-13 13:52:59', 'Mwanhale Dispensary', NULL, '02ce6656-b682-11e8-8c84-f23c91cc1ef2', '02ce6656-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(154, '2018-09-13 13:52:59', 'Mwanjoro Dispensary', NULL, '02d09808-b682-11e8-8c84-f23c91cc1ef2', '02d09808-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(155, '2018-09-13 13:52:59', 'Mwanunui Dispensary', NULL, '02d13baa-b682-11e8-8c84-f23c91cc1ef2', '02d13baa-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(156, '2018-09-13 13:52:59', 'Mwasamba Dispensary', NULL, '02d8e9a7-b682-11e8-8c84-f23c91cc1ef2', '02d8e9a7-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(157, '2018-09-13 13:52:59', 'Mwasayi Health Center', NULL, '02d99a81-b682-11e8-8c84-f23c91cc1ef2', '02d99a81-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(158, '2018-09-13 13:52:59', 'Mwashata Dispensary', NULL, '02dad953-b682-11e8-8c84-f23c91cc1ef2', '02dad953-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(159, '2018-09-13 13:52:59', 'Mwashegeshi Dispensary', NULL, '02dc02de-b682-11e8-8c84-f23c91cc1ef2', '02dc02de-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(160, '2018-09-13 13:52:59', 'Mwasinasi Dispensary', NULL, '02dd2800-b682-11e8-8c84-f23c91cc1ef2', '02dd2800-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(161, '2018-09-13 13:52:59', 'Mwasubuya Dispensary', NULL, '02de36de-b682-11e8-8c84-f23c91cc1ef2', '02de36de-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(162, '2018-09-13 13:52:59', 'Mwaswale Dispensary', NULL, '02df1883-b682-11e8-8c84-f23c91cc1ef2', '02df1883-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(163, '2018-09-13 13:52:59', 'Mwaukoli Dispensary', NULL, '02dfa94f-b682-11e8-8c84-f23c91cc1ef2', '02dfa94f-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(164, '2018-09-13 13:52:59', 'Nanga Dispensary', NULL, '02e0663f-b682-11e8-8c84-f23c91cc1ef2', '02e0663f-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(165, '2018-09-13 13:52:59', 'Nangale Dispensary', NULL, '02e11a71-b682-11e8-8c84-f23c91cc1ef2', '02e11a71-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(166, '2018-09-13 13:52:59', 'Nassa Health center Health Center', NULL, '02e1fb32-b682-11e8-8c84-f23c91cc1ef2', '02e1fb32-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(167, '2018-09-13 13:52:59', 'New stand Bakwata Dispensary', NULL, '02e2defc-b682-11e8-8c84-f23c91cc1ef2', '02e2defc-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(168, '2018-09-13 13:52:59', 'Ng\'alita Dispensary', NULL, '02e9f890-b682-11e8-8c84-f23c91cc1ef2', '02e9f890-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(169, '2018-09-13 13:52:59', 'Ng\'hoboko Dispensary', NULL, '02ec746f-b682-11e8-8c84-f23c91cc1ef2', '02ec746f-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(170, '2018-09-13 13:52:59', 'Ngasamo Dispensary', NULL, '02f37720-b682-11e8-8c84-f23c91cc1ef2', '02f37720-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(171, '2018-09-13 13:52:59', 'Ngeme Dispensary', NULL, '02f4435b-b682-11e8-8c84-f23c91cc1ef2', '02f4435b-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(172, '2018-09-13 13:52:59', 'Nguliguli Dispensary', NULL, '02f50cfa-b682-11e8-8c84-f23c91cc1ef2', '02f50cfa-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(173, '2018-09-13 13:52:59', 'Ngulyati Health Center', NULL, '02f5ced5-b682-11e8-8c84-f23c91cc1ef2', '02f5ced5-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(174, '2018-09-13 13:52:59', 'Nkololo Dispensary', NULL, '02f67b97-b682-11e8-8c84-f23c91cc1ef2', '02f67b97-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(175, '2018-09-13 13:52:59', 'Nkoma Dispensary', NULL, '02fd9fcc-b682-11e8-8c84-f23c91cc1ef2', '02fd9fcc-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(176, '2018-09-13 13:52:59', 'Nkoma Health Center', NULL, '0302ce9b-b682-11e8-8c84-f23c91cc1ef2', '0302ce9b-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(177, '2018-09-13 13:52:59', 'Nkuyu Dispensary', NULL, '03095ecb-b682-11e8-8c84-f23c91cc1ef2', '03095ecb-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(178, '2018-09-13 13:52:59', 'Nyaluhande Dispensary', NULL, '030e1c91-b682-11e8-8c84-f23c91cc1ef2', '030e1c91-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(179, '2018-09-13 13:52:59', 'Nyamalapa Dispensary', NULL, '0317a726-b682-11e8-8c84-f23c91cc1ef2', '0317a726-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(180, '2018-09-13 13:52:59', 'Nyamikoma Dispensary', NULL, '031fa634-b682-11e8-8c84-f23c91cc1ef2', '031fa634-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(181, '2018-09-13 13:52:59', 'Nyamikoma Dispensary', NULL, '03216b7b-b682-11e8-8c84-f23c91cc1ef2', '03216b7b-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(182, '2018-09-13 13:52:59', 'Nyamswa Dispensary', NULL, '0325ab9e-b682-11e8-8c84-f23c91cc1ef2', '0325ab9e-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(183, '2018-09-13 13:52:59', 'Nyangokolwa Dispensary', NULL, '0326779e-b682-11e8-8c84-f23c91cc1ef2', '0326779e-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(184, '2018-09-13 13:52:59', 'Nyanza Dispensary', NULL, '03274289-b682-11e8-8c84-f23c91cc1ef2', '03274289-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(185, '2018-09-13 13:52:59', 'Nyashimba Dispensary', NULL, '032834ea-b682-11e8-8c84-f23c91cc1ef2', '032834ea-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(186, '2018-09-13 13:52:59', 'Nyaumata Dispensary', NULL, '03296430-b682-11e8-8c84-f23c91cc1ef2', '03296430-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(187, '2018-09-13 13:52:59', 'Nyawa Dispensary', NULL, '032aba8d-b682-11e8-8c84-f23c91cc1ef2', '032aba8d-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(188, '2018-09-13 13:52:59', 'Old Maswa Dispensary', NULL, '032b8965-b682-11e8-8c84-f23c91cc1ef2', '032b8965-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(189, '2018-09-13 13:52:59', 'Paji Dispensary', NULL, '032c88a4-b682-11e8-8c84-f23c91cc1ef2', '032c88a4-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(190, '2018-09-13 13:52:59', 'RC Mission Dispensary', NULL, '032d78d4-b682-11e8-8c84-f23c91cc1ef2', '032d78d4-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(191, '2018-09-13 13:52:59', 'Sagata Dispensary', NULL, '032e5211-b682-11e8-8c84-f23c91cc1ef2', '032e5211-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(192, '2018-09-13 13:52:59', 'Sakasaka Dispensary', NULL, '03310754-b682-11e8-8c84-f23c91cc1ef2', '03310754-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(193, '2018-09-13 13:52:59', 'Sakwe Dispensary', NULL, '0332ddfc-b682-11e8-8c84-f23c91cc1ef2', '0332ddfc-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(194, '2018-09-13 13:52:59', 'Sangamwalugesha Dispensary', NULL, '0335c911-b682-11e8-8c84-f23c91cc1ef2', '0335c911-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(195, '2018-09-13 13:52:59', 'Sapiwi Dispensary', NULL, '033bf48d-b682-11e8-8c84-f23c91cc1ef2', '033bf48d-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(196, '2018-09-13 13:52:59', 'Sawida Dispensary', NULL, '033ccae7-b682-11e8-8c84-f23c91cc1ef2', '033ccae7-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(197, '2018-09-13 13:52:59', 'Sayusayu Dispensary', NULL, '033f6b45-b682-11e8-8c84-f23c91cc1ef2', '033f6b45-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(198, '2018-09-13 13:52:59', 'Semu Dispensary', NULL, '034657c8-b682-11e8-8c84-f23c91cc1ef2', '034657c8-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(199, '2018-09-13 13:52:59', 'Senani Dispensary', NULL, '03474eca-b682-11e8-8c84-f23c91cc1ef2', '03474eca-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(200, '2018-09-13 13:52:59', 'Seng\'wa Dispensary', NULL, '03482c78-b682-11e8-8c84-f23c91cc1ef2', '03482c78-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(201, '2018-09-13 13:52:59', 'Shigala Dispensary', NULL, '034fbe5a-b682-11e8-8c84-f23c91cc1ef2', '034fbe5a-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(202, '2018-09-13 13:52:59', 'Shinyangamwenge Dispensary', NULL, '035253a8-b682-11e8-8c84-f23c91cc1ef2', '035253a8-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(203, '2018-09-13 13:52:59', 'Shishiyu Dispensary', NULL, '0358f7ca-b682-11e8-8c84-f23c91cc1ef2', '0358f7ca-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(204, '2018-09-13 13:52:59', 'Simiyu Vision Centre Clinic - Eye Clinic', NULL, '035e4a60-b682-11e8-8c84-f23c91cc1ef2', '035e4a60-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(205, '2018-09-13 13:52:59', 'Songambele RC Health Center', NULL, '035f3577-b682-11e8-8c84-f23c91cc1ef2', '035f3577-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(206, '2018-09-13 13:52:59', 'Sulu Dispensary', NULL, '0362574c-b682-11e8-8c84-f23c91cc1ef2', '0362574c-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(207, '2018-09-13 13:52:59', 'Sungu Dispensary', NULL, '03633a9c-b682-11e8-8c84-f23c91cc1ef2', '03633a9c-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(208, '2018-09-13 13:52:59', 'Sunzula Dispensary', NULL, '03646eb1-b682-11e8-8c84-f23c91cc1ef2', '03646eb1-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(209, '2018-09-13 13:52:59', 'Wazazi-Binza Dispensary', NULL, '03653fc2-b682-11e8-8c84-f23c91cc1ef2', '03653fc2-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(210, '2018-09-13 13:52:59', 'Zagayu Health Center', NULL, '036a24a4-b682-11e8-8c84-f23c91cc1ef2', '036a24a4-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(211, '2018-09-13 13:52:59', 'Zanzui Dispensary', NULL, '036fd1a1-b682-11e8-8c84-f23c91cc1ef2', '036fd1a1-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59'),
(212, '2018-09-13 13:52:59', 'Zebeya Dispensary', NULL, '037f6e4b-b682-11e8-8c84-f23c91cc1ef2', '037f6e4b-b682-11e8-8c84-f23c91cc1ef2', '2018-09-13 13:52:59');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_health_facility_clients`
--

CREATE TABLE `tbl_health_facility_clients` (
  `health_facility_client_id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ctc_number` varchar(255) DEFAULT NULL,
  `facility_id` bigint(20) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `client_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_health_facility_clients`
--

INSERT INTO `tbl_health_facility_clients` (`health_facility_client_id`, `created_at`, `ctc_number`, `facility_id`, `updated_at`, `client_id`) VALUES
(1, '2018-09-15 05:24:22', NULL, 1, '2018-09-15 05:24:22', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_indicator_signs`
--

CREATE TABLE `tbl_indicator_signs` (
  `referral_indicator_id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` tinyint(1) DEFAULT NULL,
  `referral_indicator_name` varchar(255) DEFAULT NULL,
  `referral_indicator_name_sw` varchar(255) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_indicator_signs`
--

INSERT INTO `tbl_indicator_signs` (`referral_indicator_id`, `created_at`, `is_active`, `referral_indicator_name`, `referral_indicator_name_sw`, `updated_at`) VALUES
(1, '2018-09-13 14:03:31', 1, 'Condoms', 'Kondomu', '2018-09-13 14:03:31'),
(2, '2018-09-13 14:03:31', 1, 'Emergency Contraceptive Pills', 'Vidonge vya kuzuia mimba', '2018-09-13 14:03:31');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_patient_referral_indicator`
--

CREATE TABLE `tbl_patient_referral_indicator` (
  `patient_referral_indicator_id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` tinyint(1) DEFAULT NULL,
  `referral_id` bigint(20) DEFAULT NULL,
  `referral_service_indicator_id` bigint(20) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_pnc_clients`
--

CREATE TABLE `tbl_pnc_clients` (
  `pnc_clients_id` bigint(20) NOT NULL,
  `apgar_score` int(11) DEFAULT NULL,
  `childs_gender` varchar(255) DEFAULT NULL,
  `childs_weight` double DEFAULT NULL,
  `childs_abnormalites` tinyint(1) DEFAULT NULL,
  `childs_discharge_condition` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_of_admission` datetime DEFAULT NULL,
  `date_of_delivery` datetime DEFAULT NULL,
  `delivery_methods` int(11) DEFAULT NULL,
  `delivery_complications` int(11) DEFAULT NULL,
  `died_within_24_hrs` tinyint(1) DEFAULT NULL,
  `health_facility_client_id` bigint(20) DEFAULT NULL,
  `kuharibika_mimba` tinyint(1) DEFAULT NULL,
  `mothers_discharge_condition` int(11) DEFAULT NULL,
  `types_of_still_birth` int(11) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_push_notifications_users`
--

CREATE TABLE `tbl_push_notifications_users` (
  `_id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `facility_uiid` varchar(255) DEFAULT NULL,
  `google_push_notification_token` varchar(255) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_type` int(11) DEFAULT NULL,
  `user_uiid` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_push_notifications_users`
--

INSERT INTO `tbl_push_notifications_users` (`_id`, `created_at`, `facility_uiid`, `google_push_notification_token`, `updated_at`, `user_type`, `user_uiid`) VALUES
(1, '2018-09-12 08:09:14', 'aff27d58-a15c-49a6-9beb-d30dcfc0c66e', 'cclxVdamw20:APA91bGITKZTGaULy66Mgs93Txi22gfNQDBkyxRLKdlWvxI5wyILA_s3Azw3W-Ja_cXsJQYe-F0kp2_swT7eHhkzIKsbjUcwxoNR6jB9OKendiiTRuXu7ZuxduY8KAQwW6XRrkphAH-x', '2018-09-12 08:09:14', 0, '222ea795-9f1d-4051-8b09-ef08108be9bc'),
(2, '2018-09-12 13:15:50', '008986f5-b682-11e8-8c84-f23c91cc1ef2', 'e60hGQUbMMM:APA91bEcnPYbVPepUwIvcfkife0CN1VzaBb0tkyFaoBql3bLleVYcHfb-TqsmdvnFvoUaf20xCvtUAs3c1xHur9xycgQo51kwkvOZdPVTSqbXvkn9QyheJG4pegnR7XXS0nEeOdX34rn09aaXoy9GQM-l44GjvaRvg', '2018-09-12 13:15:50', 1, '3a211b6d-7254-4ee9-b1d1-d3a79d770b93'),
(3, '2018-09-12 13:42:23', '008986f5-b682-11e8-8c84-f23c91cc1ef2', 'dBmG_NRsXHo:APA91bH6aPZl-36IMYmplUqlAVyRFlDtb1jenTatQlz22T54hS80NiVpwMDm0iOHEGCAzS1Km7lrnpqL3e3lHhn0wqtwlmEHsRIw8YPJSSituWVR5rEEIX-C4embDRJRltqiCpBlhMkH', '2018-09-12 13:42:23', 0, '3a211b6d-7254-4ee9-b1d1-d3a79d770b93'),
(4, '2018-09-13 09:20:30', '008986f5-b682-11e8-8c84-f23c91cc1ef2', 'd1WCgVApdfk:APA91bESuob7eGbSIgxOhnR3w9Diwpn7fWHbXTEGDRiqNVJkQWHaLu5W5H9AkYY2BC0favhTNd-b_AFLpmzAQVBLhlmGvqUh_2uD8GH4FCt073omStYmHrY1sSHXJOuU4PnlO5uYindR', '2018-09-13 09:20:30', 1, '3a211b6d-7254-4ee9-b1d1-d3a79d770b93'),
(5, '2018-09-13 09:45:52', '008986f5-b682-11e8-8c84-f23c91cc1ef2', 'dPmY7tsLcmY:APA91bGyAvisgXcXVeJDfD0jKZ2RQaDai7n4RqEyQEHpeOi-Z1up-2qJQKgGD-CRwo2D2PkuXuPfGXdQWba6sHqvrG5MRz6mpQBA0nU30dtt0spZLAmQG0ophsTNx8qafxax1EAUjUXM', '2018-09-13 09:45:52', 1, '3a211b6d-7254-4ee9-b1d1-d3a79d770b93'),
(6, '2018-09-13 09:58:16', '008986f5-b682-11e8-8c84-f23c91cc1ef2', 'dvsJfwe9Ttg:APA91bED4DjXoJoWJ5hFzQsfMfilUm-UrwVndPvBIsMjKAHbG2ksTaKPcQ-Z6uI6CVDWQZ1ylBVu05uh8pv4BOy9eB4wBYRDs5qUbHvP9Nf9W5M4TW6EgTz7wtbc0j34vzNIlhZhnPXT', '2018-09-13 09:58:16', 1, '3a211b6d-7254-4ee9-b1d1-d3a79d770b93'),
(7, '2018-09-13 14:17:42', '022a0ea1-b682-11e8-8c84-f23c91cc1ef2', 'dTyajpT7zM4:APA91bE5iXg8DMKYnAZZ2239JIkAhjBb9v6yjWL7hpFO1uQ5Uvz6TJ2rTdJx-8ybyOPncvqTSgOdLnIknvyIzq7UeSDv3moOosHbP7yCWEz3kl0UcIP4f5n_Y7jtTZTuGJ2ilJcYjf-a', '2018-09-13 14:17:42', 0, 'bb20ba32-7fa5-4e73-a6c9-2f7d023a9074'),
(8, '2018-09-13 14:42:26', '022a0ea1-b682-11e8-8c84-f23c91cc1ef2', 'e7T7ZuNtn3E:APA91bHjNdvzzaolwVDD0745o1mCwzqGc1CsfBiTr03_-0sGV9beQ0RPA3ia4rXQ0vZ_ixoxCwb2UQADb_76oKjmiqLgFqdVnbqWEkSDidmim0389Kpg8mspjdQRfAUeWaKKHcBUseX7', '2018-09-13 14:42:26', 0, 'bb20ba32-7fa5-4e73-a6c9-2f7d023a9074'),
(9, '2018-09-13 15:02:13', '022a0ea1-b682-11e8-8c84-f23c91cc1ef2', 'cMPW9nX6Lgw:APA91bGoDgOWAfz0OjAwoShrb8jhKjBU-tC3MoFOq7FjRMiG5jAGIlDhOyMoCMyD-WyflpxVmr1HlSpf_2CYTrgpHls5R-roX9ZKP9-htVgHYfIngtqub4-6KclyAZn7gswxZfRysS0j', '2018-09-13 15:02:13', 0, 'bb20ba32-7fa5-4e73-a6c9-2f7d023a9074'),
(10, '2018-09-13 15:29:23', '022a0ea1-b682-11e8-8c84-f23c91cc1ef2', 'f0xRXJmnFJU:APA91bFIjClET_daqr3YIRYsDdV1lLNQmwKUbOI64AjHZEhApQRao-iz7fOLZYLahoQB6vGA5ip2yaEZoJhMrdyU9Amk6pjir8PfReLdCRMbuJqbiEGpCaoNUsSFKeQe7WpP1BkyK7gS', '2018-09-13 15:29:23', 0, '222ea795-9f1d-4051-8b09-ef08108be9bc'),
(11, '2018-09-14 07:30:41', '022a0ea1-b682-11e8-8c84-f23c91cc1ef2', 'cjVxtA6Ad0A:APA91bF-OGYuBCBFeur0wbYB0uJDKufip4KH3S351TYkURiYQMZKR25AtZsvriOmXlX1ctlxjvhm8kg428Wy0hz2oC9TTLW6SwtXlb_kONDsW9dxBZGZ_k23pKpZDrFyt-SwhIIALTLg', '2018-09-14 07:30:41', 1, '953bb5ce-fa16-4213-890a-e8a0fb712a52'),
(13, '2018-09-14 10:39:13', '022a0ea1-b682-11e8-8c84-f23c91cc1ef2', 'fecMvR92SlE:APA91bGJziL2FBKaoCZg7GOzmaZ9cEfHLW61sJslr8HXwWkW-r-4LX6prR_J7IbRdUoqGn49fTnNZG5YFEbJS_P12HQ2GloL6Kxgr-gCDhGSjAqlVY6YoIJo2nL8aD73sNBWkAy4sIEh', '2018-09-14 10:39:13', 1, '953bb5ce-fa16-4213-890a-e8a0fb712a52');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_referral_type`
--

CREATE TABLE `tbl_referral_type` (
  `referral_type_id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` tinyint(1) DEFAULT NULL,
  `referral_type_name` varchar(255) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_routine_visits`
--

CREATE TABLE `tbl_routine_visits` (
  `health_facility_client_id` bigint(20) NOT NULL,
  `anaemia` tinyint(1) DEFAULT NULL,
  `antepartum_Haemorrhage` tinyint(1) DEFAULT NULL,
  `appointment_date` datetime DEFAULT NULL,
  `appointment_id` bigint(20) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fetus_lie` tinyint(1) DEFAULT NULL,
  `high_blood_pressure` tinyint(1) DEFAULT NULL,
  `_id` bigint(20) DEFAULT NULL,
  `oedema` tinyint(1) DEFAULT NULL,
  `protenuria` tinyint(1) DEFAULT NULL,
  `sugar_in_the_urine` tinyint(1) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `visit_date` datetime DEFAULT NULL,
  `visit_umber` int(11) DEFAULT NULL,
  `weight_stagnation` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_services`
--

CREATE TABLE `tbl_services` (
  `referral_service_id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` tinyint(1) DEFAULT NULL,
  `referral_category_name` varchar(255) DEFAULT NULL,
  `referral_service_name` varchar(255) DEFAULT NULL,
  `referral_service_name_sw` varchar(255) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_services`
--

INSERT INTO `tbl_services` (`referral_service_id`, `created_at`, `is_active`, `referral_category_name`, `referral_service_name`, `referral_service_name_sw`, `updated_at`) VALUES
(1, '2018-09-13 13:59:30', 1, 'Family Planning', 'Family Planning', 'Uzazi wa Mpango', '2018-09-13 13:59:18');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_service_indicator`
--

CREATE TABLE `tbl_service_indicator` (
  `indicator_id` bigint(20) NOT NULL,
  `service_id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` tinyint(1) DEFAULT NULL,
  `service_indicator_id` bigint(20) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_service_indicator`
--

INSERT INTO `tbl_service_indicator` (`indicator_id`, `service_id`, `created_at`, `is_active`, `service_indicator_id`, `updated_at`) VALUES
(1, 1, '2018-09-13 14:05:06', 1, 1, '2018-09-13 14:05:06'),
(2, 1, '2018-09-13 14:05:06', 1, 2, '2018-09-13 14:05:06');

-- --------------------------------------------------------

--
-- Table structure for table `unique_ids`
--

CREATE TABLE `unique_ids` (
  `_id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `openmrs_id` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `used_by` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_anc_clients`
--
ALTER TABLE `tbl_anc_clients`
  ADD PRIMARY KEY (`clients_id`);

--
-- Indexes for table `tbl_client_appointments`
--
ALTER TABLE `tbl_client_appointments`
  ADD PRIMARY KEY (`health_facility_client_id`,`appointment_date`),
  ADD UNIQUE KEY `UK_jopmox0rdtre0yn8jhffacj3l` (`appointment_id`),
  ADD KEY `FK_7lq9w6sc76g6bev2wofj5bn2j` (`health_facility_client_id`);

--
-- Indexes for table `tbl_client_referral`
--
ALTER TABLE `tbl_client_referral`
  ADD PRIMARY KEY (`referral_id`),
  ADD UNIQUE KEY `UK_tpg2spcul3dski5mem6jlgjdt` (`instance_id`);

--
-- Indexes for table `tbl_health_facilities`
--
ALTER TABLE `tbl_health_facilities`
  ADD PRIMARY KEY (`_id`),
  ADD UNIQUE KEY `UK_pi34a2ucurf655l2qg3p2kv9g` (`HFR_code`),
  ADD UNIQUE KEY `UK_3tpfb14nfmpttwd4r7baqem6g` (`openmrs_UIID`);

--
-- Indexes for table `tbl_health_facility_clients`
--
ALTER TABLE `tbl_health_facility_clients`
  ADD PRIMARY KEY (`health_facility_client_id`),
  ADD KEY `FK_5ccrxr3ufn9yoy91i6f641g5r` (`client_id`);

--
-- Indexes for table `tbl_indicator_signs`
--
ALTER TABLE `tbl_indicator_signs`
  ADD PRIMARY KEY (`referral_indicator_id`),
  ADD UNIQUE KEY `UK_nrmuso7jyi4m5a4vl3s9bx9oj` (`referral_indicator_name`),
  ADD UNIQUE KEY `UK_jpoiskvmw7dwi6apcqvbgusm` (`referral_indicator_name_sw`);

--
-- Indexes for table `tbl_patient_referral_indicator`
--
ALTER TABLE `tbl_patient_referral_indicator`
  ADD PRIMARY KEY (`patient_referral_indicator_id`);

--
-- Indexes for table `tbl_pnc_clients`
--
ALTER TABLE `tbl_pnc_clients`
  ADD PRIMARY KEY (`pnc_clients_id`);

--
-- Indexes for table `tbl_push_notifications_users`
--
ALTER TABLE `tbl_push_notifications_users`
  ADD PRIMARY KEY (`_id`),
  ADD UNIQUE KEY `UK_ny86g6nqhwicg9jyn4543n4yw` (`google_push_notification_token`);

--
-- Indexes for table `tbl_referral_type`
--
ALTER TABLE `tbl_referral_type`
  ADD PRIMARY KEY (`referral_type_id`),
  ADD UNIQUE KEY `UK_aqx3r8w6o2gsnwvnhoiyk7ere` (`referral_type_name`);

--
-- Indexes for table `tbl_routine_visits`
--
ALTER TABLE `tbl_routine_visits`
  ADD PRIMARY KEY (`health_facility_client_id`);

--
-- Indexes for table `tbl_services`
--
ALTER TABLE `tbl_services`
  ADD PRIMARY KEY (`referral_service_id`),
  ADD UNIQUE KEY `UK_nix66g0ufokce1fmu65g934h8` (`referral_service_name`),
  ADD UNIQUE KEY `UK_5t5kcnxwp08gfq0ajtnre00v7` (`referral_service_name_sw`);

--
-- Indexes for table `tbl_service_indicator`
--
ALTER TABLE `tbl_service_indicator`
  ADD PRIMARY KEY (`indicator_id`,`service_id`);

--
-- Indexes for table `unique_ids`
--
ALTER TABLE `unique_ids`
  ADD PRIMARY KEY (`_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_anc_clients`
--
ALTER TABLE `tbl_anc_clients`
  MODIFY `clients_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tbl_client_referral`
--
ALTER TABLE `tbl_client_referral`
  MODIFY `referral_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_health_facilities`
--
ALTER TABLE `tbl_health_facilities`
  MODIFY `_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=213;
--
-- AUTO_INCREMENT for table `tbl_health_facility_clients`
--
ALTER TABLE `tbl_health_facility_clients`
  MODIFY `health_facility_client_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tbl_indicator_signs`
--
ALTER TABLE `tbl_indicator_signs`
  MODIFY `referral_indicator_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tbl_patient_referral_indicator`
--
ALTER TABLE `tbl_patient_referral_indicator`
  MODIFY `patient_referral_indicator_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_pnc_clients`
--
ALTER TABLE `tbl_pnc_clients`
  MODIFY `pnc_clients_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_push_notifications_users`
--
ALTER TABLE `tbl_push_notifications_users`
  MODIFY `_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `tbl_referral_type`
--
ALTER TABLE `tbl_referral_type`
  MODIFY `referral_type_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_services`
--
ALTER TABLE `tbl_services`
  MODIFY `referral_service_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `unique_ids`
--
ALTER TABLE `unique_ids`
  MODIFY `_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_client_appointments`
--
ALTER TABLE `tbl_client_appointments`
  ADD CONSTRAINT `FK_7lq9w6sc76g6bev2wofj5bn2j` FOREIGN KEY (`health_facility_client_id`) REFERENCES `tbl_health_facility_clients` (`health_facility_client_id`);

--
-- Constraints for table `tbl_health_facility_clients`
--
ALTER TABLE `tbl_health_facility_clients`
  ADD CONSTRAINT `FK_5ccrxr3ufn9yoy91i6f641g5r` FOREIGN KEY (`client_id`) REFERENCES `tbl_anc_clients` (`clients_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
