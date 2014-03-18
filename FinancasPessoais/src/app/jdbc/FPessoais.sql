CREATE TABLE IF NOT EXISTS `conta` (
  `idconta` INTEGER PRIMARY KEY,
  `banco` VARCHAR(45) NOT NULL,
  `num` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `saldo` DOUBLE NOT NULL DEFAULT '0',
  `usuario_idusuario` INT(11) NOT NULL,
  CONSTRAINT `fk_conta_usuario`
    FOREIGN KEY (`usuario_idusuario`)
    REFERENCES `usuario` (`idusuario`));

CREATE TABLE IF NOT EXISTS `ganhos` (
  `idganhos` INTEGER PRIMARY KEY,
  `titulo` VARCHAR(45) NOT NULL,
  `valor` DOUBLE NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `conta_idconta` INT(11) NOT NULL,
  `conta_usuario_idusuario` INT(11) NOT NULL,
  CONSTRAINT `fk_ganhos_conta1`
    FOREIGN KEY (`conta_idconta` , `conta_usuario_idusuario`)
    REFERENCES `conta` (`idconta` , `usuario_idusuario`));

CREATE TABLE IF NOT EXISTS `gastos` (
  `idgastos` INTEGER PRIMARY KEY,
  `titulo` VARCHAR(45) NOT NULL,
  `descricao` TEXT NULL DEFAULT NULL,
  `vencimento` DATE NOT NULL,
  `prioridade` INT(11) NULL DEFAULT NULL,
  `valor` DOUBLE NULL DEFAULT NULL,
  `conta_idconta` INT(11) NOT NULL,
  `conta_usuario_idusuario` INT(11) NOT NULL,
  CONSTRAINT `fk_gastos_conta1`
    FOREIGN KEY (`conta_idconta` , `conta_usuario_idusuario`)
    REFERENCES `conta` (`idconta` , `usuario_idusuario`));

CREATE TABLE IF NOT EXISTS `usuario` (
  `idusuario` INTEGER PRIMARY KEY,
  `nome` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `nascimento` VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS `historico` (
  `idhistorico` INTEGER PRIMARY KEY,
  `titulo` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  `data` DATE NOT NULL,
  `conta_idconta` INTEGER(11) NOT NULL,
  CONSTRAINT `fk_historico_conta`
    FOREIGN KEY (`conta_idconta`) REFERENCES `conta`(`idconta`));

CREATE TABLE IF NOT EXISTS `bancos` (
  `idbanco` INT(11) NOT NULL,
  `nome` VARCHAR(45) NOT NULL
);

INSERT INTO `bancos` (idbanco, nome) VALUES (
246, 	'Banco ABC Brasil S.A.'),
(075 ,	'Banco ABN AMRO S.A.'),
(248 ,	'Banco Boavista Interatlântico S.A.'),
(218 ,	'Banco Bonsucesso S.A.'),
(065 ,	'Banco Bracce S.A.'),
(237 ,	'Banco Bradesco S.A.'),
(473 ,	'Banco Caixa Geral - Brasil S.A.'),
(040 ,	'Banco Cargill S.A.'),
(233 ,	'Banco Cifra S.A.'),
(003 ,	'Banco da Amazônia S.A.'),
(024 ,	'Banco de Pernambuco S.A. - BANDEPE'),
(001 ,	'Banco do Brasil S.A.'),
(047 ,	'Banco do Estado de Sergipe S.A.'),
(037 ,	'Banco do Estado do Pará S.A.'),
(041 ,	'Banco do Estado do Rio Grande do Sul S.A.'),
(004 ,	'Banco do Nordeste do Brasil S.A.'),
(612 ,	'Banco Guanabara S.A.'),
(604 ,	'Banco Industrial do Brasil S.A.'),
(320 ,	'Banco Industrial e Comercial S.A.'),
(184 ,	'Banco Itaú S.A.'),
(074 ,	'Banco J. Safra S.A.'),
(389 ,	'Banco Mercantil do Brasil S.A.'),
(079 ,	'Banco Original do Agronegócio S.A.'),
(623 ,	'Banco Panamericano S.A.'),
(611 ,	'Banco Paulista S.A.'),
(356 ,	'Banco Real S.A.'),
(633 ,	'Banco Rendimento S.A.'),
(453 ,	'Banco Rural S.A.'),
(422 ,	'Banco Safra S.A.'),
(033 ,	'Banco Santander (Brasil) S.A.'),
(021 ,	'BANESTES S.A. Banco do Estado do Espírito Santo'),
(104 ,	'Caixa Econômica Federal'),
(399 ,	'HSBC Bank Brasil S.A. - Banco Múltiplo'),
(409 ,	'UNIBANCO - União de Bancos Brasileiros S.A'
);

