create table USER
(
    ID           INT auto_increment,
    ACCOUNT_ID   VARCHAR,
    NAME         VARCHAR(100),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint TABLE_NAME_PK
        primary key (ID)
);

