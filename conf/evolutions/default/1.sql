# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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
  text                      varchar(255),
  creator_username          varchar(255),
  constraint pk_requirement primary key (id))
;

create table user (
  username                  varchar(255) not null,
  password                  varchar(255),
  constraint pk_user primary key (username))
;


create table requirement_user (
  requirement_id                 bigint not null,
  user_username                  varchar(255) not null,
  constraint pk_requirement_user primary key (requirement_id, user_username))
;
create sequence note_seq;

create sequence requirement_seq;

create sequence user_seq;

alter table note add constraint fk_note_assignedTo_1 foreign key (assigned_to_username) references user (username) on delete restrict on update restrict;
create index ix_note_assignedTo_1 on note (assigned_to_username);
alter table note add constraint fk_note_requirement_2 foreign key (requirement_id) references requirement (id) on delete restrict on update restrict;
create index ix_note_requirement_2 on note (requirement_id);
alter table requirement add constraint fk_requirement_creator_3 foreign key (creator_username) references user (username) on delete restrict on update restrict;
create index ix_requirement_creator_3 on requirement (creator_username);



alter table requirement_user add constraint fk_requirement_user_requireme_01 foreign key (requirement_id) references requirement (id) on delete restrict on update restrict;

alter table requirement_user add constraint fk_requirement_user_user_02 foreign key (user_username) references user (username) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists note;

drop table if exists requirement;

drop table if exists requirement_user;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists note_seq;

drop sequence if exists requirement_seq;

drop sequence if exists user_seq;

