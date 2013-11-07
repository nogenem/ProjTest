-- 
-- Structure for table `EQUIPE`
-- 

DROP TABLE IF EXISTS `EQUIPE`;
CREATE TABLE IF NOT EXISTS `EQUIPE` (
  `ID_EQUIPE` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_EQUIPE`),
  UNIQUE KEY `NOME` (`NOME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 
-- Structure for table `POSTIT`
-- 

DROP TABLE IF EXISTS `POSTIT`;
CREATE TABLE IF NOT EXISTS `POSTIT` (
  `ID_POSTIT` int(11) NOT NULL AUTO_INCREMENT,
  `TITULO` varchar(50) NOT NULL,
  `CONTEUDO` text NOT NULL,
  `ID_USUARIO` int(11) NOT NULL,
  PRIMARY KEY (`ID_POSTIT`),
  KEY `ID_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `POSTIT_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `USUARIO` (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 
-- Structure for table `PROJETO`
-- 

DROP TABLE IF EXISTS `PROJETO`;
CREATE TABLE IF NOT EXISTS `PROJETO` (
  `ID_PROJETO` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(50) NOT NULL,
  `ID_EQUIPE` int(11) NOT NULL,
  PRIMARY KEY (`ID_PROJETO`),
  KEY `ID_EQUIPE` (`ID_EQUIPE`),
  CONSTRAINT `PROJETO_ibfk_1` FOREIGN KEY (`ID_EQUIPE`) REFERENCES `EQUIPE` (`ID_EQUIPE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 
-- Structure for table `TAREFA`
-- 

DROP TABLE IF EXISTS `TAREFA`;
CREATE TABLE IF NOT EXISTS `TAREFA` (
  `ID_TAREFA` int(11) NOT NULL AUTO_INCREMENT,
  `TITULO` varchar(50) NOT NULL,
  `DESCRICAO` varchar(200) NOT NULL,
  `DATA_INICIO` varchar(50) NOT NULL,
  `DATA_FINAL` varchar(50) NOT NULL,
  `RECURSOS` varchar(50) NOT NULL,
  `ID_PROJETO` int(11) NOT NULL,
  PRIMARY KEY (`ID_TAREFA`),
  KEY `ID_PROJETO` (`ID_PROJETO`),
  CONSTRAINT `TAREFA_ibfk_1` FOREIGN KEY (`ID_PROJETO`) REFERENCES `PROJETO` (`ID_PROJETO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 
-- Structure for table `USUARIO`
-- 

DROP TABLE IF EXISTS `USUARIO`;
CREATE TABLE IF NOT EXISTS `USUARIO` (
  `ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT,
  `LOGIN` varchar(50) NOT NULL,
  `SENHA` varchar(50) NOT NULL,
  `NOME` varchar(50) NOT NULL,
  `NIVEL` int(11) NOT NULL,
  `ID_EQUIPE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_USUARIO`),
  KEY `ID_EQUIPE` (`ID_EQUIPE`),
  CONSTRAINT `USUARIO_ibfk_1` FOREIGN KEY (`ID_EQUIPE`) REFERENCES `EQUIPE` (`ID_EQUIPE`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- 
-- Data for table `USUARIO`
-- 

INSERT INTO `USUARIO` (`ID_USUARIO`, `LOGIN`, `SENHA`, `NOME`, `NIVEL`, `ID_EQUIPE`) VALUES
  ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'ADMINISTRADOR', '0', NULL);

