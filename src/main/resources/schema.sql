CREATE TABLE accounts
(
    id                     UUID PRIMARY KEY,
    update                 TIMESTAMP       NOT NULL,
    last_updated_timestamp TIMESTAMP       NOT NULL,
    name                   VARCHAR(50)     NOT NULL,
    product                VARCHAR(250)    NOT NULL,
    status                 VARCHAR(250)    NOT NULL,
    type                   VARCHAR(50)     NOT NULL,
    balance                DECIMAL(30, 12) NOT NULL
);

CREATE TABLE transactions
(
    id                     UUID PRIMARY KEY,
    account_id             UUID            NOT NULL,
    update                 TIMESTAMP       NOT NULL,
    last_updated_timestamp TIMESTAMP       NOT NULL,
    exchange_rate          JSON            NOT NULL,
    original_amount        JSON            NOT NULL,
    creditor               JSON            NOT NULL,
    debtor                 JSON            NOT NULL,
    transaction_status     VARCHAR(50)     NOT NULL,
    currency_code          VARCHAR(50)     NOT NULL,
    amount                 DECIMAL(31, 12) NOT NULL,
    description            VARCHAR(250)    NOT NULL
);

CREATE TABLE users
(
    user_id                BIGINT      PRIMARY KEY ,
    user_name              VARCHAR(50)     NOT NULL,
    phone_number           VARCHAR(50)     NOT NULL,
    email                  VARCHAR(50)     NOT NULL,
    created_at             TIMESTAMP       NOT NULL
);

CREATE TABLE alerts
(
    user_id                BIGINT          NOT NULL,
    account_id             UUID            NOT NULL,
    created_at             TIMESTAMP       NOT NULL,
    updated_at             TIMESTAMP       NOT NULL,
    alert_type             VARCHAR(50)     NOT NULL,
    threshold              INT             NOT NULL,
    primary key (user_id, account_id),
    foreign key (user_id) references users(user_id)
);