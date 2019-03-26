alter table INSTRUMENT add PID VARCHAR2(255);
alter table PARAMETERTYPE add PID VARCHAR2(255);
alter table SAMPLE add PID VARCHAR2(255);
alter table SHIFT add (
	INSTRUMENT_ID NUMBER(19),
	constraint FK_SHIFT_INSTRUMENT_ID
	foreign key (INSTRUMENT_ID) references INSTRUMENT (ID)
);
alter table STUDY add PID VARCHAR2(255);
alter table STUDY add ENDDATE TIMESTAMP;
alter table USER_ add (
	GIVENNAME VARCHAR2(255),
	FAMILYNAME VARCHAR2(255),
	AFFILIATION VARCHAR2(255)
);
exit
