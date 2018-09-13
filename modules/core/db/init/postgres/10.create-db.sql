-- begin YAAP_TRANSACTION
create table YAAP_TRANSACTION (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME_ID uuid,
    DATE_ date not null,
    DIRECTION boolean not null,
    CATEGORY_ID uuid,
    SOURCE_ID uuid,
    TOTAL double precision not null,
    VOLUME double precision,
    AMOUNT double precision not null,
    DESCRIPTION text,
    PERIOD_ID uuid,
    --
    primary key (ID)
)^
-- end YAAP_TRANSACTION
-- begin YAAP_CATEGORY
create table YAAP_CATEGORY (
    ID uuid,
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
    PARENT_ID uuid,
    --
    primary key (ID)
)^
-- end YAAP_CATEGORY
-- begin YAAP_ACCOUNT
create table YAAP_ACCOUNT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    OWNER_ID uuid not null,
    IS_DEFAULT boolean not null,
    NAME varchar(255),
    CURRENT_PERIOD_ID uuid,
    CURRENCY varchar(255),
    --
    primary key (ID)
)^
-- end YAAP_ACCOUNT
-- begin YAAP_PERIOD
create table YAAP_PERIOD (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    BALANCE_START double precision not null,
    BALANCE_END double precision,
    DATE_START timestamp not null,
    DATE_END timestamp,
    ACCOUNT_ID uuid,
    --
    primary key (ID)
)^
-- end YAAP_PERIOD
-- begin YAAP_COMPANY
create table YAAP_COMPANY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    --
    primary key (ID)
)^
-- end YAAP_COMPANY
-- begin YAAP_PRODUCT
create table YAAP_PRODUCT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end YAAP_PRODUCT
