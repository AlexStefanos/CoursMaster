-- Généré par Oracle SQL Developer Data Modeler 4.1.1.888
--   à :        2016-11-21 11:01:05 CET
--   site :      Oracle Database 12c
--   type :      Oracle Database 12c




DROP TABLE LeClient CASCADE CONSTRAINTS ;

DROP TABLE Sejour81 CASCADE CONSTRAINTS ;

DROP TABLE Stations CASCADE CONSTRAINTS ;

CREATE TABLE LeClient
  (
    id             NUMBER (10) NOT NULL ,
    nom            VARCHAR2 (20) NOT NULL ,
    prenom         VARCHAR2 (20) ,
    ville_region   VARCHAR2 (40) NOT NULL,
    solde          NUMBER (10) DEFAULT 0 NOT NULL ,
    pointsFidelite VARCHAR2 (6) DEFAULT 'bronze' NOT NULL
  ) ;
ALTER TABLE LeClient ADD CONSTRAINT LeClient_PK PRIMARY KEY ( id ) ;


CREATE TABLE Sejour81
  (
    idClient NUMBER (10) NOT NULL ,
    station  VARCHAR2 (20) NOT NULL ,
    debut    DATE NOT NULL ,
    nbPlaces NUMBER (4) NOT NULL,
nbjours NUMBER(4) NOT NULL
  ) ;
ALTER TABLE Sejour81 ADD CONSTRAINT Sejour81_PK PRIMARY KEY ( idClient, station, debut ) ;


CREATE TABLE Stations
  (
    nomStation       VARCHAR2 (20) NOT NULL ,
    capacite         NUMBER (10) NOT NULL ,
    lieu             VARCHAR2 (20) NOT NULL ,
    region           VARCHAR2 (20) NOT NULL ,
    tarif            NUMBER (10) DEFAULT 0 ,
    Activite_libelle VARCHAR2 (20) NOT NULL
  ) ;
ALTER TABLE Stations ADD CONSTRAINT nom_region81 CHECK ( region IN ('ocean indien', 'antilles', 'europe', 'ameriques', 'extreme orient')) ;
ALTER TABLE Stations ADD CONSTRAINT tarif_positif81 CHECK ( tarif > 0) ;
ALTER TABLE Stations ADD CONSTRAINT cle_station81 PRIMARY KEY ( nomStation ) ;
ALTER TABLE Stations ADD CONSTRAINT cle_lieu_region81 UNIQUE ( lieu , region ) ;


ALTER TABLE Sejour81 ADD CONSTRAINT FK_LeClient FOREIGN KEY ( idClient ) REFERENCES LeClient ( id ) ;

ALTER TABLE Sejour81 ADD CONSTRAINT FK_Station81 FOREIGN KEY ( station ) REFERENCES Stations ( nomStation ) ON
DELETE CASCADE ;


-- Rapport récapitulatif d'Oracle SQL Developer Data Modeler : 
-- 
-- CREATE TABLE                             3
-- CREATE INDEX                             0
-- ALTER TABLE                              8
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- TSDP POLICY                              0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
