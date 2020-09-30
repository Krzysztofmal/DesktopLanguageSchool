--------------------------------------------------------
--  File created - czwartek-marca-30-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table ADMIN
--------------------------------------------------------

  CREATE TABLE "KRZYSZTOF"."ADMIN" 
   (	"LOGIN" VARCHAR2(20 BYTE), 
	"HASLO" VARCHAR2(20 BYTE), 
	"ID_ADMIN" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table KURS
--------------------------------------------------------

  CREATE TABLE "KRZYSZTOF"."KURS" 
   (	"ID_KURSU" NUMBER, 
	"NAZWA_KURSU" VARCHAR2(40 BYTE), 
	"ILOSC_GODZIN" NUMBER, 
	"POZIOM" VARCHAR2(25 BYTE), 
	"JEZYK" VARCHAR2(20 BYTE), 
	"ID_SALI" NUMBER, 
	"ID_WYKLADOWCY" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table KURSANCI
--------------------------------------------------------

  CREATE TABLE "KRZYSZTOF"."KURSANCI" 
   (	"ID_KURSANTA" NUMBER, 
	"IMIE" VARCHAR2(20 BYTE), 
	"NAZWISKO" VARCHAR2(20 BYTE), 
	"PESEL" NUMBER, 
	"NR_TEL" NUMBER, 
	"EMAIL" VARCHAR2(25 BYTE), 
	"LOGIN" VARCHAR2(20 BYTE), 
	"HASLO" VARCHAR2(20 BYTE), 
	"STATUS" VARCHAR2(20 BYTE), 
	"PLEC" VARCHAR2(1 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table KURSKURSANCI
--------------------------------------------------------

  CREATE TABLE "KRZYSZTOF"."KURSKURSANCI" 
   (	"ID_KURSU" NUMBER, 
	"ID_KURSANTA" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SALA
--------------------------------------------------------

  CREATE TABLE "KRZYSZTOF"."SALA" 
   (	"ID_SALI" NUMBER, 
	"NR_SALI" NUMBER, 
	"ILOSC_MIEJSC" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table WYKLADOWCY
--------------------------------------------------------

  CREATE TABLE "KRZYSZTOF"."WYKLADOWCY" 
   (	"ID_WYKLADOWCY" NUMBER(*,0), 
	"IMIE" VARCHAR2(12 BYTE), 
	"NAZWISKO" VARCHAR2(20 BYTE), 
	"EMAIL" VARCHAR2(25 BYTE), 
	"TELEFON" NUMBER, 
	"JEZYK" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ZALICZENIA
--------------------------------------------------------

  CREATE TABLE "KRZYSZTOF"."ZALICZENIA" 
   (	"ID_ZALICZENIA" NUMBER, 
	"DATA_ZAL" DATE, 
	"WYNIK" NUMBER, 
	"TYP" VARCHAR2(10 BYTE), 
	"ID_KURSU" NUMBER, 
	"ID_KURSANTA" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Sequence ADMIN_SEQ_ID
--------------------------------------------------------

   CREATE SEQUENCE  "KRZYSZTOF"."ADMIN_SEQ_ID"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 3 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence KURSANCI_SEQ_ID
--------------------------------------------------------

   CREATE SEQUENCE  "KRZYSZTOF"."KURSANCI_SEQ_ID"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 42 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence KURS_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "KRZYSZTOF"."KURS_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SALA_SEQ_ID
--------------------------------------------------------

   CREATE SEQUENCE  "KRZYSZTOF"."SALA_SEQ_ID"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 3 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence WYKLADOWCY_SEQ_ID
--------------------------------------------------------

   CREATE SEQUENCE  "KRZYSZTOF"."WYKLADOWCY_SEQ_ID"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 3 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence ZALICZENIA_SEQ_ID
--------------------------------------------------------

   CREATE SEQUENCE  "KRZYSZTOF"."ZALICZENIA_SEQ_ID"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;
REM INSERTING into KRZYSZTOF.ADMIN
SET DEFINE OFF;
Insert into KRZYSZTOF.ADMIN (LOGIN,HASLO,ID_ADMIN) values ('admin','admin','1');
Insert into KRZYSZTOF.ADMIN (LOGIN,HASLO,ID_ADMIN) values ('pracownik1','bioreurlop123','2');
REM INSERTING into KRZYSZTOF.KURS
SET DEFINE OFF;
Insert into KRZYSZTOF.KURS (ID_KURSU,NAZWA_KURSU,ILOSC_GODZIN,POZIOM,JEZYK,ID_SALI,ID_WYKLADOWCY) values ('1','Francuski dla zielonych','65','podstawowy','francuski','1','2');
Insert into KRZYSZTOF.KURS (ID_KURSU,NAZWA_KURSU,ILOSC_GODZIN,POZIOM,JEZYK,ID_SALI,ID_WYKLADOWCY) values ('2','Francuski dna rozmownych','80','średnio-zaawansowany','francuski','1','2');
Insert into KRZYSZTOF.KURS (ID_KURSU,NAZWA_KURSU,ILOSC_GODZIN,POZIOM,JEZYK,ID_SALI,ID_WYKLADOWCY) values ('3','chiński','20','zaawansowany','chiński','2',null);
Insert into KRZYSZTOF.KURS (ID_KURSU,NAZWA_KURSU,ILOSC_GODZIN,POZIOM,JEZYK,ID_SALI,ID_WYKLADOWCY) values ('4','albański','30','średnio-zaawansowany','albański','2',null);
Insert into KRZYSZTOF.KURS (ID_KURSU,NAZWA_KURSU,ILOSC_GODZIN,POZIOM,JEZYK,ID_SALI,ID_WYKLADOWCY) values ('5','niemiecki dla zaawansowanych','70','zaawansowany','niemiecki','1',null);
REM INSERTING into KRZYSZTOF.KURSANCI
SET DEFINE OFF;
Insert into KRZYSZTOF.KURSANCI (ID_KURSANTA,IMIE,NAZWISKO,PESEL,NR_TEL,EMAIL,LOGIN,HASLO,STATUS,PLEC) values ('2','Karol','Nowak','99033072183','505980767','karolnowak99@gmail.com','kknowak99','nowak99','uczeń','M');
Insert into KRZYSZTOF.KURSANCI (ID_KURSANTA,IMIE,NAZWISKO,PESEL,NR_TEL,EMAIL,LOGIN,HASLO,STATUS,PLEC) values ('1','Karolina','Sowa','97081208930','881223410','sowakarolina@gmail.com','sowkar','karolcia11','student','K');
Insert into KRZYSZTOF.KURSANCI (ID_KURSANTA,IMIE,NAZWISKO,PESEL,NR_TEL,EMAIL,LOGIN,HASLO,STATUS,PLEC) values ('3','Jan','Kowalski','96090992184','500921889','kowalski.jan@onet.pl','19kowalski96','jankowalski','student','M');
REM INSERTING into KRZYSZTOF.KURSKURSANCI
SET DEFINE OFF;
Insert into KRZYSZTOF.KURSKURSANCI (ID_KURSU,ID_KURSANTA) values ('5','3');
Insert into KRZYSZTOF.KURSKURSANCI (ID_KURSU,ID_KURSANTA) values ('3','1');
Insert into KRZYSZTOF.KURSKURSANCI (ID_KURSU,ID_KURSANTA) values ('1','2');
Insert into KRZYSZTOF.KURSKURSANCI (ID_KURSU,ID_KURSANTA) values ('1','1');
Insert into KRZYSZTOF.KURSKURSANCI (ID_KURSU,ID_KURSANTA) values ('4','1');
REM INSERTING into KRZYSZTOF.SALA
SET DEFINE OFF;
Insert into KRZYSZTOF.SALA (ID_SALI,NR_SALI,ILOSC_MIEJSC) values ('1','50','35');
Insert into KRZYSZTOF.SALA (ID_SALI,NR_SALI,ILOSC_MIEJSC) values ('2','51','40');
REM INSERTING into KRZYSZTOF.WYKLADOWCY
SET DEFINE OFF;
Insert into KRZYSZTOF.WYKLADOWCY (ID_WYKLADOWCY,IMIE,NAZWISKO,EMAIL,TELEFON,JEZYK) values ('1','Danuta','Hojarska','d.hojarska@onet.pl','880991342','niemiecki');
Insert into KRZYSZTOF.WYKLADOWCY (ID_WYKLADOWCY,IMIE,NAZWISKO,EMAIL,TELEFON,JEZYK) values ('2','Tomasz','Nowak','t_nowak@gmail.com','700920398','francuski');
REM INSERTING into KRZYSZTOF.ZALICZENIA
SET DEFINE OFF;
Insert into KRZYSZTOF.ZALICZENIA (ID_ZALICZENIA,DATA_ZAL,WYNIK,TYP,ID_KURSU,ID_KURSANTA) values ('21',to_date('17/03/29','RR/MM/DD'),'58','pisemny','5','3');
Insert into KRZYSZTOF.ZALICZENIA (ID_ZALICZENIA,DATA_ZAL,WYNIK,TYP,ID_KURSU,ID_KURSANTA) values ('22',to_date('17/03/30','RR/MM/DD'),'43','ustny','5','3');
Insert into KRZYSZTOF.ZALICZENIA (ID_ZALICZENIA,DATA_ZAL,WYNIK,TYP,ID_KURSU,ID_KURSANTA) values ('1',to_date('17/03/20','RR/MM/DD'),'72','pisemny','1','1');
Insert into KRZYSZTOF.ZALICZENIA (ID_ZALICZENIA,DATA_ZAL,WYNIK,TYP,ID_KURSU,ID_KURSANTA) values ('2',to_date('17/03/25','RR/MM/DD'),'80','ustny','1','1');
--------------------------------------------------------
--  DDL for Index SALA_UNIQUE
--------------------------------------------------------

  CREATE UNIQUE INDEX "KRZYSZTOF"."SALA_UNIQUE" ON "KRZYSZTOF"."SALA" ("NR_SALI") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index ZALICZENIA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "KRZYSZTOF"."ZALICZENIA_PK" ON "KRZYSZTOF"."ZALICZENIA" ("ID_ZALICZENIA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index KURS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "KRZYSZTOF"."KURS_PK" ON "KRZYSZTOF"."KURS" ("ID_KURSU") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SALA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "KRZYSZTOF"."SALA_PK" ON "KRZYSZTOF"."SALA" ("ID_SALI") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index WYKLADOWCY_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "KRZYSZTOF"."WYKLADOWCY_PK" ON "KRZYSZTOF"."WYKLADOWCY" ("ID_WYKLADOWCY") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index ADMIN_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "KRZYSZTOF"."ADMIN_PK" ON "KRZYSZTOF"."ADMIN" ("ID_ADMIN") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index KURSANCI_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "KRZYSZTOF"."KURSANCI_PK" ON "KRZYSZTOF"."KURSANCI" ("ID_KURSANTA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger AUTO_ID_ADMIN
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "KRZYSZTOF"."AUTO_ID_ADMIN" 
   before insert on "KRZYSZTOF"."ADMIN" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_ADMIN" is null then 
         select ADMIN_SEQ_ID.nextval into :NEW."ID_ADMIN" from dual; 
      end if; 
   end if; 
end;

/
ALTER TRIGGER "KRZYSZTOF"."AUTO_ID_ADMIN" ENABLE;
--------------------------------------------------------
--  DDL for Trigger AUTO_ID_KURSANTA
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "KRZYSZTOF"."AUTO_ID_KURSANTA" 
   before insert on "KRZYSZTOF"."KURSANCI" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_KURSANTA" is null then 
         select KURSANCI_SEQ_ID.nextval into :NEW."ID_KURSANTA" from dual; 
      end if; 
   end if; 
end;

/
ALTER TRIGGER "KRZYSZTOF"."AUTO_ID_KURSANTA" ENABLE;
--------------------------------------------------------
--  DDL for Trigger AUTO_ID_KURSU
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "KRZYSZTOF"."AUTO_ID_KURSU" 
   before insert on "KRZYSZTOF"."KURS" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_KURSU" is null then 
         select KURS_ID_SEQ.nextval into :NEW."ID_KURSU" from dual; 
      end if; 
   end if; 
end;

/
ALTER TRIGGER "KRZYSZTOF"."AUTO_ID_KURSU" ENABLE;
--------------------------------------------------------
--  DDL for Trigger AUTO_ID_SALI
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "KRZYSZTOF"."AUTO_ID_SALI" 
   before insert on "KRZYSZTOF"."SALA" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_SALI" is null then 
         select SALA_SEQ_ID.nextval into :NEW."ID_SALI" from dual; 
      end if; 
   end if; 
end;

/
ALTER TRIGGER "KRZYSZTOF"."AUTO_ID_SALI" ENABLE;
--------------------------------------------------------
--  DDL for Trigger AUTO_ID_WYKLADOWCY
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "KRZYSZTOF"."AUTO_ID_WYKLADOWCY" 
   before insert on "KRZYSZTOF"."WYKLADOWCY" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_WYKLADOWCY" is null then 
         select WYKLADOWCY_SEQ_ID.nextval into :NEW."ID_WYKLADOWCY" from dual; 
      end if; 
   end if; 
end;

/
ALTER TRIGGER "KRZYSZTOF"."AUTO_ID_WYKLADOWCY" ENABLE;
--------------------------------------------------------
--  DDL for Trigger AUTO_ID_ZALICZENIA
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "KRZYSZTOF"."AUTO_ID_ZALICZENIA" 
   before insert on "KRZYSZTOF"."ZALICZENIA" 
   for each row 
begin  
   if inserting then 
      if :NEW."ID_ZALICZENIA" is null then 
         select ZALICZENIA_SEQ_ID.nextval into :NEW."ID_ZALICZENIA" from dual; 
      end if; 
   end if; 
end;

/
ALTER TRIGGER "KRZYSZTOF"."AUTO_ID_ZALICZENIA" ENABLE;
--------------------------------------------------------
--  DDL for Function CZESTY_JEZYK
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "KRZYSZTOF"."CZESTY_JEZYK" return VARCHAR is
temp VARCHAR(15);
BEGIN
SELECT jezyk INTO temp FROM (SELECT kurs.jezyk, count(kurskursanci.id_kursanta) AS ilosc_kursantow FROM kurs,kurskursanci WHERE kurskursanci.id_kursu=kurs.id_kursu GROUP BY jezyk ORDER BY ilosc_kursantow DESC) WHERE rownum=1;
RETURN temp;
end;

/
--------------------------------------------------------
--  DDL for Function CZY_ZDANE
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "KRZYSZTOF"."CZY_ZDANE" (idkursanta IN NUMBER, idkursu IN NUMBER) return VARCHAR is
zdane VARCHAR(15);
procenty NUMBER;
BEGIN
procenty:=moj_wynik(idkursanta,idkursu);
IF ( procenty >= 65 ) THEN
    zdane:='zaliczony';
ELSE
    zdane:='niezaliczony';
END IF;    
RETURN zdane;
end;

/
--------------------------------------------------------
--  DDL for Function MOJ_WYNIK
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "KRZYSZTOF"."MOJ_WYNIK" (idkursanta IN NUMBER, idkursu IN NUMBER) return NUMBER is
wynik NUMBER;
BEGIN
SELECT AVG(wynik) INTO wynik FROM zaliczenia WHERE id_kursu=idkursu AND id_kursanta=idkursanta;
RETURN wynik;
end;

/
--------------------------------------------------------
--  DDL for Function OBLICZ_CENE
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "KRZYSZTOF"."OBLICZ_CENE" (idkursu IN NUMBER) return NUMBER is
poziomJezyka VARCHAR(25);
godziny Number;
cena Number;
BEGIN
SELECT ilosc_godzin INTO godziny FROM KURS WHERE Kurs.id_kursu=idkursu;
SELECT poziom INTO poziomJezyka FROM KURS WHERE Kurs.id_kursu=idkursu;
CASE poziomJezyka
   WHEN 'podstawowy' THEN cena:=10*godziny;
   WHEN 'średnio-zaawansowany' THEN cena:=15*godziny;
   WHEN 'zaawansowany' THEN cena:=20*godziny;
END CASE;
RETURN cena;
end;

/
--------------------------------------------------------
--  DDL for Function OBLICZ_CENE_ZNIZKI
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "KRZYSZTOF"."OBLICZ_CENE_ZNIZKI" (idkursanta IN NUMBER, idkursu IN NUMBER) return number is
statusKursanta varchar(20);
znizka number;
cena number;
iloscKursow number;
BEGIN
znizka:=1;
SELECT status INTO statusKursanta FROM Kursanci WHERE Kursanci.id_kursanta=idkursanta;
CASE statusKursanta
   WHEN 'uczeń' THEN znizka:=0.9;
   WHEN 'student' THEN znizka:=0.85;
   WHEN 'emeryt/rencista' THEN znizka:=0.75;
   else cena:=oblicz_cene(idkursu);   
END CASE;
select count(id_kursu) into iloscKursow from kurskursanci where id_kursanta=idkursanta;
if (iloscKursow >= 3) THEN
    znizka:=znizka-0.1;
elsif (iloscKursow >= 1) THEN
    znizka:=znizka-0.05;
END if;
cena:=oblicz_cene(idkursu)*znizka;
RETURN cena;
end;

/
--------------------------------------------------------
--  DDL for Function POLICZ_WOLNE_MIEJSCA
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "KRZYSZTOF"."POLICZ_WOLNE_MIEJSCA" (idkursu IN NUMBER) return NUMBER is
miejsca NUMBER;
zajete NUMBER;
BEGIN
SELECT COUNT(id_kursanta) INTO zajete FROM KURSKURSANCI WHERE id_kursu=idkursu;
SELECT ilosc_miejsc INTO miejsca FROM SALA,KURS WHERE KURS.id_kursu=idkursu AND KURS.id_sali=SALA.id_sali;
RETURN miejsca-zajete;
end;

/
--------------------------------------------------------
--  DDL for Function SREDNI_WYNIK_EFEKTU
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "KRZYSZTOF"."SREDNI_WYNIK_EFEKTU" (efekt IN VARCHAR) return NUMBER is
temp NUMBER;
BEGIN
SELECT AVG(wynik) INTO temp FROM zaliczenia WHERE typ=efekt;
RETURN temp;
end;

/
--------------------------------------------------------
--  Constraints for Table KURS
--------------------------------------------------------

  ALTER TABLE "KRZYSZTOF"."KURS" ADD CONSTRAINT "KURS_PK" PRIMARY KEY ("ID_KURSU")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "KRZYSZTOF"."KURS" MODIFY ("ID_SALI" NOT NULL ENABLE);
  ALTER TABLE "KRZYSZTOF"."KURS" MODIFY ("ID_KURSU" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ZALICZENIA
--------------------------------------------------------

  ALTER TABLE "KRZYSZTOF"."ZALICZENIA" ADD CONSTRAINT "ZALICZENIA_PK" PRIMARY KEY ("ID_ZALICZENIA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "KRZYSZTOF"."ZALICZENIA" MODIFY ("ID_ZALICZENIA" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table WYKLADOWCY
--------------------------------------------------------

  ALTER TABLE "KRZYSZTOF"."WYKLADOWCY" ADD CONSTRAINT "WYKLADOWCY_PK" PRIMARY KEY ("ID_WYKLADOWCY")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "KRZYSZTOF"."WYKLADOWCY" MODIFY ("ID_WYKLADOWCY" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ADMIN
--------------------------------------------------------

  ALTER TABLE "KRZYSZTOF"."ADMIN" ADD CONSTRAINT "ADMIN_PK" PRIMARY KEY ("ID_ADMIN")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "KRZYSZTOF"."ADMIN" MODIFY ("ID_ADMIN" NOT NULL ENABLE);
  ALTER TABLE "KRZYSZTOF"."ADMIN" MODIFY ("HASLO" NOT NULL ENABLE);
  ALTER TABLE "KRZYSZTOF"."ADMIN" MODIFY ("LOGIN" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table KURSANCI
--------------------------------------------------------

  ALTER TABLE "KRZYSZTOF"."KURSANCI" ADD CONSTRAINT "KURSANCI_PK" PRIMARY KEY ("ID_KURSANTA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "KRZYSZTOF"."KURSANCI" MODIFY ("ID_KURSANTA" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SALA
--------------------------------------------------------

  ALTER TABLE "KRZYSZTOF"."SALA" ADD CONSTRAINT "SALA_PK" PRIMARY KEY ("ID_SALI")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "KRZYSZTOF"."SALA" ADD CONSTRAINT "SALA_UNIQUE" UNIQUE ("NR_SALI")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "KRZYSZTOF"."SALA" MODIFY ("ID_SALI" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table KURS
--------------------------------------------------------

  ALTER TABLE "KRZYSZTOF"."KURS" ADD CONSTRAINT "FK_KURS_SALA" FOREIGN KEY ("ID_SALI")
	  REFERENCES "KRZYSZTOF"."SALA" ("ID_SALI") ENABLE;
  ALTER TABLE "KRZYSZTOF"."KURS" ADD CONSTRAINT "FK_KURS_WYKLADOWCY" FOREIGN KEY ("ID_WYKLADOWCY")
	  REFERENCES "KRZYSZTOF"."WYKLADOWCY" ("ID_WYKLADOWCY") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table KURSKURSANCI
--------------------------------------------------------

  ALTER TABLE "KRZYSZTOF"."KURSKURSANCI" ADD CONSTRAINT "FK_KURSANCIKURS" FOREIGN KEY ("ID_KURSANTA")
	  REFERENCES "KRZYSZTOF"."KURSANCI" ("ID_KURSANTA") ENABLE;
  ALTER TABLE "KRZYSZTOF"."KURSKURSANCI" ADD CONSTRAINT "FK_KURSKURSANCI" FOREIGN KEY ("ID_KURSU")
	  REFERENCES "KRZYSZTOF"."KURS" ("ID_KURSU") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ZALICZENIA
--------------------------------------------------------

  ALTER TABLE "KRZYSZTOF"."ZALICZENIA" ADD CONSTRAINT "FK_ZALICZENIA_KURS" FOREIGN KEY ("ID_KURSU")
	  REFERENCES "KRZYSZTOF"."KURS" ("ID_KURSU") ENABLE;
  ALTER TABLE "KRZYSZTOF"."ZALICZENIA" ADD CONSTRAINT "FK_ZALICZENIA_KURSANCI" FOREIGN KEY ("ID_KURSANTA")
	  REFERENCES "KRZYSZTOF"."KURSANCI" ("ID_KURSANTA") ENABLE;
