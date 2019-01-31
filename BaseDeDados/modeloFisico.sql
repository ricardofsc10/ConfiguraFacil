-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ConfiguraFacil
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ConfiguraFacil
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ConfiguraFacil` DEFAULT CHARACTER SET utf8 ;
USE `ConfiguraFacil` ;

-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Funcionario` (
  `idFuncionario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `fabrica` TINYINT NOT NULL,
  PRIMARY KEY (`idFuncionario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Configuracao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Configuracao` (
  `idConfiguracao` INT NOT NULL AUTO_INCREMENT,
  `orcamento` TINYINT NOT NULL,
  `orcamentoValor` FLOAT NOT NULL,
  `idFuncionario` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `morada` VARCHAR(45) NOT NULL,
  `pagamento` FLOAT NOT NULL,
  `nif` VARCHAR(45) NOT NULL,
  `produzido` TINYINT NOT NULL,
  PRIMARY KEY (`idConfiguracao`),
  INDEX `fk_Configuracao_Funcionario1_idx` (`idFuncionario` ASC) VISIBLE,
  CONSTRAINT `fk_Configuracao_Funcionario1`
    FOREIGN KEY (`idFuncionario`)
    REFERENCES `ConfiguraFacil`.`Funcionario` (`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Componente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Componente` (
  `idComponente` INT NOT NULL AUTO_INCREMENT,
  `preco` FLOAT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `stock` INT NOT NULL,
  PRIMARY KEY (`idComponente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Configuracao_Componente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Configuracao_Componente` (
  `idConfiguracao` INT NOT NULL,
  `idComponente` INT NOT NULL,
  PRIMARY KEY (`idConfiguracao`, `idComponente`),
  INDEX `fk_Configuracao_has_Componente_Componente1_idx` (`idComponente` ASC) VISIBLE,
  INDEX `fk_Configuracao_has_Componente_Configuracao1_idx` (`idConfiguracao` ASC) VISIBLE,
  CONSTRAINT `fk_Configuracao_has_Componente_Configuracao1`
    FOREIGN KEY (`idConfiguracao`)
    REFERENCES `ConfiguraFacil`.`Configuracao` (`idConfiguracao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Configuracao_has_Componente_Componente1`
    FOREIGN KEY (`idComponente`)
    REFERENCES `ConfiguraFacil`.`Componente` (`idComponente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Pacote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Pacote` (
  `idPacote` INT NOT NULL,
  `preco` FLOAT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPacote`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Pacote_Componente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Pacote_Componente` (
  `idComponente` INT NOT NULL AUTO_INCREMENT,
  `idPacote` INT NOT NULL,
  PRIMARY KEY (`idComponente`, `idPacote`),
  INDEX `fk_Componente_has_Pacote_Pacote1_idx` (`idPacote` ASC) VISIBLE,
  INDEX `fk_Componente_has_Pacote_Componente1_idx` (`idComponente` ASC) VISIBLE,
  CONSTRAINT `fk_Componente_has_Pacote_Componente1`
    FOREIGN KEY (`idComponente`)
    REFERENCES `ConfiguraFacil`.`Componente` (`idComponente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Componente_has_Pacote_Pacote1`
    FOREIGN KEY (`idPacote`)
    REFERENCES `ConfiguraFacil`.`Pacote` (`idPacote`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Configuracao_Pacote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Configuracao_Pacote` (
  `idConfiguracao` INT NOT NULL,
  `idPacote` INT NOT NULL,
  PRIMARY KEY (`idConfiguracao`, `idPacote`),
  INDEX `fk_Configuracao_has_Pacote_Pacote1_idx` (`idPacote` ASC) VISIBLE,
  INDEX `fk_Configuracao_has_Pacote_Configuracao1_idx` (`idConfiguracao` ASC) VISIBLE,
  CONSTRAINT `fk_Configuracao_has_Pacote_Configuracao1`
    FOREIGN KEY (`idConfiguracao`)
    REFERENCES `ConfiguraFacil`.`Configuracao` (`idConfiguracao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Configuracao_has_Pacote_Pacote1`
    FOREIGN KEY (`idPacote`)
    REFERENCES `ConfiguraFacil`.`Pacote` (`idPacote`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Incompatibilidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Incompatibilidade` (
  `idComponente` INT NOT NULL,
  `idComponenteIncompativel` INT NOT NULL,
  PRIMARY KEY (`idComponente`, `idComponenteIncompativel`),
  INDEX `fk_Componente_has_Componente_Componente2_idx` (`idComponenteIncompativel` ASC) VISIBLE,
  INDEX `fk_Componente_has_Componente_Componente1_idx` (`idComponente` ASC) VISIBLE,
  CONSTRAINT `fk_Componente_has_Componente_Componente1`
    FOREIGN KEY (`idComponente`)
    REFERENCES `ConfiguraFacil`.`Componente` (`idComponente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Componente_has_Componente_Componente2`
    FOREIGN KEY (`idComponenteIncompativel`)
    REFERENCES `ConfiguraFacil`.`Componente` (`idComponente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Obrigatoriedade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Obrigatoriedade` (
  `idComponente` INT NOT NULL,
  `idComponenteObrigatorio` INT NOT NULL,
  PRIMARY KEY (`idComponente`, `idComponenteObrigatorio`),
  INDEX `fk_Componente_has_Componente_Componente4_idx` (`idComponenteObrigatorio` ASC) VISIBLE,
  INDEX `fk_Componente_has_Componente_Componente3_idx` (`idComponente` ASC) VISIBLE,
  CONSTRAINT `fk_Componente_has_Componente_Componente3`
    FOREIGN KEY (`idComponente`)
    REFERENCES `ConfiguraFacil`.`Componente` (`idComponente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Componente_has_Componente_Componente4`
    FOREIGN KEY (`idComponenteObrigatorio`)
    REFERENCES `ConfiguraFacil`.`Componente` (`idComponente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
