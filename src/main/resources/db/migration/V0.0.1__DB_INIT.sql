CREATE TABLE td_series
(
  series_seq bigserial NOT NULL,
  name text NOT NULL,
  CONSTRAINT pk_series PRIMARY KEY (series_seq)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE td_series OWNER TO ${owner};


CREATE TABLE td_book
(
  book_seq bigserial NOT NULL,
  title text NOT NULL,
  CONSTRAINT pk_book PRIMARY KEY (book_seq)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE td_book OWNER TO ${owner};

CREATE TABLE td_page
(
  page_seq bigserial NOT NULL,
  name text NOT NULL,
  CONSTRAINT page_book PRIMARY KEY (page_seq)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE td_page OWNER TO ${owner};


CREATE TABLE tr_series_book
(
  series_seq bigint NOT NULL,
  book_seq bigint NOT NULL,
  no integer NOT NULL,
  CONSTRAINT pk_series_book PRIMARY KEY (series_seq, book_seq)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tr_series_book OWNER TO ${owner};

CREATE TABLE tr_book_page
(
  book_seq bigint NOT NULL,
  page_seq bigint NOT NULL,
  no integer NOT NULL,
  CONSTRAINT pk_book_page PRIMARY KEY (book_seq, page_seq)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tr_book_page OWNER TO ${owner};
