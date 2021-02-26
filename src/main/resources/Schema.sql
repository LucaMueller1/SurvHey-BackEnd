drop schema if exists SurvHey_DB;
create schema SurvHey_DB;



create table SurvHey_DB.User
			(
            User_ID integer auto_increment primary key,
            E_Mail varchar(45) not null,
            Password text not null
            );

create table SurvHey_DB.Survey (
	Survey_ID bigint auto_increment primary key,
    User_ID integer not null,
    Survey_Name text,
    foreign key (User_ID) references SurvHey_DB.User(User_ID) on update cascade on delete cascade
);

create table SurvHey_DB.Survey_Question (
	Question_ID bigint auto_increment primary key,
	Survey_ID bigint not null,
	Question_Text text,
	Answer_Mode varchar(4),
	foreign key (Survey_ID) references Survey(Survey_ID) on update cascade on delete cascade
);

create table SurvHey_DB.Answer_Option (
	Answer_Option_ID bigint auto_increment primary key,
	Question_ID bigint not null,
	Answer_Option text,
	foreign key (Question_ID) references SurvHey_DB.Survey_Question(Question_ID) on update cascade on delete cascade
);

create table SurvHey_DB.Survey_Participant (
	Participant_ID bigint auto_increment primary key,
    IP_Adress text
);

create table SurvHey_DB.Submission (
	Submission_ID bigint auto_increment primary key,
    Survey_ID bigint not null,
    Participant_ID bigint not null,
    Submission_Timestamp timestamp,
    foreign key (Survey_ID) references Survey(Survey_ID) on update cascade on delete cascade,
    foreign key (Participant_ID) references SurvHey_DB.Survey_Participant(Participant_ID) on update cascade on delete cascade
);

create table SurvHey_DB.Survey_Answer (
	Participation_ID bigint auto_increment primary key,
	Submission_ID bigint not null,
	Question_ID bigint not null,
	foreign key (Question_ID) references Survey_Question(Question_ID) on update cascade on delete cascade,
    foreign key (Submission_ID) references Submission(Submission_ID) on update cascade on delete cascade
);

create table SurvHey_DB.Question_Answer(
Participation_ID bigint primary key,
Answer text,
foreign key (Participation_ID) references SurvHey_DB.Survey_Answer(Participation_ID) on update cascade on delete cascade

);
insert into survhey_db.User(E_Mail,Password) values('Gur@ke.com','1234');
insert into survhey_db.User(E_Mail,Password) values('Ka@rotte.de','hallo');
insert into survhey_db.User(E_Mail,Password) values('Ban@ane.net','bye');
insert into survhey_db.User(E_Mail,Password) values('Ap@fel.info','start');