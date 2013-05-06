SET DATABASE SQL SYNTAX ORA TRUE;

----------------------------------------------------------------------------------------

CREATE SEQUENCE SEQ_CURSO 
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
;

CREATE SEQUENCE SEQ_TUTOR 
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
;

----------------------------------------------------------------------------------------

CREATE TABLE TBG_CURSO
    ( 
     ID_CURSO NUMBER (12) NOT NULL ,
     LG_ACTIVO NUMBER (1) NOT NULL ,
     DE_TITULO VARCHAR2 (100 CHAR) NOT NULL , 
     DE_NIVEL VARCHAR2 (20 CHAR) NOT NULL ,
     NU_HORAS NUMBER (5) NOT NULL , 
     BL_TEMARIO BLOB ,
     ID_TUTOR NUMBER (12) NOT NULL
    ) 
;

CREATE UNIQUE INDEX IPKTBG_CURSO_00 ON TBG_CURSO 
    ( 
     ID_CURSO ASC 
    ) 
;
CREATE INDEX IFKTBG_CURSO_01 ON TBG_CURSO 
    ( 
     ID_TUTOR ASC 
    ) 
;

ALTER TABLE TBG_CURSO 
    ADD CONSTRAINT PKTBG_CURSO_00 PRIMARY KEY ( ID_CURSO ) ;

----------------------------------------------------------------------------------------
    
CREATE TABLE TBG_TUTOR
    ( 
     ID_TUTOR NUMBER (12) NOT NULL ,
     DE_NOMBRE VARCHAR2 (50 CHAR) NOT NULL , 
     DE_APELLIDOS VARCHAR2 (50 CHAR) NULL
    ) 
;

CREATE UNIQUE INDEX IPKTBG_TUTOR_00 ON TBG_TUTOR 
    ( 
     ID_TUTOR ASC 
    ) 
;

ALTER TABLE TBG_TUTOR 
    ADD CONSTRAINT PKTBG_TUTOR_00 PRIMARY KEY ( ID_TUTOR ) ;

----------------------------------------------------------------------------------------
    
ALTER TABLE TBG_CURSO 
	ADD CONSTRAINT FKTBG_CURSO_01 FOREIGN KEY
    (
    	ID_TUTOR
    ) 
    REFERENCES TBG_TUTOR 
    (
    	ID_TUTOR
    )
;