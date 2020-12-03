-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dashio
-- ------------------------------------------------------
-- Server version	5.7.31-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `idcategorias` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `icono` varchar(45) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcategorias`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Mercado','ti-package','text-primary border-primary'),(2,'Tecnologia','ti-hummer','text-primary border-primary'),(3,'Electrodomesticos','ti-home','text-success border-success'),(4,'Hogar','ti-music-alt','text-warning border-warning'),(5,'Moda y Accesorios','ti-rocket','text-danger border-danger'),(6,'Salud y belleza','ti-comments-smiley','text-danger border-danger');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `idProductos` int(11) NOT NULL AUTO_INCREMENT,
  `serial` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `imagen_ruta` varchar(255) DEFAULT NULL,
  `cantidad` varchar(45) DEFAULT NULL,
  `valor_compra` double DEFAULT NULL,
  `valor_venta` double DEFAULT NULL,
  `fk_categoria` int(11) DEFAULT NULL,
  PRIMARY KEY (`idProductos`),
  KEY `fk_cate_idx` (`fk_categoria`),
  CONSTRAINT `fk_cate` FOREIGN KEY (`fk_categoria`) REFERENCES `categorias` (`idcategorias`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (79,'5345435.0','Producto 1','SInFoto\\nopic.png','10.0',15000,16000,1),(80,'5345435.0','Producto 2','SInFoto\\nopic.png','10.0',25000,30000,1),(81,'5345435.0','Producto 3','SInFoto\\nopic.png','10.0',25000,30000,2),(82,'5345435.0','Producto 4','SInFoto\\nopic.png','10.0',15000,15000,3),(83,'5345435.0','Producto 5','Mercado\\20201202211049394.jpg','10.0',8000,9000,4),(84,'5345435.0','Producto 6','Mercado\\20201202211049394.jpg','10.0',8000,9000,4),(85,'5345435.0','Producto 7','Mercado\\20201202211049394.jpg','10.0',15000,15000,4);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `icono` varchar(60) DEFAULT NULL,
  `ruta` varchar(120) DEFAULT NULL,
  `descripcion` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Administrador','fa fa-cogs','../administrador/index.xhtml','Lista de usuarios'),(3,'Gestion de productos','fa fa-desktop','../productos/index.xhtml','Productos'),(7,'Usuario',NULL,NULL,NULL);
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `tipoDocumento` varchar(20) DEFAULT NULL,
  `documento` bigint(20) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `clave` varchar(10) DEFAULT NULL,
  `fechaRegistro` datetime DEFAULT CURRENT_TIMESTAMP,
  `ultimoIngreso` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `documento_UNIQUE` (`documento`),
  UNIQUE KEY `correo_UNIQUE` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (7,'Jose L.','Sarta A.','Cedula',78979798,'josarta@misena.edu.co','1','2020-11-11 20:05:15',NULL),(23,'nombre Dos','apellidos Dos','Cedula',22222222,'brayan24.algecira@gmail.com','123456','2020-11-12 21:13:09',NULL),(24,'Cesar ','Bernal Macia','Cedula',6,'djbema01@gmail.com','666666','2020-11-12 21:21:30',NULL),(25,'Maikol','Parra','Cedula',77,'maikol320434@gmail.com','789','2020-11-12 21:22:17',NULL),(26,'jeferson ','piñeros','Cedula',88,'japineros91@misena.edu.co','888888','2020-11-12 21:22:45',NULL),(28,'Reporte Nombres','Reporte Apellidos','Cedula',7878787,'reporte@misena.edu.co','123456','2020-11-20 19:02:42',NULL),(29,'Usuario Nuevo','Apellidos Nuevo','Cedula',78787879,'usuarionuevo@gmail.com','123456','2020-11-20 21:11:26',NULL),(32,'Sin nombre','Sin Apellido','Cedula',555555555555,'sincorreo@gmail.com','123456','2020-11-20 21:16:19',NULL),(33,'Test Nombre','Test Apellido','Cedula',10101010,'testcorreo@gmail.com','12345','2020-11-20 21:47:36',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_rol` (
  `idUsuario` int(11) DEFAULT NULL,
  `idRol` int(11) DEFAULT NULL,
  KEY `idUsuario` (`idUsuario`),
  KEY `idRol` (`idRol`),
  CONSTRAINT `usuario_rol_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `usuario_rol_ibfk_2` FOREIGN KEY (`idRol`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
INSERT INTO `usuario_rol` VALUES (7,1),(23,7),(23,7),(23,7),(23,7),(23,7),(23,7),(23,7),(24,7),(24,7),(24,7),(24,7),(24,7),(24,7),(24,7),(24,7),(24,7),(24,7),(24,7),(24,7),(24,7),(24,7),(24,7),(24,7),(23,7),(23,7),(24,7),(25,7),(25,7),(25,7),(25,7),(25,7),(25,7),(25,7),(25,7),(25,7),(25,7),(25,7),(25,7),(25,7),(25,7),(25,7),(25,7),(25,7),(25,7),(26,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(28,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(25,1),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(23,7),(24,7),(24,7),(25,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(29,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(32,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(33,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(24,1),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(7,3),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7),(23,7),(24,7),(25,7);
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-02 21:24:03
