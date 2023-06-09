DROP DATABASE PASSWORD_LOCKER;
CREATE DATABASE PASSWORD_LOCKER;
SHOW DATABASES;
USE PASSWORD_LOCKER;

CREATE TABLE TB_USER (
	ID_USER              VARCHAR(36) PRIMARY KEY NOT NULL,
	NOME                 VARCHAR(50) NOT NULL,
	EMAIL				 VARCHAR(80) NOT NULL UNIQUE,
	SENHA				 VARCHAR(100) NOT NULL,
	TELEFONE             VARCHAR(14),
	DATA_CRIACAO         DATETIME NOT NULL,
	DATA_ALTERACAO 		 DATETIME,
	USER_KEY			 VARCHAR(36) NOT NULL UNIQUE
);

CREATE TABLE TB_SENHA (
	ID_SENHA             VARCHAR(36) PRIMARY KEY NOT NULL,
	TITULO		         VARCHAR(100) NOT NULL,
	SENHA 				 varchar(100) NOT NULL,
    USER_SITE		     varchar(100),		 
	DATA_CRIACAO         DATE NOT NULL,
	DATA_ALTERACAO 		 DATE,
	ID_USER              VARCHAR(36) NOT NULL,
	FOREIGN KEY (ID_USER) REFERENCES TB_USER(ID_USER)
);

 SELECT * FROM TB_USER;
 SELECT * FROM TB_SENHA;
 
 
 