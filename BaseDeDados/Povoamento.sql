-- Universidade do Minho
-- Mestrado Integrado em Engenharia Informática
-- Unidade Curricular de Desenvolvimento de Sistemas de Software
-- 2018/2019
-- Caso de Estudo: "ConfiguraFacil" 
-- Povoamento parcial da base de dados.

-- Base de dados de trabalho
USE ConfiguraFacil;

-- Povoamento da tabela "Funcionario"
INSERT INTO Funcionario(nome,password,fabrica)
    VALUES
        ('klopp','liverpool',true),
        ('ronaldo','cr7',false),
        ('mourinho','pogba',true),
        ('jonas','pistolas',false);

-- Povoamento da tabela "Componente"
INSERT INTO Componente(idComponente,preco,nome,stock)
	VALUES 
		(1, 100.00, 'Estofos em Pele Artico/Tecido Preto', 10),
		(2, 100.00, 'Bancos conforto', 4),
		(3, 1000.00, 'Estofos em Pele Preto', 4),
        (4, 1000.00, 'Bancos aquecidos', 2),
        (5, 600.00, 'Câmara para marcha-atrás', 9),
        (6, 450.00, 'Sistema de estacionamento ativo', 0),
        (7, 325.00, 'Sistema de Alarme Anti-Roubo', 3),
        (8, 300.00, 'Proteção do Habitáculo', 5),
        (9, 800.00, 'Jantes em liga leve de 5 raios escurecidas de 18" ', 10),
        (10, 400.00, 'Vidros escurecidos', 8),
        (11, 800.00, 'Estofos em Pele Cinzento/Preto',6),
        (12, 800.00, 'Bancos conforto',6);

-- Povoamento da tabela "Pacote"
INSERT INTO Pacote(idPacote,preco,nome)
	VALUES
        (1, 150.00, "Pack Style"), -- Componente 1 e 2
        (2, 1750.00, "Pack Pele"), -- Componente 3 e 4
        (3, 750.90, "Pack Parking"), -- Componente 5 e 6
        (4, 550.00, "Pack Alarme"), -- Componente 7 e 8
        (5, 1000.00, "Pack Night"), -- Componente 9 e 10
        (6, 1450.00, "Pack Pele Sport"); -- Componente 11 e 12

-- Povoamento da tabela "Pacote_Componente"
INSERT INTO Pacote_Componente(idComponente,idPacote)
	VALUES 
        (1,1),
        (2,1),
        (3,2),
        (4,2),
        (5,3),
        (6,3),
        (7,4),
        (8,4),
        (9,5),
        (10,5),
        (11,6),
        (12,6);

-- Povoamento da tabela "Componente"
INSERT INTO Obrigatoriedades(idComponente,idComponenteObrigatorio)
	VALUES
        (1,2),
        (3,4),
        (11,12);
        


-- Povoamento da tabela "Componente"
INSERT INTO Incompatibilidades(idComponente,idComponenteIncompativel)
	VALUES
        (1,3), -- Estofos
        (1,11), -- Estofos
        (3,11), -- Estofos
        (2,4), -- Pneus
        (2,12), -- Pneus
        (4,12); -- Pneus
        
-- <fim>