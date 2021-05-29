 DROP DATABASE IF EXISTS DTHW;
CREATE DATABASE DTHW;
USE DTHW;
 
 CREATE TABLE CLIENTE (
	USERNAME VARCHAR(30) PRIMARY KEY,
	EMAIL VARCHAR(320)  UNIQUE NOT NULL,
    PASSW VARBINARY (100) NOT NULL,
    NOME VARCHAR (50) NOT NULL,
    COGNOME VARCHAR (50) NOT NULL,
    NTELEFONO CHAR(10) NOT NULL
    );
 
 CREATE TABLE CARTADICREDITO (
	NCARTA CHAR(12)  PRIMARY KEY,
    SCADENZA TIMESTAMP,
    CVV INT NOT NULL,
    USERNAME VARCHAR(30) UNIQUE,
    FOREIGN KEY (USERNAME) REFERENCES CLIENTE (USERNAME)
    );
    
 CREATE TABLE ORDINE (
	ID INT PRIMARY KEY,
    SCONTO INT,
    TOTALE DOUBLE NOT NULL,
    DATAACQUISTO TIMESTAMP not null,
    USERNAME VARCHAR(30) UNIQUE,
    FOREIGN KEY (USERNAME) REFERENCES CLIENTE (USERNAME)
    );
    
    CREATE TABLE CATEGORIA (
	NOME VARCHAR(50) primary key,
	QUANTITA INT NOT NULL
	);
    
 CREATE TABLE PRODOTTO (
	CODICEBARRE char(12) PRIMARY KEY,
	PREZZO DOUBLE NOT NULL,
	DESCRIZIONE VARCHAR(1000),
	SPECIFICHE VARCHAR(1000) NOT NULL,
	IMMAGINE longblob,
	QUANTITA INT NOT NULL,
	MARCA VARCHAR(50) NOT NULL,
	MODELLO VARCHAR(50) NOT NULL,
    NOME VARCHAR(50),
    foreign key (NOME) references categoria(NOME)
	);
       
 CREATE TABLE INDIRIZZO (
	VIA VARCHAR(50) UNIQUE NOT NULL,
    NCIVICO INT UNIQUE NOT NULL,
    CITTA VARCHAR (20) NOT NULL,
    CAP INT NOT NULL,
    FLAG BOOLEAN,
    USERNAME VARCHAR(30),
	PRIMARY KEY (USERNAME,VIA,NCIVICO),
	FOREIGN KEY (USERNAME) REFERENCES CLIENTE (USERNAME)
    );
    
 CREATE TABLE RISIEDE (
	USERNAME VARCHAR(30),
	VIA VARCHAR(50) NOT NULL,
    NCIVICO INT NOT NULL,
	FOREIGN KEY (USERNAME) REFERENCES CLIENTE (USERNAME),
	FOREIGN KEY (VIA) REFERENCES INDIRIZZO (VIA),
	FOREIGN KEY (NCIVICO) REFERENCES INDIRIZZO (NCIVICO)
    );
    


CREATE TABLE COMPONE (
	CODICEABARRE CHAR(12) UNIQUE,
    IDORDINE INT UNIQUE,
    NPRODOTTI INT,
    foreign key (CODICEABARRE) REFERENCES PRODOTTO(CODICEBARRE),
    foreign key (IDORDINE) REFERENCES ORDINE(ID)
);
    
    