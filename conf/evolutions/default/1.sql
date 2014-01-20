# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table idea (
  id                        bigint not null,
  text                      varchar(255),
  creator_username          varchar(255) not null,
  status                    varchar(11),
  constraint ck_idea_status check (status in ('CREATED','UP_FOR_VOTE','DECLINED','ACCEPTED','PULLED')),
  constraint pk_idea primary key (id))
;

create table note (
  id                        bigint not null,
  text                      varchar(255),
  create_date               timestamp,
  assigned_to_username      varchar(255),
  requirement_id            bigint,
  constraint pk_note primary key (id))
;

create table requirement (
  id                        bigint not null,
  title                     varchar(255),
  text                      varchar(255),
  creator_username          varchar(255) not null,
  constraint pk_requirement primary key (id))
;

create table user (
  username                  varchar(255) not null,
  password                  varchar(255),
  constraint pk_user primary key (username))
;


create table idea_user (
  idea_id                        bigint not null,
  user_username                  varchar(255) not null,
  constraint pk_idea_user primary key (idea_id, user_username))
;

create table requirement_user (
  requirement_id                 bigint not null,
  user_username                  varchar(255) not null,
  constraint pk_requirement_user primary key (requirement_id, user_username))
;
create sequence idea_seq;

create sequence note_seq;

create sequence requirement_seq;

create sequence user_seq;

alter table idea add constraint fk_idea_creator_1 foreign key (creator_username) references user (username) on delete restrict on update restrict;
create index ix_idea_creator_1 on idea (creator_username);
alter table note add constraint fk_note_assignedTo_2 foreign key (assigned_to_username) references user (username) on delete restrict on update restrict;
create index ix_note_assignedTo_2 on note (assigned_to_username);
alter table note add constraint fk_note_requirement_3 foreign key (requirement_id) references requirement (id) on delete restrict on update restrict;
create index ix_note_requirement_3 on note (requirement_id);
alter table requirement add constraint fk_requirement_creator_4 foreign key (creator_username) references user (username) on delete restrict on update restrict;
create index ix_requirement_creator_4 on requirement (creator_username);



alter table idea_user add constraint fk_idea_user_idea_01 foreign key (idea_id) references idea (id) on delete restrict on update restrict;

alter table idea_user add constraint fk_idea_user_user_02 foreign key (user_username) references user (username) on delete restrict on update restrict;

alter table requirement_user add constraint fk_requirement_user_requireme_01 foreign key (requirement_id) references requirement (id) on delete restrict on update restrict;

alter table requirement_user add constraint fk_requirement_user_user_02 foreign key (user_username) references user (username) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists idea;

drop table if exists idea_user;

drop table if exists note;

drop table if exists requirement;

drop table if exists requirement_user;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists idea_seq;

drop sequence if exists note_seq;

drop sequence if exists requirement_seq;

drop sequence if exists user_seq;

