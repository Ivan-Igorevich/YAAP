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
    NAME_ID varchar(36),
    DATE_ date not null,
    DIRECTION boolean not null,
    CATEGORY_ID varchar(36),
    SOURCE_ID varchar(36),
    TOTAL double precision not null,
    VOLUME double precision,
    AMOUNT double precision not null,
    DESCRIPTION longvarchar,
    PERIOD_ID varchar(36),
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
-- begin YAAP_ACCOUNT
create table YAAP_ACCOUNT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    OWNER_ID varchar(36) not null,
    NAME varchar(255),
    CURRENT_PERIOD_ID varchar(36),
    CURRENCY varchar(255),
    --
    primary key (ID)
)^
-- end YAAP_ACCOUNT

-- begin YAAP_PERIOD
create table YAAP_PERIOD (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    BALANCE_START double precision not null,
    BALANCE_END double precision not null,
    DATE_START timestamp not null,
    DATE_END timestamp,
    ACCOUNT_ID varchar(36),
    --
    primary key (ID)
)^
-- end YAAP_PERIOD
-- begin YAAP_COMPANY
create table YAAP_COMPANY (
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
    --
    primary key (ID)
)^
-- end YAAP_COMPANY
-- begin YAAP_PRODUCT
create table YAAP_PRODUCT (
    ID varchar(36) not null,
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
