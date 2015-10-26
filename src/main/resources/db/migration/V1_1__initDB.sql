CREATE SEQUENCE id;

CREATE TABLE roles
(
  role_id integer NOT NULL DEFAULT nextval('id'::regclass),
  name character varying(60) NOT NULL,
  CONSTRAINT roles_pkey PRIMARY KEY (role_id)
);

CREATE TABLE users
(
  user_id integer NOT NULL DEFAULT nextval('id'::regclass),
  name character varying(100),
  email character varying(60) NOT NULL,
  password character varying NOT NULL,
  user_role integer,
  CONSTRAINT users_pkey PRIMARY KEY (user_id),
  CONSTRAINT roles_fkey FOREIGN KEY (user_role)
  REFERENCES roles (role_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT users_email_key UNIQUE (email)
);

CREATE TABLE accounts
(
  account_id integer NOT NULL DEFAULT nextval('id'::regclass),
  user_id integer NOT NULL,
  name character varying(60),
  CONSTRAINT accounts_pkey PRIMARY KEY (account_id),
  CONSTRAINT users_fkey FOREIGN KEY (user_id)
      REFERENCES users (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE expense_categories
(
  category_id integer NOT NULL DEFAULT nextval('id'::regclass),
  parent_id integer,
  user_id integer NOT NULL,
  name character varying(60) NOT NULL,
  CONSTRAINT expense_categories_pkey PRIMARY KEY (category_id),
  CONSTRAINT parents_fkey FOREIGN KEY (parent_id)
  REFERENCES expense_categories (category_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT users_fkey FOREIGN KEY (user_id)
  REFERENCES users (user_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE expenses
(
  expense_id integer NOT NULL DEFAULT nextval('id'::regclass),
  account_id integer NOT NULL,
  category_id integer NOT NULL,
  datetime timestamp without time zone NOT NULL,
  name character varying(100),
  value numeric,
  CONSTRAINT expenses_pkey PRIMARY KEY (expense_id),
  CONSTRAINT accounts_fkey FOREIGN KEY (account_id)
      REFERENCES accounts (account_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT categories_fkey FOREIGN KEY (category_id)
      REFERENCES expense_categories (category_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE expense_tags
(
  tag_id integer NOT NULL DEFAULT nextval('id'::regclass),
  name character varying(60) NOT NULL,
  CONSTRAINT expense_tags_pkey PRIMARY KEY (tag_id)
);

CREATE TABLE income_categories
(
  category_id integer NOT NULL DEFAULT nextval('id'::regclass),
  parent_id integer,
  user_id integer NOT NULL,
  name character varying(60) NOT NULL,
  CONSTRAINT income_categories_pkey PRIMARY KEY (category_id),
  CONSTRAINT parents_fkey FOREIGN KEY (parent_id)
  REFERENCES income_categories (category_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT users_fkey FOREIGN KEY (user_id)
  REFERENCES users (user_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE incomes
(
  income_id integer NOT NULL DEFAULT nextval('id'::regclass),
  account_id integer NOT NULL,
  category_id integer NOT NULL,
  datetime timestamp without time zone NOT NULL,
  name character varying(100) NOT NULL,
  value numeric,
  CONSTRAINT incomes_pkey PRIMARY KEY (income_id),
  CONSTRAINT accounts_fkey FOREIGN KEY (account_id)
  REFERENCES accounts (account_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT categories_fkey FOREIGN KEY (category_id)
  REFERENCES income_categories (category_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE income_tags
(
  tag_id integer NOT NULL DEFAULT nextval('id'::regclass),
  name character varying(60) NOT NULL,
  CONSTRAINT income_tags_pkey PRIMARY KEY (tag_id)
);

CREATE TABLE tags_in_expenses
(
  tag_id integer NOT NULL,
  expense_id integer NOT NULL,
  CONSTRAINT tags_in_expenses_pkey PRIMARY KEY (tag_id, expense_id),
  CONSTRAINT expenses_fkey FOREIGN KEY (expense_id)
  REFERENCES expenses (expense_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT tags_fkey FOREIGN KEY (tag_id)
  REFERENCES expense_tags (tag_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE tags_in_incomes
(
  tag_id integer NOT NULL,
  income_id integer NOT NULL,
  CONSTRAINT tags_in_incomes_pkey PRIMARY KEY (tag_id, income_id),
  CONSTRAINT incomes_fkey FOREIGN KEY (income_id)
  REFERENCES incomes (income_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT tags_fkey FOREIGN KEY (tag_id)
  REFERENCES income_tags (tag_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE OR REPLACE VIEW account_balances AS
  SELECT accounts.account_id,
    accounts.user_id,
    accounts.name,
    COALESCE(ops.value, 0::numeric) AS balance
  FROM accounts
    LEFT JOIN ( SELECT operations.accountid,
                  sum(operations.value) AS value
                FROM ( SELECT incomes.account_id AS accountid,
                         incomes.value
                       FROM incomes
                       WHERE incomes.datetime <= now()
                       UNION ALL
                       SELECT expenses.account_id,
                         - expenses.value AS value
                       FROM expenses
                       WHERE expenses.datetime <= now()) operations
                GROUP BY operations.accountid) ops ON ops.accountid = accounts.account_id;

insert into roles(name) values('Administrator');
insert into roles(name) values('User');