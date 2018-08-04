-- begin YAAP_TRANSACTION
create table YAAP_TRANSACTION (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DATE_ date not null,
    DIRECTION boolean not null,
    CATEGORY_ID varchar(36),
    SOURCE varchar(255),
    TOTAL double precision not null,
    AMOUNT double precision not null,
    DESCRIPTION longvarchar,
    --
    primary key (ID)
)^
-- end YAAP_TRANSACTION
-- begin YAAP_CATEGORY
create table YAAP_CATEGORY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(100),
    --
    NAME varchar(255) not null,
    PARENT_ID varchar(36),
    --
    primary key (ID)
)^
-- end YAAP_CATEGORY
